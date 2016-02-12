package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.scaler.ScalerGrabberGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;

public class ScalerGrabber extends CANTalonSubsystem {

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	public static final int UP = 0;
	public static final int DOWN = 0;

	public ScalerGrabber() {
		super(new CANTalon(RobotMap.talonScalerGrabber), P, I, D, rampRate, false);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ScalerGrabberGamepadControl());
	}

}
