package frc.robot.utils;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class PID {

    private PIDController pid;
    private GenericEntry kP;
    private GenericEntry kI;
    private GenericEntry kD;
    
    public PID(double p, double i, double d) {

        /*  The PID formula is: u(t) = Kp * e(t)  +  Ki * ∫ e(t) dt  +  Kd * de(t)/dt but has multiple variations possible.
            The continuous input turns the PID into a circle rather then a line. What you need it to do determines which option is more viable.
            The tolerance is where our angle has to be in order to stop and the derivative tolerance is how much its allowed to move to be considered complete.
            The intergrator range limits how much the integral affects your arm. There is an i zone which makes it reset over a certain point.  */

            this.pid = new PIDController(kP.getDouble(p), kI.getDouble(i), kD.getDouble(d));

    }

    public void PIDTuning(double p, double i, double d) {

        /*  The below section is no needed for PID, but helps with PID tuning, allowing us to set values in the dashboard.  */

        ShuffleboardTab tab = Shuffleboard.getTab("PivotInfo");
        kP = tab.add("Proportional", 0.013)
            .withPosition(3, 0)
            .getEntry();
        kI = tab.add("Integral", 0.002)
            .withPosition(3, 1)
            .getEntry();
        kD = tab.add("Derivative", 0.0003)
            .withPosition(3, 2)
            .getEntry();

    }

    public void ContinuousInput(double continuousInputMin, double continuousInputMax) {

        pid.enableContinuousInput(continuousInputMin, continuousInputMax); 

    }

    public void ToleranceConfig(double errorTolerance) {
 
        pid.setTolerance(errorTolerance); 

    }

    public void ToleranceConfig(double errorTolerance, double errorDerivativeTolerance) {
 
        pid.setTolerance(errorTolerance, errorDerivativeTolerance); 

    }

    public void IntegratorConfig(double minimalIntegral, double maximumIntegral) {

        pid.setIntegratorRange(minimalIntegral, maximumIntegral);

    }

    public double GetPIDValue(double currentPosition, double desiredPosition) {

        /*  Calculates the PID value needed and then gets absoluted and given a sign based on the direction we need it to go.  */

        double pidValue = pid.calculate(currentPosition, desiredPosition);

        if (desiredPosition >= currentPosition) {
            return -Math.abs(pidValue);
        } else {
            return Math.abs(pidValue);
        }

    }

}
