package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private static final double rampRate = 36.0;
	private static final double INCHES_PER_REVOLUTION = 8 * Math.PI;
	private static final double PULSES_PER_REVOLUTION = 256 * 4 * (60.0 / 36.0);

	private CANTalon frontLeft = new CANTalon(RobotMap.talonFrontLeft);
	private CANTalon backLeft = new CANTalon(RobotMap.talonBackLeft);
	private CANTalon frontRight = new CANTalon(RobotMap.talonFrontRight);
	private CANTalon backRight = new CANTalon(RobotMap.talonBackRight);

	private RobotDrive drive = new RobotDrive(this.frontLeft, this.frontRight);

	public AHRS navx;

	private static final double turnP = 1.0 / 15;
	private static final double turnI = 0;
	private static final double turnD = 0;
	public PIDController turnPID;

	private static final double moveP = 1.0 / inchesToTicks(12);
	private static final double moveI = 0;
	private static final double moveD = 0;
	public PIDController movePID;

	public Drivetrain() {
		this.frontLeft.enableBrakeMode(true);
		this.backLeft.enableBrakeMode(true);
		this.frontRight.enableBrakeMode(true);
		this.backRight.enableBrakeMode(true);

		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		this.frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		this.frontRight.changeControlMode(TalonControlMode.PercentVbus);

		this.backLeft.changeControlMode(TalonControlMode.Follower);
		this.backLeft.set(RobotMap.talonFrontLeft);
		this.backRight.changeControlMode(TalonControlMode.Follower);
		this.backRight.set(RobotMap.talonFrontRight);

		this.frontLeft.setVoltageRampRate(rampRate);
		this.backLeft.setVoltageRampRate(rampRate);
		this.frontRight.setVoltageRampRate(rampRate);
		this.backRight.setVoltageRampRate(rampRate);

		this.drive.setSafetyEnabled(true);
		this.drive.setExpiration(0.2);
		this.drive.setSensitivity(0.5);
		this.drive.setMaxOutput(1.0);

		try {
			this.navx = new AHRS(SerialPort.Port.kMXP);
		} catch (Exception e) {
			System.out.println("ERROR: Couldn't intialize navX");
			e.printStackTrace();
		}

		this.navx.setPIDSourceType(PIDSourceType.kDisplacement);
		this.turnPID = new PIDController(turnP, turnI, turnD, this.navx, new PIDOutput() {
			@Override
			public void pidWrite(double arg0) {
				// Do Nothing
			}
		});
		this.turnPID.setInputRange(-180, 180);
		this.turnPID.setContinuous(true);
		this.turnPID.setOutputRange(-0.8, 0.8);

		this.frontLeft.setPIDSourceType(PIDSourceType.kDisplacement);
		this.movePID = new PIDController(moveP, moveI, moveD, this.frontLeft, new PIDOutput() {
			@Override
			public void pidWrite(double arg0) {
				// Do Nothing
			}
		});
		this.movePID.setOutputRange(-0.75, 0.75);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveByJoystick());
	}

	public void drive(double move, double rotate) {
		this.drive.arcadeDrive(move, rotate);
	}

	public void resetEncoders() {
		this.frontLeft.setPosition(0);
		this.frontRight.setPosition(0);
	}

	public double getSpeed() {
		return (this.frontLeft.getSpeed() + this.frontRight.getSpeed()) / 2.0;
	}

	public int getTicks() {
		return (int) this.frontLeft.pidGet();
	}

	public double getPosition() {
		return ticksToInches(getTicks());
	}

	public static int inchesToTicks(double inches) {
		return (int) (inches / INCHES_PER_REVOLUTION * PULSES_PER_REVOLUTION);
	}

	public static double ticksToInches(int ticks) {
		return ticks / PULSES_PER_REVOLUTION * INCHES_PER_REVOLUTION;
	}

}
