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
        telemetry.addLine("Time: " + Double.toString(getRuntime()));
        //Middle Wheel
        telemetry.addLine("~Middle Motor~");
        telemetry.addLine("Speed: " + (MiddleDrive.getPower() * 100) + "%");
        telemetry.addLine("Encoder Position: " + MiddleDrive.getCurrentPosition());
        //Left Wheel
        telemetry.addLine("~Left Motor~");
        telemetry.addLine("Speed: " + (LeftDrive.getPower() * 100) + "%");
        //Right Wheel
        telemetry.addLine("~Right Motor~");
        telemetry.addLine("Speed: " + (RightDrive.getPower() * 100) + "%");
        //Ball Pickup
        telemetry.addLine("~Ball Pickup~");
        telemetry.addLine("Speed: " + (PickupDrive.getPower() * 100) + "%");

        //Range Sensor & Optical Sensor
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Ultrasonic: " + Range1.getDistance(DistanceUnit.CM) + " cm");

        //Color Sensor
        telemetry.addLine("~Color Sensor~");
        //If 'Color1' is not null
        if (Color1 != null){
            //If there is more blue than red
            if (Color1.blue() > Color1.red()){
                //Displays the color of the beacon as blue
                telemetry.addLine("Beacon Color: Blue");
                //Sets 'blue' as true and 'red' as false
                blue = true;
                red = false;
            }
            //Else if there is more red than blue
            else if (Color1.red() > Color1.blue()){
                //Displays the color of the beacon as red
                telemetry.addLine("Beacon Color: Red");
                //Sets 'blue' as false and 'red' as true
                blue = false;
                red = true;
            }
            //Else if they are the same value but not zero
            else if (Color1.blue() == Color1.red() && Color1.blue() != 0 && Color1.red() != 0){
                //Displays the color of the beacons as both
                telemetry.addLine("Beacon Color: Both");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }
            //Else (if none apply)
            else{
                //Display the color of the beacon as neither
                telemetry.addLine("Beacon Color: Neither");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }
        }

        //Line Trackers
        telemetry.addLine("~Line Tracker~");
        //If FrontLine's voltage is below 4 (0-3.999)
        if (FrontLine.getVoltage() < LineTrackerVoltage){
            //Display the color as white
            telemetry.addLine("Color 1: White");
        }
        //Else (if FrontLine's voltage is 4 or greater
        else{
            //Display the color as black
            telemetry.addLine("Color 1: Black");
        }
        telemetry.addLine("Value 1: " + FrontLine.getVoltage());
        //If LeftLine's voltage is below 4 (0-3.999)
        if (LeftLine.getVoltage() < LineTrackerVoltage){
            //Display the color as white
            telemetry.addLine("Color 2: White");
        }
        //Else (if LeftLine's voltage is 4 or greater
        else{
            //Display the color as black
            telemetry.addLine("Color 2: Black");
        }
        telemetry.addLine("Value 2: " + LeftLine.getVoltage());
        //If RightLine's voltage is below 4 (0-3.999)
        if (RightLine.getVoltage() < LineTrackerVoltage){
            //Display the color as white
            telemetry.addLine("Color 3: White");
        }
        //Else (if RightLine's voltage is 4 or greater
        else{
            //Display the color as black
            telemetry.addLine("Color 3: Black");
        }
        telemetry.addLine("Value 3: " + RightLine.getVoltage());
    }
}
