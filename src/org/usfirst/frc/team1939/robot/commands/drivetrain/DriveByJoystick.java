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

		boolean moveTurbo = Robot.oi.leftStick.getRawButton(1);
		boolean rotateTurbo = Robot.oi.rightStick.getRawButton(1);

		if (!moveTurbo) {
			move *= 0.6;
		}
		if (!rotateTurbo) {
			rotate *= 0.5;
		}
		Robot.drivetrain.drive(move, rotate);
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
