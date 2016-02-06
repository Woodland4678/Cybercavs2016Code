// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.Cybercavs2016Code.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4678.Cybercavs2016Code.Robot;

/**
 *
 */
public class SpitOut extends Command {
	
	int count = 0;
	int spitState;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SpitOut() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickupArm);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	spitState = 0;
    	Robot.pickupArm.setPickupWheelsMode(7);
		Robot.pickupArm.setElbowMode(5);
		Robot.pickupArm.setWristMode(5);
		count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 	
    	switch (spitState) {
		case 0:
			Robot.pickupArm.setElbowPosition(Robot.spitOutElbowPosition());
			if (count > 15 && Robot.pickupArm.getElbowPosition() < 65000) {		
				Robot.pickupArm.pickupSpeed(-1);
				Robot.pickupArm.wristSpitOutPosition();
			}
			if (count > 30) {
				spitState++;
				count = 0;
			}
			
		break;
		case 1:
			System.out.println("In case 1!!!!!!!");
			System.out.println("Elbow position: " + Robot.pickupArm.getElbowPosition());
			Robot.pickupArm.setElbowPosition(Robot.restElbowPosition());
			if (count > 100) {
				spitState++;
			}
		break;
		case 2:
		break;
		}
		count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}