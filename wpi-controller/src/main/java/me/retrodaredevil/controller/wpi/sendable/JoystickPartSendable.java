package me.retrodaredevil.controller.wpi.sendable;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import me.retrodaredevil.controller.input.JoystickPart;

import java.util.function.Supplier;

public class JoystickPartSendable extends ControllerPartSendable {
	private final Supplier<? extends JoystickPart> joystickPartSupplier;

	public JoystickPartSendable(JoystickPart joystick) {
		super(joystick);
		this.joystickPartSupplier = () -> joystick;
	}
	public JoystickPartSendable(Supplier<? extends JoystickPart> joystickPartSupplier){
		super(joystickPartSupplier);
		this.joystickPartSupplier = joystickPartSupplier;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		super.initSendable(builder);
		builder.addStringProperty("X", () -> DECIMAL_FORMAT.format(joystickPartSupplier.get().getX()), null);
		builder.addStringProperty("Y", () -> DECIMAL_FORMAT.format(joystickPartSupplier.get().getY()), null);
		builder.addStringProperty("correct magnitude", () -> DECIMAL_FORMAT.format(joystickPartSupplier.get().getCorrectMagnitude()), null);
		builder.addStringProperty("angle degrees", () -> DECIMAL_FORMAT.format(joystickPartSupplier.get().getAngle()), null);
		builder.addBooleanProperty("is deadzone", () -> joystickPartSupplier.get().isDeadzone(), null);

	}
}
