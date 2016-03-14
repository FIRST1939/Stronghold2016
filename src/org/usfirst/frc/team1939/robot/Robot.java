
package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.DoNothing;
import org.usfirst.frc.team1939.robot.commands.auton.LowBar;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarScore;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarScoreReturn;
import org.usfirst.frc.team1939.robot.commands.auton.RockWall;
import org.usfirst.frc.team1939.robot.subsystems.Arm;
import org.usfirst.frc.team1939.robot.subsystems.Dart;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;
import org.usfirst.frc.team1939.robot.subsystems.Winch;
import org.usfirst.frc.team1939.util.LEDs;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static double width = 32;
	public static double length = 32;

	public static Arm arm = new Arm();
	public static Dart dart = new Dart();
	public static Drivetrain drivetrain = new Drivetrain();
	public static SmartDashboardSubsystem dashboard = new SmartDashboardSubsystem();
	public static Winch winch = new Winch();

	public static Robot robot;
	public static OI oi;

	private Command autonomousCommand;
	public SendableChooser autonomousChooser;

	@Override
	public void robotInit() {
		robot = this;
		oi = new OI();

		this.autonomousChooser = new SendableChooser();
		this.autonomousChooser.addObject("Low Bar", new LowBar());
		this.autonomousChooser.addObject("Low Bar and Score", new LowBarScore());
		this.autonomousChooser.addObject("Low Bar Score and Return", new LowBarScoreReturn());
		this.autonomousChooser.addObject("Rock Wall", new RockWall());
		this.autonomousChooser.addDefault("Do Nothing", new DoNothing());

		SmartDashboard.putData("Autonomous Chooser", this.autonomousChooser);

		Thread leds = new Thread(new LEDs());
		leds.start();

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
