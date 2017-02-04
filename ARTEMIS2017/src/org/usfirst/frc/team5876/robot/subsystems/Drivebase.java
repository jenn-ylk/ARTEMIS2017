package org.usfirst.frc.team5876.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivebase extends Subsystem {

	//Motors
    SpeedController driveLeftFront = new VictorSP(0);
    SpeedController driveLeftBack = new VictorSP(1);
    SpeedController driveRightFront = new VictorSP(2);
    SpeedController driveRightBack = new VictorSP(3);
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

