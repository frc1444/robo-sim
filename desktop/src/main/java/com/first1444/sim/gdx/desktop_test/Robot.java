package com.first1444.sim.gdx.desktop_test;

import com.first1444.sim.api.Clock;
import com.first1444.sim.api.Vector2;
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDrive;
import com.first1444.sim.api.drivetrain.swerve.FourWheelSwerveDriveData;
import com.first1444.sim.api.drivetrain.swerve.SwerveDrive;
import com.first1444.sim.api.frc.BasicRobotRunnable;
import com.first1444.sim.api.frc.FrcDriverStation;
import com.first1444.sim.api.frc.FrcMode;
import com.first1444.sim.api.sensors.DefaultMutableOrientation;
import com.first1444.sim.api.sensors.MutableOrientation;
import com.first1444.sim.api.sensors.Orientation;
import me.retrodaredevil.controller.ControlConfig;
import me.retrodaredevil.controller.MutableControlConfig;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.types.StandardControllerInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Robot extends BasicRobotRunnable {
    private final FrcDriverStation driverStation;
    private final Clock clock;
    private final SwerveDrive swerveDrive;
    private final MutableOrientation orientation;

    private final StandardControllerInput controller;

    private final ControlConfig controlConfig;

    public Robot(
            FrcDriverStation driverStation,
            Clock clock,
            FourWheelSwerveDriveData swerveDriveData,
            Orientation orientation,
            StandardControllerInput controller
    ) {
        super(driverStation);
        this.driverStation = driverStation;
        this.clock = clock;
        this.swerveDrive = new FourWheelSwerveDrive(swerveDriveData);
        this.orientation = new DefaultMutableOrientation(orientation);
        this.controller = controller;

        MutableControlConfig config = new MutableControlConfig();
        config.fullAnalogDeadzone = .03;
        this.controlConfig = config;
    }

    @Override
    protected void update(@NotNull FrcMode mode, @Nullable FrcMode previousMode) {
        if(previousMode != mode){
            System.out.println("New mode: " + mode);
        }
        controller.update(controlConfig);

        updateSwerve();
        swerveDrive.run();
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
            translation = new Vector2(-y, x);
        } else {
            translation = new Vector2(-y, x).rotateRadians(-orientation.getOrientationRadians());
        }

        if(translation.getMagnitude() > 1){
            translation = translation.div(translation.getMagnitude());
        }
        final double rotate;
        if(controller.getRightJoy().isXDeadzone()){
            rotate = 0;
        } else {
            rotate =  controller.getRightJoy().getX();
        }
        double speed = controller.getRightTrigger().getPosition();

        swerveDrive.setControl(translation.getY(), translation.getX(), rotate, speed);

    }
}
