package org.usfirst.frc.team1939.robot.commands.smartdashboard;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.commands.arm.ResetArmEncoder;
import org.usfirst.frc.team1939.robot.commands.dart.ResetDartEncoder;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetDrivetrainEncoders;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.commands.winch.DisengageRatchet;
import org.usfirst.frc.team1939.robot.commands.winch.EngageRatchet;
import org.usfirst.frc.team1939.robot.commands.winch.ResetWinchEncoder;

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
				new ResetWinchEncoder(), new ResetDartEncoder(), new EngageRatchet(), new DisengageRatchet() };
		for (Command c : commands) {
			SmartDashboard.putData(c);
		}
		Robot.dashboard.gamepadControlMode.addDefault("Neither", "Neither");
		Robot.dashboard.gamepadControlMode.addObject("Dart", "Dart");
		Robot.dashboard.gamepadControlMode.addObject("Winch", "Winch");
		Robot.dashboard.gamepadControlMode.addObject("Arm", "Arm");
		SmartDashboard.putData("Gamepad Control Mode", Robot.dashboard.gamepadControlMode);
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Gyro", Robot.drivetrain.navx.pidGet());
		SmartDashboard.putNumber("Drivetrain Distance", Robot.drivetrain.getPosition());
		SmartDashboard.putNumber("Arm Encoder", Robot.arm.getTicks());
		SmartDashboard.putNumber("Dart Encoder", Robot.dart.getTicks());
		SmartDashboard.putNumber("Winch Encoder", Robot.winch.getTicks());
		SmartDashboard.putBoolean("Has Boulder", Robot.arm.hasBoulder());
		SmartDashboard.putBoolean("Arm Down", Robot.arm.isDown());
		SmartDashboard.putBoolean("Magnet Engaged", Robot.arm.isMagnetOn());
		SmartDashboard.putBoolean("Ratchet Relay", Robot.winch.isSpikeOn());
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
