package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class PairedMotors {
    
    public final Motor mainMotor;
    public final Motor slaveMotor;

    public PairedMotors(MotorInfo main, MotorInfo slave, encoderType encoder, boolean inverted) {
        
        this.mainMotor = new Motor(main, encoder, inverted);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }

    public PairedMotors(MotorInfo main, MotorInfo slave) {
        
        this.mainMotor = new Motor(main, encoderType.None, false);
        this.slaveMotor = new Motor(slave, encoderType.None, false);

    }

    public void Spin(double speed) {
        
        mainMotor.motor.set(speed);
        slaveMotor.motor.set(-speed);

    }
    
    public void SetRampRate(double rate) {

        mainMotor.motorConfig.closedLoopRampRate(rate);
        slaveMotor.motorConfig.closedLoopRampRate(rate);

    }

    public double GetVelocity() {

        return (mainMotor.inBuiltEncoder.getVelocity() + slaveMotor.inBuiltEncoder.getVelocity())/2;

    }

}
