package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.LiftPortcullis;
import org.usfirst.frc.team1939.robot.commands.arm.SetArmPosition;
import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftAndDriveUnderPortcullis extends CommandGroup {

	public LiftAndDriveUnderPortcullis() {
		this.addSequential(new LiftPortcullis());
		this.addSequential(new SetArmPosition(Arm.DOWN));
		// this.addParallel(new DriveByInches(48)); // Needs to be measured
	}
}
