// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ClimberPivot extends CommandBase {
  double pwr;
  /** Creates a new ClimberPivot. */
  public ClimberPivot(double pwr) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.pwr = pwr;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.getInstance().m_climberSub.pivot(0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.getInstance().m_climberSub.pivot(pwr);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getInstance().m_climberSub.pivot(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
