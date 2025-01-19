package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.PairedMotors;

public class shootSubsystem extends SubsystemBase {
    public final PairedMotors shooter;

    public shootSubsystem() {
        shooter = new PairedMotors(Constants.EXAMPLE_MAIN_PAIRED_INFO, Constants.EXAMPLE_SLAVE_PAIRED_INFO);
        shooter.SetRampRate(0.75);
    }

    public void start(double speed) {
        shooter.Spin(-Math.abs(speed));
    }

    public void stop() {
        shooter.Spin(0);
    }

    public double getShooterRotation() {
        return shooter.mainMotor.inBuiltEncoder.getPosition();
    }

    public boolean isShooterReady(double targetSpeed) {
        return shooter.mainMotor.inBuiltEncoder.getVelocity() <= -Math.abs(targetSpeed);
    }
    public BooleanSupplier isShooterReadySupplier(double targetSpeed) {
        return () -> isShooterReady(targetSpeed);
    }
}
