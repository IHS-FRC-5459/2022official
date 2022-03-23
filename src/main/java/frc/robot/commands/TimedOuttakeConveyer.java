// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class TimedOuttakeConveyer extends CommandBase {
  /** Creates a new TimedOuttakeConveyer. */

  WaitCommand wait2Command;
  WaitCommand wait5Command;
  public TimedOuttakeConveyer() {
    // Use addRequirements() here to declare subsystem dependencies.
     wait2Command = new WaitCommand(2);
     wait5Command = new WaitCommand(5);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {    
    wait2Command.schedule();
    wait5Command.schedule();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(wait2Command.isFinished())
    {
      RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(-0.5);
      RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(-0.6);

    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0);
      RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(wait5Command.isFinished() && wait2Command.isFinished()){
      return true;
    }

    return false;  
  }
}
