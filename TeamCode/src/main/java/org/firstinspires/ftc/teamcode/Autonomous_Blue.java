/**package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Timer;

//@Autonomous(name = "Old Blue", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it and then presses the beacons
 *

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
        //If the state is 0
        if (move_state == 0)
        {
            //If timer_state is equal to 0
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less that 1.5
            if (timer2 <= 1.5)
            {
                //Move the middle wheel by -1
                MoveMiddleDrive(-1);
                //Reset the second timer which keeps up with the time change
                Timer2Reset();
            }
            //If it does not apply
            else if (timer2 > 1.5)
            {
                //Move to the next state (NextState = 1)
                NextState(1);
            }
        }
        //If the state is 1
        if (move_state == 1)
        {
            //If timer_state is equal to 1
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If timer is less than 1.35
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
            //If timer_state is equal to 2
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //Wait 1 second for the ball to transition
            if (timer2 <= 1)
            {
//                // reset gyro sensor
//                //Calibrate
//                while (calibrated == false)
//                {
//                    if (calibrate == false)
//                    {
//                        Gyro1.calibrate();
//                        calibrate = true;
//                    }
//                    if (Gyro1.isCalibrating() == false)
//                    {
//                        calibrated = true;
//                    }
//                }

                //Refresh the timer
                Timer2Reset();
            }
            //If the timer is above 1 second
            else if (timer2 > 1)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 3
        if (move_state == 3)
        {
            // If 'timer_state' is 3
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
            // If 'timer_state' is 4
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 0.95 seconds
            if (timer2 <= 0.95)
            {
                //Move the middle wheel by -0.7
                MoveMiddleDrive(-.7);
                //Reset the timer that keeps track of time change
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 0.95)
            {
                //Move to the next state
                move_state = 5;
                //Stop the middle wheel
                MoveMiddleDrive(0);
            }
        }
        //If the state is 5
        if (move_state == 5)
        {
            // If 'timer_state' is 5
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 2.9
            if (timer2 <= 2.9)
            {
                //Move the robot at 0.8
                MoveRobot(0.8, 0.8);
                //Reset the timer that keeps track of change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 2.9)
            {
                //Move to the next state
                MoveRobot(0, 0);
                move_state = 6.5;
            }
        }
        //If the state is 6.5
        // Turn until Gyro is 160 < 180 > 200
        if (move_state == 6.5) {
            //If the timer state is in 5.5
            if (timer_state == move_state - 1) {
                //Reset all timers
                NextTimer();
            }
            double MyGyro = Gyro1.getHeading();
            MoveRobot(0.7, -0.7);
            Timer2Reset();
            //If the timer is less than or equal to 1.4
            if (MyGyro > 160 && MyGyro < 200) {
                //Spin the robot halfway
                //Reset the timer that keeps up with changes in time
                NextState(0.5);
            }
            //If it no longer applies
//            else if (Gyro1.getHeading() >= 180)
//            {
//                //Move to the next state
//                move_state = 7;
//                //Stop the motors
//                MoveRobot(0, 0);
//            }
        }
        //If the state is 7
        // Go Left until finding white line
        if (move_state == 7)
        {
            //If at least one line tracker detects white
            if (RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage)
            {
                //Move to the next state
                NextState(1);
            }
            //If none of the line trackers have yet to sense white
            else
            {
                //Move the middle wheel 0.25
                MoveMiddleDrive(0.25);
            }
        }
        //If the state is 8
        // Once line is found, go forward and lift color beacon pressor
        if (move_state == 8)
        {
            //The right and left line trackers detect white
            if (RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                //Move the robot forwards by -0.65
                MoveRobot(-0.65, -0.65);
            }
            //If it does not apply
            else
            {
                //If both the right and left line trackers detect black
                if (RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot forwards by -0.65
                    MoveRobot(-0.65, -0.65);
                }
                //Else if only the right line tracker is black
                else if (RightLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot slightly to one side
                    MoveRobot(-0.2, -0.65);
                }
                //Else if only the left line tracker is black
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
                    //Move the left beacon presser up and leave the right beacon presser down
                    MoveRightBeacon(false);
                    MoveLeftBeacon(true);
                }
                //If the beacon is blue
                else if (blue && !red)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the right beacon presser up and leave the left beacon presser down
                    MoveLeftBeacon(false);
                    MoveRightBeacon(true);
                }
                //If neither or both colors are detected
                else if ((!blue && !red) || (red && blue))
                {
                    //Leave both of the beacon pressers up
                    MoveLeftBeacon(true);
                    MoveRightBeacon(true);
                }
            }
            //If the robot is greater than 16 cm from the wall
            else
            {
                //Keep both of the beacon pressers up
                MoveLeftBeacon(true);
                MoveRightBeacon(true);
            }
        }
        //If the state is 9
        // Color is found, move forward to push beacon for TIME
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
        // Move away from the wall for TIME
        if (move_state == 10)
        {
            //If 'timer_state' is 7
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the timer is less than or equal to 1.2
            if (timer2 <= 1.2)
            {
                //Move the robot at 0.8 on both wheels
                MoveRobot(0.8, 0.8);
                //Reset the timer that keeps up with change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 1.2)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the state is 11
        // Move left to next beacon


        if (move_state == 11)
        {
            double WallDistance = Range1.getDistance(DistanceUnit.CM);
            double MyGyro = Gyro1.getHeading();
            double LeftPower = 0;
            double RightPower =0;

            // Keep robot at 30 - 50 cm from wall
            if (WallDistance < 30) //we are close
            {
                // Moves away from the wall
                LeftPower = .3;
                RightPower = .3;
            }
            else if (WallDistance > 50) //we are far away
            {
                // Moves towards from the wall
                LeftPower = -.3;
                RightPower = -.3;
            }
            if (MyGyro > 180) {
                //Spin the robot halfway
                LeftPower = LeftPower - .075;
                RightPower = RightPower + .075;
                //Reset the timer that keeps up with changes in time
            }
            else if (MyGyro <= 180)
            {
                LeftPower = LeftPower + .075;
                RightPower = RightPower - .075;

            }
            MoveRobot(LeftPower,RightPower);


            //If 'timer_state' is 8
            if (timer_state == move_state - 1)
            {
                //Reset all timers
                NextTimer();
            }
            //If the front line tracker detects black
            if ((FrontLine.getVoltage() >= LineTrackerVoltage || LeftLine.getVoltage() >= LineTrackerVoltage || RightLine.getVoltage() >= LineTrackerVoltage) && timer2 <= 0.5)
            {
                //Reset the timer that keeps track of the change in time
                Timer2Reset();
                //Move the middle motor by 32
                MoveMiddleDrive(0.32);
            }
            //If neither apply
            else if ((FrontLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || RightLine.getVoltage() < LineTrackerVoltage) && timer2 > 0.5)
            {
                //Move to the next state
                NextState(1);
            }
        }
        //If the robot is in state 12
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
                //Move the middle wheel by 0.25
                MoveMiddleDrive(0.25);
            }
        }
        //If the state is 13
        if (move_state == 13)
        {
            //If the right and left line trackers both detect white
            if (RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                //Move the robot forwards by -0.65
                MoveRobot(-0.65, -0.65);
            }
            //If either the right or left line tracker detects black
            else
            {
                //If both the right and left line trackers detect black
                if (RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot forwards by -0.65
                    MoveRobot(-0.65, -0.65);
                }
                //Else if only the right line tracker detects black
                else if (RightLine.getVoltage() >= LineTrackerVoltage)
                {
                    //Move the robot slightly to one side
                    MoveRobot(-0.2, -0.65);
                }
                //Else if only the left line tracker detects black
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
                    //Move the left beacon presser up and leave the right beacon presser down
                    MoveRightBeacon(false);
                    MoveLeftBeacon(true);
                }
                //If the beacon is blue
                else if (blue && !red)
                {
                    //Move to the next state
                    NextState(1);
                    timer_state = move_state - 1;
                    //Move the right beacon presser up and leave the left beacon presser down
                    MoveLeftBeacon(false);
                    MoveRightBeacon(true);
                }
                //If the color sensor detects no color or both colors
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
                //Keep both beacon pressers up
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
        degree = Gyro1.getHeading();
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Distance: " + Range1.getDistance(DistanceUnit.CM) + " cm");
        telemetry.addLine("Gyro: " + degree);
        telemetry.addLine("Middle wheel Pos: " + MiddleDrive.getCurrentPosition());
    }
}*/
