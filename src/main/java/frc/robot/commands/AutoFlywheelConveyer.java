// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;

public class AutoFlywheelConveyer extends CommandBase {

  double m_tolerance;
  FlywheelSub m_flywheelSub;
  /** Creates a new AutoFlywheelConveyer. */
  public AutoFlywheelConveyer(FlywheelSub subsystem, double tolerance) {

    m_tolerance = tolerance;
    m_flywheelSub = subsystem;
    addRequirements(m_flywheelSub);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((RobotContainer.getInstance().m_flywheelSub.getRotationsPerMinute() >= RobotContainer.getInstance().m_flywheelSub.getRequiredRPM() - m_tolerance) && (RobotContainer.getInstance().m_flywheelSub.getRotationsPerMinute() <= RobotContainer.getInstance().m_flywheelSub.getRequiredRPM() + m_tolerance))
    {
      RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(-0.75);
      //add part for other conveyer ahh oo ee 
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
