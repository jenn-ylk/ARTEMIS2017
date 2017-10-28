package org.usfirst.frc.team5876.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;


	RobotDrive robotDrive;
	Joystick stick;

	
	 @Override
	 public void robotInit() {	 
		 
		 stick = new Joystick(0);
		 SpeedController driveLeftFront = new VictorSP(0);
		 SpeedController driveLeftBack = new VictorSP(1);
		 SpeedController driveRightFront = new VictorSP(2);
		 SpeedController driveRightBack = new VictorSP(3);

		 robotDrive = new RobotDrive(driveLeftFront, driveLeftBack, driveRightFront, driveRightBack);
	 }

	 @Override
	 public void autonomousInit() {
	 }

	
	 @Override
		public void autonomousPeriodic() {
			
			
		}
	 
	 
	 
	 
	 @Override
	 public void teleopPeriodic() {
		  
		robotDrive.arcadeDrive((stick.getRawAxis(1)), -(stick.getRawAxis(0)), true);
		 
	 }

	 @Override
	 public void testPeriodic() {
	 }

}
