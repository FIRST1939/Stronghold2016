package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ScalerLifter extends Subsystem {

	private static final double P = 0.3;
	private static final double I = 0;
	private static final double D = 0;

	public static final int UP = 0;
	public static final int DOWN = 0;

	private CANTalon talon = new CANTalon(RobotMap.talonScalerLifter);

	public ScalerLifter() {
		this.talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.talon.setPID(P, I, D);
		enablePositionMode();
	}

	@Override
	public void initDefaultCommand() {

	}

	public void setOutput(double speed) {
		this.talon.set(speed);
	}

	public void setPosition(int position) {
		this.talon.set(position);
	}

	public void enableThrottleMode() {
		this.talon.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void enablePositionMode() {
		this.talon.changeControlMode(TalonControlMode.Position);
	}

	public void enableControl() {
		this.talon.enableControl();
	}

	public void disableControl() {
		this.talon.disableControl();
	}
}
