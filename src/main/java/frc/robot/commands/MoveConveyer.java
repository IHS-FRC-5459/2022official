// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveConveyer extends CommandBase {
  /** Creates a new MoveConveyer. */
  double m_intakeConveyerPower = 0;
  double m_outtakeConveyerPower = 0;


  public MoveConveyer(double inConveyerPwr, double outConveyerPwr) {
    m_intakeConveyerPower = inConveyerPwr;
    m_outtakeConveyerPower = outConveyerPwr;

    // Use addRequirements() here to declare subsystem dependencies.
 }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(m_intakeConveyerPower );
   

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    RobotContainer.getInstance().m_conveyerSub.moveIntakeConveyer(0);
    


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
