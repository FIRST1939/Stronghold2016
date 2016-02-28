package org.usfirst.frc.team1939.robot.commands.winch;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonResetEncoderCommand;

public class ResetWinchEncoder extends CANTalonResetEncoderCommand {

	public ResetWinchEncoder() {
		super(Robot.winch);
	}

}
