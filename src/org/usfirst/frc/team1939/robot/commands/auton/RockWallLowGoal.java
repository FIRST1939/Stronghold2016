package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.SetArmPosition;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;
import org.usfirst.frc.team1939.robot.subsystems.Arm;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockWallLowGoal extends CommandGroup {

	public RockWallLowGoal() {
		this.addSequential(new DriveByInches(270, .85));
		this.addSequential(new TurnByDegrees(-60));
		this.addSequential(new SetArmPosition(Arm.DOWN));
		this.addSequential(new DriveByInches(53));
		this.addSequential(new Wait(0.5));
		this.addSequential(new Spit());
	}
}
