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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController robotDriveleftMotor;
    public static SpeedController robotDriverightMotor;
    public static Encoder robotDriveleftEncoder;
    public static Encoder robotDriverightEncoder;
    public static AnalogGyro robotDriveturnGyro;
    public static AnalogInput robotDrivebackLightSensor;
    public static AnalogInput robotDriveAnalogInput2;
    public static CANTalon pickupArmpickupElbowMotor;
    public static CANTalon pickupArmpickupWristMotor;
    public static CANTalon pickupArmpickupWheels;
    public static DigitalInput pickupArmballSensor;
    public static SpeedController catapultwinchMotor;
    public static Servo catapultlatchServo;
    public static AnalogPotentiometer catapultwinchPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Accelerometer accel;
    
	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        robotDriveleftMotor = new Talon(0);
        LiveWindow.addActuator("RobotDrive", "leftMotor", (Talon) robotDriveleftMotor);
        
        robotDriverightMotor = new Talon(1);
        LiveWindow.addActuator("RobotDrive", "rightMotor", (Talon) robotDriverightMotor);
        
        robotDriveleftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("RobotDrive", "leftEncoder", robotDriveleftEncoder);
        robotDriveleftEncoder.setDistancePerPulse(1.0);
        robotDriveleftEncoder.setPIDSourceType(PIDSourceType.kRate);
        robotDriverightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("RobotDrive", "rightEncoder", robotDriverightEncoder);
        robotDriverightEncoder.setDistancePerPulse(1.0);
        robotDriverightEncoder.setPIDSourceType(PIDSourceType.kRate);
        robotDriveturnGyro = new AnalogGyro(1);
        LiveWindow.addSensor("RobotDrive", "turnGyro", robotDriveturnGyro);
        robotDriveturnGyro.setSensitivity(0.007);
        robotDrivebackLightSensor = new AnalogInput(2);
        LiveWindow.addSensor("RobotDrive", "backLightSensor", robotDrivebackLightSensor);
        
        robotDriveAnalogInput2 = new AnalogInput(3);
        LiveWindow.addSensor("RobotDrive", "Analog Input 2", robotDriveAnalogInput2);
        
        pickupArmpickupElbowMotor = new CANTalon(5);
        LiveWindow.addActuator("PickupArm", "pickupElbowMotor", pickupArmpickupElbowMotor);
        
        pickupArmpickupWristMotor = new CANTalon(4);
        LiveWindow.addActuator("PickupArm", "pickupWristMotor", pickupArmpickupWristMotor);
        
        pickupArmpickupWheels = new CANTalon(6);
        LiveWindow.addActuator("PickupArm", "pickupWheels", pickupArmpickupWheels);
        
        pickupArmballSensor = new DigitalInput(4);
        LiveWindow.addSensor("PickupArm", "ballSensor", pickupArmballSensor);
        
        catapultwinchMotor = new Talon(4);
        LiveWindow.addActuator("Catapult", "winchMotor", (Talon) catapultwinchMotor);
        
        catapultlatchServo = new Servo(3);
        LiveWindow.addActuator("Catapult", "latchServo", catapultlatchServo);
        
        catapultwinchPosition = new AnalogPotentiometer(0, 10.0, 0.0);
        LiveWindow.addSensor("Catapult", "winchPosition", catapultwinchPosition);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        accel = new BuiltInAccelerometer(); 
        accel = new BuiltInAccelerometer(Accelerometer.Range.k4G); 
	}
}
