package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Nicolas on 10/8/16.
 * Defines the motors
 */

public abstract class HardwareMap extends OpMode{

    //Declare variables
    public int Color;
    public double Voltage1;
    //Declare the motors
    DcMotor Motor1;
    //Declare the sensors
    ModernRoboticsI2cRangeSensor Range1;
    ColorSensor Color1;
    AnalogInput Line1;

    @Override
    public void init(){
        //Motor1
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor1.setDirection(DcMotor.Direction.FORWARD);

        //Range1
        Range1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "R1");

        //Color1
        Color1 = hardwareMap.colorSensor.get("C1");

        //Line1
        Line1 = hardwareMap.analogInput.get("L1");
    }
    //Classes
    void MoveMotor1 (double Power){
        if (Motor1 != null){
            Motor1.setPower (Power);
        }
    }
}