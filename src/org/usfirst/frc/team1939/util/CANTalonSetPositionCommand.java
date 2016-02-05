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
		this.timer = new PIDTimer(() -> this.subsystem.getSpeed(), 0, 1, 100);
		this.subsystem.setPosition(this.position);
	}

	@Override
	protected void execute() {
		this.timer.update();
	}

	@Override
	protected boolean isFinished() {
		return this.timer.isDone();
	}

	@Override
	protected void end() {
		this.subsystem.disable();
	}

	@Override
	protected void interrupted() {
		this.subsystem.disable();
	}

}
