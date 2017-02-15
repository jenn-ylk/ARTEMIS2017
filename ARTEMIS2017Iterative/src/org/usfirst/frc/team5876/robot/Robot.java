package org.usfirst.frc.team5876.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	
	RobotDrive robotDrive;
	Joystick stick;
	Joystick gamepad;
	SpeedController climbBack;
	SpeedController climbFront;
	SendableChooser<String> chooser = new SendableChooser<>();
	ADXRS450_Gyro gyro;
	Timer timer;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		stick = new Joystick(0);
		gamepad = new Joystick(1);
		
		
		SpeedController driveLeftFront = new VictorSP(0);
		SpeedController driveLeftBack = new VictorSP(1);
		SpeedController driveRightFront = new VictorSP(2);
		SpeedController driveRightBack = new VictorSP(3);
		climbFront = new VictorSP(4);
		climbBack = new VictorSP(5);
		
		timer = new Timer();
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		robotDrive = new RobotDrive(driveLeftFront, driveLeftBack, driveRightFront, driveRightBack);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
		gyro.reset();
		
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		System.out.println(gyro.getAngle());
		
		/*if (timer.get() < 1.5) {
			robotDrive.arcadeDrive(0.5, 0);
		}
		else if (timer.get() < 2.5) {
			if (gyro.getAngle() > -43) {
				robotDrive.arcadeDrive(0, 0.5); // turn left
			}
			else if (gyro.getAngle() < -47) {
				robotDrive.arcadeDrive(0, -0.5); // turn right
			}
			else {
				robotDrive.arcadeDrive(-0.5, 0);
			}
		}
		else {
			robotDrive.arcadeDrive(0, 0);
		}
	*/
		/*switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		robotDrive.arcadeDrive(stick.getRawAxis(1), -(stick.getRawAxis(0))); //using the joystick
		/*robotDrive.arcadeDrive(-(stick.getRawAxis(1)), stick.getRawAxis(4));*/ //using the gamepad
		
		/*climbFront.set(gamepad.getRawAxis(2));
		climbBack.set(gamepad.getRawAxis(2));*/ //This was letting it move backwards
		climbFront.set(-java.lang.Math.abs(gamepad.getRawAxis(3)));
		climbBack.set(-java.lang.Math.abs(gamepad.getRawAxis(3)));		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void kittAuto() {
		if (timer.get() < 1.5) {
			if (gyro.getAngle() == 0) {
				robotDrive.drive(-0.5, 0);
			}
		}
		else if (timer.get() < 2.5) {
			while (gyro.getAngle() > -47 && gyro.getAngle() < -43) {
				if (gyro.getAngle() > -43) {
					robotDrive.drive(0, 0.5); // turn left
				}
				else {
					robotDrive.drive(0, -0.5); // turn right
				}
			robotDrive.drive(-0.5, 0);
			}
		}
		else {
			robotDrive.drive(0, 0);
		}
		
	}
}

