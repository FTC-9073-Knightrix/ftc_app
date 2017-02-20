package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Blue 2 Ball 2 Beacon", group = "Autonomous")

/**
 * Created by Nicolas Bravo on 2/11/17.
 */
public class Auto_Blue_test extends Telemetry{
    //Start
    @Override
    public void start() {
        super.start();
    }
    //Main Loop
    @Override
    public void loop()
    {
        // State 0 = Move robot closer to center vortex
        if (move_state == 0)
        {
            // Reset variables
            int MiddlePosition = 0;     // Encoder middle wheel

            // Reset Left, Right and Middle wheels to run with encoders
            MiddleDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            MiddleDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            MiddlePosition = -3970;
            while (MiddleDrive.getCurrentPosition() > MiddlePosition) {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);
            }
                ChangeState(1);
        }

        //State 1 = Shoot the ball twice
        if (move_state == 1)
        {
            // Shoot the ball for the first 1.35 seconds
            if (timer2 <= 1.35)
            {
                MoveBallShooter(0.5);       // Shot first ball
            }
            // From 1.35 to 2, open the release mechanism
            else if (timer2 > 1.35 && timer2 < 2)
            {
                MoveBallShooter(0);         //Stop the shooter
                MoveReleaseDrive(true);     //Open the release mechanism
            }
            else if (timer2 > 2 && timer2 < 3)
            {
               MoveBallShooter(0.5);          // shoot second ball for 1 second
            }
            else if (timer2 > 3)
            {
                MoveReleaseDrive(false);    // Close Ball Servo
                MoveBallShooter(0);         // Stop shooter
                //Move to the next state
                // State Option A: Diagonal         -> ChangeState(2)
                // State Option B: Side & Forward   -> ChangeState(3)
                ChangeState(3);
                LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            }
        }

        //State 2 = Get closer to line for first beacon (Option A: Move diagonal)
        if (move_state == 2)
        {
            int MiddlePosition = -6500;
            int ForwardPosition = 3500;

            if (timer2 < 3)
            {
                if ((MiddleDrive.getCurrentPosition() > MiddlePosition) || (LeftDrive.getCurrentPosition() < ForwardPosition))
                {
                    MiddleDrive.setPower(-.7);
                    MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    MiddleDrive.setTargetPosition(MiddlePosition);

                    LeftDrive.setPower(.4);
                    RightDrive.setPower(.4);

                    LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LeftDrive.setTargetPosition(ForwardPosition);
                    RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    RightDrive.setTargetPosition(ForwardPosition);

                }
            }
            else if (timer2 > 3)        // After 3 seconds, robot should be in position. Move to next stage
            {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(6.5);
            }
        }

