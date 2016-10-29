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
        MoveMiddleDrive(gamepad1.right_trigger-gamepad1.left_trigger);
        MoveLeftDrive(gamepad1.right_stick_y);
        MoveRightDrive(gamepad1.left_stick_y);

        //Displays telemetry data
        UpdateTelemetry();
    }
}

