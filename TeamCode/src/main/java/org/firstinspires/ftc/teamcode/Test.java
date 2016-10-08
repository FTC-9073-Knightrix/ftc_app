package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
@TeleOp(name = "Test")

/**
 * Created by nicolasbravo on 10/8/16.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public abstract class Test extends Telemetry{

    @Override
    public void start(){

        super.start();
    }

    //main loop
    @Override
    public void loop(){

        if(gamepad1.a){

            MoveMotor1(1);
        }
        else{

            MoveMotor1(0);
        }

        UpdateTelemetry();
    }
}
