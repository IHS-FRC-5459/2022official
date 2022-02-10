// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class VisionSub extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private TalonSRX left1;
private TalonSRX left2;
private MotorControllerGroup leftMotors;
private TalonSRX right1;
private TalonSRX right2;
private MotorControllerGroup rightMotors;
private DifferentialDrive differentialDrive;
private Encoder leftEnc;
private Encoder rightEnc;
private DifferentialDrive drive;
private double range = 0;

public int rangeAdjust;
public int angleAdjust;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public VisionSub() {

    angleAdjust = 0;
    rangeAdjust = 0;

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    left1 = new TalonSRX(0);
    //addChild("left1",left1);
    left1.setInverted(true);

left2 = new TalonSRX(1);
 //addChild("left2",left2);
 left2.setInverted(true);


 

right1 = new TalonSRX(2);
 //addChild("right1",right1);
 right1.setInverted(false);

right2 = new TalonSRX(3);
 //addChild("right2",right2);
 right2.setInverted(false);
/*
leftEnc = new Encoder(0, 1, false, EncodingType.k4X);
 addChild("leftEnc",leftEnc);
 leftEnc.setDistancePerPulse(1.0);

rightEnc = new Encoder(2, 3, false, EncodingType.k4X);
 addChild("rightEnc",rightEnc);
 rightEnc.setDistancePerPulse(1.0);
*/

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    public void resetAdjustments()
    {
        angleAdjust = 0;
        rangeAdjust = 0;
    }

    public void adjustAngle(int angleAdjustment)
    {
        if((angleAdjust > -100) && (angleAdjust < 100))
        {
            angleAdjust += angleAdjustment;

        }else{
            if((angleAdjust == -100) && (angleAdjustment > 0))
            {
                angleAdjust += angleAdjustment;

            }

            if((angleAdjust == 100) && (angleAdjustment < 0))
            {
                angleAdjust += angleAdjustment;

            }
        }
    }

    public void adjustRange(int rangeAdjustment)
    {
        if((rangeAdjust > -100) && (rangeAdjust < 100))
        {
            rangeAdjust += rangeAdjustment;

        }else{
            if((rangeAdjust == -100) && (rangeAdjustment > 0))
            {
                rangeAdjust += rangeAdjustment;

            }

            if((rangeAdjust == 100) && (rangeAdjustment < 0))
            {
                rangeAdjust += rangeAdjustment;

            }
        }
    } 

    public double getRange()
    {
        return range;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1)
        {
            double goalAngle = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            range = (Constants.goalHeight - Constants.cameraHeight) / Math.abs(Math.tan(Math.toRadians(Constants.cameraAngle) + Math.toRadians(goalAngle)));
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

