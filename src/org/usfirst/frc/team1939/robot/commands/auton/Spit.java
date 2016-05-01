package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.SetRollerSpeed;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Spit extends CommandGroup {

	public Spit() {
		this.addSequential(new SetRollerSpeed(-1.0));
		this.addSequential(new Wait(2.0));
		this.addSequential(new SetRollerSpeed(0));
	}
}
