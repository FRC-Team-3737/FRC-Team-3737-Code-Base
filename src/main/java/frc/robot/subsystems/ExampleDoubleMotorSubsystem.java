package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.utils.PID;
import frc.robot.Constants;
import frc.robot.motor.PairedMotors;
import frc.robot.motor.Motor.encoderType;

public class ExampleDoubleMotorSubsystem extends SubsystemBase {

    /*  The following subsystem will be an example of a paired motor rotational subsystem. It will include both normal and complex features.
        Paired motors work just like normal motors, except they are often put in a place where they work opposite of each other.
        As normal, we will start by declaring variables and making a constructor.  */

    private final PairedMotors motor;
    private final PID pid;
    private boolean rotationActive;
    private double desiredAngle;
    private double p;
    private double i;
    private double d;

    public ExampleDoubleMotorSubsystem() {

        /*  Here we are declaring variables. The motor variable is set to inverted because you don't know how the encoder can be oriented.
            We chose the absolute encoder type because that is the most common type we use for rotation.  */

        motor = new PairedMotors(Constants.EXAMPLE_MAIN_PAIRED_INFO, Constants.EXAMPLE_SLAVE_PAIRED_INFO, encoderType.Absolute, true);
        pid = new PID(p, i, d);
        pid.PIDTuning(p, i, d);
        pid.ToleranceConfig(1);
        pid.ContinuousInput(0, 360);

        desiredAngle = 0;
        rotationActive = false;

    }
    
    private double GetCurrentAngle() {

        /*  Gets the current angle from the absolute encoder from the Motor class.  */

        return motor.mainMotor.getAbsoluteAngle();

    }

    private boolean IsInDeadzone(double deadzone) {

        /*  Checks to see if the desired angle and current angle are close enough together to be in the deadzone.  */

        return desiredAngle > GetCurrentAngle() - deadzone && desiredAngle < GetCurrentAngle() + deadzone;

    }
    
    public void SetDesiredAngle(double angle) {

        desiredAngle = angle;

    }

    public boolean IsReady(double deadzone) {

        /*  If it is in the deadzone with a low enough velocity it will return true and the command can finish.  */

        return IsInDeadzone(deadzone) && motor.GetVelocity() < 100;

    }

    public void ActivateRotation() {

        /*  Activates the rotation so that the rotation doesn't start prematurely. It also makes sure it doesn't try to continuously run.  */

        rotationActive = true;

    }

    public void PivotToTarget() {

        /*  Checks to see if the rotation is active, and if its not, returns, leaving the method. 
            Has a safety check then supplies the PID value if its within that check. */

        if (!rotationActive) return;

        double angle = GetCurrentAngle();
        double minAngle = 20;
        double maxAngle = 120;

        if (angle < minAngle) {
            Stop();
            return;
        } else if (angle > maxAngle) {
            Stop();
            return;
        } 

        double pidValue = pid.GetPIDValue(angle, desiredAngle);

        Rotate(pidValue);

    }

    public void Rotate(double speed) {

        motor.Spin(speed);

    }

    public void Stop() {

        motor.Spin(0);
        rotationActive = false;

    }
    
}
