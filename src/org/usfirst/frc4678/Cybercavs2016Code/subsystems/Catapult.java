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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Catapult extends Subsystem {
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	int shooterState = 0;
	int windState = 0;
	int count = 0;
	int previousShooterState = 0;
	int winchCurrentCount = 0;
	int winchPotFiledCount = 0;
	double MAX_WINCH_CURRENT = 15;
	double winchCurrent;
	boolean forceShoot = false;
	boolean winchPotFailed = false;
	

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
	winchCurrent = pdp.getCurrent(11);
	previousShooterState = shooterState;
	if (Robot.oi.getDriverGamepad().getRawButton(10)) {
		forceShoot = true;
	}
	// conditions to continue: pickup arm and manipulator arm out of the way, or its unwinding or its a force shoot
	if (((Robot.pickupArm.getElbowPosition() > 28000) && (Robot.manipulatorArm.getManipulatorElbowPosition() > 25000) && (Robot.manipulatorArm.getManipulatorMode() != "StraightUp")) || (shooterState > 1) || (forceShoot)) { //makes sure arm is out of the way before shooting
			switch (shooterState) {
			case 0:
				if (winchPosition.get() < (Robot.winchUnwoundDistance() - 0.1)) { //makes sure it is unwound before shooting
					shooterState = 3; //if not unwound go to unwound state
				}
				else {
					shooterState++;
					count = 0;
				}
				break;
			case 1: //releases the catapult and waits 1 second
				
				if (count < 25) {
					latchServo.set(Robot.latchShootPosition());
				} else {
					Robot.manipulatorArm.setManipulatorMode("AfterShoot");
					count = 0;
					shooterState++;
				}
				
				count++;
				break;
			case 2: //runs command to pull the catapult back down
				if (windWinch() == true) {
					if (Robot.pickupArm.getArmMode() != "Pickup") { //only moves arm if it isn't in pickup mode already
						Robot.pickupArm.setArmMode("Hold");
					}
					shooterState++;
				}
				break;
			case 3: //runs command to unwind winch once catapult is locked in
				if (unwindWinch() == true) {
					Robot.manipulatorArm.setManipulatorMode("Rest");
					shooterState++;
				}
				break;
			case 4: //resets shooter state and ends the function 
				shooterState = 0;
				forceShoot = false;
				return true;
			}
		}
		else {
			winchMotor.disable();
			return true;
		}
		//System.out.println("Catapult case: " + shooterState + "previous state: " + previousShooterState);
		return false;
		
	}

	// Greater than and Less than might be changed around
	public boolean windWinch() {
		winchCurrent = pdp.getCurrent(11);
		latchServo.set(Robot.latchReadyPosition());
		switch(windState) {
		case 0: //continuously checks until the limit switch is no longer clicked
			//!checkLatchSwitch.get() means it is false, which means it is clicked in
			if ((Robot.winchWoundDistance() > winchPosition.get())) {
				winchMotor.disable();
				windState = 0;
				winchCurrentCount = 0;
				return true; 
			}
			if(winchCurrentCount > 2) {//check if winch current gets above max value, if so disable motor set windstate to 0 and return true
				winchPotFiledCount++;
				windState = 0;
				winchMotor.disable();
				winchPotFailed = true;
				winchCurrentCount = 0;
				return true;
			}
			if ((winchCurrent >= MAX_WINCH_CURRENT) && (winchPosition.get() < Robot.winchUnwoundDistance() - 1)) {
				winchCurrentCount++;
			}
			if (winchPosition.get() < Robot.winchWoundDistance() + 0.25) {
				winchMotor.set(Robot.winchPower() * 0.5);
			} else {
				winchMotor.set(Robot.winchPower());
			}
//			else if (!checkLatchSwitch.get()) {
//				winchMotor.set(Robot.winchPower()); //pulls catapult down until switch unclicked
//			}
//			else if ((checkLatchSwitch.get())) {
//				windState++;
//			}
			//System.out.println("case 0 winch pos: " + winchPosition.get() + "target: " + Robot.winchWoundDistance());
			break;
//		case 1: //Starts checking until switch is clicked again (which means the catapult is in place)
//			if ((Robot.winchWoundDistance() > winchPosition.get())) {
//				winchMotor.disable();
//				windState = 0;
//				return true; 
//			}
////			else if (checkLatchSwitch.get()) {
////				winchMotor.set(Robot.winchPower());
////			}		
////			else if (!checkLatchSwitch.get()) { //once clicked the catapult is in place and winding in is done.
////				winchMotor.disable();
////				windState = 0;
////				return true; 
////			}
//			//System.out.println("case 1 winch pos: " + winchPosition.get()  + "target: " + Robot.winchWoundDistance());
//			break;
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
	public boolean getWinchPotFailed() {
		return winchPotFailed;
	}
	public double getWinchCurrent() {
		return winchCurrent;
	}
	public double getWinchPosition() {
		return winchPosition.get();
	}
	public void setWinchMotor(double power) {
		winchMotor.set(power);
	}
	public void setLatchServo(double position) {
		latchServo.set(position);
	}
	public void setShooterState(int state) {
		shooterState = state;
	}
	public void setPreviousShooterState(int state) {
		previousShooterState = state;
	}
	public int getPreviousShooterState() {
		return previousShooterState;
	}
	public int getWinchPotFailedCount() {
		return winchPotFiledCount;
	}
	public int getShooterState(){
		return shooterState;
	}	
}
