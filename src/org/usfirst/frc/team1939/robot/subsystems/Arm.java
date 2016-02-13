package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.arm.ArmGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends CANTalonSubsystem {

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	public static final int IN = 0;
	public static final int OUT = 0;

	private CANTalon roller = new CANTalon(RobotMap.talonArmRoller);

	private DigitalInput banner = new DigitalInput(RobotMap.armBannerSensor);

	public Arm() {
		super(new CANTalon(RobotMap.talonArmMover), P, I, D, rampRate, false);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArmGamepadControl());
	}

	public void spinRoller(double speed) {
		this.roller.set(speed);
	}

	public boolean hasBoulder() {
		return this.banner.get();
	}

}
