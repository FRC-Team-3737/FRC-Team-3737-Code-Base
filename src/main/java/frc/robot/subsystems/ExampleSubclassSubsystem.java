package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.motor.Motors;

/* This is what a double motor subsystem will look like, where there are 2 motors working in tandem to operate
 * a single mechanism.  This is also an example of how to build a subclass of another class, in this instance,
 * we're building ExampleDoubleMotorSubsystem (the subclass) on top of everything ExampleSingleMotorSubsytem
 * (the superclass) has already created.
 * 
 * Note: all additional/changed methods here only affect this class.  For this example, none of the changes here
 * will affect any part of the ExampleSingleMotorSubsytem class.
 */

public class ExampleSubclassSubsystem extends ExampleSingleMotorSubsystem {
    
    private final Motors motor;

    public ExampleSubclassSubsystem() {

        /* The "super()" call here will run everything in the superclass's constructor.  In this example,
         * "super()" calls the ExampleSingleMotorSubsytem method within the ExampleSingleMotorSubsytem class.
         * This is MANDITORY for all subclasses that change the constructor that a superclass has defined.
         */

        super();

        motor = new Motors(Constants.EXAMPLE_ALTERNATE_INFO);

    }

    /* Since we are building on top of the ExampleSingleMotorSubsytem superclass, we don't have to redefine
     * every single method created in the superclass (i.e. IsInDesiredZone, SetSpeed, SetTargetPosition, etc.).
     * The only methods we need to create here are the ones that are changed from how the superclass defines them,
     * or new methods that the superclass has not defined yet.
     */

    public void PositiveMove() {

        /* Similarly to how "super()" was used above, we can call the superclass's version of other methods,
         * the difference here being that calling the super of a method other than the constructor requires 
         * the method name to be attached, in this case ".PositiveMove" is added.
         * 
         * In contrast to the constructor above, it is not a manditory requirement to add a super call to any
         * other changed method.  If the original functionality of a method does not work with the changes needed
         * for the subclass, then it can simply be left out.
         */

        super.PositiveMove();
        motor.Spin(motorSpeed);

    }

    public void NegativeMove() {

        super.NegativeMove();
        motor.Spin(-motorSpeed);

    }

    public void Stop() {

        super.Stop();
        motor.Spin(0);


    }

    /* As with any other class, new methods may be added on top of what the superclass may provide. */

    public String[] GetDebuggingInfo() {

        return motor.GetDebuggingInformation();

    }

}
