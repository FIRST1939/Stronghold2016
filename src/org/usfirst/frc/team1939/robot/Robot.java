
package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.DoNothing;
import org.usfirst.frc.team1939.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();

	public static Robot robot;
	public static OI oi;
	public static AHRS navx;
	public static CameraServer cameraServer;

	private Command autonomousCommand;
	private SendableChooser autonomousChooser;

	@Override
	public void robotInit() {
		System.out.println("/n==========Intializing Stronghold2016==========");
		robot = this;
		oi = new OI();

		System.out.println("Intializing navX");
		try {
			navx = new AHRS(SerialPort.Port.kMXP);
		} catch (Exception e) {
			System.out.println("ERROR: Couldn't intialize navX");
			e.printStackTrace();
		}

		System.out.println("Intialzing Camera");
		try {
			cameraServer = CameraServer.getInstance();
			cameraServer.setQuality(50);
			cameraServer.startAutomaticCapture("cam0");
		} catch (Exception e) {
			System.out.println("ERROR: Couldn't intialize camera");
			e.printStackTrace();
		}

		System.out.println("Intializing Smartdashboard");
		Command[] commands = { new ResetGyro() };
		for (Command c : commands) {
			SmartDashboard.putData(c);
		}

		System.out.println("Intializing Autonomous Commands");
		this.autonomousChooser = new SendableChooser();
		Command[] autonomous = {};
		for (Command c : autonomous) {
			this.autonomousChooser.addObject(c.getName(), c);
		}
		this.autonomousChooser.addDefault("Do Nothing", new DoNothing());
		SmartDashboard.putData("Autonomous Chooser", this.autonomousChooser);

		System.out.println("==============Finished Intializing============\n");
	}

	@Override
	public void disabledInit() {
		System.out.println("Robot Disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println("Started Autonomous");
		this.autonomousCommand = (Command) this.autonomousChooser.getSelected();

		if (this.autonomousCommand != null) {
			this.autonomousCommand.start();
			System.out.println("Ran Autonomus: " + this.autonomousCommand.getName());
		} else {
			System.out.println("Didn't find an Autonomous to run");
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("Started Teleop");
		if (this.autonomousCommand != null)
			this.autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
