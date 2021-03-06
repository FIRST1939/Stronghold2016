package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.SetArmPosition;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarSpitReturn extends CommandGroup {

	public LowBarSpitReturn() {
		this.addSequential(new SetArmPosition(Arm.DOWN));
		this.addSequential(new DriveByInches(180));
		this.addSequential(new Spit());
		this.addSequential(new DriveByInches(-150));
	}
}
