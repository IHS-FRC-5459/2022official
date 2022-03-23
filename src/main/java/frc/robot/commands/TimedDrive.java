// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TimedDrive extends CommandBase {
  double pwr;     
  double time;
  WaitCommand waittime; 

  /** Creates a new TimedDrive. */
  public TimedDrive(double pwr, double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.pwr = pwr;
    this.time = time;
    waittime = new WaitCommand(time);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    waittime.schedule();
    RobotContainer.getInstance().m_driveSub.drive(0, 0);
    RobotContainer.getInstance().m_driveSub.resetGyro();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yaw =     RobotContainer.getInstance().m_driveSub.getGyroAngle()/100;
    if(pwr < 0)
    {
      RobotContainer.getInstance().m_driveSub.drive(pwr + yaw, pwr - yaw);

    }else{
      RobotContainer.getInstance().m_driveSub.drive(pwr - yaw, pwr + yaw);

    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().m_driveSub.drive(0, 0);
    RobotContainer.getInstance().m_driveSub.resetGyro();


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(waittime.isFinished())
    {
      return true;
    }

    return false;
  }
}
