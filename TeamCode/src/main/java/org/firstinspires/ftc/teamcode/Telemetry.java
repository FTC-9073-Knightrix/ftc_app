package org.firstinspires.ftc.teamcode;

/**
 * Created by Nicolas on 10/8/16.
 * Shows data on the phone
 */

public abstract class Telemetry extends HardwareMap{
    public void UpdateTelemetry (){
        //telemetry data goes here
        telemetry.addLine(Double.toString(getRuntime()));
        telemetry.addLine("Motor 1 Speed: " + Motor1.getPower());
        telemetry.addLine("Encoder Position: " + Motor1.getCurrentPosition());
        telemetry.addLine("Motor Mode: " + Motor1.getMode());
    }
}
