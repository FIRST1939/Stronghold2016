package org.usfirst.frc.team1939.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class CANTalonSubsystem extends Subsystem {

	public PIDController pid;
	private CANTalon talon;

	public CANTalonSubsystem(CANTalon talon, double P, double I, double D, double rampRate, double max) {
		this.talon = talon;
		this.talon.setVoltageRampRate(rampRate);
		this.talon.changeControlMode(TalonControlMode.PercentVbus);
		this.talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.talon.enableBrakeMode(true);

		this.talon.setPIDSourceType(PIDSourceType.kDisplacement);
		this.pid = new PIDController(P, I, D, talon, new PIDOutput() {
			@Override
			public void pidWrite(double arg0) {
				// Do Nothing
			}
		});
		this.pid.setOutputRange(-max, max);
	}

	public double getPIDOutput() {
		return this.pid.get();
	}

	public void setOutput(double speed) {
		this.talon.set(speed);
	}

	public double getSpeed() {
		return this.talon.getSpeed();
	}

	public double getTicks() {
		return this.talon.pidGet();
	}

	public void resetEncoder() {
		this.talon.setPosition(0);
	}

	public void setEncoderPosition(int position) {
		this.talon.setEncPosition(position);
	}

}
