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
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.commands.LinearInterpolator;


// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class FlywheelSub extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    /*
private Encoder flywheelEncoder;
private TalonSRX flywheelMotor;
*/

WPI_TalonFX flywheelMaster = new WPI_TalonFX(6);
WPI_TalonFX flywheelFollow = new WPI_TalonFX(7);
SimpleMotorFeedforward feedforward;
PIDController pid;




LinearInterpolator linearInterpolate = new LinearInterpolator();
RobotContainer m_robotContainer = RobotContainer.getInstance();

double rpm = 0;



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public FlywheelSub() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
//flywheelEncoder = new Encoder(8, 9, false, EncodingType.k4X);
 //addChild("flywheelEncoder",flywheelEncoder);
 //flywheelEncoder.setDistancePerPulse(1.0);

 feedforward = new SimpleMotorFeedforward(Constants.ff_kS, Constants.ff_kV, Constants.ff_kA);
            
 pid = new PIDController(Constants.pid_kP, Constants.pid_kI, Constants.pid_kD);




        flywheelMaster.set(TalonFXControlMode.PercentOutput, 0);
        flywheelFollow.set(TalonFXControlMode.PercentOutput,  0);
        flywheelMaster.setNeutralMode(NeutralMode.Coast);
        flywheelFollow.setNeutralMode(NeutralMode.Coast);
        flywheelMaster.setInverted(TalonFXInvertType.CounterClockwise);
        flywheelFollow.setInverted(TalonFXInvertType.CounterClockwise);




    }


    public double calcSetPoint(){
        double output = 0;

        double setpoint = LinearInterpolator.calcRPM(RobotContainer.getInstance().m_visionSub.getRange());
        output = (MathUtil.clamp(pid.calculate(getRotationsPerMinute(), setpoint), -1, 1)) + feedforward.calculate(Constants.ff_kV, Constants.ff_kA);

        return output;
    }


    public double calcFromRPM(double revpermin){
        double output = 0;

        double setpoint = revpermin;
        output = (MathUtil.clamp(pid.calculate(getRotationsPerMinute(), setpoint), -1, 1)) + feedforward.calculate(Constants.ff_kV, Constants.ff_kA);

        return output;
    }

    public double calcPIDOutput()
    {
        double setpoint = calcSetPoint();
        double output = (MathUtil.clamp(pid.calculate(getRotationsPerMinute(), setpoint), -1, 1)) + feedforward.calculate(Constants.ff_kV, Constants.ff_kA);
        return output;
    }

    public double calcPIDOutput(double distance)
    {
        double setpoint = LinearInterpolator.calcRPM(distance);

        double output = (MathUtil.clamp(pid.calculate(getRotationsPerMinute(), setpoint), -1, 1)) + feedforward.calculate(Constants.ff_kV, Constants.ff_kA);
        return output;
    }

    public double getRotationsPerMinute()
    {
        double RPM = (flywheelMaster.getSelectedSensorVelocity() * 600) / (Constants.kSensorUnitsPerRotation);
        return RPM;
    }
    
    public void setMotors(double pwr)
    {
        flywheelMaster.set(ControlMode.PercentOutput, pwr);
        flywheelFollow.set(ControlMode.PercentOutput, pwr);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        getRotationsPerMinute();

    }

    public double getRequiredRPM()
    {
        return rpm;
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

