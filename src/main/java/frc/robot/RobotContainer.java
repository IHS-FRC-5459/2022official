// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.filter.*;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import frc.robot.subsystems.*;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final ClimberSub m_climberSub = new ClimberSub();
    public final IntakeSub m_intakeSub = new IntakeSub();
    public final FlywheelSub m_flywheelSub = new FlywheelSub();
    public final DriveSub m_driveSub = new DriveSub();
    public final VisionSub m_visionSub = new VisionSub();
    public final ConveyerSub m_conveyerSub = new ConveyerSub();
    public final OuttakeConveyerSub m_outakeConveyerSub = new OuttakeConveyerSub();

// Joysticks
private XboxController xboxClimber = null;
private XboxController xbox = null;
private Joystick rightStick = null;
private Joystick leftStick = null;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  public RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems
    try
    {
      xbox = new XboxController(2);
    }catch(Exception e){
      System.out.println("Error w/ Xbox controller init");
    }

    try
    {
      rightStick = new Joystick(1);
    }catch(Exception e){
      System.out.println("Error w/ right joystick init");
    }

    try
    {
      leftStick = new Joystick(0);
    }catch(Exception e){
      System.out.println("Error w/ left joystick init");
    }
    
    try
    {
      xboxClimber = new XboxController(3);
    }catch(Exception e){
      System.out.println("Error w/ xbox climber controller  init");
    }
    // SmartDashboard Buttons
    //SmartDashboard.putData("Autonomous Command", new AutonomousCommand( m_driveSub ));
    //SmartDashboard.putData("Vision", new Vision( m_visionSub ));
    //SmartDashboard.putData("ReadyToFire", new ReadyToFire());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    //m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand( m_driveSub ));
    m_chooser.setDefaultOption("Autonomous Route", new AutoRoute());
    

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
    /*
    Vision lampOn = new Vision("enable");
    Vision lampOff = new Vision("disable");


    JoystickButton lampOnButton = new JoystickButton(rightStick, 1);
    JoystickButton lampOffButton = new JoystickButton(leftStick, 1);


    lampOnButton.debounce(0.25).whileActiveOnce(lampOn);
    lampOffButton.debounce(0.25).whileActiveOnce(lampOff);
    lampOffButton.cancelWhenActive(lampOn);
    lampOnButton.cancelWhenActive(lampOff);
    */

    


    MoveIntake intakePivotUp = new MoveIntake(-0.75, m_intakeSub);
    MoveIntake intakePivotDown = new MoveIntake(0.65, m_intakeSub);
    spinIntake spinIntakeIn = new spinIntake(-0.4, m_intakeSub);
    spinIntake spinIntakeOut = new spinIntake(0.4, m_intakeSub);
    OuttakeConvaer outakeConveyerIn = new OuttakeConvaer(-0.6);
    OuttakeConvaer outakeConveyerOut = new OuttakeConvaer(0.4);



    MoveConveyer conveyerMoveIn = new MoveConveyer(-0.6, -0.31);
    MoveConveyer conveyerMoveOut = new MoveConveyer(0.6, 0.3);

    JoystickButton OuttakeConvaerButton = new JoystickButton(xbox, 6); 
    //JoystickButton OuttakeConvaerButton = new JoystickButton(rightStick, 6); 
    OuttakeConvaerButton.whileHeld(outakeConveyerIn);
    OuttakeConvaerButton.whileHeld(conveyerMoveIn);

    JoystickButton intakePivotButtonDown = new JoystickButton(xbox, 3);
    //JoystickButton intakePivotButtonDown = new JoystickButton(rightStick, 12);
    intakePivotButtonDown.whileHeld(intakePivotDown);
    intakePivotButtonDown.cancelWhenActive(intakePivotUp);

    JoystickButton intakePivotButtonUp = new JoystickButton(xbox, 4);

    //JoystickButton intakePivotButtonUp = new JoystickButton(rightStick, 11);
    intakePivotButtonUp.whileHeld(intakePivotUp);
    intakePivotButtonUp.cancelWhenActive(intakePivotDown);


    JoystickButton button6 = new JoystickButton(xbox, 5);
    button6.whileHeld(outakeConveyerOut);
    button6.whileHeld(conveyerMoveOut);


    JoystickButton spinIntakeInward = new JoystickButton(xbox, 1);
    //JoystickButton spinIntakeInward = new JoystickButton(rightStick, 7);
    spinIntakeInward.toggleWhenPressed(spinIntakeIn);
    
    //JoystickButton spinFlywheelButton = new JoystickButton(rightStick, 4);
    //spinFlywheelButton.toggleWhenPressed(flywheelSpinny);

    JoystickButton conveyerButton = new JoystickButton(xbox, 1);
    //JoystickButton conveyerButton = new JoystickButton(rightStick, 8);
    conveyerButton.toggleWhenPressed(conveyerMoveIn);
    conveyerButton.toggleWhenPressed(outakeConveyerOut);
    conveyerButton.cancelWhenPressed(spinIntakeOut);
    conveyerButton.cancelWhenPressed(conveyerMoveOut);


    JoystickButton reverseIntake = new JoystickButton(xbox, 2);
    reverseIntake.toggleWhenPressed(spinIntakeOut);
    JoystickButton conveyerOut = new JoystickButton(xbox, 2);
    conveyerOut.toggleWhenPressed(conveyerMoveOut);
    conveyerOut.cancelWhenPressed(conveyerMoveIn);
    conveyerOut.cancelWhenPressed(spinIntakeIn);



    //-------

    JoystickButton winchReleaseButton = new JoystickButton(xboxClimber, 3);
    ClimberWinchRelease winchRelease = new ClimberWinchRelease();
    ClimberWinch climberWinch = new ClimberWinch();

    winchReleaseButton.whileHeld(winchRelease);
    winchReleaseButton.cancelWhenActive(climberWinch);

    JoystickButton winchInButton = new JoystickButton(xboxClimber, 4);
    winchInButton.whileHeld(climberWinch);
    winchInButton.cancelWhenActive(winchRelease);

    ClimberPivot pivotUp = new ClimberPivot(1);
    ClimberPivot pivotDown = new ClimberPivot(-0.75);
    JoystickButton pivotClimberUpButton = new JoystickButton(xboxClimber, 2);
    pivotClimberUpButton.whenActive(pivotUp);
    pivotClimberUpButton.cancelWhenActive(pivotDown);

    JoystickButton pivotClimberDownButton = new JoystickButton(xboxClimber, 1);
    pivotClimberDownButton.whenActive(pivotDown);
    pivotClimberDownButton.cancelWhenActive(pivotUp);




    

    

    /*
    Vision rangeDown = new Vision("rangeDown");
    Vision angleUp = new Vision("angleUp");
    Vision angleDown = new Vision("angleDown");
    Vision rangeUp = new Vision("rangeUp");
    Vision reset = new Vision("reset");


    JoystickButton a = new JoystickButton(xbox, 1);
    JoystickButton b = new JoystickButton(xbox, 2);
    JoystickButton x = new JoystickButton(xbox, 3);
    JoystickButton y = new JoystickButton(xbox, 4);
    JoystickButton xbox8 = new JoystickButton(xbox, 8);
    

    a.debounce(0.1).whileActiveContinuous(rangeDown, true);
    b.debounce(0.1).whileActiveContinuous(angleUp, true);
    x.debounce(0.1).whileActiveContinuous(angleDown, true);
    y.debounce(0.1).whileActiveContinuous(rangeUp, true);
    xbox8.debounce(0.1).whileActiveContinuous(reset, true);

*/





    // Binds an ExampleCommand to be scheduled when both the 'X' and 'Y' buttons of the driver gamepad are pressed


    

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }



    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getleftStick() {
        return leftStick;
    }

public Joystick getrightStick() {
        return rightStick;
    }

public XboxController getxbox() {
      return xbox;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }


  

}

