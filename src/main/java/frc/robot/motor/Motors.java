package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class Motors {
    
    public final Motor motor;

    /*  The following is method overloading, allowing different inputs to be possible by using different parameters with a same name method.  */
    
    public Motors(MotorInfo motor, encoderType encoder, boolean inverted) {

        this.motor = new Motor(motor, encoder, inverted);

    }

    public Motors(MotorInfo motor) {

        this.motor = new Motor(motor, encoderType.None, false);

    }

    public void SetRampRate(double rate) {

        motor.motorConfig.closedLoopRampRate(rate);

    }

    public double GetTemperature() {

        return motor.motor.getMotorTemperature();

    }

    public double GetCurrent() {

        return motor.motor.getOutputCurrent();

    }

    public double GetVelocity() {

        return motor.inBuiltEncoder.getVelocity();

    }

    public void Spin(double speed) {

        motor.motor.set(speed);

    }
    
    public String[] GetDebuggingInformation() {

        String[] info = {
            String.valueOf(motor.motor.getDeviceId()), 
            String.valueOf(motor.motor.getMotorType()), 
            String.valueOf(motor.motor.getFirmwareVersion()), 
            String.valueOf(motor.motor.getBusVoltage()), 
            String.valueOf(motor.motor.getAbsoluteEncoder()), 
            String.valueOf(motor.motor.getAnalog()), 
            String.valueOf(motor.motor.getStickyFaults()), 
            String.valueOf(motor.motor.getFaults()), 
            String.valueOf(motor.motor.getStickyWarnings()), 
            String.valueOf(motor.motor.getWarnings())
        };

        return info;

    }

}
