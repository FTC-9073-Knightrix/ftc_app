
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "AutoDriveSynch", group = "Autonomous")

/**
 * Created by Nicolas Bravo on 2/20/17.
 * For use in the Autonomous Portion of the match
 * Gets 2 balls into the center vortex
 */

public class AutoDriveSynch extends Telemetry
{
    //Start
    @Override
    public void start()
    {
        super.start();
    }

    //Main Loop
    @Override
    public void loop()
    {
        //0 init
        //1 wait
        //2 move middle wheel
        //3 forward
        //4 shoot

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

            ChangeState(3);
        }

        // State 1 = wait 10 seconds
        if (move_state == 1)
        {
            if (timer2 > 10)
            {
                ChangeState(3.5);
                LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }



        // State 2 = Move middle wheel
        if (move_state == 2)
        {
            int MiddlePosition = 3970;
            while (MiddleDrive.getCurrentPosition() < MiddlePosition)
            {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);
            }
            ChangeState(3);
        }

        // State 3 = Move Forward
        if (move_state == 3)
        {

            // Set destination position
            int ForwardPosition = -4000; // Used to be a negative number
            int LeftPosition = LeftDrive.getCurrentPosition();
            if (LeftPosition == 0) {LeftPosition = -1;}
            int RightPosition = RightDrive.getCurrentPosition();
            if (RightPosition == 0) {RightPosition = -1;}

            // Set Driving mode
            LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Calculate the power as ratio of differential between wheels
            LeftDrive.setPower(Range.clip(.5 * RightPosition/LeftPosition, -1,1));
            RightDrive.setPower(Range.clip(.5 * LeftPosition/RightPosition,-1,1));

            // If not reached the position, keep moving
            if (LeftPosition > ForwardPosition)
            {
                LeftDrive.setTargetPosition(ForwardPosition);
                RightDrive.setTargetPosition(ForwardPosition);
            }
            else {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(1);
            }
        }

        // State 3 = Move Backward
        if (move_state == 3.5)
        {

            // Set destination position
            int ForwardPosition = 4000; // Used to be a negative number
            int LeftPosition = LeftDrive.getCurrentPosition();
            if (LeftPosition == 0) {LeftPosition = 1;}
            int RightPosition = RightDrive.getCurrentPosition();
            if (RightPosition == 0) {RightPosition = 1;}

            // Set Driving mode
            LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Calculate the power as ratio of differential between wheels
            LeftDrive.setPower(Range.clip(.5 * RightPosition/LeftPosition, -1,1));
            RightDrive.setPower(Range.clip(.5 * LeftPosition/RightPosition,-1,1));

            // If not reached the position, keep moving
            if (LeftPosition < ForwardPosition)
            {
                LeftDrive.setTargetPosition(ForwardPosition);
                RightDrive.setTargetPosition(ForwardPosition);
            }
            else {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(20);
            }
        }


        //State 4 = Shoot the ball twice
        if (move_state == 4)
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
                ChangeState(20);
            }
        }

        // Last State -> Turn off Motors
        if (move_state == 20)
        {
            LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            MoveRobot(0, 0);
            MoveMiddleDrive(0);
        }


        // Update variables
        UpdateTelemetry();
        Timer2Reset();

        telemetry.addData("11", "Timer: " + timer2);
        telemetry.addData("12", "State: " + move_state);

        //Range Sensor & Optical Sensor
        degree = Gyro1.getHeading();
        telemetry.addLine("~Range Sensor~");
        telemetry.addLine("Gyro: " + degree);
    }
}