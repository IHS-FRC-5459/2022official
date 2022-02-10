// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.FileWriter;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoggingSub extends SubsystemBase {

  public PrintWriter printer;
  Boolean logFileFailed = true;

  /** Creates a new LoggingSub. */
  public LoggingSub() {
    try{
      printer = new PrintWriter("/home/lvuser/Output.txt");
      logFileFailed = false;
    }catch(Exception e){
      logFileFailed = true;
    }
  }

  public void clearLogFile()
  {
    if(logFileFailed)
    {
      printer.print("");
      printer.close();    
    }else{
      System.out.println("Log file clear operation failed");
    }
  }

  public void appendLogFile(String log)
  {
    if(logFileFailed)
    {
      printer.println(log);
    }else{
      System.out.println("Log file append operation failed");
    }
  }

  public void closeLogFile()
  {
    if(logFileFailed)
    {
      printer.close();
    }else{
      System.out.println("Log file close operation failed");
    }
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
