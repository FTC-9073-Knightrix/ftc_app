package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

//@Autonomous(name = "Red Beacon", group = "Beacon Push")

/**
 * Created by nicolasbravo on 11/14/16.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public class BeaconRed extends Telemetry{
    @Override
    public void start(){
        super.start();
    }
    @Override
    public void loop(){
        if(red && !blue){
            MoveRightBeacon(true);
            MoveLeftBeacon(false);
        }
        else if(blue && !red){
            MoveLeftBeacon(true);
            MoveRightBeacon(false);
        }
        else if((!blue && !red) || (red && blue)){
            MoveLeftBeacon(false);
            MoveRightBeacon(false);
        }
        UpdateTelemetry();
    }
}
