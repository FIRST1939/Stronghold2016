package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockWall extends CommandGroup {

	public RockWall() {
		this.addSequential(new DriveByInches(181, .85));
	}
}
