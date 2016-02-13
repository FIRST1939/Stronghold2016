package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.commands.smartdashboard.SmartDashboardUpdater;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class SmartDashboardSubsystem extends Subsystem {

	public SendableChooser gamepadControlMode = new SendableChooser();

	public SmartDashboardSubsystem() {
	}

	@Override
	public void initDefaultCommand() {
		Command command = new SmartDashboardUpdater();
		command.setRunWhenDisabled(true);
		setDefaultCommand(command);
	}
}
