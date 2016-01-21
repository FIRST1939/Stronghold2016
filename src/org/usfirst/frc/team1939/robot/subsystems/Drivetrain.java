package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private static final double RAMPING = 4.0;
	private static final double INCHES_PER_REVOLUTION = 8 * Math.PI;
	private static final int PULSES_PER_REVOLUTION = 256 * 4;

	private static final double P = 0.3;
	private static final double I = 0;
	private static final double D = 0;

	private CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	private CANTalon backLeft = new CANTalon(RobotMap.talonBackLeft);
	private CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	private CANTalon backRight = new CANTalon(RobotMap.talonBackRight);

	private RobotDrive drive = new RobotDrive(this.frontLeft, this.frontRight);

	public Drivetrain() {
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		this.backLeft.changeControlMode(TalonControlMode.Follower);
		this.backLeft.set(RobotMap.talonFrontLeft);
		this.backRight.changeControlMode(TalonControlMode.Follower);
		this.backRight.set(RobotMap.talonBackRight);

		this.frontLeft.setVoltageRampRate(RAMPING);
		this.backLeft.setVoltageRampRate(RAMPING);
		this.frontRight.setVoltageRampRate(RAMPING);
		this.backRight.setVoltageRampRate(RAMPING);

		this.drive.setInvertedMotor(MotorType.kFrontRight, true);
		this.drive.setInvertedMotor(MotorType.kRearRight, true);
		this.drive.setSafetyEnabled(true);
		this.drive.setExpiration(0.1);
		this.drive.setSensitivity(0.5);
		this.drive.setMaxOutput(1.0);
	}

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

	public void enableThrottleMode() {
		this.frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		this.frontLeft.set(0);
		this.frontLeft.setPID(0, 0, 0);
		this.frontRight.changeControlMode(TalonControlMode.PercentVbus);
		this.frontRight.set(0);
		this.frontRight.setPID(0, 0, 0);
	}

	public void enablePositionMode() {
		this.frontLeft.changeControlMode(TalonControlMode.Position);
		this.frontLeft.set(0);
		this.frontLeft.setPID(P, I, D);
		this.frontRight.changeControlMode(TalonControlMode.Position);
		this.frontRight.set(0);
		this.frontRight.setPID(P, I, D);
	}

	public void enableControl() {
		this.frontLeft.enableControl();
		this.frontRight.enableControl();
	}

	public void disableControl() {
		this.frontLeft.disableControl();
		this.frontRight.disableControl();
	}

	public void setPosition(double inches) {
		this.frontLeft.set(inchesToTicks(inches));
		this.frontRight.set(inchesToTicks(inches));
	}

	public double getSpeed() {
		return (this.frontLeft.getSpeed() + this.frontRight.getSpeed()) / 2;
	}

	public double getPositon() {
		return (ticksToInches((int) this.frontLeft.get()) + ticksToInches((int) this.frontRight.get())) / 2;
	}

	private static int inchesToTicks(double inches) {
		return (int) (inches / INCHES_PER_REVOLUTION * PULSES_PER_REVOLUTION);
	}

	private static double ticksToInches(int ticks) {
		return ticks / PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
	}

}
