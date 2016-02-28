package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.winch.WinchGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class Winch extends CANTalonSubsystem {

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	public static final int DOWN = 0;
	public static final double MAX = 0.5;

	private Relay spike = new Relay(RobotMap.winchRelay);
	private boolean spikeOn;
	// On = Ratchet disengaged
	// Off = Ratchet engaged

	public Winch() {
		super(new CANTalon(RobotMap.talonScalerWinch), P, I, D, rampRate, MAX);
		setSpike(true);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new WinchGamepadControl());
		setSpike(false);
	}

	@Override
	public void setOutput(double speed) {
		if (!this.spikeOn) {
			speed = 0;
		}
		super.setOutput(speed);
	}

	public boolean isSpikeOn() {
		return this.spikeOn;
	}

	public void setSpike(boolean on) {
		if (on) {
			this.spikeOn = true;
			this.spike.set(Value.kForward);
		} else {
			this.spikeOn = false;
			this.spike.set(Value.kOff);
		}
	}

}
