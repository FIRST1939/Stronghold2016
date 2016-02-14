package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.scaler.ScalerLifterGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;

public class ScalerLifter extends CANTalonSubsystem {

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	public static final int UP = 0;
	public static final int DOWN = 0;
	public static final double MAX = 0.5;

	public ScalerLifter() {
		super(new CANTalon(RobotMap.talonScalerLifter), P, I, D, rampRate, MAX);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ScalerLifterGamepadControl());
	}

}
