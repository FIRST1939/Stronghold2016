package org.usfirst.frc.team1939.robot.commands.camera;

import org.usfirst.frc.team1939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CameraJoystick extends Command {

	private double position;

	public CameraJoystick() {
		requires(Robot.camera);
	}

	@Override
	protected void initialize() {
		this.position = 0;
	}

	@Override
	protected void execute() {
		if (Robot.dashboard.gamepadControlMode.getSelected().equals("Camera")) {
			double value = Robot.oi.gamepad.getLeftY();
			if (Math.abs(value) < 0.3)
				value = 0;
			if (value < 0)
				this.position += 0.006;
			if (value > 0)
				this.position -= 0.006;
			if (this.position > 1)
				this.position = 1;
			if (this.position < 0)
				this.position = 0;
			Robot.camera.setPosition(this.position);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
