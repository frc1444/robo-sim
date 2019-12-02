package com.first1444.sim.gdx.desktop_test;

import com.first1444.dashboard.bundle.DashboardBundle;
import com.first1444.dashboard.shuffleboard.SendableComponent;
import com.first1444.sim.api.Clock;
import com.first1444.sim.api.ClockSendable;
import com.first1444.sim.api.Transform2;
import com.first1444.sim.api.Vector2;
import com.first1444.sim.api.distance.DeltaDistanceAccumulator;
import com.first1444.sim.api.distance.MutableDistanceAccumulator;
import com.first1444.sim.api.distance.OrientationDeltaDistanceCalculator;
import com.first1444.sim.api.distance.SwerveDeltaDistanceCalculator;
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDrive;
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDriveData;
import com.first1444.sim.api.drivetrain.swerve.SwerveDrive;
import com.first1444.sim.api.frc.BasicRobot;
import com.first1444.sim.api.frc.FrcDriverStation;
import com.first1444.sim.api.frc.FrcMode;
import com.first1444.sim.api.frc.implementations.deepspace.Field2019;
import com.first1444.sim.api.frc.implementations.deepspace.VisionTarget;
import com.first1444.sim.api.scheduler.match.DefaultMatchScheduler;
import com.first1444.sim.api.scheduler.match.MatchSchedulerRunnable;
import com.first1444.sim.api.scheduler.match.MatchTime;
import com.first1444.sim.api.sensors.DefaultMutableOrientation;
import com.first1444.sim.api.sensors.MutableOrientation;
import com.first1444.sim.api.sensors.Orientation;
import com.first1444.sim.api.sound.Sound;
import com.first1444.sim.api.sound.SoundCreator;
import com.first1444.sim.api.surroundings.Surrounding;
import com.first1444.sim.api.surroundings.SurroundingProvider;
import me.retrodaredevil.controller.ControlConfig;
import me.retrodaredevil.controller.MutableControlConfig;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.types.StandardControllerInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Robot implements BasicRobot {
    private final FrcDriverStation driverStation;
    private final Clock clock;
    private final DashboardBundle bundle;
    private final SwerveDrive swerveDrive;
    private final MutableOrientation orientation;
    private final StandardControllerInput controller;
    private final SurroundingProvider surroundingProvider;

    private final MatchSchedulerRunnable scheduler;
    private final ControlConfig controlConfig;
    private final MutableDistanceAccumulator distanceAccumulator;

    private final Sound soundMatchStart;
    private final Sound soundTeleopStart;
    private final Sound soundTeleopFiveSecondsLeft;

    public Robot(
            FrcDriverStation driverStation,
            Clock clock,
            DashboardBundle bundle,
            FourWheelSwerveDriveData swerveDriveData,
            Orientation orientation,
            StandardControllerInput controller,
            SurroundingProvider surroundingProvider,
            SoundCreator soundCreator) {
        this.driverStation = driverStation;
        this.clock = clock;
        this.bundle = bundle;
        this.swerveDrive = new FourWheelSwerveDrive(swerveDriveData);
        this.orientation = new DefaultMutableOrientation(orientation);
        this.controller = controller;
        this.surroundingProvider = surroundingProvider;

        this.scheduler = new DefaultMatchScheduler(driverStation, clock);
        MutableControlConfig config = new MutableControlConfig();
        config.fullAnalogDeadzone = .03;
        this.controlConfig = config;
        distanceAccumulator = new DeltaDistanceAccumulator(new OrientationDeltaDistanceCalculator(new SwerveDeltaDistanceCalculator(swerveDriveData), orientation));
        distanceAccumulator.setPosition(new Vector2(0, -6.6));

        bundle.getShuffleboard().get("dash").add("time", new SendableComponent<>(new ClockSendable(clock)));

        soundMatchStart = soundCreator.create("match_start");
        soundTeleopStart = soundCreator.create("teleop_start.wav");
        soundTeleopFiveSecondsLeft = soundCreator.create("warning.wav");
    }

    @Override
    public void close() throws Exception {
        System.out.println("It closed!");
    }

    @Override
    public void update(@NotNull FrcMode mode, @Nullable FrcMode previousMode) {
        if(previousMode != mode){
            System.out.println("New mode: " + mode);
            if(mode == FrcMode.AUTONOMOUS){
                soundMatchStart.play();
                scheduler.schedule(new MatchTime(0.0, MatchTime.Mode.TELEOP, MatchTime.Type.AFTER_START), soundTeleopStart::play);
                scheduler.schedule(new MatchTime(5.0, MatchTime.Mode.TELEOP, MatchTime.Type.FROM_END), soundTeleopFiveSecondsLeft::play);

                scheduler.schedule(new MatchTime(1.0, MatchTime.Mode.AUTONOMOUS, MatchTime.Type.AFTER_START), () -> {
                    System.out.println("1 second after auto beginning!");
                });
                scheduler.schedule(new MatchTime(1.0, MatchTime.Mode.AUTONOMOUS, MatchTime.Type.FROM_END), () -> {
                    System.out.println("1 second from auto being over!");
                });
            }
        }
        controller.update(controlConfig);
        distanceAccumulator.run();
        scheduler.run();

        updateSwerve();
        swerveDrive.run();
        Vector2 position = distanceAccumulator.getPosition();
        for(Surrounding surrounding : surroundingProvider.getSurroundings()){
            Transform2 transform = surrounding.getTransform();
            Transform2 visionTransform = transform.rotateRadians(orientation.getOrientationRadians()).plus(position);
            VisionTarget best = null;
            double closest2 = Double.MAX_VALUE;
            for(VisionTarget target : Field2019.ALL_VISION_TARGETS){
                Transform2 targetTransform = target.getTransform();
                double distance2 = targetTransform.getPosition().distance2(visionTransform.getPosition());
                if(distance2 < closest2){
                    best = target;
                    closest2 = distance2;
                }
            }
            System.out.println("We see: " + best + " distance error: " + Math.sqrt(closest2) + " yaw error: " + Math.abs(visionTransform.getRotationDegrees() - best.getTransform().getRotationDegrees()));
            // If you're doing this on a robot, you might also want to check the raw error.
            distanceAccumulator.setPosition(best.getTransform().getPosition().minus(transform.getPosition().rotateRadians(orientation.getOrientationRadians())));
        }
//        System.out.println("Position: " + distanceAccumulator.getPosition());
//        System.out.println("Surroundings: " + surroundingProvider.getSurroundings());
    }
    private void updateSwerve(){
        JoystickPart leftJoy = controller.getLeftJoy();
        final double x, y;
        if(leftJoy.isDeadzone() || leftJoy.getCorrectMagnitude() < .15){
            x = 0;
            y = 0;
        } else {
            x = leftJoy.getCorrectX();
            y = leftJoy.getCorrectY();
        }
        Vector2 translation;
        if(controller.getLeftBumper().isDown()){ // first person
            translation = new Vector2(y, -x);
        } else {
            translation = new Vector2(x, y).rotateRadians(-orientation.getOrientationRadians());
        }

        if(translation.getMagnitude() > 1){
            translation = translation.div(translation.getMagnitude());
        }
        final double rotate;
        if(controller.getRightJoy().isXDeadzone()){
            rotate = 0;
        } else {
            rotate = controller.getRightJoy().getX();
        }
        double speed = controller.getRightTrigger().getPosition() - controller.getLeftTrigger().getPosition();

        swerveDrive.setControl(translation, rotate, speed);

    }
}
