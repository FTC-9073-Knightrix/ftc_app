package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicolas on 10/8/16.
 * Defines the motors
 */

public abstract class HardwareMap extends OpMode{

    //declare the motors
    DcMotor Motor1;

    @Override
    public void init(){
        //add the names for the motors
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor1.setDirection(DcMotor.Direction.FORWARD);
    }

    //classes
    void MoveMotor1 (double Power){
        if (Motor1 != null){
            Motor1.setPower (Power);
        }
    }
}