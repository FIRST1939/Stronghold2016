package org.usfirst.frc.team1939.util;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;

public class CANTalonDriveCommand extends Command {

	private CANTalonSubsystem subsystem;
	private BooleanSupplier enabled;
	private DoubleSupplier input;

	public CANTalonDriveCommand(CANTalonSubsystem subsystem, BooleanSupplier enabled, DoubleSupplier input) {
		this.subsystem = subsystem;
		this.enabled = enabled;
		this.input = input;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (this.enabled.getAsBoolean()) {
			this.subsystem.setOutput(this.input.getAsDouble());
		} else {
			this.subsystem.disable();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		this.subsystem.disable();
	}

	@Override
	protected void interrupted() {
		this.subsystem.disable();
	}

}
