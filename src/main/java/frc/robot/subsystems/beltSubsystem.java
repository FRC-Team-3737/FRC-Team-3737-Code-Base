 
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.Motors;

public class beltSubsystem extends SubsystemBase {
    private final Motors belt;
   
    
    public beltSubsystem() {
        belt = new Motors(Constants.EXAMPLE_ALTERNATE_INFO);
    }
    public void load(double speed) {
        belt.Spin(-Math.abs(speed));
    }

    public void retract(double speed){
        belt.Spin(Math.abs(speed));
    }

    public void stop() {
        belt.Spin(0);
    }
    
}
