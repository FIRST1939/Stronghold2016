package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByJoystick extends Command {

	public DriveByJoystick() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double move = Robot.oi.leftStick.getY();
		double rotate = Robot.oi.rightStick.getX();
		if (Robot.oi.rightStick.getRawButton(1)) {
			// Turbo
			Robot.drivetrain.drive(move, rotate * 0.5);
		} else {
			// Normal
			Robot.drivetrain.drive(move * 0.6, rotate * 0.5);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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
