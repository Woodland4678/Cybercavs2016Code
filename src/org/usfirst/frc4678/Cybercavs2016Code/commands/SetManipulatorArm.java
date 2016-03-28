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
public class SetManipulatorArm extends Command {
	int autoDefense = 0;
	double operatorJoystickX = 0;
	double operatorJoystickY = 0;
	boolean holdWristPos = true;
	boolean holdElbowPos = true;
	double elbowPosition = 0;
	double wristPosition = 0;
	double wristAngle = 0;
	double elbowAngle = 0;
    double wristAnglelimit = 0;
    double acosof = 0;
    double invalidwristrangemin = 0;
    double invalidwristrangemax = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SetManipulatorArm() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.manipulatorArm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.oi.getAutoSwitch().getRawButton(16)) {
    		autoDefense += 1;
    	}
		Robot.manipulatorArm.setManipulatorElbowMode(5);
		Robot.manipulatorArm.setManipulatorWristMode(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.operatorGamepad.getPOV() == 0) {
    		Robot.manipulatorArm.setManipulatorMode("StraightUp");
    	}
    	operatorJoystickX = Robot.oi.getOperatorGamepad().getX();
    	operatorJoystickY = Robot.oi.getOperatorGamepad().getY();
    	if (Math.abs(operatorJoystickX) > 0.01) {
    		Robot.manipulatorArm.setManipulatorWristMode(4);
    		Robot.manipulatorArm.setManipulatorMode("Manual");
    		holdWristPos = false;
    		wristPosition = Robot.manipulatorArm.getManipulatorWristPosition();
    	}
    	if (Math.abs(operatorJoystickY) > 0.01) {
    		Robot.manipulatorArm.setManipulatorElbowMode(4);
    		Robot.manipulatorArm.setManipulatorMode("Manual");
    		holdElbowPos = false;
    		elbowPosition = Robot.manipulatorArm.getManipulatorElbowPosition();
    	}
    	if (Robot.manipulatorArm.getManipulatorMode() == "ShootMode") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.readyToShoot();
    	}
    	else if(Robot.manipulatorArm.getManipulatorMode() == "Rest") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.restMode();
    	}
    	else if(Robot.manipulatorArm.getManipulatorMode() == "Portcullis") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.portcullis1();
    	}
    	else if(Robot.manipulatorArm.getManipulatorMode() == "AfterShoot") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.afterShoot();
    	}
    	else if(Robot.manipulatorArm.getManipulatorMode() == "AfterShoot") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.afterShoot();
    	}
    	else if(Robot.manipulatorArm.getManipulatorMode() == "StraightUp") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.straightUp();
    	}
    	else if (Robot.manipulatorArm.getManipulatorMode() == "SallyPort") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.sallyPort();
    	}
    	else if (Robot.manipulatorArm.getManipulatorMode() == "ExitSallyPort") {
    		Robot.manipulatorArm.setManipulatorElbowMode(5);
    		Robot.manipulatorArm.setManipulatorWristMode(5);
    		Robot.manipulatorArm.exitSallyPort();
    	}
    	if (Robot.manipulatorArm.getManipulatorMode() == "Manual") {
    		if(operatorJoystickY > 0.4) {
    			operatorJoystickY = 0.4;
    		}
    		if(operatorJoystickX > 0.4) {
    			operatorJoystickX = 0.4;
    		}
    		if(operatorJoystickY < -0.4) {
    			operatorJoystickY = -0.4;
    		}
    		if(operatorJoystickX < -0.4) {
    			operatorJoystickX = -0.4;
    		}
    		if (Math.abs(operatorJoystickX) < 0.01 && holdWristPos == false) {    			
    			Robot.manipulatorArm.setManipulatorWristMode(5);
        		holdWristPos = true;
    		}
    		if (Math.abs(operatorJoystickY) < 0.01 && holdElbowPos == false) {
    			Robot.manipulatorArm.setManipulatorElbowMode(5);
    			holdElbowPos = true;
    		}
    		elbowAngle = ((elbowPosition - 4000)/27379.75); // Get elbow angle in Radians
    		wristAngle = ((Robot.manipulatorWristRestPosition() - wristPosition)/ 11589); // Get wrist angle in radians
            // When wrist angle < 0.28, elbow angle must be < 0.73.  This changes to ensure we miss the bumper
            // in that wrist angle at 0.56 will miss the bumper when elbow is at 1.5 (nearly 90 deg straight up).
            // a linear relationship could be used here.  If Elbow is moving up, wrist min angle can be calculated
            // as (elbowAngle - 0.73) * 0.363636 + 0.28.  While moving elbow manually, check if wrist position is
            // less than this angle.  If so, set wrist position by getting wristPositionCalc from this formula
            // wristPositionCalc = 2842 - wristAngle * 11589
            // Note that when elbowAngle > 1.5, then wristAngle minimum will be 0.56.
            // elbowPositionCalc = elbowAngle * 27370.75 + 4000
            // When elbow is moving down, the wrist angle should not be a problem since it will be kept
            // at or above 0.56 until the elbow is back below the 1.5 radians angle.
            
            if ((wristAngle <= 0.01)&&(operatorJoystickX < 0))
                operatorJoystickX = 0; // Prevent wrist from moving when angle is at or very near 0
            if ((wristAngle >= 3.1415)&&(operatorJoystickX > 0))
                operatorJoystickX = 0; // And no further out than 180 degrees
            if ((elbowAngle <= -0.02)&&(operatorJoystickY > 0))
                operatorJoystickY = 0; // Limit downward elbow when we are just below the 0 degree point
            if ((elbowAngle >= 2.5)&&(operatorJoystickY < 0))
                operatorJoystickY = 0; // Limit outward Elbow Position to about 135 degrees
            
            // When elbow is requested to go up, stop at 0.73 till wrist exceeds wristAnglelimit
            // when elbow is going down, limit wrist at wristAnglelimit till elbow is below 0.73.
            wristAnglelimit = 0.28; // calculate a lower wrist Angle limit based on the current elbow position.
            if (elbowAngle < 0.73)
                wristAnglelimit = 0; // no lower limit when elbowAngle is less than 0.73
            else if (elbowAngle < 1.5)  // Use formula to get wristAnglelimit
                wristAnglelimit = (elbowAngle - 0.73)*0.36363636 + 0.28;
            else // elbowAngle > 1.5, use 0.56 as the limit at all other elbow angles
                wristAnglelimit = 0.56;
             
            if ((operatorJoystickY < -0.01)&&(wristAngle < wristAnglelimit)) // Prevent elbow from moving up till wristAngle position is ok
                operatorJoystickY = 0; // NEED TO CHECK SIGN OF operatorJoystickY.  It may be negative that we need to check for.
            
            if ((operatorJoystickX < -0.01)&&(wristAngle < wristAnglelimit)) // Prevent wrist from moving down till Elbow position is ok for it.
                operatorJoystickX = 0; // Maybe check sign of this one too.
            
            // sin and cos math can be used to determine x and y positions relative to the pivot point.
            // elbow is 16.5" between pivot points and wrist is 18" from pivot to arm end point.
            // x position (distance from elbow pivot out towards the front of the robot) can be calculated as 
            // -15.6 * cos(elbowAngle) + 18.5 * cos(elbowAngle - wristAngle)
            // y position (distance up from elbow pivot to end of wrist)
            // 16.5 * sin(elbowAngle) + 18.5 * sin(elbowAngle - wristAngle)
            // We will want to keep the x position < 17" or so to ensure we don't extend beyond the robot frame
            // to find the acceptable wrist angle, use this calculation: (The 17 may be changed to give more or less distance to elbow pivot)
            acosof = (16.5*Math.cos(elbowAngle) + 17) / 18.5;
            if ((acosof >= -1)&&(acosof <= 1)) { // when acoseof is in the range of -1 to 1, we have a possible wrist angle range to avoid
                invalidwristrangemin = elbowAngle - Math.acos(acosof); // Calculate start of angle we need to keep wrist out of
                invalidwristrangemax = elbowAngle + Math.acos(acosof); // Calculate end of angle we need to keep wrist out of
                if ((wristAngle > invalidwristrangemin)&&(wristAngle < invalidwristrangemax)) { // need to get wrist out of invalid range
                    if (wristAngle - invalidwristrangemin < invalidwristrangemax - wristAngle) { // See which one we're closer to
                        wristPosition = Robot.manipulatorWristRestPosition() - invalidwristrangemin * 11589; // set position to the min location
    		         	Robot.manipulatorArm.setManipulatorWristMode(5); // Go to Position Mode
        	         	holdWristPos = true;
                        }
                    else {
                    	wristPosition = Robot.manipulatorWristRestPosition() - invalidwristrangemax * 11589; // Set position to the max location
               			Robot.manipulatorArm.setManipulatorWristMode(5); // Go to Position Mode
        	         	holdWristPos = true;
                    }
                }
            }
    		if (holdWristPos == true) {
    			Robot.manipulatorArm.setManipulatorWrist(wristPosition);
    		}
    		else {
    			Robot.manipulatorArm.setManipulatorWrist(-operatorJoystickX);
    		}
    		if (holdElbowPos == true) {
    			Robot.manipulatorArm.setManipulatorElbow(elbowPosition);
    		}  		
    		else {
    			Robot.manipulatorArm.setManipulatorElbow(-operatorJoystickY);
    		}
    	}
    	//System.out.println("IN SET MANIPULATOR ARM!!!!"+ ", " + "Angular wrist: " + Robot.manipulatorArm.getManipulatorWristAngular() + ", " + "Wrist encoder: " + Robot.manipulatorArm.getManipulatorWristPosition());
    	//System.out.println("Auto defense: " + autoDefense);
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
