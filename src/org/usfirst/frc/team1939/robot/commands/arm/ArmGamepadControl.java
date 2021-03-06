package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmGamepadControl extends Command {

	public ArmGamepadControl() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		Robot.arm.setOutput(0);
		Robot.arm.spinRoller(0);

		Robot.arm.pid.enable();
	}

	@Override
	protected void execute() {
		if (Robot.arm.isDown()) {
			Robot.arm.setEncoderPosition(Arm.DOWN);
			Robot.arm.pid.setOutputRange(-Arm.MAX, Arm.MAX);
		}

		double moveSpeed = 0;
		if (Math.abs(Robot.oi.gamepad.getLeftY()) >= 0.2
				&& Robot.dashboard.gamepadControlMode.getSelected().equals("Neither")
				|| Robot.dashboard.gamepadControlMode.getSelected().equals("Arm")) {
			// If in manual control, control with gamepad
			moveSpeed = -Robot.oi.gamepad.getLeftY();
		} else {
			// Else setpoint off of gamepad buttons
			boolean button = Robot.oi.gamepad.rightButton.get();
			boolean trigger = Robot.oi.gamepad.rightTrigger.get();

			if (!(button && trigger)) {
				if (button) {
					Robot.arm.pid.setOutputRange(-Arm.MAX, Arm.MAX);
					Robot.arm.pid.setSetpoint(Arm.UP);
				}
				if (trigger) {
					Robot.arm.pid.setOutputRange(-0.5, 0.5);
					Robot.arm.pid.setSetpoint(Arm.DOWN);
				}
			}
			moveSpeed = Robot.arm.pid.get();
		}
		if (Math.abs(moveSpeed) <= 0.1) {
			// Zero if slow
			moveSpeed = 0;
		}
		if (Robot.arm.isDown() && moveSpeed > 0) {
			// Stop arm if it is down and trying to move down
			moveSpeed = 0;
		}
		if (Robot.arm.isDown() && moveSpeed == 0 || Robot.arm.pid.getSetpoint() == Arm.DOWN) {
			// If arm is down and not moving, engage magnet
			Robot.arm.setMagnet(true);
		} else {
			// Turn off magnet
			Robot.arm.setMagnet(false);
		}
		Robot.arm.setOutput(moveSpeed);
		SmartDashboard.putNumber("Arm Setpoint", Robot.arm.pid.getSetpoint());
		SmartDashboard.putNumber("Arm Output", moveSpeed);

		// Drive roller off gamepad input
		if (!Robot.robot.isAutonomous()) {
			double speed = Robot.oi.gamepad.getRightY();
			if (Robot.arm.hasBoulder() && speed > 0) {
				speed = 0;
			}
			Robot.arm.spinRoller(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.arm.setOutput(0);
		Robot.arm.spinRoller(0);
		Robot.arm.setMagnet(false);
		Robot.arm.pid.disable();
	}

	@Override
	protected void interrupted() {
		Robot.arm.setOutput(0);
		Robot.arm.spinRoller(0);
		Robot.arm.setMagnet(false);
		Robot.arm.pid.disable();
	}
}
