package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonResetEncoderCommand;

public class ResetLifterEncoder extends CANTalonResetEncoderCommand {

	public ResetLifterEncoder() {
		super(Robot.lifter);
	}

}
