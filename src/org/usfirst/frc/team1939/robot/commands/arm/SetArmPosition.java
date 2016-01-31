package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetArmPosition extends CANTalonSetPositionCommand {

	public SetArmPosition(int position) {
		super(Robot.arm, position);
	}

}
