package me.retrodaredevil.controller.wpi.sendable;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import me.retrodaredevil.controller.ControllerPart;

import java.text.DecimalFormat;
import java.util.function.Supplier;

public class ControllerPartSendable implements Sendable {
	static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(" #0.00;-#0.00");
	private final Supplier<? extends ControllerPart> controllerPartSupplier;
	
	public ControllerPartSendable(Supplier<? extends ControllerPart> controllerPartSupplier) {
		this.controllerPartSupplier = controllerPartSupplier;
	}
	public ControllerPartSendable(ControllerPart controllerPart){
		this.controllerPartSupplier = () -> controllerPart;
	}
	
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addBooleanProperty("is connected", () -> controllerPartSupplier.get().isConnected(), null);
	}
}
