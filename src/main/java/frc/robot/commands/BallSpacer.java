// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class BallSpacer extends CommandBase {
  /** Creates a new BallSpacer. */
  WaitCommand clock2 = new WaitCommand(0.1);

  WaitCommand clock = new WaitCommand(0.25);
  public BallSpacer() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     clock.initialize();
     clock2.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((clock2.isFinished()))
    {
      RobotContainer.getInstance().m_intakeSub.intakeRoller(0);

      RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(0.4);
    RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0.25);
    }else{
      RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(-0.4);
      RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0.31);    
    }
;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().m_intakeSub.intakeRoller(0);

    RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(0);
  RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0);
  RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(0);
  RobotContainer.getInstance().m_outakeConveyerSub.moveOuttakeConveyer(0);    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(clock.isFinished())
    {
      return true;
    }
    return false;
  }
}
