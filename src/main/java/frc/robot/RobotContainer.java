// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Base Command Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;

// Controller Imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// Command Imports
import frc.robot.commands.DriveCommands.DriveStopCommand;
import frc.robot.commands.DriveCommands.TeleopMoveCommand;

// Subsystem Imports
import frc.robot.subsystems.DriveSubsystem;

// Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {
  DriveSubsystem drive = new DriveSubsystem();
  
  CommandXboxController commandDriverController = new CommandXboxController(Constants.DRIVE_CONTROL_PORT);
  XboxController driverController = new XboxController(Constants.DRIVE_CONTROL_PORT);
  CommandXboxController commandOperatorController = new CommandXboxController(Constants.OPERATOR_CONTROL_PORT);
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROL_PORT);
  CommandGenericHID buttonBoard = new CommandGenericHID(Constants.BUTTON_BOARD_PORT);

  public RobotContainer() {
    drive.setDefaultCommand(new DriveStopCommand(drive));

    configureBindings();
  }

  
  private void configureBindings() {

    SmartDashboard.putData("Reset Gyro", new InstantCommand(()->{DriveSubsystem.resetGyro(0);}));

    // Driver Triggers

    commandDriverController.axisGreaterThan(0, 0.1)
      .or(commandDriverController.axisLessThan(0, -0.1))
      .or(commandDriverController.axisGreaterThan(1, 0.1))
      .or(commandDriverController.axisLessThan(1, -0.1))
      .or(commandDriverController.axisGreaterThan(2, 0.1))
      .or(commandDriverController.axisGreaterThan(3, 0.1))
      .or(commandDriverController.leftBumper())
      .or(commandDriverController.rightBumper())
      .onTrue(new TeleopMoveCommand(drive, driverController));

    // Operator Triggers

    displayDashboard();
  }

  public Command getAutonomousCommand() {
    return null;
  }

  public void displayDashboard() {
    // SMART DASHBOARD
    // import.method();
    
    // SHUFFLEBOARD
    // Shuffleboard.getTab("TabName").addDouble("DisplayName", () -> import.method())
    //   .withWidget("Widget")
    //   .withPosition(x, y);

  }
}