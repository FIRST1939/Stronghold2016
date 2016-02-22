package org.usfirst.frc.team1939.util;

import org.usfirst.frc.team1939.robot.Robot;
import org.usfirst.frc.team1939.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class LEDs implements Runnable {

	private DigitalOutput one = new DigitalOutput(RobotMap.ledsOne);
	private DigitalOutput two = new DigitalOutput(RobotMap.ledsTwo);
	private DigitalOutput three = new DigitalOutput(RobotMap.ledsThree);

	@Override
	public void run() {
		while (true) {
			if (Robot.robot.isDisabled()) {
				this.one.set(false);
				this.two.set(false);
				this.three.set(true);
			} else if (DriverStation.getInstance().getMatchTime() >= 130) {
				this.one.set(true);
				this.two.set(true);
				this.three.set(true);
			} else if (Robot.robot.isAutonomous()) {
				this.one.set(false);
				this.two.set(true);
				this.three.set(false);
			} else if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
				if (Robot.arm.hasBoulder()) {
					this.one.set(true);
					this.two.set(false);
					this.three.set(true);
				} else {
					this.one.set(false);
					this.two.set(true);
					this.three.set(true);
				}
			} else {
				if (Robot.arm.hasBoulder()) {
					this.one.set(true);
					this.two.set(true);
					this.three.set(false);
				} else {
					this.one.set(true);
					this.two.set(false);
					this.three.set(false);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte writeBit(byte b, int value, int pos) {
		return (byte) (b | value << pos);
	}

}
