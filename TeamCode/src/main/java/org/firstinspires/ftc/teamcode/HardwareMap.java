package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by nicolasbravo on 10/8/16.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public abstract class HardwareMap extends OpMode{

    //declare the motors
    DcMotor Motor1;

    @Override
    public void init(){
        //add the names for the motors
        Motor1 = hardwareMap.dcMotor.get("M1");
    }

    //classes
    void MoveMotor1 (double Power){
        if (Motor1 != null){
            Motor1.setPower (Power);
        }
    }
}