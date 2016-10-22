package org.firstinspires.ftc.teamcode;

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
        //If 'Color1' is not null
        if (Color1 != null){
            //If 'Color1' detects a color around blue
            if (Color1.blue() >= 2 && Color1.blue() <= 4){
                //'Color' is given the value of '0'
                Color = 0;
            }
            //Else if 'Color1' detects a color around red
            else if (Color1.red() >= 9 && Color1.red() <= 11){
                //'Color' is given the value of '1'
                Color = 1;
            }
            //Else
            else{
                //'Color' is given the value of '2'
                Color = 2;
            }
        }
        //If 'Line1' has a voltage above '0'
        if(Line1.getVoltage() > 0){
            //'Voltage1' is calculated (and 1 is subtracted)
            Voltage1 = (Line1.getVoltage() * 204.8) - 1;
        }
        //Else if 'Line1' has a voltage that is equal to '0'
        else if(Line1.getVoltage() == 0){
            //'Voltage1' is calculated (and 1 is NOT subtracted, because this value should not be negative
            Voltage1 = Line1.getVoltage() * 204.8;
        }
    }
}
