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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PickupArm extends Subsystem {
	private static final int STOP = 5;
	private static final int RESET_WRIST_POSITION = 3;
	private static final int READY_FOR_WRIST_RESET = 2;
	private static final int RESET_ELBOW_POSITION = 1;
	private static final int INITILIAZTION = 0;
	public int calibrateState = 0;
	public int calibrateCount;
	public int previousElbowEncPosition = 0;
	public int previousWristEncPosition = 0;
	public int previousElbowEncPosition2 = 0;
	PowerDistributionPanel pdp = new PowerDistributionPanel();

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon pickupElbowMotor = RobotMap.pickupArmpickupElbowMotor;
	private final CANTalon pickupWristMotor = RobotMap.pickupArmpickupWristMotor;
	private final CANTalon pickupWheels = RobotMap.pickupArmpickupWheels;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public int getElbowPosition() {
		return pickupElbowMotor.getEncPosition();
	}

	public int getWristPosition() {
		return pickupWristMotor.getEncPosition();
	}

	public int getPickupWheelsPosition() {
		return pickupWheels.getEncPosition();
	}

	public void spinIntakeWheels() {
		System.out.println("Called spinIntakeWheels Function");
		pickupWheels.set(Robot.pickupWheelsPower());
	}

	public void stopIntakeWheels() {
		pickupWheels.disable();
	}

	public void rotateElbowToPickupPosition() {
		pickupElbowMotor.changeControlMode(TalonControlMode.Position);
		pickupElbowMotor.set(Robot.pickupElbowPosition());
	}

	public void rotateElbowToRestPosition() {
		pickupElbowMotor.changeControlMode(TalonControlMode.Position);
		pickupElbowMotor.set(Robot.restElbowPosition());
	}

	public void rotateWristToPickupPosition() {
		pickupWristMotor.changeControlMode(TalonControlMode.Position);
		pickupWristMotor.set(Robot.pickupWristPosition());
	}

	public void rotateWristToRestPosition() {
		pickupWristMotor.changeControlMode(TalonControlMode.Position);
		pickupWristMotor.set(Robot.restWristPosition());
	}

	public double getCurrent(int channel) {
		return pdp.getCurrent(channel);
	}

	public void pickupSpeed(int direction) {
		pickupWheels.set(direction * Robot.pickupWheelsPower());
	}

	public void elbowPickupPosition() {
		pickupElbowMotor.configPeakOutputVoltage(+7f, -7f);
		pickupElbowMotor.setPID(0.03, 0, 0);
		pickupElbowMotor.set(Robot.pickupElbowPosition());
	}

	public void wristPickupPosition() {
		pickupWristMotor.configPeakOutputVoltage(+4f, -4f);
		pickupWristMotor.setPID(0.03, 0, 0);
		pickupWristMotor.set(Robot.pickupWristPosition());
	}

	public void spitOutSpeed() {
		pickupWheels.set(-Robot.pickupWheelsPower());
	}

	public void elbowSpitOutPosition() {
		pickupElbowMotor.configPeakOutputVoltage(+7f, -7f);
		pickupElbowMotor.setPID(0.03, 0, 0);
		pickupElbowMotor.set(Robot.spitOutElbowPosition());
	}

	public void wristSpitOutPosition() {
		pickupWristMotor.configPeakOutputVoltage(+9f, -9f);
		pickupWristMotor.setPID(0.03, 0, 0);
		pickupWristMotor.set(Robot.spitOutWristPosition());
	}

	public void stopArmMotors() {
		pickupElbowMotor.disable();
		pickupWristMotor.disable();
		pickupWheels.disable();
	}

	public void calibratePickupArm() {
		switch (calibrateState) {
		case INITILIAZTION:
			calibrateCount = 0;
			calibrateState++;
			previousElbowEncPosition = 0;
			previousWristEncPosition = 0;
			previousElbowEncPosition2 = 0;
			break;
		case RESET_ELBOW_POSITION:
			pickupElbowMotor.changeControlMode(TalonControlMode.Voltage);
			pickupElbowMotor.set(-9);
			if (calibrateCount > 12) {
				int error = pickupElbowMotor.getEncPosition() - previousElbowEncPosition2;
				System.out.println("Elbow error: " + error);
				if (Math.abs(error) < 3) {
					pickupElbowMotor.setEncPosition(0);
					calibrateState++;
				}
			}
			calibrateCount++;
			previousElbowEncPosition2 = previousElbowEncPosition;
			previousElbowEncPosition = pickupElbowMotor.getEncPosition();
			break;
		case READY_FOR_WRIST_RESET:
			pickupElbowMotor.changeControlMode(TalonControlMode.Position);
			pickupElbowMotor.configPeakOutputVoltage(+9f, -9f);
			pickupElbowMotor.setPID(0.03, 0, 0);
			// pickupElbowMotor.setAllowableClosedLoopErr(5000);
			pickupElbowMotor.set(Robot.restElbowPosition());
			calibrateCount++;
			if (calibrateCount > 50) {
				calibrateState++;
				calibrateCount = 0;
			}
			break;
		case RESET_WRIST_POSITION:
			previousWristEncPosition = pickupWristMotor.getEncPosition();
			pickupWristMotor.changeControlMode(TalonControlMode.Voltage);
			pickupWristMotor.set(5);
			if (calibrateCount > 12) {
				if (Math.abs(pickupWristMotor.getEncPosition() - previousWristEncPosition) < 2) {
					pickupWristMotor.setEncPosition(0);
					calibrateState++;
				}
			}
			calibrateCount++;
			break;
		case 4:
			pickupWristMotor.changeControlMode(TalonControlMode.Position);
			pickupWristMotor.configPeakOutputVoltage(+4f, -4f);
			pickupWristMotor.setPID(0.05, 0, 0);
			pickupWristMotor.set(Robot.restWristPosition());
			calibrateCount++;
			if (calibrateCount > 50) {
				// calibrateState++;
			}
			break;
		case STOP:
			pickupElbowMotor.changeControlMode(TalonControlMode.Speed);
			pickupElbowMotor.disable();
			pickupWristMotor.changeControlMode(TalonControlMode.Speed);
			pickupWristMotor.disable();
			calibrateCount = 0;
		}
		//System.out.println("Elbow Current: " + pdp.getCurrent(5));
		//System.out.println("Elbow Position: " + pickupElbowMotor.getEncPosition());
	}

	public void resetCalibrateState() {
		calibrateState = 0;
	}

	public void testWristMove(float degree) {
		pickupWristMotor.changeControlMode(TalonControlMode.Voltage);
		pickupWristMotor.set(degree);
	}

	public void testElbowMove(float degree) {
		pickupElbowMotor.changeControlMode(TalonControlMode.Voltage);
		pickupElbowMotor.set(degree);
	}

	public void testPickupWheelsMove(float degree) {
		pickupWheels.changeControlMode(TalonControlMode.Voltage);
		pickupWheels.set(degree);
	}

	public void testMecanumPosition(int position) {
		pickupWheels.changeControlMode(TalonControlMode.Position);
		pickupWheels.setPID(2, 1, 1);
		pickupWheels.set(position);
	}

	public void setPickupWheelsMode(int mode) {
		if (mode == 0) {
			pickupWheels.changeControlMode(TalonControlMode.Current);
		}
		if (mode == 1) {
			pickupWheels.changeControlMode(TalonControlMode.Disabled);
		}
		if (mode == 2) {
			pickupWheels.changeControlMode(TalonControlMode.Follower);
		}
		if (mode == 3) {
			pickupWheels.changeControlMode(TalonControlMode.MotionProfile);
		}
		if (mode == 4) {
			pickupWheels.changeControlMode(TalonControlMode.PercentVbus);
		}
		if (mode == 5) {
			pickupWheels.changeControlMode(TalonControlMode.Position);
		}
		if (mode == 6) {
			pickupWheels.changeControlMode(TalonControlMode.Speed);
		}
		if (mode == 7) {
			pickupWheels.changeControlMode(TalonControlMode.Voltage);
		}
	}

	public void setElbowMode(int mode) {
		if (mode == 0) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Current);
		}
		if (mode == 1) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Disabled);
		}
		if (mode == 2) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Follower);
		}
		if (mode == 3) {
			pickupElbowMotor.changeControlMode(TalonControlMode.MotionProfile);
		}
		if (mode == 4) {
			pickupElbowMotor.changeControlMode(TalonControlMode.PercentVbus);
		}
		if (mode == 5) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Position);
		}
		if (mode == 6) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Speed);
		}
		if (mode == 7) {
			pickupElbowMotor.changeControlMode(TalonControlMode.Voltage);
		}
	}

	public void setWristMode(int mode) {
		if (mode == 0) {
			pickupWristMotor.changeControlMode(TalonControlMode.Current);
		}
		if (mode == 1) {
			pickupWristMotor.changeControlMode(TalonControlMode.Disabled);
		}
		if (mode == 2) {
			pickupWristMotor.changeControlMode(TalonControlMode.Follower);
		}
		if (mode == 3) {
			pickupWristMotor.changeControlMode(TalonControlMode.MotionProfile);
		}
		if (mode == 4) {
			pickupWristMotor.changeControlMode(TalonControlMode.PercentVbus);
		}
		if (mode == 5) {
			pickupWristMotor.changeControlMode(TalonControlMode.Position);
		}
		if (mode == 6) {
			pickupWristMotor.changeControlMode(TalonControlMode.Speed);
		}
		if (mode == 7) {
			pickupWristMotor.changeControlMode(TalonControlMode.Voltage);
		}
	}
	
	public void setElbowPosition(int position) {
		System.out.println("Setting Elbow Position!");
		pickupElbowMotor.configPeakOutputVoltage(+10f, -10f);
		pickupElbowMotor.setPID(0.03, 0, 0);
		pickupElbowMotor.set(position);
	}

}
