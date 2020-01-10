package me.retrodaredevil.controller.wpi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.input.*;
import me.retrodaredevil.controller.input.implementations.DigitalAnalogInputPart;
import me.retrodaredevil.controller.input.implementations.TwoAxisJoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;

/**
 * This is used to create different types of {@link InputPart}s and {@link JoystickPart}s using a
 * {@link GenericHID} which is easily instantiated by using a {@link Joystick}
 */
public class WpiInputCreator implements ControllerPartCreator {

	private final GenericHID hid;
	private final boolean returnZeroValueIfDisconnected;

	public WpiInputCreator(GenericHID hid, boolean returnZeroValueIfDisconnected){
		this.hid = hid;
		this.returnZeroValueIfDisconnected = returnZeroValueIfDisconnected;
	}
	public WpiInputCreator(GenericHID hid){
		this(hid, true);
	}

	public WpiInputCreator(int port){
		this(new Joystick(port));
	}

	@Override
	public InputPart createDigital(int code) {
		return new HidButtonInputPart(hid, code + 1);
	}

	@Override
	public JoystickPart createPov(int povNumber, int xAxis, int yAxis) {
		return createPov(povNumber);
	}

	@Override
	public JoystickPart createPov(int povNumber) {
		return new HidPovJoystickPart(hid, povNumber);
	}

	@Override
	public JoystickPart createPov(int xAxis, int yAxis) {
		return createJoystick(xAxis, yAxis);
	}

	@Override
	public JoystickPart createJoystick(int xAxis, int yAxis) {
		return new TwoAxisJoystickPart(
				new HidInputPart(AxisType.FULL_ANALOG, hid, xAxis, false, true, returnZeroValueIfDisconnected),
				new HidInputPart(AxisType.FULL_ANALOG, hid, yAxis, true, true, returnZeroValueIfDisconnected)
		);
	}

	@Override
	public InputPart createFullAnalog(int axisCode) {
		System.err.println("Using createFullAnalog(int) method instead of createFullAnalog(int, boolean)!");
		return createFullAnalog(axisCode, false);
	}

	@Override
	public InputPart createAnalog(int axisCode) {
		System.err.println("Using createAnalog(int) method instead of createAnalog(int, boolean)!");
		return createAnalog(axisCode, false);
	}

	@Override
	public InputPart createFullAnalog(int axisCode, boolean isVertical) {
		return new HidInputPart(AxisType.FULL_ANALOG, hid, axisCode, isVertical, true, returnZeroValueIfDisconnected);
	}

	@Override
	public InputPart createAnalog(int axisCode, boolean isVertical) {
		return new HidInputPart(AxisType.ANALOG, hid, axisCode, isVertical, true, returnZeroValueIfDisconnected);
	}

	@Override
	public InputPart createAnalogTrigger(int axisCode) {
		return new HidInputPart(AxisType.ANALOG, hid, axisCode, false, true, returnZeroValueIfDisconnected);
	}

	@Override
	public InputPart createTrigger(int digitalCode, int analogCode) {
		return new DigitalAnalogInputPart(
				new HidButtonInputPart(hid, digitalCode + 1),
				new HidInputPart(AxisType.ANALOG, hid, analogCode, false, true, returnZeroValueIfDisconnected)
		);
	}

	@Override
	public ControllerRumble createRumble() {
		return new HidRumble(hid);
	}

	@Override
	public boolean isConnected() {
		return HidUtil.isConnected(hid);
	}

	@Override
	public String getName() {
		return hid.getName();
	}

	@Override
	public String toString() {
		return String.format("%s{name=%s,port=%s,axi count=%s,button count=%s,pov count=%s}",
				getClass().getSimpleName(), hid.getName(), hid.getPort(), hid.getAxisCount(), hid.getButtonCount(), hid.getPOVCount());
	}
}
