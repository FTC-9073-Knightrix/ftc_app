package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "ViNi", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it
 */

public class ViNi extends Telemetry {


    // used for keeping track of the state
    double timer = getRuntime();
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
        switch (move_state)
        {
            case 0:
                // Move Sideways for 2 seconds
                while (getRuntime() <= 2) {
                    MoveMiddleDrive(-1);
                }
                MoveMiddleDrive(0);
                // After the while Move Shooter as well
                while (getRuntime() <= 4) {
                    MoveBallShooter(0.5);
                }
                MoveBallShooter(0);
                // 
                move_state++;
                break;

/**
                while (getRuntime() <= 2 && getRuntime() <= 3) {
                    MoveBallShooter(0.5);
                }
                MoveBallShooter(0);
                MoveReleaseDrive(true);
                while (getRuntime() >= 4.5 && getRuntime() <= 5.5) {
                    MoveBallShooter(0.5);
                }
                MoveBallShooter(0);
                move_state++;
                break;
            case 1:
                while(Range1.getDistance(DistanceUnit.CM) > 52 && getRuntime() >= 5.5) {
                    MoveRobot(-1, -1);
                }
                MoveRobot(0,0);
                move_state++;
                break;
            case 2:
                MoveMiddleDrive(-0.3);
                //While FrontLine is black
                while(FrontLine.getVoltage() >= LineTrackerVoltage);
                //Move MiddleDrive to the right by 0.5
                MoveMiddleDrive(0);
                //Move to next case
                move_state++;
                break;
            case 3:
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
            case 4:
                //While FrontLine senses white
                while(Range1.getDistance(DistanceUnit.CM) > 11){
                    MoveRobot(-1 * (LeftLine.getVoltage() * 0.2 * 0.5), -1 * (RightLine.getVoltage() * 0.2 * 0.5));
                }
                MoveRobot(0,0);
                move_state++;
                break;
            case 5:
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
                move_state++;
                break;
            case 6:
                timer = getRuntime();
                while(timer + getRuntime() < 2) {
                    MoveRobot(-0.5,-0.5);
                }
                MoveRobot(0,0);
                move_state++;
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
            default:
                // end the movement of the robot
                break;

        }
        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Clock: " + getRuntime());
        /*telemetry.addData("13", "Var: "+ Variance);
        telemetry.addData("14", "TimeNow: "+ TimeNow );*/
        
    }
}