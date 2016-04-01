package org.usfirst.frc.team1939.robot.commands.dart;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDartOutput extends Command {

	private double output;

	public SetDartOutput(double output) {
		this.output = output;
	}

	@Override
	protected void initialize() {
		Robot.dart.setOutput(this.output);
		Robot.dart.isReleased = true;
	}

	@Override
	protected void execute() {
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
