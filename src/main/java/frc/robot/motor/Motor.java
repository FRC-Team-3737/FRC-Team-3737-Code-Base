package frc.robot.motor;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.motor.MotorInfo.controllerType;

public class Motor {
    
    public final SparkBase motor;
    public final SparkBaseConfig motorConfig;
    public SparkAnalogSensor analogEncoder;
    public SparkAbsoluteEncoder absoluteEncoder;
    public RelativeEncoder inBuiltEncoder;
    public final MotorInfo info;
    
    public enum encoderType {
        None ,Analog, Absolute;
    }

    public Motor(MotorInfo info, encoderType encoder) {

        this.info = info;

        if (info.CONTROLLER == controllerType.MAX) {
            this.motor = new SparkMax(info.ID, MotorType.kBrushless);
            this.motorConfig = new SparkMaxConfig();
            motorConfig.idleMode(IdleMode.kBrake);
            motor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        } else {
            this.motor = new SparkFlex(info.ID, MotorType.kBrushless);
            this.motorConfig = new SparkFlexConfig();
            motorConfig.idleMode(IdleMode.kBrake);
            motor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        }

        this.inBuiltEncoder = motor.getEncoder();

        if (encoder == encoderType.Analog) {
            this.analogEncoder = motor.getAnalog();
        } else if (encoder == encoderType.Absolute) {
            this.absoluteEncoder = motor.getAbsoluteEncoder();
        } 

    }

    public double getAnalogRawAngle() {
        
        double degreesPerVolt = 360/info.MAX_ENCODER_VALUE;
        double encoderVoltage = analogEncoder.getVoltage();

        double rawAngle = degreesPerVolt * encoderVoltage;

        return rawAngle;

    }

    public double getAnalogAngle() {
        
        return getAnalogRawAngle() - info.REFERENCE_ANGLE;

    }

    public double getAbsoluteRawAngle() {

        return absoluteEncoder.getPosition();

    }

    public double getAbsoluteAngle() {

        return getAbsoluteRawAngle() - info.REFERENCE_ANGLE;

    }

}
