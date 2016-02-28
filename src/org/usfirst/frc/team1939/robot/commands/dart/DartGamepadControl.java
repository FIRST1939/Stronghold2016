package org.usfirst.frc.team1939.robot.commands.dart;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DartGamepadControl extends Command {

	public DartGamepadControl() {
		requires(Robot.dart);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Dart")) {
			double speed = Robot.oi.gamepad.getLeftY();
			Robot.dart.setOutput(speed);
			SmartDashboard.putNumber("Dart Output", speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.dart.setOutput(0);
	}

	@Override
	protected void interrupted() {
		Robot.dart.setOutput(0);
	}
}
