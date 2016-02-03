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
import org.usfirst.frc4678.Cybercavs2016Code.commands.*;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Catapult extends Subsystem {

	public int shooterState = 0;
	int count = 0;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController winchMotor = RobotMap.catapultwinchMotor;
    private final Servo latchServo = RobotMap.catapultlatchServo;
    private final AnalogPotentiometer winchPosition = RobotMap.catapultwinchPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void shootBoulder() {
		switch (shooterState) {
		case 0:
			if (count < 25) {
				latchServo.setAngle(Robot.latchShootPosition());
			} else {
				shooterState++;
			}
			count++;
			break;
		case 1:
			if (windWinch() == true) {
				shooterState++;
			}
			break;
		case 2:
			if (unwindWinch() == true) {
				shooterState++;
			}
			break;
		case 3:
			shooterState = 0;
			break;
		}
	}

	// Greater than and Less than might be changed around
	public boolean windWinch() {
		latchServo.setAngle(Robot.latchReadyPosition());
		if (Robot.winchWoundDistance() < winchPosition.get()) {
			winchMotor.set(Robot.winchWoundDistance());
			return false;
		} else {
			winchMotor.disable();
			return true;
		}
	}

	public boolean unwindWinch() {
		if (Robot.winchUnwoundDistance() > winchPosition.get()) {
			winchMotor.set(Robot.winchUnwoundDistance());
			return false;
		} else {
			winchMotor.disable();
			return true;
		}
	}
}
