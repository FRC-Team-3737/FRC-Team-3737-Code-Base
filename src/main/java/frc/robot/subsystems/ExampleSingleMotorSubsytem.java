package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;

public class ExampleSingleMotorSubsytem extends SubsystemBase {

    /*  This is what a single motor subsystem will look like, in other words, a system with not secondary motor to depend on.
        This is also what a transitional based subsystem not rotational based subsytem will look like.
        Below is where the variables are made, which can be declared directly, in the constructor, or later in the code.  
        Above is where the imports and the class are. A class is always "public class {filename} {}" and can optionally have extensions.
        Imports are from other libraries or files and allow you to use methods under those classes.  */

    private final Motors motor;
    private final AnalogInput sensor;
    private final DigitalInput microswitch;
    private double desiredPosition;

    /* In Java, there are 3 Access Modifier keywords that you may see used in front of various classes, methods, variables, etc.
     * These keywords and their meanings are: 
     * 
     * "public": this keyword allows anything inside or outside of the class to access the element it's used on.  For example, this
     * class is a public class, and can be called from any file within this project
     * 
     * "protected": this keyword is used to limit access to only the class it's defined in, or any subclasses of that class.  In this case,
     * the "motorSpeed" variable below is used in this class here, and in the subclass "ExampleDoubleMotorSubsystem".  Outside of that,
     * no other file may access this variable.
     * 
     * "private": this keyword restricts usage of the element it's used on to only be usable within the class it's created in.  No other files
     * or classes, even subclasses of the class that it's used in, may access that element.
     */

    protected double motorSpeed;
    
    public ExampleSingleMotorSubsytem() {

        /*  Here, the constructor, is where you declare any final variables, or other variables you wish to start with a specified value.
            Variables can be declared here or with the variable itself instead of the constructor.
            The constructor will always be "public {filename}() {}"  */

        motor = new Motors(Constants.EXAMPLE_INFO);
        motorSpeed = 0;
        desiredPosition = 0;

        /*  When aquiring a piece, you might want a surefire way to make sure you have it. You will either have a sensor or a microswitch.  */

        sensor = new AnalogInput(0);
        microswitch = new DigitalInput(0);

    }

    /*  Below are the private methods, only able to be used in this file, we keep these above the public methods for ease of access.
        In a single motor transitional subsystem, there is normally no need for a private method, but I've exampled 2 below that could be used.  */

    private double CurrentPosition() {

        /*  The in built encoder only get the amount of rotations the motor makes. It is useless for continuous movements, such as an intake.  */

        return motor.motor.inBuiltEncoder.getPosition();

    }

    @SuppressWarnings("unused")
    private boolean IsInDesiredZone(double deadzone) {

        /*  This is a way to create a deadzone that can say for the arm to stop, or say slow down. This can be used to define a safezone.
            This creates an allowed leeway point, because we know that an angle can never be perfect. This is done by adding a zone of acceptance.
            Example 20 > 21 - 4 && 20 < 21 + 4 returns true, because it is within the 8 unit acceptence zone.  */

        return desiredPosition > this.CurrentPosition() - deadzone && desiredPosition < this.CurrentPosition() + deadzone;

    }

    /*  Below are the public methods, our practice is to put Stop() at the bottom of the public pile and Move() or similar methods above it.
        The only exception is debugging information, that gets put at the very bottom and will be a get method, meaning it will return a value.
        Any other public method, such as a set, get, or other method, will go above both Stop() and Move() or similar methods.
        In the public organization, please do sets, then gets, than any other method, move methods, stop method, then debugging info.  */

    public void SetSpeed(double speed) {

        /*  This sets the motor speed variable to whatever was inputted.  */

        motorSpeed = Math.abs(speed);

    }

    public void SetTargetPosition(double target) {

        /*  This sets the desired position variable to whatever was inputted.  */

        desiredPosition = target;

    }

    /*  The following IsObjectIn/IsObjectOut are considered gets because you are recieving a variable from them. We use these to know when to move on to the next line of a command.
        They could also be used as a way to know when to stop a command all together. Such as, if a motor temperature gets too hot, it cuts off the function.
        You will see Switch and Sensor. A switch is a true/false value while a sensor returns a voltage value. The switch will always be more reliable then a sensor.
        You might have the signs switched around, depending on how the robot is set up. These were both from Synthwave, before and after the switch.  */

    public Boolean SwitchObjectIn() {

        return !microswitch.get();

    }

    public Boolean SwitchObjectOut() {

        return microswitch.get();

    }

    public Boolean SensorObjectIn() {

        return sensor.getVoltage() < 0.01;

    }

    public Boolean SensorObjectOut() {

        return sensor.getVoltage() > 0.1;

    }

    /*  The following 2 methods are a replacement of Move() and splits it into two simple actions. Normally when moving there is no need for overcomplex move systems.
        The exception are in double motor rotational systems or even multi motor transtional and rotational systems. Those systems often use more complex move systems.
        Remember a positive motor value is not always a outwards/forwards/up movement, it depends on how the motor was put in. Same goes for negative, as you could guess.
        Note: you could use an input instead of a variable for the motorspeed, but it makes more sense normally to have a speed variable and not part of the move.  */

    public void PositiveMove() {

        /*  The motor will spin in the positive direction, at the given speed. (0 to 1)  */

        motor.Spin(motorSpeed);

    }

    public void NegativeMove() {

        /*  The motor will spin in the negative direction, at the given speed. (-1 to 0)  */

        motor.Spin(-motorSpeed);

    }

    public void Stop() {

        /*  This is very self explanatory, when this is called it stops the motor.  */

        motor.Spin(0);

    }

    public String[] GetDebuggingInfo() {

        return motor.GetDebuggingInformation();

    }

}
