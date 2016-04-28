package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarSpitReturnTurn extends CommandGroup {

	public LowBarSpitReturnTurn() {
		this.addSequential(new LowBarSpitReturn());
		this.addSequential(new TurnByDegrees(90));
		this.addSequential(new TurnByDegrees(90));
	}
}
