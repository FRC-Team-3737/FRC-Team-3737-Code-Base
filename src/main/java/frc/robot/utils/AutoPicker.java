package frc.robot.utils;

/*  First import your auto routines you wish to use.  */

import frc.robot.auto.Routines.ExampleAutoRoutine;

/*  Then, add your subsystems which you need.  */

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSingleMotorSubsystem;
import frc.robot.subsystems.ExampleDoubleMotorSubsystem;

/*  Finally import the smart dashboard, sendable chooser, and command.  */

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoPicker {
    
    /*  First you will make your variables for the subsystems.  */

    private final DriveSubsystem drive;
    private final ExampleSingleMotorSubsystem singleMotorSubsystem;
    private final ExampleDoubleMotorSubsystem doubleMotorSubsystem;

    /*  Then, you need to initialize the sendable chooser.  */

    private final SendableChooser<Command> sendableChooser = new SendableChooser<>();

    /*  Now create the Constructor. Make sure to declare your subsystems, chooser options and where the data is going.  */

    public AutoPicker(DriveSubsystem m_drive, ExampleSingleMotorSubsystem m_singleMotorSubsystem, ExampleDoubleMotorSubsystem m_doubleMotorSubsystem) {

        drive = m_drive;
        singleMotorSubsystem = m_singleMotorSubsystem;
        doubleMotorSubsystem = m_doubleMotorSubsystem;

        sendableChooser.setDefaultOption("Choose Auto", null);
        sendableChooser.addOption("Example Auto", new ExampleAutoRoutine(singleMotorSubsystem, doubleMotorSubsystem, drive));

        SmartDashboard.putData(sendableChooser);

    }

    public Command GetAuto() {

        return sendableChooser.getSelected();

    }

}
