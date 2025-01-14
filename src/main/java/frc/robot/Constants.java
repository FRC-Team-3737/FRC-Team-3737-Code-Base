// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.motor.MotorInfo;
import frc.robot.motor.MotorInfo.controllerType;
import frc.robot.swervemodule.SwerveModuleInfo;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final double MODULE_ANGLE_KP = -0.00524;

  public static final int DRIVE_CONTROL_PORT = 0;
  public static final int OPERATOR_CONTROL_PORT = 1;
  public static final int BUTTON_BOARD_PORT = 2;

  //Center triangle is ID 15, Left triangle is ID 9
  public static final int BackRightDriveID = 5;
  public static final int BackRightSteerID = 6;
  public static final int BackLeftDriveID = 7;
  public static final int BackLeftSteerID = 8;
  public static final int FrontRightDriveID = 1;
  public static final int FrontRightSteerID = 2;
  public static final int FrontLeftDriveID = 3;
  public static final int FrontLeftSteerID = 4;
  public static final int ExampleID = 9;
  public static final int ExampleMainPairedID = 10;
  public static final int ExampleSlavePairedID = 11;
  public static final int ExampleAlternateID = 12;

  // Swerve
  public static final SwerveModuleInfo FRONT_RIGHT = new SwerveModuleInfo(FrontRightDriveID, FrontRightSteerID, 3.371094, 360, 82.5, -1, -1);
  public static final SwerveModuleInfo FRONT_LEFT = new SwerveModuleInfo(FrontLeftDriveID, FrontLeftSteerID, 3.410156, 360, 113, -1, 1);
  public static final SwerveModuleInfo BACK_RIGHT = new SwerveModuleInfo(BackRightDriveID, BackRightSteerID, 3.433594, 360, 88.25, 1, -1);
  public static final SwerveModuleInfo BACK_LEFT = new SwerveModuleInfo(BackLeftDriveID, BackLeftSteerID, 3.496094, 360, 98.5, 1, 1);

  // Robot
  // The MAX_ENCODER_VALUE is used to say what the max encoder value is, while REFERENCE_ANGLE is the starting angle. Both are currently unused, and are most likely going to get depracated.
  public static final MotorInfo EXAMPLE_INFO = new MotorInfo(ExampleID, controllerType.MAX, 0, 0);
  public static final MotorInfo EXAMPLE_MAIN_PAIRED_INFO = new MotorInfo(ExampleMainPairedID, controllerType.MAX, 0, 0);
  public static final MotorInfo EXAMPLE_SLAVE_PAIRED_INFO = new MotorInfo(ExampleSlavePairedID, controllerType.MAX, 0, 0);
  public static final MotorInfo EXAMPLE_ALTERNATE_INFO = new MotorInfo(ExampleAlternateID, controllerType.MAX, 0, 0);

  public static final class Swerve {
    public static final Translation2d flModuleOffset = new Translation2d(0.28, 0.28);
    public static final Translation2d frModuleOffset = new Translation2d(0.28, -0.28);
    public static final Translation2d blModuleOffset = new Translation2d(-0.28, 0.28);
    public static final Translation2d brModuleOffset = new Translation2d(-0.28, -0.28);

    public static final double maxModuleSpeed = 4.5; // M/S
  }

}