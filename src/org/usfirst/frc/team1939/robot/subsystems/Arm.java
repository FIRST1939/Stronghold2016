package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	private static final double P = 0.3; // Change this number
	private static final double I = 0;
	private static final double D = 0;

	public static final int IN = 0; // Some Number
	public static final int OUT = 0; // Some Number

	private CANTalon roller = new CANTalon(RobotMap.talonArmRoller);
	private CANTalon mover = new CANTalon(RobotMap.talonArmMover);

	public Arm() {
		this.roller.changeControlMode(TalonControlMode.PercentVbus);
		enablePositionMode();
	}

	@Override
	public void initDefaultCommand() {
		// Need to make a Joystick controller
	}

	public void spinRoller(double speed) {
		this.roller.set(speed);
	}

	public void setPosition(int position) {
		this.mover.set(position);
	}

	public void enableThrottleMode() {
		this.mover.changeControlMode(TalonControlMode.PercentVbus);
		this.mover.set(0);
		this.mover.setPID(0, 0, 0);
	}

	public void enablePositionMode() {
		this.mover.changeControlMode(TalonControlMode.Position);
		this.mover.set(0);
		this.mover.setPID(P, I, D);
	}

	public void enableControl() {
		this.mover.enableControl();
	}

	public void disableControl() {
		this.mover.disableControl();
	}
}
