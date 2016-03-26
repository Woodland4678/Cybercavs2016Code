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

import org.usfirst.frc4678.Cybercavs2016Code.commands.AutoAim;
import org.usfirst.frc4678.Cybercavs2016Code.commands.AutonomousCommand;
import org.usfirst.frc4678.Cybercavs2016Code.commands.CalibratePickup;
import org.usfirst.frc4678.Cybercavs2016Code.commands.DrawBridge;
import org.usfirst.frc4678.Cybercavs2016Code.commands.DriveTrain;
import org.usfirst.frc4678.Cybercavs2016Code.commands.HoldBallMode;
import org.usfirst.frc4678.Cybercavs2016Code.commands.ManipulatorCategoryCMode;
import org.usfirst.frc4678.Cybercavs2016Code.commands.ManipulatorRestMode;
import org.usfirst.frc4678.Cybercavs2016Code.commands.ManipulatorShootMode;
import org.usfirst.frc4678.Cybercavs2016Code.commands.Pickup;
import org.usfirst.frc4678.Cybercavs2016Code.commands.PickupArmLowBarPosition;
import org.usfirst.frc4678.Cybercavs2016Code.commands.PickupArmTesting;
import org.usfirst.frc4678.Cybercavs2016Code.commands.Portcullis;
import org.usfirst.frc4678.Cybercavs2016Code.commands.Position1;
import org.usfirst.frc4678.Cybercavs2016Code.commands.Position2;
import org.usfirst.frc4678.Cybercavs2016Code.commands.ReadyToShoot;
import org.usfirst.frc4678.Cybercavs2016Code.commands.ReleaseClimber;
import org.usfirst.frc4678.Cybercavs2016Code.commands.RetractClimber;
import org.usfirst.frc4678.Cybercavs2016Code.commands.SetManipulatorArm;
import org.usfirst.frc4678.Cybercavs2016Code.commands.SetPickupArm;
import org.usfirst.frc4678.Cybercavs2016Code.commands.SetServoMotor;
import org.usfirst.frc4678.Cybercavs2016Code.commands.Shoot;
import org.usfirst.frc4678.Cybercavs2016Code.commands.SpitOut;
import org.usfirst.frc4678.Cybercavs2016Code.commands.TurnOnLightRing;
import org.usfirst.frc4678.Cybercavs2016Code.commands.sallyPort;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driverBtn1;
    public JoystickButton driverBtn2;
    public JoystickButton driverBtn3;
    public JoystickButton driverBtn4;
    public JoystickButton driverBtn7;
    public JoystickButton driverBtn8;
    public JoystickButton driverBtn6;
    public Joystick driverGamepad;
    public JoystickButton operatorBtn1;
    public JoystickButton operatorBtn2;
    public JoystickButton operatorBtn3;
    public JoystickButton operatorBtn4;
    public JoystickButton operatorBtn5;
    public JoystickButton operatorBtn6;
    public Joystick operatorGamepad;
    public Joystick autoSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        autoSwitch = new Joystick(2);
        
        operatorGamepad = new Joystick(1);
        
        operatorBtn6 = new JoystickButton(operatorGamepad, 6);
        operatorBtn6.whileHeld(new RetractClimber());
        operatorBtn5 = new JoystickButton(operatorGamepad, 5);
        operatorBtn5.whileHeld(new ReleaseClimber());
        operatorBtn4 = new JoystickButton(operatorGamepad, 4);
        operatorBtn4.whileHeld(new Portcullis());
        operatorBtn3 = new JoystickButton(operatorGamepad, 3);
        operatorBtn3.whileHeld(new DrawBridge());
        operatorBtn2 = new JoystickButton(operatorGamepad, 2);
        operatorBtn2.whenPressed(new ManipulatorRestMode());
        operatorBtn1 = new JoystickButton(operatorGamepad, 1);
        operatorBtn1.whileHeld(new sallyPort());
        driverGamepad = new Joystick(0);
        
        driverBtn6 = new JoystickButton(driverGamepad, 6);
        driverBtn6.whileHeld(new TurnOnLightRing());
        driverBtn8 = new JoystickButton(driverGamepad, 8);
        driverBtn8.whenPressed(new Shoot());
        driverBtn7 = new JoystickButton(driverGamepad, 7);
        driverBtn7.whileHeld(new AutoAim());
        driverBtn4 = new JoystickButton(driverGamepad, 4);
        driverBtn4.whenPressed(new PickupArmLowBarPosition());
        driverBtn3 = new JoystickButton(driverGamepad, 3);
        driverBtn3.whenPressed(new SpitOut());
        driverBtn2 = new JoystickButton(driverGamepad, 2);
        driverBtn2.whenPressed(new HoldBallMode());
        driverBtn1 = new JoystickButton(driverGamepad, 1);
        driverBtn1.whenPressed(new Pickup());


        // SmartDashboard Buttons
        SmartDashboard.putData("SetManipulatorArm", new SetManipulatorArm());
        SmartDashboard.putData("ManipulatorShootMode", new ManipulatorShootMode());
        SmartDashboard.putData("ManipulatorRestMode", new ManipulatorRestMode());
        SmartDashboard.putData("ManipulatorCategoryCMode", new ManipulatorCategoryCMode());
        SmartDashboard.putData("PickupArmLowBarPosition", new PickupArmLowBarPosition());
        SmartDashboard.putData("Portcullis", new Portcullis());
        SmartDashboard.putData("sallyPort", new sallyPort());
        SmartDashboard.putData("AutoAim", new AutoAim());
        SmartDashboard.putData("TurnOnLightRing", new TurnOnLightRing());
        SmartDashboard.putData("DrawBridge", new DrawBridge());
        SmartDashboard.putData("ReleaseClimber", new ReleaseClimber());
        SmartDashboard.putData("RetractClimber", new RetractClimber());
        SmartDashboard.putData("Position1", new Position1());
        SmartDashboard.putData("Position2", new Position2());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverGamepad() {
        return driverGamepad;
    }

    public Joystick getOperatorGamepad() {
        return operatorGamepad;
    }

    public Joystick getAutoSwitch() {
        return autoSwitch;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

