package org.usfirst.frc.team1939.robot.commands.auton;

import org.usfirst.frc.team1939.robot.commands.dart.SetDartOutput;
import org.usfirst.frc.team1939.robot.commands.winch.DisengageRatchet;
import org.usfirst.frc.team1939.robot.commands.winch.SetWinchPosition;
import org.usfirst.frc.team1939.robot.subsystems.Winch;
import org.usfirst.frc.team1939.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtendScaler extends CommandGroup {

	public ExtendScaler() {
		this.addSequential(new DisengageRatchet());
		this.addSequential(new SetDartOutput(-1.0 / 3.0));
		this.addSequential(new Wait(1.0));
		this.addSequential(new SetDartOutput(0));
		this.addSequential(new SetWinchPosition(Winch.UP));
	}
}
