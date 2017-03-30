package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by nicolasbravo on 2/6/17.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

//@TeleOp(name = "Gyro", group = "Test Programs")

public class GyroTest extends Telemetry
{
    //Start
    @Override
    public void start() {
        super.start();
    }
    //Main Loop
    @Override
    public void loop()
    {
        //Calibrate
        while (calibrated == false)
        {
            if (calibrate == false)
            {
                Gyro1.calibrate();
                calibrate = true;
            }
            if (Gyro1.isCalibrating() == false)
            {
                calibrated = true;
            }
        }

        degree = Gyro1.getHeading();
        telemetry.addLine("Gyro: " + degree);
    }
}
