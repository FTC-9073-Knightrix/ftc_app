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
        telemetry.addLine(Double.toString(getRuntime()));
        telemetry.addLine("~Motor 1~");
        telemetry.addLine("Speed: " + Motor1.getPower() + "%");
        telemetry.addLine("Encoder Position: " + Motor1.getCurrentPosition());
        telemetry.addLine("Mode: " + Motor1.getMode());
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Ultrasonic: " + Range1.getDistance(DistanceUnit.CM) + " cm");
        telemetry.addLine("Optical: " + Range1.cmOptical() + " cm");
        telemetry.addLine("~Color Sensor~");
        telemetry.addLine("RGB: " + (16 * Color1.red()) + ", " + (16 * Color1.green()) + ", " + (16 * Color1.blue()));
        if(Color == 0){
            telemetry.addLine("Beacon Color: Blue");
        }
        else if(Color == 1){
            telemetry.addLine("Beacon Color: Red");
        }
        else if(Color == 2){
            telemetry.addLine("Beacon Color: Other");
        }
        telemetry.addLine("~Line Tracker~");
        telemetry.addLine("Value: " + Voltage1);
    }
}
