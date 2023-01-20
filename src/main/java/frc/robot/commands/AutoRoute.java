// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveForward;
import frc.robot.commands.TurnToAngle;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.WaitCommand;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutoRoute extends SequentialCommandGroup {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutoRoute(){

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    addCommands(
        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );

        //new TimedDrive(0.3, 10)
        
        new TimedIntakeMove(),
        //new ParallelRaceGroup(new MoveForward(0.2, 50, 0.3), new AutoIntakeSeries()),
        new ParallelRaceGroup(new TimedDrive(0.2, 2.5), new AutoIntakeSeries(2.5)),        new ParallelRaceGroup(new WaitCommand(2), new AutoIntakeSeries(2)),

        new BallSpacer(),
        //new MoveForward(0.2, 13, 0.3),
        //new MoveForward(0.2, -20, 0.3),

        new TimedDrive(-0.2, 1.25),
        new TurnToAngle(165, 0.2),


        new EjectAuto(),

        new TimedDrive(-0.4, 1.4)

//----=-----
        //new ParallelRaceGroup(new MoveForward(0.2, 10, 0.05), new AutoIntakeSeries())
        //new AutoIntakeSeries()
       //new MoveForward(0.4,27.666,1),
        //new TimedDrive(-0.35, 1.1),
        //new EjectAuto()
        //new TurnToAngle(180, 1, 0.3)

        //new MoveForward(0.4,36,1),
        //new TimedDrive(-0.4, 1.5)
 


        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
