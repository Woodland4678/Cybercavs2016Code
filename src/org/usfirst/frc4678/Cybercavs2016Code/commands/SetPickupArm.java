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
public class SetPickupArm extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SetPickupArm() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickupArm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.pickupArm.setPickupWheelsMode(7);
		Robot.pickupArm.setElbowMode(5);
		Robot.pickupArm.setWristMode(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { //This is the default command of the pickup arm, it will always run, the commands simply change the mode which this runs
    	if ((Robot.pickupArm.getArmMode()) == ("Pickup")) {
    		Robot.pickupArm.pickup();
    	}
    	else if ((Robot.pickupArm.getArmMode()) == ("Hold")) {
    		Robot.pickupArm.holdPosition();
    	}
    	else if ((Robot.pickupArm.getArmMode()) == ("spitOut")) {
    		Robot.pickupArm.spitOut();
    	}
    	else if ((Robot.pickupArm.getArmMode()) == ("lowBar")) {
    		Robot.pickupArm.lowBarPosition();
    	}
    	else if ((Robot.pickupArm.getArmMode()) == ("ShootMode")) {
    		Robot.pickupArm.readyToShoot();
    	}
    	else if (Robot.pickupArm.getArmMode() == ("AlternateSpit")) {
    		Robot.pickupArm.setPickupWheels(-Robot.pickupWheelsPower());
    	}
    	
    	else {//dicks
//    		Robot.pickupArm.setArmMode("Hold");
    	}
    	if (Robot.oi.getOperatorGamepad().getRawButton(9)) {
    		//Robot.pickupArm.setArmMode("AlternateSpit");
    		Robot.pickupArm.setPickupWheels(-Robot.pickupWheelsPower());
    	}
    	//System.out.println("Current Arm Mode: " + Robot.pickupArm.getArmMode());
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
