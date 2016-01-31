package org.usfirst.frc.team1939.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class CANTalonSubsystem extends Subsystem {

	private CANTalon talon;

	public CANTalonSubsystem(CANTalon talon, double P, double I, double D, double rampRate, boolean invertEncoder) {
		this.talon = talon;
		this.talon.setPID(P, I, D);
		this.talon.setVoltageRampRate(rampRate);
		this.talon.setCloseLoopRampRate(rampRate);
		this.talon.reverseSensor(invertEncoder);

		this.talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.talon.enableBrakeMode(true);
	}

	public void setOutput(double speed) {
		this.talon.enable();
		this.talon.changeControlMode(TalonControlMode.PercentVbus);
		this.talon.set(speed);
	}

	public void setPosition(int position) {
		this.talon.enable();
		this.talon.changeControlMode(TalonControlMode.Position);
		this.talon.set(position);
	}

	public double getSpeed() {
		return this.talon.getSpeed();
	}

	public void resetEncoder() {
		this.talon.setPosition(0);
	}

	public void disable() {
		this.talon.disable();
	}

}
