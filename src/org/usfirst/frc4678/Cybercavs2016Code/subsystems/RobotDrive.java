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
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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
	double leftPower = 0;
	double rightPower = 0;
	int count = 0;
	boolean Auton;
	double previousRightEnc = 0;
	double previousLeftEnc = 0;
	double cameraXPos = 0;
	
	boolean isinAuto = true;
	int retrycount = 0;
	
    double targleft, targright;
    double lastleftEncoder, lastrightEncoder, leftEncoderdiff, rightEncoderdiff;
    int foundCount = 0;
	
	private final NetworkTable grip = NetworkTable.getTable("GRIP");
	private double WIDTH = 320.0;
	private double CENTERX = (WIDTH / 2) + 5; //decreaing makes robot go to the right
	//private double PIXEL_ENCODER_RATIO = Robot.pixelsPerEncoderChange();
	private double AUTOAIM_TURN_RATE = Robot.autoAimTurnRate();
	private double AUTOAIM_MAX_POWER = Robot.autoAimMaxPower();
	private double[] currentCenterXs;
	private double[] currentWidths;
	
	enum AutoAimState {
		INITIAL,
		MOVING,
		FINISHED
	}
	
	private int state = 0;
	private int cnt = 0;
	private double widest = 0;
	private int wideidx = 0;
	private double closest = 0;
	private int closeidx = 0;
	private int closecnt = 0;
	private double pixelsToTurn;

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
	private final Gyro gyro = RobotMap.gyro;

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
	
	public double getRightPower() {return rightPower;}
	
	public double getLeftPower() {return leftPower;}
	
	public double getGyroAngle() {return gyro.getAngle();}
	
	/////////////////////////////////////
	////////// Setter functions//////////
	/////////////////////////////////////
	
	public void setRightMotor(double power) {
		rightMotor.set(-power);
		rightPower = power;
	}

	public void setLeftMotor(double power) {
		leftMotor.set(power);
		leftPower = power;
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void setRightTarg(double target) {
		targright = target;
	}
	public void setLeftTarg(double target) {
		targleft = target;
	}
	public void resetGoToDistanceState() {
		goToDistanceState = 0;
	}
	public void setIsInAuto(boolean val) {
		isinAuto = val;
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
		gyroError = gyroTarget - gyro.getAngle();
		gyroPower = gyroError * 0.0155; // 0.0067 was pretty good
		setLeftMotor(-gyroPower);
		setRightMotor(gyroPower);
		if (Math.abs(gyroError) < 10) {
			// System.out.println("THE GYRO TURNING HAS REACHED TARGET!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return true;
		}
		return false;

	}

	public boolean checkFrontLightSensorIsOnCarpet() {
		if (frontLightSensor.getValue() < 800) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkBackLightSensorIsOnCarpet() {
		if (backLightSensor.getValue() < 800) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getLeftSpeed() {
		return leftEncoder.getRate();
	}
	public double getRightSpeed() {
		return rightEncoder.getRate();
	}
    
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void setTurnPower(double power) {
    	setLeftMotor(power);
    	setRightMotor(-power);
    }
    
    public void autoAimInit() {
    	state = 0;//AutoAimState.INITIAL;
    	cnt = 0;
    }
    
    public void setTarget(double left, double right) {
    	targleft = left;
    	targright = right;
    }

    // 1.4 pixels / encoder click (both wheels)
    public boolean autoAim() {
    	double leftDiff = previousLeftEnc - leftEncoder.get();
    	double rightDiff = previousRightEnc - rightEncoder.get();
    	previousLeftEnc = leftEncoder.get();
    	previousRightEnc = rightEncoder.get();
    	switch(state) {
    		case 0:
    			cnt = 0;
    			retrycount = 0;
    			state++;
    			break;
    		case 1:
    			if (leftDiff == 0 && rightDiff == 0) {
    				cnt++;
    			}
    			if (cnt > 5) {
    				state++;
    				cnt = 0;
    			}
    			break;
    		case 2: // Init for Camera auto aim
    			currentCenterXs = grip.getNumberArray("myContoursReport/centerX", new double[]{});
    			currentWidths = grip.getNumberArray("myContoursReport/width",new double[]{});
	    		//System.out.println("Looking for contours" + currentCenterXs.length);    	    	
    			if (currentCenterXs.length == 0) {
    	    		setTurnPower(0);
    	    		cnt = 0;
    	    		break;
    	    	}
    	    	else {
    	    		//System.out.println("Found at least 1");
    	    		cnt++; // Increment counter.  If we get readings 5 times in a row, assume target is good
    	    		if (cnt >= 25) {
    	    			state++;
    	    			cnt = 0;
    	    			widest = 0;
    	    			while(cnt < currentCenterXs.length) {
    	    				if (currentWidths[cnt] > widest) {
    	    					widest = currentWidths[cnt];
    	    					wideidx = cnt;
    	    				}
    	    				cnt++;
   	        	    	pixelsToTurn = currentCenterXs[wideidx] - CENTERX;
    	    			}

    	    		}
    	    	}
    	    	break;
    		case 3: // Target is good, find largest width value and use that as target
    			// 0.735 is a pretty good multiplier when close to target.  When far away, we need a lower multiplier
    			// Center is 173.
    			// Target X,  Encoder Left start,  Encoder Right start, Encoder left end, Encoder Right end
    			// 142, 0, 0, -30, -58 , avg = -44
    			// 209, 83, 108, 89, 70, avg = -16
    			// 95, 120, -9, 33, 25
    			// 
    			cnt = 0;

    	    	// try mult = 0.735 for pixelsToTurn at 10 Going with 0.74
    	    	// try mult = 0.7 for pixelsToTurn at 40 seemed to work ok.
    	    	double pixelmult = (Robot.pixelsPerEncoderChange() - 0.76)*(Math.abs(pixelsToTurn) - 10)/30.0 + 0.76 ;
    	    	resetEncoders();
    	    	targleft = pixelsToTurn * pixelmult;
    	    	System.out.println("pixelmult = "+pixelmult);
    	    	targright = -targleft;
    	    	System.out.println("pixelsToTurn:" + pixelsToTurn+" CENTERX = "+CENTERX+" Targets("+targleft+","+targright+")");
    			state++;
    			break;
    		case 4:
    			currentCenterXs = grip.getNumberArray("myContoursReport/centerX", new double[]{});
    			if (currentCenterXs.length > 0)
    				cameraXPos = currentCenterXs[0];
    			else 
    				cameraXPos = 0;
    			if (findTarget()) {
    				setLeftMotor(0);
    				setRightMotor(0);
    				state++;
    				cnt = 0;
    			}
    	    	break;
    		case 5:
    			// Robot encoders say we're at the right position.  Camera lag can be 25 to 30 cycles at 50Hz so
    			// Assume the findTarget stuff has used up 12 of those, we need to acquire the image for another 25 cycles
    			// Then assess if the position is good or not.  If not, repeat at case 3 with the new adjustment.
    			if (isinAuto) { // Only do this extra part when in Autonomous
    				cnt++; // Wait the 25 counts
    				if (cnt > 25) {
        				if (retrycount < 5) { // give up after 5 re-tries
        	    			currentCenterXs = grip.getNumberArray("myContoursReport/centerX", new double[]{});
        					// Find the CenterX closest to CENTERX and see how close we are.
        	    			closecnt = 0;
        	    			closest = 10000.0;
        	    			while(closecnt < currentCenterXs.length) {
        	    				double tmp = Math.abs(currentCenterXs[closecnt] - CENTERX);
        	    				if (tmp < closest) {
        	    					closest = tmp;
        	    					closeidx = closecnt;
        	    				}
        	    				closecnt++;
        	    			}
        	    			if (currentCenterXs.length > 0) {
	        	    			double tmp = Math.abs(currentCenterXs[closeidx] - CENTERX);
	        	    			if (tmp > 2) { // Not Close enough.  Let's try again.
	           	        	    	pixelsToTurn = currentCenterXs[closeidx] - CENTERX; // Here's how far we need to turn
	           	        	    	state = 3; // State 3 takes care of things once we've set pixelsToTurn
	           	        	    	retrycount++; // Keep track of how many times we re-try.
	        	    				
	        	    			}
	        	    			else {
	        	    				state++;
	        	    			}
        	    			}
        	    		System.out.println("Retry "+retrycount+": cnt = "+cnt+", pixelsToTurn = "+pixelsToTurn+" closest = "+closest+" CENTERX="+CENTERX);
        				}
    					
    				}
    			}
    			else
    				return true;
    			break;
    		case 6: // Done.  Do nothing for now.
    			return true;
    			
    	}
    	
    	return false;
    }
    
    double left_PID_I = 0, right_PID_I = 0;
    
    public boolean findTarget() {
    	// This routine will attempt to position encoders to match targleft and targright
    	// Using a fast and accurate PID-type control method.
    	double PFACTOR = 0.015;
    	double DFACTOR = 0.027; // was 0.065
    	double IFACTOR = 0.005; // was 0.0055
    	double IRANGE = 13; // Only apply I when we get pretty close to the target.
    	double MAXPOWER = 0.6;
    	double left_PID_P, right_PID_P, left_PID_D, right_PID_D;
    	double lefterr = targleft - leftEncoder.get();
    	double righterr = targright - rightEncoder.get();
    	leftEncoderdiff = leftEncoder.get() - lastleftEncoder;
    	rightEncoderdiff = rightEncoder.get() - lastrightEncoder;
    	
    	lastleftEncoder = leftEncoder.get();
    	lastrightEncoder = rightEncoder.get();
    	// Using get rate to see if this is useful for velocity.
    	// Data shows that the slow encoder update rate of the
    	// drive train can lead to rather poor rate values.  
    	// when speed is really 0, we sometimes get multiple 
    	// readings of a fairly high rate that should be 0.
    	// recommend tracking encoder readings and use a rate of 0
    	// if the encoder readings don't change in 1/50sec.
    	//double leftvel = leftEncoder.getRate();
    	//double rightvel = rightEncoder.getRate();
    	
    	left_PID_D = leftEncoderdiff * DFACTOR;
    	right_PID_D = rightEncoderdiff * DFACTOR;
    	
    	left_PID_P = lefterr * PFACTOR;
    	right_PID_P = righterr * PFACTOR;
    	
    	if (Math.abs(lefterr) < IRANGE) {
    		left_PID_I += lefterr * IFACTOR;
    	} else {
    		left_PID_I = 0;
    	}
    	
    	if (Math.abs(righterr) < IRANGE) {
    		right_PID_I += righterr * IFACTOR;
    	} else {
    		right_PID_I = 0;
    	}
    	
    	double leftpwr = left_PID_P - left_PID_D + left_PID_I;
    	double rightpwr = right_PID_P - right_PID_D + right_PID_I;

    	if (leftpwr > MAXPOWER)
   	   		leftpwr =  MAXPOWER;
   	   	if (leftpwr < -MAXPOWER)
   	   		leftpwr = -MAXPOWER;
   		if (rightpwr > MAXPOWER)
   			rightpwr = MAXPOWER;
   		if (rightpwr < -MAXPOWER)
   			rightpwr = -MAXPOWER;
    	
    	System.out.println(cameraXPos + ", " + lefterr+","+righterr+","+leftpwr+","+rightpwr+","+leftEncoderdiff+","+rightEncoderdiff+","+left_PID_P+","+right_PID_P+","+left_PID_D+","+right_PID_D);
    	setLeftMotor(-leftpwr);
    	setRightMotor(-rightpwr);
    	if (Math.abs(lefterr) < 2.0 && Math.abs(righterr) < 2.0) {
    		foundCount++;
    	}
    	else {
    		foundCount = 0;
    	}
    	if (foundCount > 12) {
    		return true;
    	}
    	return false;
    }
    public void DrivePath(double leftPos, double leftSpeed, double rightPos, double rightSpeed) {
    	// This routine will attempt to put the drive train on the path defined by the speeds and positions.
    	double PFACTOR = 0.01; // Position Error Multiplier.  0.01 works fairly well.
    	// double DFACTOR = 0.0; // Probably won't use this.
    	double IFACTOR = 0.02; // Speed multiplier.  0.02 seems to work fairly well.
    	double MAXPOWER = 0.90;
    	double left_P, right_P, left_I, right_I;
    	double lefterr = leftPos - leftEncoder.get();
    	double righterr = rightPos - rightEncoder.get();
    	leftEncoderdiff = leftEncoder.get() - lastleftEncoder;
    	rightEncoderdiff = rightEncoder.get() - lastrightEncoder;
    	
    	lastleftEncoder = leftEncoder.get();
    	lastrightEncoder = rightEncoder.get();
    	
    	left_P = lefterr * PFACTOR;
    	right_P = righterr * PFACTOR;
    	
    	left_I = leftSpeed * IFACTOR;
    	right_I = rightSpeed * IFACTOR;
    	
    	double leftpwr = left_P + left_I;
    	double rightpwr = right_P + right_I;

    	if (leftpwr > MAXPOWER)
   	   		leftpwr =  MAXPOWER;
   	   	if (leftpwr < -MAXPOWER)
   	   		leftpwr = -MAXPOWER;
   		if (rightpwr > MAXPOWER)
   			rightpwr = MAXPOWER;
   		if (rightpwr < -MAXPOWER)
   			rightpwr = -MAXPOWER;
   		
   		System.out.printf("LP=%6.2f RP=%6.2f LS=%6.2f RS=%6.2f LPWR=%6.2f RPWR=%6.2f Lerr:%6.2f=%6.2f-%6.2f Rerr:%6.2f=%6.2f-%6.2f \n",leftPos,rightPos,leftSpeed,rightSpeed,leftpwr,rightpwr,lefterr,leftPos,lastleftEncoder,righterr,rightPos,lastrightEncoder);
    	setLeftMotor(-leftpwr);
    	setRightMotor(-rightpwr);
    	
    }


}
