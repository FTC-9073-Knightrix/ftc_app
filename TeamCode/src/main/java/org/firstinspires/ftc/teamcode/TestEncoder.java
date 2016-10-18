package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//adds the file to the FtcOpmodeRegister
@TeleOp(name = "Test (Encoder)", group = "Test Programs")

/**
 * Created by Nicolas Bravo on 10/15/16.
 * Testing
 * Moves the motor until a certain point with the joystick, and does a full rotation when you press "A"
 */

public class TestEncoder extends Telemetry{
    @Override
    public void start(){
        super.start();
    }

    //Checks if the user is using the joystick and stops the rotation
    public boolean Reset = true;
    //Sets the value of a full rotation
    public static final int ONE_ROTATION = 1120;
    //Keeps up with the value of "ONE_ROTATION" and never resets (keeps on getting larger)
    public int counter = ONE_ROTATION;
    //Keeps track with the amount of rotations done
    public int times = 0;
    //A variable that keeps track of Motor 1's position
    public int position = 0;

    //main loop
    @Override
    public void loop(){
        //If the gamepad 1 is being used (does not equal 0)
        if(gamepad1.left_stick_y != 0){
            //Set the mode of Motor 1 to run without encoders
            Motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //Makes the power to Motor 1 equal 0.3 times the value of gamepad 1's left stick
            MoveMotor1(gamepad1.left_stick_y * 0.2);
            //Makes "Reset" true
            Reset = true;
            //Makes "position" equal Motor 1's current position
            position = Motor1.getCurrentPosition();
        }
        //If "Reset" is true and gamepad 1 is not in use (does equal 0)
        if (Reset && gamepad1.left_stick_y == 0){
            //Sets the power of Motor 1 to 0
            MoveMotor1(0);
            //Sets "Reset" to false
            Reset = false;
        }
        //If gamepad 1's "A" button is being pressed and "Reset" is false
        if (gamepad1.a && !Reset){
            //Sets the target position of Motor 1 to "counter" so that it can go back to its original position
            Motor1.setTargetPosition(position + 950);
            //Makes "position" equal Motor 1's current position
            position = Motor1.getCurrentPosition();
            //A loop that runs while Motor 1's current position is less than "counter"
            while (Motor1.getCurrentPosition() < position + 950){
                //Sets Motor 1 at full speed
                Motor1.setPower(1);
                //Sets the mode of Motor 1 to run to the position set ("counter")
                Motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //Makes "times" equivalent to "counter" divided by "ONE_ROTATION" (the amount of full rotations done by the motor)
                times = counter / ONE_ROTATION;
            }
            //A loop that runs while the current position of Motor 1 is greater than "counter"
            while (Motor1.getCurrentPosition() > position + 950){
                //Makes "counter" equal "counter" plus "ONE_ROTATION"
                counter += ONE_ROTATION;
                //Sets the power of Motor 1 to 0
                Motor1.setPower(0);
                //Makes "times" equivalent to "counter" divided by "ONE_ROTATION" (the amount of full rotations done by the motor)
                times = counter / ONE_ROTATION;
                //Makes "position" equal to the current position of Motor 1
                position = Motor1.getCurrentPosition();
            }
        }

        //Updates the data shown on the phone display
        updateTelemetry();
    }

    //Adds telemetry data to the phone display
    public void updateTelemetry(){
        super.UpdateTelemetry();
        //Shows the number of rotations done by the motor
        telemetry.addLine("# Of Rotations: " + times);
        //Shows the total distance the motor has traveled
        telemetry.addLine("Counter: " + counter);
        //Shows if the user is currently controlling the motor
        telemetry.addLine ("User Controlling: " + Reset);
    }
}
