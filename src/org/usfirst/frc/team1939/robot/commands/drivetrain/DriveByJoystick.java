package org.usfirst.frc.team1939.robot.commands.drivetrain;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByJoystick extends Command {

	private boolean correcting = false;

	public DriveByJoystick() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.turnPID.setSetpoint(0);
		Robot.drivetrain.turnPID.enable();
	}

	@Override
	protected void execute() {
		double move = Robot.oi.leftStick.getY();
		double rotate = Robot.oi.rightStick.getX();

		boolean moveTurbo = Robot.oi.leftStick.getRawButton(1);
		boolean rotateTurbo = Robot.oi.rightStick.getRawButton(1);
		boolean turnCorrect = Robot.oi.rightStick.getRawButton(2);

		if (Math.abs(move) < 0.1) {
			move = 0;
		}
		if (Math.abs(rotate) < 0.1) {
			rotate = 0;
		}

		if (moveTurbo) {
			if (move > 0) {
				move = map(move, 0.1, 1.0, 0.6, 1.0);
			} else if (move < 0) {
				move = map(move, -1.0, -0.1, -1.0, -0.6);
			}
		} else {
			if (move > 0) {
				move = map(move, 0.1, 1.0, 0.4, 0.8);
			} else if (move < 0) {
				move = map(move, -1.0, -0.1, -0.8, -0.4);
			}
		}
		if (turnCorrect) {
			if (this.correcting) {
				rotate = Robot.drivetrain.turnPID.get();
			} else {
				this.correcting = true;
				Robot.drivetrain.navx.reset();
			}
		} else {
			this.correcting = false;
			if (rotateTurbo) {
				if (rotate > 0) {
					rotate = map(rotate, 0.1, 1.0, 0.5, 1.0);
				} else if (rotate < 0) {
					rotate = map(rotate, -1.0, -0.1, -1.0, -0.5);
				}
			} else {
				if (rotate > 0) {
					rotate = map(rotate, 0.1, 1.0, 0.3, 0.8);
				} else if (rotate < 0) {
					rotate = map(rotate, -1.0, -0.1, -0.8, -0.3);
				}
			}
		}
		Robot.drivetrain.drive(move, rotate);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.turnPID.disable();
		Robot.drivetrain.drive(0, 0);
	}

	private static double map(double x, double in_min, double in_max, double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
