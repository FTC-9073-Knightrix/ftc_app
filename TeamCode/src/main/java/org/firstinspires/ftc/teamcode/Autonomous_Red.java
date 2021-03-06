/**package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Timer;

//@Autonomous(name = "Old Red", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing patch
 * Testing
 * Moves towards the hoop and shoots two balls at it and then presses the beacons
 *

public class Autonomous_Red extends Telemetry {
    //Start
    @Override
    public void start() {
        super.start();
    }
    //Main Loop
    @Override
    public void loop()
    {
        //If the state is 0
        if (move_state == 0)
        {
            //If the timer state is 0
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 1.5 seconds
            if (timer2 <= 1.5)
            {
                //Move the robot by -1
                MoveMiddleDrive(-1);
                //Reset the second timer which keeps up with the time change
                Timer2Reset();
            }
            //If it does not apply
            else if (timer2 > 1.5)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 1
        if (move_state == 1)
        {
            //If timer_state is 1
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If time timer is less than or equal to 1.35 seconds
            if (timer2 <= 1.35)
            {
                //Shoot the ball
                MoveBallShooter(0.5);
                //Reset the timer that keeps track of time change
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 1.35)
            {
                //Move to the next state
                NextState(1);
                //Open the release mechanism
                MoveReleaseDrive(true);
            }
        }
        //If the state is 2
        if (move_state == 2)
        {
            //If 'timer_state' is 2
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //Wait 1 second for the ball to transition
            if (timer2 <= 1)
            {
                //Refresh the timer
                Timer2Reset();
            }
            //If the timer is above 1
            else if (timer2 > 1)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 3
        if (move_state == 3)
        {
            //If 'timer_state' is 3
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 1.3
            if (timer2 <= 1.3)
            {
                //Close the release mechanism
                MoveReleaseDrive(false);
                //Shoot the ball
                MoveBallShooter(0.5);
                //Reset the timer that keeps track of time change
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 1.3)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 4
        if (move_state == 4)
        {
            //If 'timer_state' is 4
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 1.1 seconds
            if (timer2 <= 0.95)
            {
                //Move the robot by -0.7
                MoveMiddleDrive(-0.7);
                //Reset the timer that keeps track of time change
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 0.95)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 5
        if (move_state == 5)
        {
            //If 'timer_state' is 5
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 3.3
            if (timer2 <= 3.3)
            {
                //Move the robot at -0.7
                MoveRobot(-0.7, -0.7);
                //Reset the timer that keeps track of change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 3.3)
            {
                //Move to the next state
                NextState(2);
            }
        }
        //If the state is 7
        if (move_state == 7)
        {
            //If any of the line trackers sense white
            if (RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage)
            {
                //Move to the next state
                NextState(1);
            }
            //If none have yet to see white
            else
            {
                //Move the middle wheel by -0.25
                MoveMiddleDrive(-0.25);
            }
        }
        //If the state is 8
        if (move_state == 8)
        {
            //If the right and left line trackers sense white
            if (RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                //Move the robot forwards at -0.65
                MoveRobot(-0.65, -0.65);
            }
            //If either the right or left line tracker detects black
            else
            {
                //If the right and left line trackers detect black
                if (RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot forwards at -0.65
                    MoveRobot(-0.65, -0.65);
                }
                //Else if only the right line tracker sees black
                else if (RightLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot slightly to one side
                    MoveRobot(-0.2, -0.65);
                }
                //Else if only the left line tracker sees black
                else if (LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot slightly to one side
                    MoveRobot(-0.65, -0.2);
                }
            }
            //If the robot is less than 16 cm from the wall
            if (Range1.getDistance(DistanceUnit.CM) < 16)
            {
                //If the beacon is red
                if (red && !blue)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the right beacon presser up and leave the left beacon presser down
                    MoveRightBeacon(true);
                    MoveLeftBeacon(false);
                }
                //If the beacon is blue
                else if (blue && !red)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the left beacon presser up and leave the right beacon presser down
                    MoveLeftBeacon(true);
                    MoveRightBeacon(false);
                }
                //If neither blue nor red is sensed or both colors are sensed by the color sensor
                else if ((!blue && !red) || (red && blue))
                {
                    //Leave both of the beacon pressers up
                    MoveLeftBeacon(true);
                    MoveRightBeacon(true);
                }
            }
            //If the robot is greater than or equal to 16 cm away from the wall
            else
            {
                //Move both beacons up
                MoveLeftBeacon(true);
                MoveRightBeacon(true);
            }
        }
        //If the state is 9
        if (move_state == 9)
        {
            //If 'timer_state' is 6
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 2
            if (timer2 <= 2)
            {
                //Move the robot at -0.5 on both wheels
                MoveRobot(-0.5, -0.5);
                //Reset the timer that keeps up with change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 2)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 10
        if (move_state == 10)
        {
            //If 'timer_state' is 7
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 1.1
            if (timer2 <= 1.1)
            {
                //Move the robot at 0.8 on both wheels
                MoveRobot(0.8, 0.8);
                //Reset the timer that keeps up with change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 1.1)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 11
        if (move_state == 11)
        {
            //If 'timer_state' is 8
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the front line tracker detects black
            if (FrontLine.getVoltage() >= LineTrackerVoltage)
            {
                //Reset the timer that keeps track of the change in time
                Timer2Reset();
                //Move the middle motor by -0.32
                MoveMiddleDrive(-0.32);
            }
            //If the timer is less that half a second
            else if (timer2 <= 0.5)
            {
                //Reset the timer that keeps track of the change in time
                Timer2Reset();
                //Move the middle motor by -0.32
                MoveMiddleDrive(-0.32);
            }
            //If it no longer applies
            else if (FrontLine.getVoltage() < LineTrackerVoltage && timer2 > 0.5)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 12
        if (move_state == 12)
        {
            //If any of the line trackers detect white
            if (RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage)
            {
                //Move to the next state
                NextState(1);
            }
            //If none of the line trackers have yet to detect white
            else
            {
                //Move the middle drive -0.25
                MoveMiddleDrive(-0.25);
            }
        }
        //If the robot is in state 13
        if (move_state == 13)
        {
            //If the right and left line trackers detect white
            if (RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                //Move the robot -0.65
                MoveRobot(-0.65, -0.65);
            }
            //If the right or left line tracker detects black
            else
            {
                //If the right and left line trackers detect black
                if (RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot -0.65
                    MoveRobot(-0.65, -0.65);
                }
                //If the right line tracker detects black
                else if (RightLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot to one side
                    MoveRobot(-0.2, -0.65);
                }
                //If the left line tracker is black
                else if (LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot to one side
                    MoveRobot(-0.65, -0.2);
                }
            }
            //If the robot is less than 16 cm away from the wall
            if (Range1.getDistance(DistanceUnit.CM) < 16)
            {
                //If the beacon is red
                if (red && !blue)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the right beacon presser up and leave the left beacon presser down
                    MoveRightBeacon(true);
                    MoveLeftBeacon(false);
                }
                //If the beacon is blue
                else if (blue && !red)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the left beacon presser up and leave the right beacon presser down
                    MoveLeftBeacon(true);
                    MoveRightBeacon(false);
                }
                //If neither red nor blue is detected or both colors are detected by the sensor
                else if ((!blue && !red) || (red && blue))
                {
                    //Leave both of the beacon pressers up
                    MoveLeftBeacon(true);
                    MoveRightBeacon(true);
                }
            }
            //If the robot is greater than or equal to 16 cm from the wall
            else
            {
                //Leave the beacon pressers up
                MoveLeftBeacon(true);
                MoveRightBeacon(true);
            }
        }
        //If the state is 14
        if (move_state == 14)
        {
            //If 'timer_state' is 9
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 2.3
            if (timer2 <= 2.3)
            {
                //Move the robot at -0.5 on both wheels
                MoveRobot(-0.5, -0.5);
                //Reset the timer that keeps up with change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 2.3)
            {
                //Move to the next state
                NextState(1);
            }
        }
        /***
        UpdateTelemetry();
        telemetry.addData("11", "Timer: " + timer2);
        telemetry.addData("12", "Timer State: " + timer_state);
        telemetry.addData("13", "Move State: " + move_state);
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
        telemetry.addLine("Gyro: " + degree);
    }
}*/