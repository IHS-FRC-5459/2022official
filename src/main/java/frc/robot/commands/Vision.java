// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveSub;
import frc.robot.commands.pixy2api.Pixy2;
import frc.robot.commands.pixy2api.Pixy2CCC;
import frc.robot.commands.pixy2api.Pixy2CCC.Block;
import frc.robot.commands.pixy2api.links.I2CLink;
import frc.robot.commands.pixy2api.links.SPILink;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.VisionSub;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */ 
public class Vision extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private DriveSub m_driveSub = null;
        public Pixy2 pixy; 
        public Pixy2CCC pixycam;
        public String mode = null;

        public RobotContainer m_robotContainer = RobotContainer.getInstance();
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS



    public Vision(DriveSub driveSub)
    {
        m_driveSub = driveSub;
        addRequirements(m_driveSub);  

    }


    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    // here


    }

    public int getAngleAdjust()
    {
        return m_robotContainer.m_visionSub.angleAdjust;
    }

    public int getRangeAdjust()
    {
        return m_robotContainer.m_visionSub.rangeAdjust;
    }


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {


        if(m_driveSub != null){
            if(blocksInFrame()){
                
                    center();
                
            }else{
                m_driveSub.drive(0,0);
            }

        }
    }

    public Boolean blocksInFrame()
    {
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

        return (tv >= 1.0 ? true : false);
    }


    //center on target
    public void center()
    {
        
        double pwr = 0.1;
        int cameraCenter = 0;
        int tolerance = 2;
        //int blockCenter = findBlockCenter();
        double degreesToBlock = m_robotContainer.getInstance().m_visionSub.tx;

        if(blocksInFrame()){
            if((degreesToBlock > cameraCenter - tolerance) && (degreesToBlock <  cameraCenter + tolerance)){
                //System.out.println(findBlockCenter());
    
                m_driveSub.drive(0,0);
            }else if((degreesToBlock > cameraCenter - tolerance)){
                m_driveSub.drive(-pwr, pwr);
            }else{
    
                m_driveSub.drive(pwr, -pwr);
            }
        }



    }



    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }


    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
