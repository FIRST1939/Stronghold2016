package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.RobotMap;
import org.usfirst.frc.team1939.robot.commands.arm.ArmGamepadControl;
import org.usfirst.frc.team1939.util.CANTalonSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class Arm extends CANTalonSubsystem {

	public static final int UP = 0;
	public static final int DOWN = 160000;
	public static final double MAX = 0.3;

	private static final double P = 1.0 / DOWN;
	private static final double I = 0;
	private static final double D = 0;
	private static final double rampRate = 12;

	private CANTalon roller = new CANTalon(RobotMap.talonArmRoller);

	private DigitalInput banner = new DigitalInput(RobotMap.armBannerSensor);
	private DigitalInput limit = new DigitalInput(RobotMap.armLimitSwitch);

	private Relay magnet = new Relay(0);
	private boolean magnetOn = false;

	public Arm() {
		super(new CANTalon(RobotMap.talonArmMover), P, I, D, rampRate, MAX, true);
		setMagnet(false);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArmGamepadControl());
	}

	public void spinRoller(double speed) {
		this.roller.set(speed);
	}

	public boolean hasBoulder() {
		return !this.banner.get();
	}

	public boolean isDown() {
		return !this.limit.get();
	}

	public boolean isMagnetOn() {
		return this.magnetOn;
	}

	public void setMagnet(boolean on) {
		if (on) {
			this.magnetOn = true;
			this.magnet.set(Value.kForward);
		} else {
			this.magnetOn = false;
			this.magnet.set(Value.kOff);
		}
	}

}
