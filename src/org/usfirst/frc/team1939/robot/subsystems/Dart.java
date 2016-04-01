package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.dart.DartGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;

public class Dart extends CANTalonSubsystem {

	public static final int RELEASE = 0; // Measure this
	public static final double MAX = 1.0 / 3.0;

	private static final double P = RELEASE / 3.0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	// Postive down
	// Negative up

	public boolean isReleased = false;

	public Dart() {
		super(new CANTalon(RobotMap.talonScalerDart), P, I, D, rampRate, MAX);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DartGamepadControl());
	}

	public boolean isReleased() {
		return this.isReleased;
	}

}