        //State 3 = Get closer to line for first beacon (Option B: Move Sideways)
        if (move_state == 3)
        {
            int MiddlePosition = -6500;

            if ((MiddleDrive.getCurrentPosition() > MiddlePosition) )
            {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);
            }
            else
            {
                ChangeState(4);
            }
        }

        //State 4 = Get closer to line for first beacon (Option B: Move Forward)
        if (move_state == 4)
        {
            int ForwardPosition = 3500;

            if ((LeftDrive.getCurrentPosition() < ForwardPosition))
            {
                LeftDrive.setPower(.4);
                RightDrive.setPower(.4);

                LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LeftDrive.setTargetPosition(ForwardPosition);
                RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RightDrive.setTargetPosition(ForwardPosition);

            }
            else
            {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(6.5);
            }
        }


        // Rotate: Turn until Gyro is 160 < 180 > 200
        if (move_state == 6.5) {


            double MyGyro = Gyro1.getHeading();     // Store Gyro Position
            MoveRobot(0.7, -0.7);                   // Move Robot
            if (MyGyro > 155 && MyGyro < 205)       // Stop robot when reaching position
            {
                MoveRobot(0, 0);
                ChangeState(7);
            }
        }

        // Go Left until finding white line
        if (move_state == 7)
        {
            //If at least one line tracker detects white, we reached the line and go to next stage
            if (RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage)
            {
                //Move to the next state
                ChangeState(8);
            }
            //If none of the line trackers have yet to sense white, keep moving left
            else
            {
                MoveMiddleDrive(0.3);
            }
        }

        // Follow the line forward and lower color beacon arm when reaching distance
        if (move_state == 8)
        {

            ///////// FOLLOW THE LINE CODE
            // The right and left line trackers detect white -> Go Forward
            if (RightLine.getVoltage() < LineTrackerVoltage && LeftLine.getVoltage() < LineTrackerVoltage)
            {
                MoveRobot(-0.65, -0.65);
            }
            else
            {
                // The right and left line trackers detect black -> Go Forward
                if (RightLine.getVoltage() >= LineTrackerVoltage && LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    MoveRobot(-0.65, -0.65);
                }
                // Else if only the right line tracker is black -> Turn one side
                else if (RightLine.getVoltage() >= LineTrackerVoltage)
                {
                    MoveRobot(-0.2, -0.65);
                }
                // Else if only the left line tracker is black -> Turn the other side
                else if (LeftLine.getVoltage() >= LineTrackerVoltage)
                {
                    MoveRobot(-0.65, -0.2);
                }
            }
            ///////// END FOLLOW THE LINE CODE


            // If the robot is less than 16 cm from the wall, detect color and lower ARM Servo
            if (Range1.getDistance(DistanceUnit.CM) < 16)
            {
                // If the beacon is RED
                if (red && !blue)
                {
                    //Move the left beacon presser up and leave the right beacon presser down
                    MoveRightBeacon(false);     // Right DOWN
                    MoveLeftBeacon(true);       // Left UP
                    //Move to the next state
                    ChangeState(9);
                }
                // If the beacon is BLUE
                else if (blue && !red)
                {
                    //Move the right beacon presser up and leave the left beacon presser down
                    MoveRightBeacon(true);      // Right UP
                    MoveLeftBeacon(false);      // Left DOWN
                    //Move to the next state
                    ChangeState(9);
                }
                // If neither or both colors are detected
                else if ((!blue && !red) || (red && blue))
                {
                    //Leave both of the beacon pressers up
                    MoveLeftBeacon(true);       // Right UP
                    MoveRightBeacon(true);      // Left DOWN
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

        // Color is found, move forward to push beacon for TIME
        if (move_state == 9)
        {
            //If the timer is less than or equal to 1 -> Move Forwards
            if (timer2 <= .8)
            {
                MoveRobot(-0.5, -0.5);      // Move Forwards
            }
            // After 1 second, move to next state
            else if (timer2 > 1)
            {
                if (BeaconNum == 1) {
                    BeaconNum = 2;
                    ChangeState(10);
                } else {
                    ChangeState(13);
                    // Reset Left, Right and Middle wheels to run with encoders
                    MiddleDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    MiddleDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    LeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    RightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
            }
        }

        // Move away from the wall for TIME
        if (move_state == 10)
        {
            // Move Away from the wall for 1 seconds
            if (timer2 <= .8)
            {
                MoveRobot(1, 1);
            }
            // After 1 second, jump to next state
            else if (timer2 > .8)
            {
                //Move to the next state
                ChangeState(11);
            }
        }


        // Move left to the next beacon adjusting for:
        //      a. Distance to the wall
        //      b. Heading from gyro sensor
        //

        if (move_state == 11)
        {

            // #########   Keep robot parallel to the wall ##########
            double WallDistance = Range1.getDistance(DistanceUnit.CM);
            double MyGyro = Gyro1.getHeading();
            double LeftPower = 0;
            double RightPower =0;

            // (A) Keep robot at 30 - 50 cm from wall
            if (WallDistance < 30)      //we are close
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

            // (B) Alight the Gyro to 180 degrees
            if (MyGyro > 180) {
                //  Spin the robot in one direction
                LeftPower = LeftPower - .075;
                RightPower = RightPower + .075;
            }
            else if (MyGyro <= 180) {
                //  Spin the robot in the other direction
                LeftPower = LeftPower + .075;
                RightPower = RightPower - .075;

            }
            // (A) + (B) Values to move the left+right wheels to keep robot parallel to the wall
            MoveRobot(LeftPower,RightPower);
            // #########   End Keep robot parallel to the wall ##########


            // #########   Move the robot left until reaching white line   ###########
            if (timer2 < 2)           // Go faster during the first 2 seconds
            {
                MoveMiddleDrive(1);
            }
            else                    // After 3 seconds, start searching for the line
            {
                // If at least one line tracker detects white, we reached the line and go to next stage
                if (RightLine.getVoltage() < LineTrackerVoltage || LeftLine.getVoltage() < LineTrackerVoltage || FrontLine.getVoltage() < LineTrackerVoltage) {
                    //Move to the next state
                    ChangeState(8);
                }
                //If none of the line trackers have yet to sense white, keep moving left
                else
                {
                    MoveMiddleDrive(0.25);
                }
            }
        }


        // Park Robot in center vortex, removing cap ball
        if (move_state == 13)
        {

            // Select coordinates for center vortex parking
            int MiddlePosition = -3000;
            int ForwardPosition = 6000;

            if (timer2 < 3)
            {
                if (MiddleDrive.getCurrentPosition() > MiddlePosition || LeftDrive.getCurrentPosition() < ForwardPosition)
                {

                    MiddleDrive.setPower(-0.7);
                    MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    MiddleDrive.setTargetPosition(MiddlePosition);

                    LeftDrive.setPower(1);
                    RightDrive.setPower(1);

                    LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    LeftDrive.setTargetPosition(ForwardPosition);
                    RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    RightDrive.setTargetPosition(ForwardPosition);


                }
            }
            else if (timer2 > 3)        // After 3 seconds, robot should be in position. Move to next stage
            {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(20);
            }

        }




        // Last State -> Turn off Motors
        if (move_state == 20)
        {
            LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            MoveRobot(0,0);
            MoveMiddleDrive(0);
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

        // Update variables
        Timer2Reset();

        //Range Sensor & Optical Sensor
        degree = Gyro1.getHeading();
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Distance: " + Range1.getDistance(DistanceUnit.CM) + " cm");
        telemetry.addLine("Gyro: " + degree);
        telemetry.addLine("X"+ Gyro1.rawX());
        telemetry.addLine("X"+ Gyro1.rawY());
        telemetry.addLine("X"+ Gyro1.rawZ());
    }
}
