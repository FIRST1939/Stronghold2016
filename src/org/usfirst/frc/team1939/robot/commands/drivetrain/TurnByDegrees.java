package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class TurnByDegrees extends Command {

	private double degrees;
	private PIDTimer timer;

	public TurnByDegrees(double degrees) {
		requires(Robot.drivetrain);
		this.degrees = degrees;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.navx.reset();
		Robot.drivetrain.turnPID.setSetpoint(this.degrees);
		Robot.drivetrain.turnPID.enable();
		this.timer = new PIDTimer(() -> Robot.drivetrain.getSpeed(), 0, 1, 300);
	}

	@Override
	protected void execute() {
		this.timer.update();
		Robot.drivetrain.drive(0, Robot.drivetrain.turnPID.get());
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		Robot.drivetrain.turnPID.disable();
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.drive(0, 0);
		Robot.drivetrain.turnPID.disable();
	}
}
