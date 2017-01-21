package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
        import java.util.Timer;

@Autonomous(name = "Autonomous Line Testing", group = "Autonomous")

/**
 * Created by Vijay Rudraraju on 11/19/2016.
 * Testing
 * Moves towards the hoop and shoots two balls at it and then presses the beacons
 */

public class AutoFollowLine extends Telemetry{
    //Start
    @Override
    public void start() {
        super.start();
    }
    //Main Loop
    @Override
    public void loop()
    {
        if ( move_state == 0 ) { move_state = 7;} ;
        // Move_state == 7
        // Moves sideways trying to sense the 1st white line
        //If at least one line tracker detects black and the robot is in state 7
        if( move_state == 7)
        {
//            // Move sideways while reading black floor
//            if ((RightLine.getVoltage() > LineTrackerVoltage && LeftLine.getVoltage() > LineTrackerVoltage && FrontLine.getVoltage() > LineTrackerVoltage) )
//            {
//                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
//                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
//                MoveMiddleDrive(0.25);
//            }
//            // If Finds line, execute Line Tracker Left
//            if ((RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage) && !(RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage && FrontLine.getVoltage() < LineTrackerVoltage))
//            {
//                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
//                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
//                LineTrackerLeft();
//            }
//            //If it no longer applies
//            else if ((RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage && FrontLine.getVoltage() < LineTrackerVoltage) )
//            {
//                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
//                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
//                //Move to the next state
//                NextState();
//            }
            if(RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage)
            {
                NextState();
            }
            else
            {
                MoveMiddleDrive(0.25);
            }

        }

        //If the robot is in state 8 and 17 cm from the wall
        /**if (Range1.getDistance(DistanceUnit.CM) > 17 && move_state == 8)
         {
         //Move the robot in a straight line, going left or right based on what the voltage of the line trackers are
         MoveRobot(-1 * (LeftLine.getVoltage() * 0.2 * 0.3), -1 * (RightLine.getVoltage() * 0.2 * 0.3));
         }*/
        // Move_state = 8
        // Line track 1st line
        if (move_state == 8)
        {
            if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && RightLine.getVoltage() >= LineTrackerVoltage)
            {
                MoveRobot(-0.3,-0.5);
                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
            }
            if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && LeftLine.getVoltage() >= LineTrackerVoltage)
            {
                MoveRobot(-0.5,-0.3);
                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
            }
            if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                MoveRobot(-0.5,-0.5);
                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
            }
            if (Range1.getDistance(DistanceUnit.CM) > 15 && move_state == 8 && RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
            {
                MoveMiddleDrive(0.25);
                RightBeacon.setPosition(1-(RightLine.getVoltage()/5*.6));
                LeftBeacon.setPosition((LeftLine.getVoltage()/5*.6));
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
                MoveLeftBeacon(false);
                MoveRightBeacon(false);
            }
        }

        // Move_state == 9
        // Sees the beacon and pushes forward
        //If 'timer_state' is 6
/*        if (move_state == 9)
        {
            if (timer_state == 6 )
            {
                //Reset all timers
                TimerReset();
                timer_state++;
            }
            //If the timer is less than or equal to 2 and the robot is in state 9
            if (timer2 <= 2 )
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
                NextState();
            }


        }

        // Move_state == 10
        //If 'timer_state' is 7
        if (move_state == 10)
        {
            if (timer_state == 7)
            {
                //Reset all timers
                TimerReset();
                timer_state++;
            }
            //If the timer is less than or equal to 1 and the robot is in state 10
            if (timer2 <= 0.8)
            {
                //Move the robot at 0.8 on both wheels
                MoveRobot(0.8,0.8);
                //Reset the timer that keeps up with change in time
                Timer2Reset();
            }
            //If it no longer applies
            else if (timer2 > 0.8)
            {
                //Move to the next state
                NextState();
            }

        }
  */      /***/
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