package org.usfirst.frc.team1939.util;

import edu.wpi.first.wpilibj.command.Command;

public class CANTalonSetPositionCommand extends Command {

	private CANTalonSubsystem subsystem;
	private int position;
	private PIDTimer timer;

	public CANTalonSetPositionCommand(CANTalonSubsystem subsystem, int position) {
		this.subsystem = subsystem;
		this.position = position;

		requires(subsystem);
	}

	@Override
	protected void initialize() {
		this.timer = new PIDTimer(() -> this.subsystem.getSpeed(), 0, 1, 300);
		this.subsystem.pid.setSetpoint(this.position);
		this.subsystem.pid.enable();
	}

	@Override
	protected void execute() {
		this.timer.update();
		this.subsystem.setOutput(this.subsystem.getPIDOutput());
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		this.subsystem.setOutput(0);
		this.subsystem.pid.disable();
	}

	@Override
	protected void interrupted() {
		this.subsystem.setOutput(0);
		this.subsystem.pid.disable();
	}

}
