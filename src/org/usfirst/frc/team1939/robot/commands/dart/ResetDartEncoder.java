package org.usfirst.frc.team1939.robot.commands.dart;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonResetEncoderCommand;

public class ResetDartEncoder extends CANTalonResetEncoderCommand {

	public ResetDartEncoder() {
		super(Robot.dart);
	}

}
