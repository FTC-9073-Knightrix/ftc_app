package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
@TeleOp(name = "H Drive", group = "H Drive Programs")

/**
 * Created by Nicolas Bravo & Vijay Rudraraju on 10/24/16.
 * Testing
 * Moves the motors on the H Drive
 */

public class HDRIVE extends Telemetry{
    //Start
    @Override
    public void start(){
        super.start();
    }

    boolean leftBumperBoolean = false;
    boolean rightBumperBoolean = false;

    //Main loop
    @Override
    public void loop(){
        //If gamepad 2's left bumper is pressed and 'leftBumperBoolean' is false
        if(gamepad2.left_bumper && leftBumperBoolean == false){
            //Move the ball release
            //MoveReleaseDrive();
            //Set 'leftBumperBoolean' to true
            leftBumperBoolean = true;
        }
        //Else if gamepad 2's left bumper is not pressed and 'leftBumperBoolean' is true
        else if(!gamepad2.left_bumper && leftBumperBoolean == true){
            //Set 'leftBumperBoolean' to false
            leftBumperBoolean = false;
        }

        if(gamepad2.right_bumper){
            MoveBallShooter(0.5);
        }
        else if(!gamepad2.right_bumper){
            MoveBallShooter(0);
        }
        /*
        //If gamepad 2's right bumper is pressed and 'rightBumperBoolean' is false
        if(gamepad2.right_bumper && rightBumperBoolean == false){
            //Shoot the ball
            MoveBallShooter();
            //Set 'rightBumperBoolean' to true
            rightBumperBoolean = true;
        }
        //Else if gamepad 2's right bumper is not pressed and 'rightBumperBoolean' is true
        else if(!gamepad2.right_bumper && rightBumperBoolean == true){
            //Set 'rightBumperBoolean' to false
            rightBumperBoolean = false;
        }
        */
        //If gamepad 2's 'A' button is pressed
        if(gamepad2.a){
            //Move the ball pickup at full speed
            MovePickupDrive(1);
        }
        //Else if gamepad 2's 'A' button is pressed
        else if(gamepad2.b){
            //Move the ball pickup backwards at full speed
            MovePickupDrive(-1);
        }
        //Else (if gamepad 1's 'A' button is not pressed)
        else{
            //Stop the ball pickup
            MovePickupDrive(0);
        }
        //If gamepad 1's left bumper is pressed
        if(gamepad1.left_bumper){
            //Move the left beacon presser
            MoveLeftBeacon(true);
        }
        //Else if gamepad 1's left bumper is not being pressed
        else if(!gamepad1.left_bumper){
            //Move the left beacon presser back to its original position
            MoveLeftBeacon(false);
        }
        //If gamepad 1's right bumper is pressed
        if(gamepad1.right_bumper){
            //Move the right beacon presser
            MoveRightBeacon(true);
        }
        //Else if gamepad 1's right bumper is not being pressed
        else if(!gamepad1.right_bumper){
            //Move the right beacon presser back to its original position
            MoveRightBeacon(false);
        }
        //Move the middle wheel at the speed of the right trigger minus the left trigger (the right trigger being a positive value and the left being negative)
        MoveMiddleDrive(gamepad1.right_trigger-gamepad1.left_trigger);
        //Move the left wheel at the speed of gamepad 1's right stick
        MoveLeftDrive(gamepad1.right_stick_y);
        //Move the right wheel at the speed of gamepad 1's left stick
        MoveRightDrive(gamepad1.left_stick_y);

        //Displays telemetry data
        UpdateTelemetry();
    }
}

