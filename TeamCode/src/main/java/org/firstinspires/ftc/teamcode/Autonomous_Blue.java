package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Autonomous Blue", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it
 */

public class Autonomous_Blue extends Telemetry {


    // used for keeping track of the state
    int move_state = 0;
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
                /* might need to reset motor */
                move_state++;
                //moves to next state
                break;

            case 1:
                if (getRuntime() > 1) {
                    MoveMiddleDrive(-1);
                    //if time is greater than one second then move forwards for 2 seconds
                    move_state++;
                    break;
                }
            case 2:
                if (getRuntime() > 2.175) {
                    MoveMiddleDrive(0);
                    //if time is greater than 3.5 second stop for one second
                    move_state++;
                }
            case 3:
                if (getRuntime() > 3.5) {
                    MoveBallShooter(0.5);
                    //if time is greater than 5 seconds point turn towards rescue zone
                    move_state++;
                    break;
                }
            case 4:
                if (getRuntime() > 5.5) {
                    MoveBallShooter(0);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    break;
                }
            case 5:
                if (getRuntime() > 6) {
                    MoveReleaseDrive(true);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    break;
                }
            case 6:
                if (getRuntime() > 7) {
                    MoveBallShooter(0.5);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    break;
                }
            case 7:
                if (getRuntime() > 9) {
                    MoveBallShooter(0);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    break;
                }
            case 8:
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
                }
                // if none of the cases match up with move_state
            default:
                // end the movement of the robot
                break;

        }
        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Clock: " + getRuntime());
        /*telemetry.addData("13", "Var: "+ Variance);
        telemetry.addData("14", "TimeNow: "+ TimeNow );
        */
    }
}