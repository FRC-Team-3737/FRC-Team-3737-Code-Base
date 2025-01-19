package frc.robot.utils;

/*  First import your auto routines you wish to use.  */

import frc.robot.auto.Routines.ExampleAutoRoutine;

/*  Then, add your subsystems which you need.  */

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSingleMotorSubsystem;
import frc.robot.subsystems.SubsystemList;
import frc.robot.subsystems.ExampleDoubleMotorSubsystem;

/*  Finally import the smart dashboard, sendable chooser, and command.  */

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoPicker {
    
    /*  First you will make your variables for the subsystems.  */

    /*  Then, you need to initialize the sendable chooser.  */

    private final SendableChooser<Command> sendableChooser = new SendableChooser<>();

    /*  Now create the Constructor. Make sure to declare your subsystems, chooser options and where the data is going.  */

    public AutoPicker(SubsystemList subsystems) {

        sendableChooser.setDefaultOption("Choose Auto", null);
        Command[] autoCommands = {
            new ExampleAutoRoutine(subsystems)
        };

        this.SetAutoRoutines(autoCommands);

        SmartDashboard.putData(sendableChooser);
    }

    public Command GetAuto() {

        return sendableChooser.getSelected();

    }

    private void SetAutoRoutines(Command[] routines) {
        for (Command routine : routines) {
            String name = routine.getName();
            sendableChooser.addOption(name, routine);
        }
    }

}
