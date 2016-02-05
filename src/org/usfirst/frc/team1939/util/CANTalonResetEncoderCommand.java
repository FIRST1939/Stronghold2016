package org.usfirst.frc.team1939.util;

import edu.wpi.first.wpilibj.command.Command;

public class CANTalonResetEncoderCommand extends Command {

	private CANTalonSubsystem subsystem;

	public CANTalonResetEncoderCommand(CANTalonSubsystem subsystem) {
		this.subsystem = subsystem;
	}

	@Override
	protected void initialize() {
		this.subsystem.resetEncoder();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
