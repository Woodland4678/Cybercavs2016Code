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
public class Position2 extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Position2() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.robotDrive);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.robotDrive.setTarget(-100, 100);
    	Robot.robotDrive.resetEncoders();
    }

    boolean result = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	result = Robot.robotDrive.findTarget();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return result;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.robotDrive.setRightMotor(0);
    	Robot.robotDrive.setLeftMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
