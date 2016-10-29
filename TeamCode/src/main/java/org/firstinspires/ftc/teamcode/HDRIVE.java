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
    //Main loop
    @Override
    public void loop(){
        //If gamepad 1's 'A' button is pressed
        if(gamepad1.a){
            //Move the ball pickup at full speed
            MovePickupDrive(1);
        }
        //Else (if gamepad 1's 'A' button is not pressed)
        else{
            //Stop the ball pickup
            MovePickupDrive(0);
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

