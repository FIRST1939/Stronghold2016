package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.commands.smartdashboard.SmartDashboardUpdater;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SmartDashboardSubsystem extends Subsystem {

	@Override
	public void initDefaultCommand() {
		Command command = new SmartDashboardUpdater();
		command.setRunWhenDisabled(true);
		setDefaultCommand(command);
	}
}
