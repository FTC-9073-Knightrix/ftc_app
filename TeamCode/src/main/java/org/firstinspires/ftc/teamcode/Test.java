package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
//@TeleOp(name = "Test", group = "Test Programs")

/**
 * Created by Nicolas Bravo on 10/8/16.
 * Testing
 * Moves the motor by pressing 'A'
 */

public class Test extends Telemetry{

    @Override
    public void start(){

        super.start();
    }

    //main loop
    @Override
    public void loop(){

        //Moves the motor when 'A' is pressed
        if(gamepad1.a)
        {

            MoveMiddleDrive(1);
        }
        else
        {

            MoveMiddleDrive(0);
        }

        //Shows telemetry data
        UpdateTelemetry();
    }
}
