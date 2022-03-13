// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class TimedFlywheel extends CommandBase {

  WaitCommand wait5Command;
  /** Creates a new TimedFlywheel. */
  public TimedFlywheel() {
    // Use addRequirements() here to declare subsystem dependencies.
    wait5Command = new WaitCommand(5);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wait5Command.execute();
    RobotContainer.getInstance().m_flywheelSub.setMotors(0.75);


    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().m_flywheelSub.setMotors(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(wait5Command.isFinished()){
      return true;
    }

    return false;  
  }  
}
