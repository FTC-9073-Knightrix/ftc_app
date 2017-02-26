package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
//@TeleOp(name = "Test (Sensors)", group = "Test Programs")

/**
 * Created by Nicolas Bravo on 10/22/16.
 * Testing
 * For testing sensors such as the color sensor and line tracker
 */

public class SensorTest extends Telemetry{
    @Override
    public void start(){
        super.start();
    }

    @Override
    //Main loop
    public void loop(){
        /*
        //If 'Line1' has a voltage above '0'
        if(Line1.getVoltage() > 0){
            //'Voltage1' is calculated (and 1 is subtracted)
            Voltage1 = (Line1.getVoltage() * 204.8) - 1;
        }
        //Else if 'Line1' has a voltage that is equal to '0'
        else if(Line1.getVoltage() == 0){
            //'Voltage1' is calculated (and 1 is NOT subtracted, because this value should not be negative
            Voltage1 = Line1.getVoltage() * 204.8;
        }*/

        //Displays telemetry data
        UpdateTelemetry();
    }
}
