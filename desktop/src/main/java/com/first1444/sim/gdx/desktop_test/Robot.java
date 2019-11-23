package com.first1444.sim.gdx.desktop_test;

import com.first1444.sim.api.Clock;
import com.first1444.sim.api.Transform;
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
import com.first1444.sim.api.surroundings.Surrounding;
import com.first1444.sim.api.surroundings.SurroundingProvider;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import me.retrodaredevil.controller.ControlConfig;
import me.retrodaredevil.controller.MutableControlConfig;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.types.StandardControllerInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Robot implements BasicRobot {
    private final FrcDriverStation driverStation;
    private final Clock clock;
    private final SwerveDrive swerveDrive;
    private final MutableOrientation orientation;
    private final StandardControllerInput controller;
    private final SurroundingProvider surroundingProvider;

    private final MatchSchedulerRunnable scheduler;
    private final ControlConfig controlConfig;
    private final MutableDistanceAccumulator distanceAccumulator;

    public Robot(
            FrcDriverStation driverStation,
            Clock clock,
            FourWheelSwerveDriveData swerveDriveData,
            Orientation orientation,
            StandardControllerInput controller,
            SurroundingProvider surroundingProvider) {
        this.driverStation = driverStation;
        this.clock = clock;
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

        Shuffleboard.getTab("dash").add("name", new SendableBase() {
            @Override
            public void initSendable(SendableBuilder builder) {
                builder.addDoubleProperty("time", clock::getTimeSeconds, null);
            }
        });
        Shuffleboard.getTab("dash").add("hello", "hello");
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
            Transform transform = surrounding.getTransform();
            Transform visionTransform = transform.rotateRadians(orientation.getOrientationRadians()).plus(position);
            VisionTarget best = null;
            double closest2 = Double.MAX_VALUE;
            for(VisionTarget target : Field2019.ALL_VISION_TARGETS){
                Transform targetTransform = target.getTransform();
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
