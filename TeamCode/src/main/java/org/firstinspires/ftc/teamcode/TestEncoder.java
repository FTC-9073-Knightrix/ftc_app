package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
@TeleOp(name = "Test (Encoder)", group = "Test Programs")

/**
 * Created by Nicolas Bravo on 10/15/16.
 * Testing
 * Moves the motor until a certain point, and does a full rotation when you press 'A'
 */

public class TestEncoder extends Telemetry{

    @Override
    public void start(){

        super.start();
    }

    //main loop
    @Override
    public void loop(){

        if(gamepad1.left_stick_y != 0){

            MoveMotor1(gamepad1.left_stick_y);
        }
        else{

            MoveMotor1(0);
        }

        /*
        if(gamepad1.a)
        {

            MoveMotor1(1);
        }
        else
        {

            MoveMotor1(0);
        }
        */

        UpdateTelemetry();
    }
}
