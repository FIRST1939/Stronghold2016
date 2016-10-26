package org.usfirst.frc.team1939.robot.commands.smartdashboard;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.commands.arm.ResetArmEncoder;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetDrivetrainEncoders;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;

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
		Command[] commands = { new ResetGyro(), new ResetDrivetrainEncoders(), new ResetArmEncoder() };
		for (Command c : commands) {
			SmartDashboard.putData(c);
		}
		Robot.dashboard.gamepadControlMode.addDefault("Neither", "Neither");
		Robot.dashboard.gamepadControlMode.addObject("Dart", "Dart");
		Robot.dashboard.gamepadControlMode.addObject("Winch", "Winch");
		Robot.dashboard.gamepadControlMode.addObject("Arm", "Arm");
		SmartDashboard.putData("Gamepad Control Mode", Robot.dashboard.gamepadControlMode);
		SmartDashboard.putBoolean("Reset Everything", false);
	}

	@Override
	protected void execute() {
		try {
			SmartDashboard.putString("Selected Auto", Robot.robot.autonomousChooser.getSelected().getClass().getName());
			SmartDashboard.putNumber("Gyro", Robot.drivetrain.navx.pidGet());
			SmartDashboard.putNumber("Drivetrain Distance", Robot.drivetrain.getPosition());
			SmartDashboard.putNumber("Arm Encoder", Robot.arm.getTicks());
			SmartDashboard.putBoolean("Has Boulder", Robot.arm.hasBoulder());
			SmartDashboard.putBoolean("Arm Down", Robot.arm.isDown());
			SmartDashboard.putBoolean("Magnet Engaged", Robot.arm.isMagnetOn());

			if (SmartDashboard.getBoolean("Reset Everything")) {
				Robot.drivetrain.resetEncoders();
				Robot.drivetrain.navx.reset();
				Robot.arm.resetEncoder();
				SmartDashboard.putBoolean("Reset Everything", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
