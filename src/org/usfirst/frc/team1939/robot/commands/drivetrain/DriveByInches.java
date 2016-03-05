package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.util.PIDTimer;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByInches extends Command {

	private double inches;
	private double speed;
	private PIDTimer timer;

	public DriveByInches(double inches) {
		this(inches, Drivetrain.MAX_MOVE_SPEED);
	}

	public DriveByInches(double inches, double speed) {
		requires(Robot.drivetrain);
		this.inches = inches;
		this.speed = Math.abs(speed);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.movePID.setOutputRange(-this.speed, this.speed);

		this.timer = new PIDTimer(() -> Robot.drivetrain.getSpeed(), 0, 1, 300);
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.navx.reset();

		Robot.drivetrain.movePID.setSetpoint(-Drivetrain.inchesToTicks(this.inches));
		Robot.drivetrain.turnPID.setSetpoint(0);

		Robot.drivetrain.movePID.enable();
		Robot.drivetrain.turnPID.enable();
	}

	@Override
	protected void execute() {
		this.timer.update();
		Robot.drivetrain.drive(Robot.drivetrain.movePID.get(), Robot.drivetrain.turnPID.get());
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		Robot.drivetrain.movePID.disable();
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.movePID.setOutputRange(-Drivetrain.MAX_MOVE_SPEED, Drivetrain.MAX_MOVE_SPEED);
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.drive(0, 0);
		Robot.drivetrain.movePID.disable();
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.movePID.setOutputRange(-Drivetrain.MAX_MOVE_SPEED, Drivetrain.MAX_MOVE_SPEED);
	}
}
