package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

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
        //Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean RunToPosition = false;
    public boolean Resset = true;
    public static final int ONE_ROTATION = 1120;
    public int counter = ONE_ROTATION;
    public int times = 0;

    //main loop
    @Override
    public void loop(){

        if(gamepad1.left_stick_y != 0 ){
            Motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            MoveMotor1(gamepad1.left_stick_y * 0.3);
            Resset = true;
        }
        if (Resset && gamepad1.left_stick_y == 0 )
        {
            MoveMotor1(0);
            //Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Resset = false;

        }

        if (gamepad1.a && !Resset )
        {
            Motor1.setTargetPosition(counter);
            //RunToPosition = true;
            while (Motor1.getCurrentPosition() < counter) {
                Motor1.setPower(1);
                Motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                times =  counter / ONE_ROTATION;
            }
            while (Motor1.getCurrentPosition() > counter){
                counter += ONE_ROTATION;
                Motor1.setPower(0);
                times = counter / ONE_ROTATION;
            }

        }
       updateTelemetry();
    }

    public void updateTelemetry()
    {
        super.UpdateTelemetry();
        telemetry.addLine("Times: " + times);
        telemetry.addLine("Counter: " + counter);
        telemetry.addLine ("Reset: " + Resset);

    }
}
