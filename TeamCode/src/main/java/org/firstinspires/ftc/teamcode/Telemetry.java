package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Nicolas on 10/8/16.
 * Shows data on the phone
 */

public abstract class Telemetry extends HardwareMap{
    public void UpdateTelemetry (){
        //telemetry data goes here
        telemetry.addLine("~Gamepad 1~");
        telemetry.addLine("Right Trigger: " + gamepad1.right_trigger);
        telemetry.addLine("Left Stick: " + gamepad1.left_stick_y);
        telemetry.addLine("Right Stick: " + gamepad1.right_stick_y);
        telemetry.addLine("Dpad Left: " + gamepad1.dpad_left);
        telemetry.addLine("Dpad Right: " + gamepad1.dpad_right);
        //
        telemetry.addLine(Double.toString(getRuntime()));
        telemetry.addLine("~Middle Motor~");
        telemetry.addLine("Speed: " + MiddleDrive.getPower() + "%");
        telemetry.addLine("Encoder Position: " + MiddleDrive.getCurrentPosition());
        telemetry.addLine("Mode: " + MiddleDrive.getMode());
        telemetry.addLine("~Left Motor~");
        telemetry.addLine("Speed: " + LeftDrive.getPower() + "%");
        telemetry.addLine("Mode: " + LeftDrive.getMode());
        telemetry.addLine("~Right Motor~");
        telemetry.addLine("Speed: " + RightDrive.getPower() + "%");
        telemetry.addLine("Mode: " + RightDrive.getMode());
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Ultrasonic: " + Range1.getDistance(DistanceUnit.CM) + " cm");
        telemetry.addLine("Optical: " + Range1.cmOptical() + " cm");
        telemetry.addLine("~Color Sensor~");
        //telemetry.addLine("RGB: " + (4 * Color1.red()) + ", " + (4 * Color1.green()) + ", " + (4 * Color1.blue()));
        //If 'Color1' is not null
        if (Color1 != null){
            //If there is more blue than red
            if (Color1.blue() > Color1.red()){
                telemetry.addLine("Beacon Color: Blue");
            }
            //Else if there is more red than blue
            else if (Color1.red() > Color1.blue()){
                telemetry.addLine("Beacon Color: Red");
            }
            //Else if they are the same value but not zero
            else if (Color1.blue() == Color1.red() && Color1.blue() != 0 && Color1.red() != 0){
                telemetry.addLine("Beacon Color: Both");
            }
            //Else
            else{
                telemetry.addLine("Beacon Color: Neither");
            }
        }/*
        telemetry.addLine("~Line Tracker~");
        telemetry.addLine("Value: " + Voltage1);*/
    }
}
