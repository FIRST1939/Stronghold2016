package org.usfirst.frc.team1939.robot.commands.winch;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetWinchPosition extends CANTalonSetPositionCommand {

	public SetWinchPosition(int position) {
		super(Robot.winch, position);
	}

}
