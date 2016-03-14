package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.commands.arm.SetRollerSpeed;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarScore extends CommandGroup {

	public LowBarScore() {
		this.addSequential(new LowBar());
		this.addSequential(new TurnByDegrees(60));
		this.addSequential(new DriveByInches(141 - Robot.length / 2 - 15));
		this.addSequential(new SetRollerSpeed(-1.0));
		this.addSequential(new Wait(3.0));
		this.addSequential(new SetRollerSpeed(0));
	}
}
