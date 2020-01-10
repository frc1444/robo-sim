package me.retrodaredevil.controller.wpi;

import edu.wpi.first.wpilibj.GenericHID;
import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;

/**
 * An InputPart only for buttons on a {@link GenericHID}. When using buttons, this is recommended
 * over using {@link HidInputPart} as it uses more methods directly from {@link GenericHID} and
 * doesn't calculated it's own pressed and released.
 * <p>
 * NOTE, if you call {@link GenericHID#getRawButtonPressed(int)} or {@link GenericHID#getRawButtonReleased(int)},
 * on the passed {@link GenericHID}, the instance of this class may not return the correct values from
 * {@link #isJustPressed()} or {@link #isJustReleased()}
 */
class HidButtonInputPart extends SimpleControllerPart implements InputPart {
	private final GenericHID hid;
	private final int buttonCode;

	private Boolean pressed = null;
	private Boolean released = null;

	/**
	 *
	 * @param hid The HID device
	 * @param buttonCode The button code, starting from 1
	 */
	public HidButtonInputPart(GenericHID hid, int buttonCode) {
		this.hid = hid;
		this.buttonCode = buttonCode;
		if(buttonCode <= 0){
			System.err.println("buttonCode is out of range! buttonCode: " + buttonCode);
		}
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		pressed = null;
		released = null;
	}

	@Override
	public AxisType getAxisType() {
		return AxisType.DIGITAL;
	}

	@Override
	public boolean isDeadzone() {
		return !isDown();
	}

	@Override
	public double getPosition() {
		return getDigitalPosition();
	}

	@Override
	public boolean isDown() {
		return hid.getRawButton(buttonCode);
	}

	@Override
	public boolean isJustPressed() {
		if(pressed != null){
			return pressed;
		}
		return pressed = hid.getRawButtonPressed(buttonCode);
	}

	@Override
	public boolean isJustReleased() {
		if(released != null){
			return released;
		}
		return released = hid.getRawButtonReleased(buttonCode);
	}

	@Override
	public int getDigitalPosition() {
		return isDown() ? 1 : 0;
	}

	@Override
	public boolean isConnected() {
		if(hid.getButtonCount() < buttonCode){
			return false;
		}
		return HidUtil.isConnected(hid);
	}
}
