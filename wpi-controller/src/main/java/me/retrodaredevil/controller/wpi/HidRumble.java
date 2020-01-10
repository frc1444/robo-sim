package me.retrodaredevil.controller.wpi;

import edu.wpi.first.wpilibj.GenericHID;
import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.output.ControllerRumble;

public class HidRumble extends SimpleControllerPart implements ControllerRumble {

	private final GenericHID hid;

	/** The time since the epoch in milliseconds representing the time at which to cancel the rumble*/
	private Long cancelAt = null;

	public HidRumble(GenericHID hid){
		this.hid = hid;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		if(cancelAt != null && cancelAt <= System.currentTimeMillis()){
			rumbleForever(0);
		}
	}

	@Override
	public void rumbleForever(double intensity) {
		rumbleForever(intensity, intensity);
	}

	@Override
	public void rumbleForever(double leftIntensity, double rightIntensity) {
		hid.setRumble(GenericHID.RumbleType.kLeftRumble, leftIntensity);
		hid.setRumble(GenericHID.RumbleType.kRightRumble, rightIntensity);
		cancelAt = null;
	}

	@Override
	public void rumbleTimeout(long millisTimeout, double leftIntensity, double rightIntensity) { rumbleTime(millisTimeout, leftIntensity, rightIntensity); }
	@Override
	public void rumbleTimeout(long millisTimeout, double intensity) { rumbleTime(millisTimeout, intensity); }

	@Override
	public void rumbleTime(long millis, double leftIntensity, double rightIntensity) {
		hid.setRumble(GenericHID.RumbleType.kLeftRumble, leftIntensity);
		hid.setRumble(GenericHID.RumbleType.kRightRumble, rightIntensity);
		cancelAt = System.currentTimeMillis() + millis;
	}

	@Override
	public void rumbleTime(long millis, double intensity) {
		rumbleTime(millis, intensity, intensity);
	}

	@Override
	public boolean isAnalogRumbleSupported() { return true; }
	@Override
	public boolean isAnalogRumbleNativelySupported() { return true; }
	@Override
	public boolean isLeftAndRightSupported() { return true; }


	@Override
	public boolean isConnected() {
		return HidUtil.isConnected(hid);
	}
}
