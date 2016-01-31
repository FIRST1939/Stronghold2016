package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetScalerGrabberPosition extends CANTalonSetPositionCommand {

	public SetScalerGrabberPosition(int position) {
		super(Robot.grabber, position);
	}

}
