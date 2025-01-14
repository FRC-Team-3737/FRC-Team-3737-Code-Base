package frc.robot.motor;

public class MotorInfo {
    
    public final int ID;
    public final controllerType CONTROLLER;
    public final double MAX_ENCODER_VALUE;
    public final double REFERENCE_ANGLE;

    public enum controllerType {

        MAX, FLEX;

    }

    public MotorInfo(int MOTOR_CAN_ID, controllerType CONTROLLER, double MAX_ENCODER_VALUE, double REFERENCE_ANGLE) {
        
        this.ID = MOTOR_CAN_ID;
        this.CONTROLLER = CONTROLLER;
        this.MAX_ENCODER_VALUE = MAX_ENCODER_VALUE;
        this.REFERENCE_ANGLE = REFERENCE_ANGLE;
        
    }

}
