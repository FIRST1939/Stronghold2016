package org.usfirst.frc.team1939.robot.commands.winch;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.CANTalonSetPositionCommand;

public class SetWinchPosition extends CANTalonSetPositionCommand {

	public SetWinchPosition(int position) {
		super(Robot.winch, position);
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.winch.setSpike(true);
	}

	@Override
	protected boolean isFinished() {
		return super.isFinished() || !Robot.dart.isReleased;
	}

	@Override
	protected void end() {
		super.end();
		Robot.winch.setSpike(false);
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		Robot.winch.setSpike(false);
	}

}
