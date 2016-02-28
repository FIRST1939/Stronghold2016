package org.usfirst.frc.team1939.robot.commands.winch;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchGamepadControl extends Command {

	public WinchGamepadControl() {
		requires(Robot.winch);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Winch")) {
			double speed = Robot.oi.gamepad.getLeftY();
			Robot.winch.setOutput(speed);
			SmartDashboard.putNumber("Winch Output", speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.winch.setOutput(0);
	}

	@Override
	protected void interrupted() {
		Robot.winch.setOutput(0);
	}

}
