package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonResetEncoderCommand;

public class ResetArmEncoder extends CANTalonResetEncoderCommand {

	public ResetArmEncoder() {
		super(Robot.arm);
	}

}
