package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetScalerLifterPosition extends CANTalonSetPositionCommand {

	public SetScalerLifterPosition(int position) {
		super(Robot.lifter, position);
	}

}
