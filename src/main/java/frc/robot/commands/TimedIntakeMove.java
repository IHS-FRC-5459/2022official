// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TimedIntakeMove extends CommandBase {
  WaitCommand clock = new WaitCommand(1);

  
  double pwr;    

  /** Creates a new TimedIntakeMove. */
  public TimedIntakeMove() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    clock.schedule();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.getInstance().m_intakeSub.intakePivot(0.3);
    System.out.println(pwr);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().m_intakeSub.intakePivot(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(clock.isFinished()){
      return true;
    }
    return false;
  }
}
