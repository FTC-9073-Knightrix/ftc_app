package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Red Testing", group = "Autonomous")
/**
 * Created by Vijay Rudraraju on 12/3/16.
 * For use in the Autonomous Portion of the match
 * Stops at the white line after going sideways
 */

public class AutonomousLineTesting extends Telemetry {

    int move_state = 0;

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void loop(){
        /*while(FrontLine.getVoltage() >= 4){
            MoveMiddleDrive(-1);
        }

        MoveMiddleDrive(0);*/

        switch (move_state){
            case 0:
                //Move to next case
                move_state++;
                break;
            case 1:
                MoveMiddleDrive(-0.3);
                //While FrontLine is black
                while(FrontLine.getVoltage() >= LineTrackerVoltage);
                    //Move MiddleDrive to the right by 0.5
                MoveMiddleDrive(0);
                //Move to next case
                move_state++;
                break;
            case 2:
                //While all the line trackers are black
                while(RightLine.getVoltage() >= LineTrackerVoltage || LeftLine.getVoltage() >= LineTrackerVoltage || FrontLine.getVoltage() >= LineTrackerVoltage)
                {
                    //While LeftLine is black
                    while (LeftLine.getVoltage() >= LineTrackerVoltage)
                    {
                        //Move MiddleDrive left by 0.2
                        MoveMiddleDrive(0.15);
                    }
                    MoveMiddleDrive(0);
                    //While RightLine is black
                    while (RightLine.getVoltage() >= LineTrackerVoltage)
                    {
                        //Move MiddleDrive right by 0.2
                        MoveMiddleDrive(-0.15);
                    }
                    MoveMiddleDrive(0);
                }
                //Move to next case
                move_state++;
                break;
            case 3:
                //While FrontLine senses white
                while(Range1.getDistance(DistanceUnit.CM) > 10){
                    MoveRobot(-1 * (LeftLine.getVoltage() * 0.2 * 0.5), -1 * (RightLine.getVoltage() * 0.2 * 0.5));
                }
                MoveRobot(0,0);
                move_state++;
                break;
            default:
                //Stop the program
                break;
        }

        telemetry.addLine("Case: " + move_state);
        UpdateTelemetry();
    }
}
