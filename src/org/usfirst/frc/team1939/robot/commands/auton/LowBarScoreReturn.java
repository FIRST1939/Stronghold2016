package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarScoreReturn extends CommandGroup {

	public LowBarScoreReturn() {
		this.addSequential(new LowBarScore());
		this.addSequential(new DriveByInches(141 - Robot.length / 2 - 15));
		this.addSequential(new TurnByDegrees(120));
		this.addSequential(new DriveByInches(226 - Robot.length / 2));
	}
}
