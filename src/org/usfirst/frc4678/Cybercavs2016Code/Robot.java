// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4678.Cybercavs2016Code;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc4678.Cybercavs2016Code.commands.*;
import org.usfirst.frc4678.Cybercavs2016Code.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. hi
 */
public class Robot extends IterativeRobot {

	////////////////////////////////////////
	////////// Parameters for Elbow//////////
	////////////////////////////////////////

	public static int pickupElbowPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("pickupElbowPosition")) {
			prefs.putInt("pickupElbowPosition", 39500);
		}
		return prefs.getInt("pickupElbowPosition", 39500);
	}

	public static int holdElbowPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("holdElbowPosition")) {
			prefs.putInt("holdElbowPosition", 1417);
		}
		return prefs.getInt("holdElbowPosition", 1417);
	}

	public static int spitOutElbowPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("spitOutElbowPosition")) {
			prefs.putInt("spitOutElbowPosition", 10438);
		}
		return prefs.getInt("spitOutElbowPosition", 10438);
	}

	////////////////////////////////////////
	////////// Parameters for Wrist//////////
	////////////////////////////////////////

	public static int pickupWristPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("pickupWristPosition")) {
			prefs.putInt("pickupWristPosition", -5957);
		}
		return prefs.getInt("pickupWristPosition", -5957);
	}

	public static int holdWristPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("holdWristPosition")) {
			prefs.putInt("holdWristPosition", 2717);
		}
		return prefs.getInt("holdWristPosition", 2717);
	}

	public static int spitOutWristPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("spitOutWristPosition")) {
			prefs.putInt("spitOutWristPosition", -38845);
		}
		return prefs.getInt("spitOutWristPosition", -38845);
	}
	public static int wristPullInPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("wristPullInPosition")) {
			prefs.putInt("wristPullInPosition", 19257);
		}
		return prefs.getInt("wristPullInPosition", 19257);
	}
	public static int wristLiftPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("wristLiftPosition")) {
			prefs.putInt("wristLiftPosition", 17500);
		}
		return prefs.getInt("wristLiftPosition", 17500);
	}

	///////////////////////////////////////////
	////////// Parameters for Catapult//////////
	//////////////////////////////////////////

	public static double latchReadyPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("latchReadyPostion")) {
			prefs.putDouble("latchReadyPostion", 0.205);
		}
		return prefs.getDouble("latchReadyPostion", 0.205);
	}

	public static double latchShootPosition() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("latchShootPosition")) {
			prefs.putDouble("latchShootPosition", 0.4);
		}
		return prefs.getDouble("latchShootPosition", 0.4);
	}
	
	public static double latchLockPosition() { //position to lower server after latch to ensure it doesn't launch prematurely
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("latchLockPosition")) {
			prefs.putDouble("latchLockPosition", 0);
		}
		return prefs.getDouble("latchLockPosition", 0);
	}

	public static double winchPower() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("winchPower")) {
			prefs.putDouble("winchPower", 0.2);
		}
		return prefs.getDouble("winchPower", 0.2);
	}

	public static double winchWoundDistance() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("winchWoundDistance")) {
			prefs.putDouble("winchWoundDistance", 1.5);
		}
		return prefs.getDouble("winchWoundDistance", 1.5);
	}

	public static double winchUnwoundDistance() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("winchUnwoundDistance")) {
			prefs.putDouble("winchUnwoundDistance", 4);
		}
		return prefs.getDouble("winchUnwoundDistance", 4);
	}

	///////////////////////////////////
	////////// Misc Parameters//////////
	///////////////////////////////////

	public static double pickupWheelsPower() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("pickupWheelsPower")) {
			prefs.putDouble("pickupWheelsPower", 3);
		}
		return prefs.getDouble("pickupWheelsPower", 3);
	}

	public static double encoderClicksPerCentimeter() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("encoderClicksPerCentimeter")) {
			prefs.putDouble("encoderClicksPerCentimeter", 7.2);
		}
		return prefs.getDouble("encoderClicksPerCentimeter", 7.2);
	}

	public static int encoderChangePerTurn() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.containsKey("encoderChangePerTurn")) {
			prefs.putInt("encoderChangePerTurn", 3000);
		}
		return prefs.getInt("encoderChangePerTurn", 3000);
	}
    
    public static int targetLightSensorValue() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("targetLightSensorValue")) {
            prefs.putInt("targetLightSensorValue", 400);
        }
        return prefs.getInt("targetLightSensorValue", 400);
    }
	
    public static int lightSensorMargin() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lightSensorMargin")) {
            prefs.putInt("lightSensorMargin", 50);
        }
        return prefs.getInt("lightSensorMargin", 50);
    }
    
    public static double goToBoxTurnSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("goToBoxTurnSpeed")) {
            prefs.putDouble("goToBoxTurnSpeed", .05);
        }
        return prefs.getDouble("goToBoxTurnSpeed", .05);
    }

    public static int autoTurnReductionSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("autoTurnReductionSpeed")) {
            prefs.putInt("autoTurnReductionSpeed", 2);
        }
        return prefs.getInt("autoTurnReductionSpeed", 2);
    }
    public static int autoMode() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("autoMode")) {
            prefs.putInt("autoMode", 0);
        }
        return prefs.getInt("autoMode", 0);
    }
    
	Command autonomousCommand;

	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static RobotDrive robotDrive;
    public static PickupArm pickupArm;
    public static Catapult catapult;
    public static ManipulatorArm manipulatorArm;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
    
	public void robotInit() {
		RobotMap.init();
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        robotDrive = new RobotDrive();
        pickupArm = new PickupArm();
        catapult = new Catapult();
        manipulatorArm = new ManipulatorArm();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		Robot.pickupArm.CalibratePickup();
		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
