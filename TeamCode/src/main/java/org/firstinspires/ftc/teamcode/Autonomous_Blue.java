package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Autonomous Blue", group = "Autonomous9073")
/**
 * Created by vijay on 11/19/2016.
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
      /*      case 0:
                // adds one to the case
                move_state++;
                // gives a new value to Variance
                Variance = getRuntime();
                // resets the value of TimeNow
                TimeNow = 0;
                //moves to next state
                break;
            case 1:
                // moves the robot at 50% power
                MoveMiddleDrive(-1);
                // if the robot moved for at least 2 seconds
                if (TimeNow > 2) {
                    MoveMiddleDrive(0);
                    // adds one to the case
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves to the next case
                break;
            case 2:
                // stops the robot
                MovePickupDrive(0.5);
                // if the robot is stopped for at least .5 seconds
                if (TimeNow > 2) {
                    // adds one to the case
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves to the next case
                break;
            case 3:
                // turns the robot with 60% power on Motor 2
                MoveReleaseDrive(true);
                // if the robot moved for at least 1.5 seconds
                if ( TimeNow > 1) {
                    // adds one to move_state
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets the value of TimeNow
                    TimeNow = 0;
                }
                // moves on to the next case
                break;
            case 4:
                // stops the robot
                MovePickupDrive(2);
                // if the robot stopped for at least .5 seconds
                if ( TimeNow > .5) {
                    // adds one to the counter
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves on to the next case
                break;
                */
                // if none of the cases match up with move_state
            default:
                // end the movement of the robot
                break;
/*
            case 0:
                move_state++;
                break;

            case 1:
                if (getRuntime() > 2) {
                    MoveMiddleDrive(1);
                    move_state++;
                    break;
                }
            case 2:

                if (getRuntime() > 4) {
                    MoveBallShooter(0.5);
                    move_state++;
                    break;
                }
            case 3:
                if (getRuntime() > 6){
                    MoveReleaseDrive(true);
                    move_state++;
                    break;
                }
            case 4:
                if (getRuntime() > 8) {
                    MoveBallShooter(0.5);
                    move_state++;
                    break;
*/
        }
        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Clock: " + getRuntime());
        /*telemetry.addData("13", "Var: "+ Variance);
        telemetry.addData("14", "TimeNow: "+ TimeNow );
        */
    }
}