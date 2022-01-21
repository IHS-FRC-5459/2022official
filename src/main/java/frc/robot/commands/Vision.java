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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Drive;
import frc.robot.commands.pixy2java.*;
import frc.robot.subsystems.DriveSub;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */ 
public class Vision extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        //private final DriveSub m_DriveSub;
        public PixyCam2 pixy; 
        public String mode;

 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public Vision()
    {
        pixy = new PixyCam2(0x54);
    }

    public Vision (String mode) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.mode = mode;
        pixy = new PixyCam2(0x54);
        //m_DriveSub = driveSub;
        //addRequirements(m_DriveSub);  

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    // here


    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(mode.equals("enable"))
        {
            System.out.println("enable");
            pixy.SetLamps(true, true);

        }
        if(mode.equals("disable"))
        {
            System.out.println("disable");
            pixy.SetLamps(false, false);
        }
    }

    //center on target
    public void center()
    {
        /*
        double pwr = 0.1;
        int cameraCenter = 160;
        int blockCenter = 0;
        int tolerance = 5;



        if((blockCenter - tolerance) > cameraCenter)
        {
            m_DriveSub.drive(-pwr, pwr);
            blockCenter = findBlockCenter();

        }else if((blockCenter + tolerance) < cameraCenter){
            m_DriveSub.drive(pwr, -pwr);
            blockCenter = findBlockCenter();

        }else{
            m_DriveSub.drive(0, 0);

        }
        */
    }

    public Boolean blocksInFrame()
    {
        Boolean exists = false;
        PixyCamBlock blocks[]= {};
        try{

        }catch (Exception e){
            blocks = pixy.getBlocks();
        } 

        if(blocks.length > 0){
            exists = true;
        }

        return exists; 
    }

    public int findBlockCenter()
    {
        int center = 0;
        PixyCamBlock centerBlock = pixy.GetCentermostBlock();
        System.out.println(centerBlock);

        return center;
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
