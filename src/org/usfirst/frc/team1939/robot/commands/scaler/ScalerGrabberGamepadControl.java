package org.usfirst.frc.team1939.robot.commands.scaler;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ScalerGrabberGamepadControl extends Command {

	public ScalerGrabberGamepadControl() {
		requires(Robot.grabber);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Grabber")) {
			double speed = Robot.oi.gamepad.getLeftY();
			Robot.grabber.setOutput(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.grabber.setOutput(0);
	}

	@Override
	protected void interrupted() {
		Robot.grabber.setOutput(0);
	}

}
