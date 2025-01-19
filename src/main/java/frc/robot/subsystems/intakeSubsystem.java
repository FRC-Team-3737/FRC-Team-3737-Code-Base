package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.motor.Motors;
import frc.robot.Constants;

public class intakeSubsystem extends SubsystemBase {
    private final Motors motor;
    private double motorSpeed;

    public intakeSubsystem() {
        motor = new Motors(Constants.EXAMPLE_INFO);


    }

   public void SetSpeed(double speed){
    motorSpeed = Math.abs(speed);
   }
   
    public void PositiveMove() {
      
        motor.Spin(motorSpeed);
    }

    public void NegativeMove() {
      
        motor.Spin(-motorSpeed);
    }

    public void Stop() {

        motor.Spin(0);


    }
}
