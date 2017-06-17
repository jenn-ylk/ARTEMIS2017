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
	final Integer middleAuto = new Integer(0);
	final Integer rightAuto = new Integer(1);
	final Integer leftAuto = new Integer(2);
	Integer autoSelected;

	RobotDrive robotDrive;
	Joystick stick;
	Joystick gamepad;
	SpeedController climbBack;
	SpeedController climbFront;
	SpeedController shoot;
	SpeedController gear;
	SpeedController intake;
	SpeedController agitator;
	SendableChooser<Integer> chooser = new SendableChooser<Integer>();
	ADXRS450_Gyro gyro;
	Timer timer;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	 @Override
	 public void robotInit() {
		 chooser.addDefault("Middle Gear", middleAuto);
		 chooser.addObject("Right Gear", rightAuto);
		 chooser.addObject("Left Gear", leftAuto);
		 SmartDashboard.putData("Auto choices", chooser);
		 // chooser.addObject("Middle Gear", new Integer(0));
		 // chooser.addObject("Right", new Integer(1));  
		 stick = new Joystick(0);
		 gamepad = new Joystick(1);

		 SpeedController driveLeftFront = new VictorSP(0);
		 SpeedController driveLeftBack = new VictorSP(1);
		 SpeedController driveRightFront = new VictorSP(2);
		 SpeedController driveRightBack = new VictorSP(3);
		 climbFront = new VictorSP(4);
		 climbBack = new VictorSP(5);
		 shoot = new VictorSP(7);
		 gear = new VictorSP(8);
		 intake = new VictorSP(9);
		 agitator = new VictorSP(10);

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
		 while (isAutonomous() && isEnabled()){
			 double angle = gyro.getAngle();
			 double Kp = 0.03;
			 robotDrive.arcadeDrive(-1.0, -angle * Kp);
			 Timer.delay(0.01);
		 }
		 switch (autoSelected) {
		 case 1:
			 // Put custom auto code here
			 System.out.println(gyro.getAngle());
			 System.out.println("Auto 1");


			 if (timer.get() < 2.5) {
				 robotDrive.arcadeDrive(-0.5, 0);
			 } else if (timer.get() < 4.0) {
				 if (gyro.getAngle() > -44) {
					 System.out.println("need to turn left");
					 robotDrive.arcadeDrive(0.1, 0.5); // turn left
				 } else if (gyro.getAngle() < -46) {
					 System.out.println("need to turn right");
					 robotDrive.arcadeDrive(0.1, -0.5); // turn right
				 } else {
					 robotDrive.arcadeDrive(0.5, 0);
				 }
			 } else {
				 robotDrive.arcadeDrive(0, 0);
			 }
			 break;
		 case 2:
			 // Put custom auto code here
			 System.out.println(gyro.getAngle());
			 System.out.println("Auto 2");

			 if (timer.get() < 2.5) {
				 robotDrive.arcadeDrive(-0.5, 0);
			 } else if (timer.get() < 4.0) {
				 if (gyro.getAngle() < 44) {
					 System.out.println("need to turn right");
					 robotDrive.arcadeDrive(0.1, 0.5); // turn right
				 } else if (gyro.getAngle() > 46) {
					 System.out.println("need to turn left");
					 robotDrive.arcadeDrive(0.1, -0.5); // turn left
				 } else {
					 robotDrive.arcadeDrive(0.5, 0);
				 }
			 } else {
				 robotDrive.arcadeDrive(0, 0);
			 }
			 break;
		 case 0:
		 default:
			 // Put default auto code here
			 System.out.println(gyro.getAngle());
			 System.out.println("Auto 0");

			 if (timer.get() < 2.5) {
				 robotDrive.arcadeDrive(-0.5, 0);
			 }
			 break;
		 }
	 }

	 /**
	  * This function is called periodically during operator control
	  */
	 @Override
	 public void teleopPeriodic() {
		 System.out.println(gyro.getAngle());
		 /*
		  * if (stick.getRawButton(0) == true) { gyro.reset(); while
		  * (stick.getRawButton(0) == true) { driveForward(); }
		  */
		 // robotDrive.arcadeDrive((stick.getRawAxis(1)), -(stick.getRawAxis(4)),
		 // true); //using the gamepad
		 // }
		 int pov = stick.getPOV();
		 boolean button = gamepad.getRawButton(1);
		 float slow = 0.5f;
		 if (gamepad.getRawButton(2)) {
			 
		 }
		 if (pov != -1) {
			 switch (pov) {
			 case 0:
				 robotDrive.arcadeDrive(-slow, 0);
				 break;
			 case 45:
				 robotDrive.arcadeDrive(-slow, -slow);
				 break;
			 case 90:
				 robotDrive.arcadeDrive(0, -slow);
				 break;
			 case 135:
				 robotDrive.arcadeDrive(slow, 0);
				 break;
			 case 180:
				 robotDrive.arcadeDrive(slow, 0);
				 break;
			 case 225:
				 robotDrive.arcadeDrive(slow, slow);
				 break;
			 case 270:
				 robotDrive.arcadeDrive(0, slow);
				 break;
			 case 315:
				 robotDrive.arcadeDrive(slow, slow);
				 break;
			 }                                           
		 }


		 else {
			 robotDrive.arcadeDrive((stick.getRawAxis(1)), -(gamepad.getRawAxis(0)), true);
		 }

		 if(button==true){ //SHOOT
			 shoot.set((gamepad.getRawAxis(3)+1)/2);
		 }
		 else if(button==false){
			 shoot.set(0);
		 }

		
		 if (stick.getRawButton(1) == true){ //CLIMB
			 climbFront.set((stick.getRawAxis(3)+1)/2);

			 climbBack.set((stick.getRawAxis(3)+1)/2);
		 }
		 else {
			 climbFront.set(0);
			 climbBack.set(0);
		 }
		 if (gamepad.getRawButton(2)==true){ //GEAR
			 gear.set(0.4);
		 }
		 else {
			 gear.set(-0.4);
		 }
		 if(gamepad.getRawButton(3)==true){ //FUEL INTAKE
			 intake.set(1);
			 agitator.set(1);
		 }
		 else {
			 intake.set(0);
			 agitator.set(0);
		 }
		  
	 }

	 @Override
	 public void testPeriodic() {
	 }

}