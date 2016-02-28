package org.usfirst.frc.team1939.robot.commands.winch;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DisengageRatchet extends Command {

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.winch.setSpike(true);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
