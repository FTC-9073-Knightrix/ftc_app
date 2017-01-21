package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Timer;

@Autonomous(name = "Autonomous Blue", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it and then presses the beacons
 */

public class Autonomous_Blue extends Telemetry{
    //Start
    @Override
    public void start() {
        super.start();
    }
    //Main Loop
    @Override
    public void loop()
    {
        //If this is the first time the timer is being reset
        if (timer_state == 0 && move_state == 0)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
    // Move_state == 0
    // Go Sideways
        //If the timer is less than or equal to 1.2 seconds and 'move_state' is 0
        // Do this for 1.5 seconds
        if (move_state == 0 && timer2 <= 1.5 )
        {
            //Move the robot
            MoveMiddleDrive(-1);
            //Reset the second timer which keeps up with the time change
            Timer2Reset();
        }
        //If it does not apply
        else if (move_state == 0 && timer2 > 1.5 )
        {
            //Move to the next state (NextState = 1)
            NextState();
        }

    // Move_State == 1
    // Shoots & Opens Servo
        // If timer_state is 1 (and move_state is also 1)
        if (move_state == 1 && timer_state == 1)
        {
            //Rest all timers
            TimerReset();
            timer_state++;
        }

        //If time timer is less than or equal to 1.35 seconds and 'move_state' is 1
        if (move_state == 1 && timer2 <= 1.35)
        {
            //Shoot the ball
            MoveBallShooter(0.5);
            //Reset the timer that keeps track of time change
            Timer2Reset();
        }
        //If it no longer applies
        else if (move_state ==1 && timer2 > 1.35)
        {
            //Move to the next state
            NextState();
            //Open the release mechanism
            MoveReleaseDrive(true);
        }
    // Move_state == 2
    // Waits 1 second for ball to drop in position
        // If 'timer_state' is 2
        if (timer_state == 2 && move_state == 2)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //Wait 1 second for the ball to transition
        if (timer2 <= 1 && move_state == 2)
        {
            Timer2Reset();
        }
        //If the timer is above 1
        else if (timer2 > 1 && move_state == 2)
        {
            //Move to the next state
            move_state++;
        }

    // Move_state == 3
    // Shoots 2nd ball
        // If 'timer_state' is 3
        if (timer_state == 3 && move_state == 3)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //If the timer is less than or equal to 1 and 'move_state' is 3
        if (timer2 <= 1.3 && move_state == 3)
        {
            //Close the release mechanism
            MoveReleaseDrive(false);
            //Shoot the ball
            MoveBallShooter(0.5);
            //Reset the timer that keeps track of time change
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 1.3 && move_state == 3)
        {
            //Move to the next state
            NextState();
        }
    // Move_state == 4
    // Goes Sideways towards whirlpool
        // If 'timer_state' is 4
        if (timer_state == 4 && move_state == 4)
        {
            //Reset all timers
            TimerReset();
            timer_state = 5;
        }
        //If the timer is less than or equal to 2 seconds and 'move_state' is 4
        if (timer2 <= 1.3 && move_state == 4)
        {
            //Move the robot
            MoveMiddleDrive(-.7);
            //Reset the timer that keeps track of time change
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 1.3 && move_state == 4)
        {
            //Move to the next state
            move_state = 5;
            //Stop the middle wheel
            MoveMiddleDrive(0);
        }
    // Move_state == 4.5
    // Spins the robot
        // If 'timer_state' equals 4.5
//        if (timer_state == 4.5 && move_state == 4.5)
//        {
//            //Reset all timers
//            TimerReset();
//            timer_state = 5;
//        }
//        //If the timer is less than or equal to 1.7 and the robot is in state 4.5
//        if (timer2 <= 1.45 && move_state == 4.5)
//        {
//            //Spin the robot halfway
//            MoveRobot(1,-1);
//            //Reset the timer that keeps up with changes in time
//            Timer2Reset();
//        }
//        //If it no longer applies
//        else if (timer2 > 1.45 && move_state == 4.5)
//        {
//            //Move to the next state
//            move_state = 5;
//            //Stop the motors
//            MoveRobot(0,0);
//        }
    // Move_state == 5
    // Goes Forward
        // If 'timer_state' is 5
        if (timer_state == 5 && move_state == 5)
        {
            //Reset all timers
            TimerReset();
            //timer_state = 5.1;
            timer_state = 5.5;
        }
        //If the timer is less than or equal to 2.3 and the robot is in state 5
        if (timer2 <= 3.6 && move_state == 5)
        {
            //Move the robot at full speed
            MoveRobot(0.7, 0.7);
            //Reset the timer that keeps track of change in time
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 3.6 && move_state == 5)
        {
            //Move to the next state
            MoveRobot(0,0);
            //move_state = move_state + 1.1;
            //move_state = 7;
            move_state = 6.5;
        }
        /**/
        if (timer_state == 5.5 && move_state == 6.5)
        {
            //Reset all timers
            TimerReset();
            timer_state = 6;
        }
        if (timer2 <= 1.65 && move_state == 6.5)
        {
            //Spin the robot halfway
            MoveRobot(1,-1);
            //Reset the timer that keeps up with changes in time
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 1.65 && move_state == 6.5)
        {
            //Move to the next state
            move_state = 7;
            //Stop the motors
            MoveRobot(0,0);
        }
        /**/
        /*//If the front line tracker detects white and the robot is in state 6
        if (FrontLine.getVoltage() >= LineTrackerVoltage && move_state == 6)
        {
            //Move the middle motor by -0.25
            MoveMiddleDrive(0.25);
        }
        //If it no longer applies
        else if (FrontLine.getVoltage() < LineTrackerVoltage && move_state == 6)
        {
            //Move to the next state
            move_state = move_state + 0.1;
            //Stop the middle motor
            MoveMiddleDrive(0);
        }*/

    // Move_state == 6.1
    // Move sideways
        //If 'timer_state' is 5.1
        /**if (timer_state == 5.1 && move_state == 6.1)
        {
            //Reset all timers
            TimerReset();
            timer_state = 5.2;
        }
        if (timer2 <= 3 && move_state == 6.1)
        {
            MoveMiddleDrive(1);
            Timer2Reset();
        }
        else if (timer2 > 3 && move_state == 6.1)
        {
            move_state = 6.2;
            MoveMiddleDrive(0);
        }
    // Move_state == 6.2
    // Move Aligns Robot with wall
        if (timer_state == 5.2 && move_state == 6.2)
        {
            TimerReset();
            timer_state = timer_state + 0.1;
        }
        //Move towards the wall
        if (timer2 <= 2.5 && move_state == 6.2)
        {
//            MoveRightBeacon(true);
//            MoveLeftBeacon(true);
            MoveRobot(-0.8,-0.8);
            Timer2Reset();
        }
        //Reached the wall
        else if (timer2 > 2.5 && move_state == 6.2)
        {
            move_state = move_state + 0.1;
            MoveRobot(0,0);
        }

    // Move_state == 6.3
    // Move away from the wall
        if (timer_state == 5.3 && move_state == 6.3)
        {
            TimerReset();
            timer_state = timer_state + 0.7;
        }
        //Move away from the wall
        if (timer2 <= 1 && move_state == 6.3)
        {
            MoveRobot(1,1);
            Timer2Reset();
        }
        else if (timer2 > 1 && move_state == 6.3)
        {
            move_state = move_state + 0.7;
            MoveRobot(0,0);
        }*/
    // Move_state == 7
    // Moves sideways trying to sense the 1st white line
        //If at least one line tracker detects black and the robot is in state 7
        if ((RightLine.getVoltage() > LineTrackerVoltage && LeftLine.getVoltage() > LineTrackerVoltage && FrontLine.getVoltage() > LineTrackerVoltage) && move_state == 7)
        {
            MoveMiddleDrive(0.2);
        }
        if ((RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage) && !(RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage && FrontLine.getVoltage() < LineTrackerVoltage) && move_state == 7)
        {
            LineTrackerLeft();
        }
        //If it no longer applies
        else if ((RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage && FrontLine.getVoltage() < LineTrackerVoltage) && move_state == 7)
        {
            //Move to the next state
            MoveRightBeacon(true);
            MoveLeftBeacon(true);
            NextState();
        }
        //If the robot is in state 8 and 17 cm from the wall
        /**if (Range1.getDistance(DistanceUnit.CM) > 17 && move_state == 8)
        {
            //Move the robot in a straight line, going left or right based on what the voltage of the line trackers are
            MoveRobot(-1 * (LeftLine.getVoltage() * 0.2 * 0.3), -1 * (RightLine.getVoltage() * 0.2 * 0.3));
        }*/
    // Move_state = 8
    // Line track 1st line

        if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && RightLine.getVoltage() >= LineTrackerVoltage)
        {
            MoveRobot(-0.3,-0.5);
        }
        if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && LeftLine.getVoltage() >= LineTrackerVoltage)
        {
            MoveRobot(-0.5,-0.3);
        }
        if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
        {
            MoveRobot(-0.5,-0.5);
        }
        //If the color sensor detects red and the robot is in state 8 and the robot is 17 cm away from the wall
        if (red && !blue && move_state == 8 && Range1.getDistance(DistanceUnit.CM) <= 15)
        {
            //Move to the next state
            NextState();
            //Move the left beacon presser up and leave the right beacon presser down
            MoveRightBeacon(false);
            MoveLeftBeacon(true);
        }
        //If the color sensor detects blue and the robot is in state 8 and the robot is 17 cm away from the wall
        else if(blue && !red && move_state == 8 && Range1.getDistance(DistanceUnit.CM) <= 15)
        {
            //Move to the next state
            NextState();
            //Move the right beacon presser up and leave the left beacon presser down
            MoveLeftBeacon(false);
            MoveRightBeacon(true);
        }
        //If neither red or blue is seen by the color sensor or both are seen by the color sensor
        else if (((!blue && !red) || (red && blue)) && move_state == 8)
        {
            //Leave both of the beacon pressers down
            MoveLeftBeacon(true);
            MoveRightBeacon(true);
        }
    // Move_state == 9
    // Sees the beacon and pushes forward
        //If 'timer_state' is 6
        if (timer_state == 6 && move_state == 9)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //If the timer is less than or equal to 2 and the robot is in state 9
        if (timer2 <= 2 && move_state == 9)
        {
            //Move the robot at -0.5 on both wheels
            MoveRobot(-0.5, -0.5);
            //Reset the timer that keeps up with change in time
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 2 && move_state == 9)
        {
            //Move to the next state
            NextState();
        }

    // Move_state == 10
        //If 'timer_state' is 7
        if (timer_state == 7 && move_state == 10)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //If the timer is less than or equal to 1 and the robot is in state 10
        if (timer2 <= 0.8 && move_state == 10)
        {
            //Move the robot at 0.8 on both wheels
            MoveRobot(0.8,0.8);
            //Reset the timer that keeps up with change in time
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 0.8 && move_state == 10)
        {
            //Move to the next state
            NextState();
        }
    // Move_state == 11
        //If 'timer_state' is 8
        if (timer_state == 8 && move_state == 11)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //If the front line tracker detects white and the robot is in state 11
        if (FrontLine.getVoltage() >= LineTrackerVoltage && move_state == 11)
        {
            //Reset the timer that keeps track of the change in time
            Timer2Reset();
            //Move the middle motor by -0.25
            MoveMiddleDrive(0.32);
        }
        //If the timer is less that half a second
        else if (timer2 <= 0.5 && move_state == 11)
        {
            //Reset the timer that keeps track of the change in time
            Timer2Reset();
            //Move the middle motor by -0.25
            MoveMiddleDrive(0.32);
        }
        //If it no longer applies
        else if (FrontLine.getVoltage() < LineTrackerVoltage && move_state == 11 && timer2 > 0.5)
        {
            //Move to the next state
            NextState();
        }
    // Move_state == 12
        if (move_state ==12)
        {
            //If at least one line tracker detects black and the robot is in state 12
            if ((RightLine.getVoltage() >= LineTrackerVoltage || LeftLine.getVoltage() >= LineTrackerVoltage || FrontLine.getVoltage() >= LineTrackerVoltage) && move_state == 12)
            {
                LineTrackerLeft();
            }
            //If it no longer applies
            else if ((RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage && FrontLine.getVoltage() < LineTrackerVoltage) && move_state == 12)
            {
                //Move to the next state
                MoveRightBeacon(true);
                MoveLeftBeacon(true);
                NextState();
            }
        }

    // Move_state == 13
        //If the robot is in state 13
        if (Range1.getDistance(DistanceUnit.CM) > 16 && move_state == 13 && RightLine.getVoltage() >= LineTrackerVoltage)
        {
            MoveRobot(-0.3,-0.5);
        }
        if (Range1.getDistance(DistanceUnit.CM) > 16 && move_state == 13 && LeftLine.getVoltage() >= LineTrackerVoltage)
        {
            MoveRobot(-0.5,-0.3);
        }
        if (Range1.getDistance(DistanceUnit.CM) > 16 && move_state == 13 && RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
        {
            MoveRobot(-0.5,-0.5);
        }
        //If the color sensor detects red and the robot is in state 13 and the robot is 17 cm from the robot
        if (red && !blue && move_state == 13 && Range1.getDistance(DistanceUnit.CM) <= 16)
        {
            //Move to the next state
            NextState();
            //Move the left beacon presser up and leave the right beacon presser down
            MoveRightBeacon(false);
            MoveLeftBeacon(true);
        }
        //If the color sensor detects blue and the robot is in state 13 and the robot is 17 cm from the robot
        else if(blue && !red && move_state == 13 && Range1.getDistance(DistanceUnit.CM) <= 16)
        {
            //Move to the next state
            NextState();
            //Move the right beacon presser up and leave the left beacon presser down
            MoveLeftBeacon(false);
            MoveRightBeacon(true);
        }
        //If neither red or blue is seen by the color sensor or both are seen by the color sensor
        else if (((!blue && !red) || (red && blue)) && move_state == 13)
        {
            //Leave both of the beacon pressers down
            MoveLeftBeacon(true);
            MoveRightBeacon(true);
        }
        //If 'timer_state' is 9
        if (timer_state == 9 && move_state == 14)
        {
            //Reset all timers
            TimerReset();
            timer_state++;
        }
        //If the timer is less than or equal to 2 and the robot is in state 14
        if (timer2 <= 2 && move_state == 14)
        {
            //Move the robot at -0.5 on both wheels
            MoveRobot(-0.5, -0.5);
            //Reset the timer that keeps up with change in time
            Timer2Reset();
        }
        //If it no longer applies
        else if (timer2 > 2 && move_state == 14)
        {
            //Move to the next state
            NextState();
        }
        /***/
        UpdateTelemetry();
        telemetry.addData("11", "Timer: " + timer2);
        telemetry.addData("12", "State: " + move_state);
        if (Color1 != null){
            //If there is more blue than red
            if (Color1.blue() > Color1.red()){
                //Displays the color of the beacon as blue
                telemetry.addLine("Beacon Color: Blue");
                //Sets 'blue' as true and 'red' as false
                blue = true;
                red = false;
            }
            //Else if there is more red than blue
            else if (Color1.red() > Color1.blue()){
                //Displays the color of the beacon as red
                telemetry.addLine("Beacon Color: Red");
                //Sets 'blue' as false and 'red' as true
                blue = false;
                red = true;
            }
            //Else if they are the same value but not zero
            else if (Color1.blue() == Color1.red() && Color1.blue() != 0 && Color1.red() != 0){
                //Displays the color of the beacons as both
                telemetry.addLine("Beacon Color: Both");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }
            //Else (if none apply)
            else{
                //Display the color of the beacon as neither
                telemetry.addLine("Beacon Color: Neither");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }

        }
        //Range Sensor & Optical Sensor
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Distance: " + Range1.getDistance(DistanceUnit.CM) + " cm");

    }
}