package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarScore extends CommandGroup {

	public LowBarScore() {
		this.addSequential(new LowBar());
		this.addSequential(new TurnByDegrees(60));
		this.addSequential(new DriveByInches(140, .80));
		this.addSequential(new Wait(0.5));
		this.addSequential(new Spit());
	}
}
