package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Timer;

@Autonomous(name = "Autonomous Red", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it
 */

public class Autonomous_Blue extends Telemetry {


    // used for keeping track of the state
    // used to determine TimeNow
   /* double Variance = 0;
    // The amount of time we want the robot to move
    double TimeNow = 0;
*/

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop()
    {
        if (timer_state == 0 && move_state == 0) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 1.5 && move_state == 0) {
            MoveMiddleDrive(-1);
            Timer2Reset();
        }
        else if (timer2 > 1.5 && move_state == 0) {
            move_state++;
            MoveMiddleDrive(0);
        }
        if (timer_state == 1 && move_state == 1) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 1.35 && move_state == 1) {
            MoveBallShooter(0.5);
            Timer2Reset();
        }
        else if (timer2 > 1.35 && move_state == 1) {
            move_state++;
            MoveBallShooter(0);
            MoveReleaseDrive(true);
        }
        if (timer_state == 2 && move_state == 2) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 1 && move_state == 2) {
            Timer2Reset();
        }
        else if (timer2 > 1 && move_state == 2) {
            move_state++;
        }
        if (timer_state == 3 && move_state == 3) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 1 && move_state == 3) {
            MoveReleaseDrive(false);
            MoveBallShooter(0.5);
            Timer2Reset();
        }
        else if (timer2 > 1 && move_state == 3) {
            move_state++;
            MoveBallShooter(0);
        }
        if (timer_state == 4 && move_state == 4) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 0.5 && move_state == 4) {
            MoveMiddleDrive(-1);
            Timer2Reset();
        }
        else if (timer2 > 0.5 && move_state == 4) {
            move_state++;
            MoveMiddleDrive(0);
        }
        if (timer_state == 5 && move_state == 5) {
            TimerReset();
            timer_state++;
        }
        if (timer2 <= 2.3 && move_state == 5) {
            MoveRobot(-1,-1);
            Timer2Reset();
        }
        else if (timer2 > 2.3 && move_state == 5) {
            move_state++;
            MoveRobot(0,0);
        }
        /*while(Range1.getDistance(DistanceUnit.CM) > 52) {
            MoveRobot(-1, -1);
        }
        MoveRobot(0,0);
        /*
        MoveMiddleDrive(-0.3);
        //While FrontLine is black
        while(FrontLine.getVoltage() >= LineTrackerVoltage);
        //Move MiddleDrive to the right by 0.5
        MoveMiddleDrive(0);
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
        //While FrontLine senses white
        while(Range1.getDistance(DistanceUnit.CM) > 11){
            MoveRobot(-1 * (LeftLine.getVoltage() * 0.2 * 0.5), -1 * (RightLine.getVoltage() * 0.2 * 0.5));
        }
        MoveRobot(0,0);
        if (red && !blue){
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
        timer = getRuntime();
        while(timer + getRuntime() < 2) {
            MoveRobot(-0.5,-0.5);
        }
        MoveRobot(0,0);
           /* case 8:
                if (getRuntime() > 10.5) {
                    MoveRobot(0,1);
                    move_state++;
                    break;
                }
            case 9:
                if (getRuntime() > 16.5) {
                    MoveRobot(1,1);
                    if (FrontLine.getVoltage() < 4) {
                        MoveRobot(1,0);
                    }
                    move_state++;
                    break;
                }
            case 10:
                if (getRuntime() > 22) {
                    while (LeftLine.getVoltage() >= 4) {
                        MoveRobot(0.8,1);
                    }
                    while (RightLine.getVoltage() >= 4) {
                        MoveRobot(1,0.8);
                    }
                    while (LeftLine.getVoltage() < 4 && RightLine.getVoltage() < 4 && FrontLine.getVoltage() < 4) {
                        MoveRobot(1,1);
                    }
                }*/
                // if none of the cases match up with move_state
        UpdateTelemetry();
        telemetry.addData("11", "Timer: " + timer2);
        telemetry.addData("12", "State: " + move_state);
        /*telemetry.addData("13", "Var: "+ Variance);
        telemetry.addData("14", "TimeNow: "+ TimeNow );*/
    }
}