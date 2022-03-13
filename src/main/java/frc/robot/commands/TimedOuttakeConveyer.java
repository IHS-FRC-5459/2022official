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
  WaitCommand wait3Command;
  public TimedOuttakeConveyer() {
    // Use addRequirements() here to declare subsystem dependencies.
     wait2Command = new WaitCommand(2);
     wait3Command = new WaitCommand(3);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    wait2Command.execute();
    if(wait2Command.isFinished())
    {
      wait3Command.execute();
      RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(-0.15);

    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(wait3Command.isFinished()){
      return true;
    }

    return false;  
  }
}
