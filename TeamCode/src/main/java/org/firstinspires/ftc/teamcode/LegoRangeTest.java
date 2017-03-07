package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Lego Range Testing", group = "Autonomous")

/**
 * Created by vijay on 3/4/2017.
 * What will replace the first step of the Autonomous Programs
 */

public class LegoRangeTest extends Telemetry {
    @Override
    public void start()

    {
        super.start();
    }

    //Main Loop
    @Override
    public void loop() {
        if (LegoRange.getUltrasonicLevel() < 46) {
            MoveMiddleDrive(-1);
        } else {
            MoveMiddleDrive(0);
        }
    UpdateTelemetry();
    }

}