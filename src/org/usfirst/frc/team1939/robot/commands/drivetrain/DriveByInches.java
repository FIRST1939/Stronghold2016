package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {

	private double inches;
	private PIDTimer timer;

	public DriveByInches(double inches) {
		requires(Robot.drivetrain);
		this.inches = inches;
	}

	@Override
	protected void initialize() {
		this.timer = new PIDTimer(() -> Robot.drivetrain.getSpeed(), 0, 1, 100);
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.setPosition(this.inches);
	}

	@Override
	protected void execute() {
		this.timer.update();
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		Robot.drivetrain.disable();
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.disable();
	}
}
