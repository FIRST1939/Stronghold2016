package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	private CANTalon backLeft = new CANTalon(RobotMap.talonBackLeft);
	private CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	private CANTalon backRight = new CANTalon(RobotMap.talonBackRight);

	private RobotDrive drive = new RobotDrive(this.frontLeft, this.backLeft, this.frontRight, this.backRight);

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveByJoystick());
	}

	public void drive(double move, double rotate) {
		this.drive.arcadeDrive(move, rotate);
	}

	public double getGyro() {
		return Robot.navx.getAngle();
	}

	public void resetGyro() {
		Robot.navx.reset();
	}

}
