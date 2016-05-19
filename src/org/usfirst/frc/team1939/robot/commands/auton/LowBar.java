package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.SetArmPosition;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBar extends CommandGroup {

	public LowBar() {
		this.addSequential(new SetArmPosition(Arm.DOWN));
		this.addSequential(new DriveByInches(227.5, .85));
	}
}
