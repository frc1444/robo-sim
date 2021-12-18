package me.retrodaredevil.controller.wpi.sendable;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import me.retrodaredevil.controller.options.ControlOption;
import me.retrodaredevil.controller.options.OptionValue;

public class ControlOptionSendable implements Sendable {
	private final ControlOption option;

	public ControlOptionSendable(ControlOption option) {
		this.option = option;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		final OptionValue value = option.getOptionValue();
		final String key = "Value";
		if(value.isOptionValueBoolean()){
			builder.addBooleanProperty(key, value::getBooleanOptionValue, value::setBooleanOptionValue);
		} else if(value.isOptionValueRadio()){
			throw new UnsupportedOperationException("Cannot add property for a radio option");
		} else {
			builder.addDoubleProperty(key, value::getOptionValue, value::setOptionValue);
		}
	}
}
