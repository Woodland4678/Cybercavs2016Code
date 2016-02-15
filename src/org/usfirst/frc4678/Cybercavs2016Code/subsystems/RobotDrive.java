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
import org.usfirst.frc4678.Cybercavs2016Code.commands.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDrive extends Subsystem {

	double powerReduction = 0.6;
	boolean robotDriving = false;
	double GO_TO_DISTANCE_CORRECTION_SPEED = 50;
	int AUTO_DRIVE_RAMP_DISTANCE = 30;
	int ENCODER_DIFFERENCE_PER_TURN = Robot.encoderChangePerTurn();
	int LIGHT_SENSOR_MARGIN = Robot.lightSensorMargin();
	double GO_TO_BOX_TURN_SPEED = Robot.goToBoxTurnSpeed();
	int TARGET_LIGHT_SENSOR_VALUE = Robot.targetLightSensorValue();
	double AUTO_TURN_MARGIN = .05;// This is a percentage
	double AUTO_TURN_REDUCTION_SPEED = Robot.autoTurnReductionSpeed();
	double AUTO_TURN_REDUCTION_DISTANCE = 0.6;// Starts reducing the speed when
												// it is x percent of the way to
												// the target distance
	long goalTime;
	int timedDriveState = 0;
	int goToDistanceState = 0;
	int turnState = 0;
	int startingLeftDistance;
	int startingRightDistance;
	boolean gentleModeOn = true;
	// GoToDistance variables
	double targetLeft;
	double targetRight;
	double currentLeft;
	double currentRight;
	double currentLeftCentimeters;
	double currentRightCentimeters;
	double leftPercentThere;
	double rightPercentThere;
	double leftMotorMultiplier;
	double rightMotorMultiplier;
	double powerOffset;
	double gyroTarget = 0;
	double gyroError;
	double gyroPower = 0.75;
	double previousAccelVal = 1000;
	int count = 0;
	boolean Auton;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftMotor = RobotMap.robotDriveleftMotor;
    private final SpeedController rightMotor = RobotMap.robotDriverightMotor;
    private final Encoder leftEncoder = RobotMap.robotDriveleftEncoder;
    private final Encoder rightEncoder = RobotMap.robotDriverightEncoder;
    private final AnalogGyro turnGyro = RobotMap.robotDriveturnGyro;
    private final AnalogInput frontLightSensor = RobotMap.robotDrivefrontLightSensor;
    private final AnalogInput backLightSensor = RobotMap.robotDrivebackLightSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final Accelerometer accel = RobotMap.accel;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveTrain());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	//////////////////////////////////////
	////////// Access Functions//////////
	/////////////////////////////////////
	
	public int getLeftEncoder() {return leftEncoder.get();}

	public int getRightEncoder() {return rightEncoder.get();}

	public double getBuiltInAccelX() {return accel.getX();}

	public double getBuiltInAccelY() {return accel.getY();}

	public double getBuiltInAccelZ() {return accel.getZ();}
	
	public double getFrontLightSensorValue() {return frontLightSensor.getValue();}

	public double getBackLightSensorValue() {return backLightSensor.getValue();}
	
	public double getGyroPosition() {return turnGyro.getAngle();}
	
	/////////////////////////////////////
	////////// Setter functions//////////
	/////////////////////////////////////
	
	public void setRightMotor(double power) {
		rightMotor.set(power);
	}

	public void setLeftMotor(double power) {
		leftMotor.set(-power);
	}
	
	public void resetGyro() {
		turnGyro.reset();
	}
	
	/////////////////////////////////////////
	//////////autonomous functions//////////
	///////////////////////////////////////
	
	// To use this method, you keep calling it until it returns true
	public boolean goToDistance(double rightCentimeters,
			double leftCentimeters, double power, int rampUpDistance,
			int rampDownDistance, double startingPower, double endingPower) {
		SmartDashboard.putNumber("Left Wheels Position",
				Robot.robotDrive.getLeftEncoder());
		SmartDashboard.putNumber("Right Wheels Position",
				Robot.robotDrive.getRightEncoder());

		// --------------------------------------------------------------------------
		// -----------------------Reset variables if
		// necessary-----------------------
		// --------------------------------------------------------------------------

		// If this method is being called for the first time since it last
		// finished, you want to record the initial encoder values
		if (goToDistanceState == 0) {
			goToDistanceState++;
			startingLeftDistance = getLeftEncoder();
			startingRightDistance = getRightEncoder();
			// Robot.logger.info("Drivetrain",
			// "goToDistance starting encoder values are " + getRightEncoder() +
			// ", " + getLeftEncoder());
		}

		System.out.println("hi goToDistanceState=" + goToDistanceState + " "
				+ startingLeftDistance + " " + startingRightDistance);

		// --------------------------------------------------------------------------
		// ---------------------Get target and current
		// distances---------------------
		// --------------------------------------------------------------------------

		// Get target distance in centimeters
		targetLeft = leftCentimeters * Robot.encoderClicksPerCentimeter();
		targetRight = rightCentimeters * Robot.encoderClicksPerCentimeter();

		// Get the current distance in centimeters
		currentLeft = Math.abs(getLeftEncoder() - startingLeftDistance);
		currentRight = Math.abs(getRightEncoder() - startingRightDistance);
		currentLeftCentimeters = currentLeft
				/ Robot.encoderClicksPerCentimeter();
		currentRightCentimeters = currentRight
				/ Robot.encoderClicksPerCentimeter();

		// Find the percentage the left and right are to their target
		leftPercentThere = Math.abs(currentLeft / targetLeft);
		rightPercentThere = Math.abs(currentRight / targetRight);
		// Robot.logger.debug("Drivetrain", "gpToDistance Percentages At " +
		// rightPercentThere + ", " + leftPercentThere);

		// Initially set the powers to their default values
		leftMotorMultiplier = 1;
		rightMotorMultiplier = 1;

		// --------------------------------------------------------------------------
		// ----------------Adjust powers if one side has gone
		// farther----------------
		// --------------------------------------------------------------------------

		// Difference between how far the left and right have gone
		powerOffset = GO_TO_DISTANCE_CORRECTION_SPEED
				* Math.abs(leftPercentThere - rightPercentThere);

		// Only start adjusting the powers once the motors have gone 2 percent
		// of the target distance, to avoid calculation errors
		if (currentRight >= (targetRight * 0.02)
				&& (currentLeft >= (targetLeft * 0.02))) {
			// If the right is closer than the left, increase the left power and
			// decrease the right power
			if (rightPercentThere > (leftPercentThere + 0.001)) {
				leftMotorMultiplier *= 1 + powerOffset;
				rightMotorMultiplier *= 1 - powerOffset;
			}

			// If the left is closer than the right, increase the right power,
			// and decrease the left power
			if ((rightPercentThere + 0.001) < leftPercentThere) {
				leftMotorMultiplier *= 1 - powerOffset;
				rightMotorMultiplier *= 1 + powerOffset;
			}
		}
		// Robot.logger.debug("Drivetrain", "goToDistance percentages at " +
		// (int)(rightPercentThere * 100) + ", " + (int)(leftPercentThere * 100)
		// + " Power Offset At " + (((int)(1000 * powerOffset)) / 1000.0));

		// --------------------------------------------------------------------------
		// -----------------------Flip the powers if
		// necessary-----------------------
		// --------------------------------------------------------------------------

		// We use the absolute values for setting the powers, so we have to flip
		// the powers based on what direction the robot is going
		if (targetRight < 0) {
			// If the robot is trying to go backwards and has not passed the
			// target
			if (getRightEncoder() - startingRightDistance > targetRight) {
				rightMotorMultiplier *= -1;
			}
		} else {
			// If the robot is trying to go forwards and has passed the target
			if (getRightEncoder() - startingRightDistance > targetRight) {
				rightMotorMultiplier *= -1;
			}
		}

		if (targetLeft < 0) {
			// If the robot is trying to go backwards and has not passed the
			// target
			if (getLeftEncoder() - startingLeftDistance > targetLeft) {
				leftMotorMultiplier *= -1;
			}
		} else {
			// If the robot is trying to go forwards and has passed the target
			if (getLeftEncoder() - startingLeftDistance > targetLeft) {
				leftMotorMultiplier *= -1;
			}
		}

		// --------------------------------------------------------------------------
		// -----------------------------Ramp Down
		// Speeds-----------------------------
		// --------------------------------------------------------------------------

		double rampDownPercentage = 1;
		if (currentRightCentimeters < rampUpDistance) {
			rampDownPercentage = ((currentRightCentimeters / rampUpDistance) * (1 - startingPower))
					+ startingPower;
			// Robot.logger.info("Drivetrain", "goToDistance ramping down " +
			// (int)(rampDownPercentage * 100) + "%");
		} else if (currentRightCentimeters > Math.abs(rightCentimeters)
				- rampDownDistance) {
			rampDownPercentage = (((Math.abs(rightCentimeters) - currentRightCentimeters) / rampDownDistance) * (1 - endingPower))
					+ endingPower;
			// Robot.logger.info("Drivetrain", "goToDistance ramping down " +
			// (int)(rampDownPercentage * 100) + "%");
		}

		// Robot.logger.debug("Drivetrain", "goToDistance target is " +
		// rightCentimeters + ", " + leftCentimeters + " current is " +
		// (-(int)((getRightEncoder() - startingRightDistance) /
		// Robot.encoderClicksPerCentimeter())) + ", " +
		// (-(int)((getLeftEncoder() - startingLeftDistance) /
		// Robot.encoderClicksPerCentimeter())));

		setLeftMotor(leftMotorMultiplier * power * rampDownPercentage);
		setRightMotor(rightMotorMultiplier * power * rampDownPercentage);

		// If the left and the right both have gone far enough stop the motors,
		// and reset the goToDistanceState so that the next time
		// the method is called, it will record the starting encoder values
		// again
		if (rightPercentThere >= 1 && leftPercentThere >= 1) {
			setLeftMotor(0);
			setRightMotor(0);
			goToDistanceState = 0;
			System.out.println("Drivetrain goToDistance at target");
			System.out
					.println("Drivetrain goToDistance final encoder values are "
							+ getRightEncoder() + ", " + getLeftEncoder());
			return true;
		}
		System.out.println(" return false here...");

		return false;
	}

	public boolean isFlat() {
		if (checkFrontLightSensorIsOnCarpet()
				&& checkBackLightSensorIsOnCarpet()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean gyroTurn(double target) {
		gyroTarget = target;
		gyroError = gyroTarget - turnGyro.getAngle();
		gyroPower = gyroError * 0.0067; // 0.0067 was pretty good
		Robot.robotDrive.setLeftMotor(gyroPower);
		Robot.robotDrive.setRightMotor(-gyroPower);
		if (Math.abs(gyroError) < 15) {
			// System.out.println("THE GYRO TURNING HAS REACHED TARGET!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return true;
		}
		return false;

	}

	public boolean checkFrontLightSensorIsOnCarpet() {
		if (frontLightSensor.getValue() < 1500) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkBackLightSensorIsOnCarpet() {
		if (backLightSensor.getValue() < 1500) {
			return true;
		} else {
			return false;
		}
	}
}
