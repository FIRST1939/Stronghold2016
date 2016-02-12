package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ScalerLifterGamepadControl extends Command {

	public ScalerLifterGamepadControl() {
		requires(Robot.lifter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Lifter")) {
			double speed = Robot.oi.gamepad.getLeftY();
			Robot.lifter.setOutput(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.lifter.setOutput(0);
	}

	@Override
	protected void interrupted() {
		Robot.lifter.setOutput(0);
	}
}
