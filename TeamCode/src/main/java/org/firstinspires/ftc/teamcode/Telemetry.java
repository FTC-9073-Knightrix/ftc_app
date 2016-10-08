package org.firstinspires.ftc.teamcode;

/**
 * Created by nicolasbravo on 10/8/16.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public abstract class Telemetry extends HardwareMap{

    public void UpdateTelemetry (){
        //telemetry data goes here
        telemetry.addLine(Double.toString(getRuntime()));
        telemetry.addLine("Motor 1 Speed: " + Motor1.getPower());
    }
}
