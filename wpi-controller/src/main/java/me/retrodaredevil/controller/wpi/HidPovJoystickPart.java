package me.retrodaredevil.controller.wpi;

import edu.wpi.first.wpilibj.GenericHID;
import me.retrodaredevil.controller.ControllerPartNotUpdatedException;
import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.input.JoystickType;
import me.retrodaredevil.controller.input.implementations.JoystickAxisFollowerPart;

class HidPovJoystickPart extends SimpleControllerPart implements JoystickPart {
	private static final double INV_SQRT2 = 1.0 / Math.sqrt(2.0);
	private static final JoystickType JOYSTICK_TYPE = new JoystickType(false, false, false, true);

	private final InputPart xAxis = new JoystickAxisFollowerPart(this, partUpdater, false);
	private final InputPart yAxis = new JoystickAxisFollowerPart(this, partUpdater, true);

	private final GenericHID hid;
	private final int povCode;

	private double x, y;
	private int pov;
	private boolean updated = false;

	/**
	 *
	 * @param hid The hid device
	 * @param povCode The index of the POV - starting at 0
	 */
	public HidPovJoystickPart(GenericHID hid, int povCode){
		this.hid = hid;
		this.povCode = povCode;
	}
	private void checkUpdated(){
		if(!updated){
			throw new ControllerPartNotUpdatedException("updated=false. x: " + x + " y: " + y + " pov: " + pov + " povCode: " + povCode);
		}
	}

	@Override
	public InputPart getXAxis() {
		return xAxis;
	}

	@Override
	public InputPart getYAxis() {
		return yAxis;
	}

	@Override
	public JoystickType getJoystickType() {
		return JOYSTICK_TYPE;
	}

	@Override
	public double getMagnitude() {
		checkUpdated();
		return (x != 0 || y != 0) ? 1 : 0;
	}

	@Override
	public double getCorrectMagnitude() {
		return getMagnitude();
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		pov = hid.getPOV(povCode);
		switch(pov){
			case -1: x = 0; y = 0; break;
			case 0: x = 0; y = 1; break;
			case 45: x = INV_SQRT2; y = INV_SQRT2; break;
			case 90: x = 1; y = 0; break;
			case 135: x = INV_SQRT2; y = -INV_SQRT2; break;
			case 180: x = 0; y = -1; break;
			case 225: x = -INV_SQRT2; y = -INV_SQRT2; break;
			case 270: x = -1; y = 0; break;
			case 315: x = -INV_SQRT2; y = INV_SQRT2; break;
			default: throw new UnsupportedOperationException("Unknown pov: " + pov);
		}
		updated = true;
	}

	@Override
	public double getAngle() {
		checkUpdated();
		return 90 - pov;
	}

	@Override
	public double getAngleRadians() {
		checkUpdated();
		return Math.toRadians(90 - pov);
	}

	@Override
	public double getX() {
		checkUpdated();
		return x;
	}

	@Override
	public double getY() {
		checkUpdated();
		return y;
	}

	@Override
	public boolean isXDeadzone() {
		checkUpdated();
		return x == 0;
	}

	@Override
	public boolean isYDeadzone() {
		checkUpdated();
		return y == 0;
	}

	@Override
	public boolean isConnected() {
		if(hid.getPOVCount() < povCode + 1){
			return false;
		}
		return HidUtil.isConnected(hid);
	}

	@Override
	public boolean isDeadzone() {
		checkUpdated();
		return x == 0 && y == 0;
	}
}
