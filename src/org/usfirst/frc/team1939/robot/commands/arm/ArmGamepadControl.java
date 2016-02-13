package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ArmGamepadControl extends Command {

	private boolean buttonReleased = false;
	private boolean triggerReleased = false;

	public ArmGamepadControl() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		Robot.arm.setOutput(0);
		Robot.arm.spinRoller(0);
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Arm")) {
			double speed = Robot.oi.gamepad.getLeftY();
			Robot.arm.setOutput(speed);
		} else {
			boolean button = Robot.oi.gamepad.rightButton.get();
			boolean trigger = Robot.oi.gamepad.rightTrigger.get();

			if (!button) {
				this.buttonReleased = true;
			}
			if (!trigger) {
				this.triggerReleased = true;
			}

			if (!(button && trigger)) {
				if (button && this.buttonReleased) {
					Robot.arm.setPosition(Arm.IN);
					this.buttonReleased = false;
				}
				if (trigger && this.triggerReleased) {
					Robot.arm.setPosition(Arm.OUT);
					this.triggerReleased = false;
				}
			}
		}

		double speed = Robot.oi.gamepad.getRightY();
		if (Robot.arm.hasBoulder() && speed > 0) {
			speed = 0;
		} else {
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
	}

	@Override
	protected void interrupted() {
		Robot.arm.setOutput(0);
		Robot.arm.spinRoller(0);
	}
}
