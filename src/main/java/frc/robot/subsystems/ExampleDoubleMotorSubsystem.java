package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.motor.PairedMotors;
import frc.robot.motor.Motor.encoderType;

public class ExampleDoubleMotorSubsystem extends SubsystemBase {

    /*  The following subsystem will be an example of a paired motor rotational subsystem. It will include both normal and complex features.
        Paired motors work just like normal motors, except they are often put in a place where they work opposite of each other.
        As normal, we will start by declaring variables and making a constructor.  */

    private final PairedMotors motor;
    private PIDController pid;
    private double rotationSpeed;
    private boolean rotationActive;
    private double desiredAngle;
    private GenericEntry p;
    private GenericEntry i;
    private GenericEntry d;

    public ExampleDoubleMotorSubsystem() {

        /*  The below section is no needed for PID, but helps with PID tuning, allowing us to set values in the dashboard.  */

        ShuffleboardTab tab = Shuffleboard.getTab("PivotInfo");
        p = tab.add("Proportional", 0.013)
            .withPosition(3, 0)
            .getEntry();
        i = tab.add("Integral", 0.002)
            .withPosition(3, 1)
            .getEntry();
        d = tab.add("Derivative", 0.0003)
            .withPosition(3, 2)
            .getEntry();

        /*  Here we are declaring variables. The motor variable is set to inverted because you don't know how the encoder can be oriented.
            We chose the absolute encoder type because that is the most common type we use for rotation.  */

        motor = new PairedMotors(Constants.EXAMPLE_MAIN_PAIRED_INFO, Constants.EXAMPLE_SLAVE_PAIRED_INFO, encoderType.Absolute, true);
        
        rotationSpeed = 0;
        desiredAngle = 0;
        rotationActive = false;

        /*  Calling the PIDConfig() method to set the PID.  */

        this.PIDConfig();

    }

    private void PIDConfig() {

        /*  The PID formula is: u(t) = Kp * e(t)  +  Ki * ∫ e(t) dt  +  Kd * de(t)/dt but has multiple variations possible.
            The continuous input turns the PID into a circle rather then a line. What you need it to do determines which option is more viable.
            The tolerance is where our angle has to be in order to stop and the derivative tolerance is how much its allowed to move to be considered complete.
            The intergrator range limits how much the integral affects your arm. There is an i zone which makes it reset over a certain point.  */

        this.pid = new PIDController(p.getDouble(0.013), i.getDouble(0.002), d.getDouble(0.0003));
        pid.enableContinuousInput(0, 360); // Limits PID to circle
        pid.setTolerance(1, 0.25);
        pid.setIntegratorRange(0.01, 0.25);

    }

    private double GetPIDValue(double angle) {

        /*  Calculates the PID value needed and then gets absoluted and given a sign based on the direction we need it to go.  */

        double pidValue = pid.calculate(angle, desiredAngle);

        if (desiredAngle >= angle) {
            return -Math.abs(pidValue);
        } else {
            return Math.abs(pidValue);
        }

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

    public boolean IsReady() {

        /*  If it is in the deadzone with a low enough velocity it will return true and the command can finish.  */

        return IsInDeadzone(1) && motor.GetVelocity() < 100;

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

        double pidValue = GetPIDValue(angle);

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
