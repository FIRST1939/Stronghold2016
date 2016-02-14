
package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.DoNothing;
import org.usfirst.frc.team1939.robot.commands.drivetrain.DriveByInches;
import org.usfirst.frc.team1939.robot.subsystems.Arm;
import org.usfirst.frc.team1939.robot.subsystems.Camera;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.ScalerGrabber;
import org.usfirst.frc.team1939.robot.subsystems.ScalerLifter;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Arm arm = new Arm();
	public static Camera camera = new Camera();
	public static Drivetrain drivetrain = new Drivetrain();
	public static ScalerGrabber grabber = new ScalerGrabber();
	public static ScalerLifter lifter = new ScalerLifter();
	public static SmartDashboardSubsystem dashboard = new SmartDashboardSubsystem();

	public static Robot robot;
	public static OI oi;

	private Command autonomousCommand;
	private SendableChooser autonomousChooser;

	@Override
	public void robotInit() {
		robot = this;
		oi = new OI();

		this.autonomousChooser = new SendableChooser();
		Command[] autonomous = { new DriveByInches(12) };
		for (Command c : autonomous) {
			this.autonomousChooser.addObject(c.getName(), c);
		}
		this.autonomousChooser.addDefault("Do Nothing", new DoNothing());

		SmartDashboard.putData("Autonomous Chooser", this.autonomousChooser);

		System.out.println("\n==============Intialized Stronghold2016============\n");
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
