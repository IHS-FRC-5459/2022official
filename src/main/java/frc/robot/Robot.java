// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoRoute;
import frc.robot.commands.Drive;
import frc.robot.commands.LinearInterpolator;
import frc.robot.commands.SpinFlywheelDumb;
import frc.robot.commands.SpinFlywheelVision;
import frc.robot.commands.Vision;
import frc.robot.subsystems.DriveSub;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command driveCommand;
  private Vision pixycam;

  
  DecimalFormat df = new DecimalFormat("0.00");

  public RobotContainer m_robotContainer;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.

  


    m_robotContainer = RobotContainer.getInstance();
    driveCommand = new Drive(m_robotContainer.m_driveSub);
    //flywheelSpinny = new SpinFlywheelDumb(m_robotContainer.m_flywheelSub, 36, 80);

    SmartDashboard.putNumber("flywheel pwr", 0);




  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.


    CommandScheduler.getInstance().run();
    
    
    String S="tx="+
     String.format("%.01f", m_robotContainer.getInstance().m_visionSub.tx)+
      " ty=" +
       String.format("%.01f", m_robotContainer.getInstance().m_visionSub.ty);
  

  SmartDashboard.putString("range: ", df.format(m_robotContainer.m_visionSub.getRange()/12) + "ft");
  
  SmartDashboard.putBoolean("target in vision", m_robotContainer.m_visionSub.blocksInFrame());
 

  SmartDashboard.putNumber("Flywheel RPM", m_robotContainer.m_flywheelSub.getRotationsPerMinute());
  SmartDashboard.putNumber("Drivebase Distance", m_robotContainer.getInstance().m_driveSub.getEncoderDistance());
  try
  {
      double rpm = LinearInterpolator.calcRPM(m_robotContainer.m_visionSub.getRange());
      SmartDashboard.putNumber("Needed RPM", rpm);

  }catch(Exception e)
  {
      System.out.println(e);
  }
  //double pwr = SmartDashboard.getNumber("flywheel pwr", 0);
  //m_robotContainer.m_flywheelSub.setMotors(pwr);
}

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    m_autonomousCommand = new AutoRoute();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();

    }

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      m_robotContainer.m_driveSub.drive(0, 0);


    }



    driveCommand.schedule();


    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {


    driveCommand.execute();
    /*
      try{
        SmartDashboard.putBoolean("Target In Frame", pixycam.blocksInFrame());
        SmartDashboard.putNumber("Distance from Center (VISION)", pixycam.findBlockCenter());
        SmartDashboard.putNumber("Block Height", pixycam.blockAngle());
      }catch(Exception e){
        System.out.println("Whoops");
      }

      SmartDashboard.putNumber("Angle Adjustment %", pixycam.getAngleAdjust());
      SmartDashboard.putNumber("Range Adjustment %", pixycam.getRangeAdjust());



      */
      

    if(m_robotContainer.getxbox().getRightTriggerAxis() > 0.5)
    {
      RobotContainer.getInstance().m_flywheelSub.setMotors(0.666); //change flywheel speed here :)
    }else{
      RobotContainer.getInstance().m_flywheelSub.setMotors(0);

    }


      
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
