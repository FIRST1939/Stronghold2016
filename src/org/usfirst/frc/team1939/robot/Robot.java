
package org.usfirst.frc.team1939.robot;

import org.usfirst.frc.team1939.robot.commands.auton.DoNothing;
import org.usfirst.frc.team1939.robot.commands.auton.LowBar;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarScore;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarScoreReturn;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarSpitReturn;
import org.usfirst.frc.team1939.robot.commands.auton.LowBarSpitReturnTurn;
import org.usfirst.frc.team1939.robot.commands.auton.Ramparts;
import org.usfirst.frc.team1939.robot.commands.auton.RockWall;
import org.usfirst.frc.team1939.robot.commands.auton.RockWallLowGoal;
import org.usfirst.frc.team1939.robot.commands.drivetrain.TurnByDegrees;
import org.usfirst.frc.team1939.robot.subsystems.Arm;
import org.usfirst.frc.team1939.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1939.robot.subsystems.SmartDashboardSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Arm arm;
	public static Drivetrain drivetrain;
	public static SmartDashboardSubsystem dashboard;

	{
		try {
			arm = new Arm();
			drivetrain = new Drivetrain();
			dashboard = new SmartDashboardSubsystem();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		this.autonomousChooser.addObject("Low Bar Spit Return", new LowBarSpitReturn());
		this.autonomousChooser.addObject("Low Bar Spit Return Turn", new LowBarSpitReturnTurn());
		this.autonomousChooser.addObject("Rock Wall", new RockWall());
		this.autonomousChooser.addObject("Rock Wall Low Goal", new RockWallLowGoal());
		this.autonomousChooser.addObject("Ramparts", new Ramparts());
		this.autonomousChooser.addObject("Turn 60", new TurnByDegrees(60));
		this.autonomousChooser.addObject("Turn 90", new TurnByDegrees(90));
		this.autonomousChooser.addObject("Turn 120", new TurnByDegrees(120));
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
