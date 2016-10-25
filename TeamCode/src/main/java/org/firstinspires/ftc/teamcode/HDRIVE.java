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
    //Main loop
    @Override
    public void loop(){
        /*
        //If gamepad 1's left stick is not equal to 0
        if (gamepad1.left_stick_y != 0){
            //Move the left motor by the value of gamepad 1's left stick
            MoveLeftDrive(gamepad1.left_stick_y);
        }
        //If gamepad 1's right stick is not equal to 0
        if (gamepad1.right_stick_y != 0){
            //Move the right motor by the value of gamepad 1's right stick
            MoveRightDrive(gamepad1.right_stick_y);
        }
        */

        //If gamepad 1's left dpad is pressed
        if (gamepad1.dpad_left){
            //Move the middle motor left
            MoveMiddleDrive(-1);
        }
        //Else if gamepad 1's right dpad is pressed
        else if (gamepad1.dpad_right){
            //Move the middle motor right
            MoveMiddleDrive(1);
        }
        //Else if gamepad 1's left dpad and right dpad are not pressed
        else if (!gamepad1.dpad_left && !gamepad1.dpad_right){
            //Stop the middle motor
            MoveMiddleDrive(0);
        }
        MoveLeftDrive(gamepad1.left_stick_y);
        MoveRightDrive(gamepad1.right_stick_y);

        //Displays telemetry data
        UpdateTelemetry();
    }
}

