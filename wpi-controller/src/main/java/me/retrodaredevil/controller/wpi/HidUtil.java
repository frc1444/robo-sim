package me.retrodaredevil.controller.wpi;

import edu.wpi.first.wpilibj.GenericHID;

final class HidUtil {
	private HidUtil() { throw new UnsupportedOperationException(); }

	public static boolean isConnected(GenericHID hid){
		return hid.getAxisCount() > 0 || hid.getButtonCount() > 0 || hid.getPOVCount() > 0;
	}
}
