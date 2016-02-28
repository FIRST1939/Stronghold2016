package org.usfirst.frc.team1939.robot.commands.dart;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetDartPosition extends CANTalonSetPositionCommand {

	public SetDartPosition(int position) {
		super(Robot.dart, position);
	}

}
