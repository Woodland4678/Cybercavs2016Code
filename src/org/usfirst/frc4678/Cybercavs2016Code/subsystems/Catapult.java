// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4678.Cybercavs2016Code.subsystems;

import org.usfirst.frc4678.Cybercavs2016Code.Robot;
import org.usfirst.frc4678.Cybercavs2016Code.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Catapult extends Subsystem {

	public int shooterState = 0;
	int windState = 0;
	int count = 0;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController winchMotor = RobotMap.catapultwinchMotor;
    private final Servo latchServo = RobotMap.catapultlatchServo;
    private final AnalogPotentiometer winchPosition = RobotMap.catapultwinchPosition;
    private final DigitalInput checkLatchSwitch = RobotMap.catapultcheckLatchSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean shootBoulder() {
		switch (shooterState) {
		case 0: //releases the catapult and waits 1 second
			if (count < 50) {
				latchServo.set(Robot.latchShootPosition());
			} else {
				count = 0;
				shooterState++;
			}
			count++;
			break;
		case 1: //runs command to pull the catapult back down
			if (windWinch() == true) {
				shooterState++;
			}
			break;
		case 2: //runs command to unwind winch once catapult is locked in
			if (unwindWinch() == true) {
				shooterState++;
			}
			break;
		case 3: //resets shooter state
			shooterState = 0;
			return true;
		}
		return false;
	}

	// Greater than and Less than might be changed around
	public boolean windWinch() {
		latchServo.set(Robot.latchReadyPosition());
		switch(windState) {
		case 0: //continuously checks until the limit switch is no longer clicked
			//!checkLatchSwitch.get() means it is false, which means it is clicked in
			if (!checkLatchSwitch.get()) {
				winchMotor.set(Robot.winchPower()); //pulls catapult down until switch unclicked
			}
			else if (checkLatchSwitch.get()) {
				windState++;
			}
			break;
		case 1: //Starts checking until switch is clicked again (which means the catapult is in place)
			if (checkLatchSwitch.get()) {
				winchMotor.set(Robot.winchPower());
			}		
			else if (!checkLatchSwitch.get()) { //once clicked the catapult is in place and winding in is done.
				winchMotor.disable();
				windState = 0;
				return true; 
			}
			break;
		}
		return false;
	}

	public boolean unwindWinch() {
		latchServo.set(Robot.latchLockPosition()); //moves servo down more to make latch more secure
		if (Robot.winchUnwoundDistance() > winchPosition.get()) { //keeps going until winch reaches correct position
			winchMotor.set(-Robot.winchPower());
			return false;
		} else {
			winchMotor.disable();
			return true;
		}
	}
	public boolean getLatchSwitch() {
		return checkLatchSwitch.get();
	}
}
