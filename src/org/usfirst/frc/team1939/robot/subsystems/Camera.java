package org.usfirst.frc.team1939.robot.subsystems;

import org.usfirst.frc.team1939.robot.commands.camera.CameraJoystick;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {

	private CameraServer cameraServer;
	private Servo servo;

	public Camera() {
		System.out.println("Intialzing Camera");
		try {
			this.cameraServer = CameraServer.getInstance();
			this.cameraServer.setQuality(50);
			this.cameraServer.startAutomaticCapture("cam0");
		} catch (Exception e) {
			System.out.println("ERROR: Couldn't intialize camera");
			e.printStackTrace();
		}
		this.servo = new Servo(0);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new CameraJoystick());
	}

	public void setPosition(double position) {
		this.servo.set(position);
	}
}
