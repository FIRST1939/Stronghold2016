package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.arm.SetArmPosition;
import org.usfirst.frc.team1939.robot.commands.arm.SetRollerSpeed;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.subsystems.Arm;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarSpitReturn extends CommandGroup {

	public LowBarSpitReturn() {
		this.addSequential(new SetArmPosition(Arm.DOWN));
		this.addSequential(new DriveByInches(180));
		this.addSequential(new SetRollerSpeed(-1.0));
		this.addSequential(new Wait(2.0));
		this.addSequential(new SetRollerSpeed(0.0));
		this.addSequential(new DriveByInches(-150));
	}
}
