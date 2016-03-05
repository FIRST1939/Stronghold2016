package org.usfirst.frc.team1939.robot.commands.arm;

import org.usfirst.frc.team1939.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftPortcullis extends CommandGroup {

	public LiftPortcullis() {
		this.addSequential(new SetArmMaxSpeed(1.0));
		this.addSequential(new SetArmPosition(Arm.DOWN / 2));
		this.addSequential(new SetArmMaxSpeed(Arm.MAX));
	}

}
