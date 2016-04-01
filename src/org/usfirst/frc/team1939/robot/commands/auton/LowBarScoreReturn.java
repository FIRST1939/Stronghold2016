package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarScoreReturn extends CommandGroup {

	public LowBarScoreReturn() {
		this.addSequential(new LowBarScore());
		this.addSequential(new DriveByInches(-117.5));
		this.addSequential(new TurnByDegrees(120));
		this.addSequential(new DriveByInches(215.5));
	}
}
