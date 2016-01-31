package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonResetEncoderCommand;

public class ResetGrabberEncoder extends CANTalonResetEncoderCommand {

	public ResetGrabberEncoder() {
		super(Robot.grabber);
	}

}
