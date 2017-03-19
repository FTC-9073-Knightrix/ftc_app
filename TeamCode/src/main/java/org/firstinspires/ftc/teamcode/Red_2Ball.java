package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//@Autonomous(name = "Red 2 Ball", group = "Autonomous")

/**
 * Created by Nicolas Bravo on 2/20/17.
 * For use in the Autonomous Portion of the match
 * Gets 2 balls into the center vortex
 */

public class Red_2Ball extends Telemetry
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

            ChangeState(1);
        }

        // State 1 = wait 10 seconds
        if (move_state == 1)
        {
            if (timer2 > 10)
            {
                ChangeState(2);
            }
        }



        // State 2 = Move middle wheel
        if (move_state == 2)
        {
            int MiddlePosition = -3970;
            if (MiddleDrivePosition > MiddlePosition)
            {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);
            }
            else {
                ChangeState(3);
            }
        }

        // State 3 = Move Forward
        if (move_state == 3)
        {
            int ForwardPosition = -500;
            int LeftPosition = 0;

            // Define Driving mode using encoders
            LeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (LeftPosition > ForwardPosition)
            {
                LeftDrive.setPower(.4);
                RightDrive.setPower(.4);

                LeftDrive.setTargetPosition(ForwardPosition);
                RightDrive.setTargetPosition(LeftPosition);
            }
            else {
                LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ChangeState(4);
            }
        }


        //State 4 = Shoot the ball twice
        if (move_state == 4)
        {
            // Shoot the ball for the first 1.35 seconds
            if (ShootState == 0)
            {
                ShooterEncoderPosition = BallShooterPosition;
                BallShooter.setPower(1);
                BallShooter.setTargetPosition(ShooterEncoderRotation + BallShooterPosition);
                ShootState = 1;
            }
            else if (ShootState == 1 && BallShooterPosition == ShooterEncoderPosition + ShooterEncoderRotation && BallShooterPosition != ShooterEncoderPosition)
            {
                TimerReset();
                ShootState = 2;

                MoveReleaseDrive(true);     //Open the release mechanism
            }
            else if (timer2 > 1 && ShootState == 2)
            {
                ShooterEncoderPosition = BallShooterPosition;
                BallShooter.setPower(1);
                BallShooter.setTargetPosition(ShooterEncoderRotation + BallShooterPosition);
                ShootState = 3;
            }
            else if (ShootState == 3 && BallShooterPosition == ShooterEncoderPosition + ShooterEncoderRotation && BallShooterPosition != ShooterEncoderPosition)
            {
                MoveReleaseDrive(false);
                ChangeState(20);
                ShootState = 0;
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
        Timer2Reset();
        UpdateTelemetry();
    }
}
