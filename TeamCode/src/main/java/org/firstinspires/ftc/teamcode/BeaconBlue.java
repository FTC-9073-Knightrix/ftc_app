package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue Beacon", group = "Beacon Push")

/**
 * Created by nicolasbravo on 11/14/16.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public class BeaconBlue extends Telemetry{
    @Override
    public void start(){
        super.start();
    }
    @Override
    public void loop(){
        if(blue && !red){
            MoveRightBeacon(true);
            MoveLeftBeacon(false);
        }
        else if(red && !blue){
            MoveLeftBeacon(true);
            MoveRightBeacon(false);
        }
        else if((!blue && !red) || (blue && red)){
            MoveRightBeacon(false);
            MoveLeftBeacon(false);
        }
        UpdateTelemetry();
    }
}
