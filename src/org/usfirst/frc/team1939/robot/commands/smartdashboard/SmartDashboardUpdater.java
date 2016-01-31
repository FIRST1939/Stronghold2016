package org.usfirst.frc.team1939.robot.commands.smartdashboard;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.commands.arm.ResetArmEncoder;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetDrivetrainEncoders;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.scaler.ResetGrabberEncoder;
import org.usfirst.frc.team1939.robot.commands.scaler.ResetLifterEncoder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

	public SmartDashboardUpdater() {
		requires(Robot.dashboard);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putData(Scheduler.getInstance());
		Command[] commands = { new ResetGyro(), new ResetDrivetrainEncoders(), new ResetArmEncoder(),
				new ResetGrabberEncoder(), new ResetLifterEncoder() };
		for (Command c : commands) {
			SmartDashboard.putData(c);
		}
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Gyro", Robot.drivetrain.getAngle());
		SmartDashboard.putNumber("Drivetrain Distance", Robot.drivetrain.getPositon());
		SmartDashboard.putNumber("Arm Encoder", Robot.arm.getEncoder());
		SmartDashboard.putNumber("Lifter Encoder", Robot.lifter.getEncoder());
		SmartDashboard.putNumber("Grabber Encoder", Robot.grabber.getEncoder());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
