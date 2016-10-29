package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Nicolas Bravo on 10/8/16.
 * Program Hierarchy
 * Defines the motors
 */

public abstract class HardwareMap extends OpMode{
    //Declare the motors
    DcMotor MiddleDrive;
    DcMotor LeftDrive;
    DcMotor RightDrive;
    DcMotor PickupDrive;
    //Declare the sensors
    ModernRoboticsI2cRangeSensor Range1;
    ColorSensor Color1;
    AnalogInput Line1;

    @Override
    public void init(){
        //Motor1
        MiddleDrive = hardwareMap.dcMotor.get("M1");
        MiddleDrive.setDirection(DcMotor.Direction.REVERSE);
        //LeftDrive
        LeftDrive = hardwareMap.dcMotor.get("M2");
        LeftDrive.setDirection(DcMotor.Direction.REVERSE);
        //RightDrive
        RightDrive = hardwareMap.dcMotor.get("M3");
        RightDrive.setDirection(DcMotor.Direction.FORWARD);
        //BallPickup
        PickupDrive = hardwareMap.dcMotor.get("M4");
        PickupDrive.setDirection(DcMotor.Direction.FORWARD);
        //Range1
        Range1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "R1");

        //Color1
        Color1 = hardwareMap.colorSensor.get("C1");
        Color1.enableLed(false);

        //Line1
        Line1 = hardwareMap.analogInput.get("L1");
    }
    //Classes
    void MoveMiddleDrive (double Power){
        if (MiddleDrive != null){
            MiddleDrive.setPower (Power);
        }
    }
    void MoveLeftDrive (double Power){
        if (LeftDrive != null){
            LeftDrive.setPower (Power);
        }
    }
    void MoveRightDrive (double Power){
        if (RightDrive != null){
            RightDrive.setPower (Power);
        }
    }
    void MovePickupDrive (double Power){
        if (PickupDrive != null){
            PickupDrive.setPower (Power);
        }
    }
}