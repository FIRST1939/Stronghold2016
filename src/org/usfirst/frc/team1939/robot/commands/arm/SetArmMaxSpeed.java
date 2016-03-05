package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetArmMaxSpeed extends Command {

	private double max;

	public SetArmMaxSpeed(double max) {
		this.max = Math.abs(max);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.arm.pid.setOutputRange(-this.max, this.max);
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
