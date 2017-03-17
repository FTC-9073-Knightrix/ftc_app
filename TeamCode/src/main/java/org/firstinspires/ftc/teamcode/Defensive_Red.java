package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Blue 2 Ball 2 Beacon", group = "Autonomous")
/**
 * Created by nicolasbravo on 3/16/17.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public class Defensive_Red extends Telemetry
{
    @Override
    public void start()
    {
        super.start();
    }

    @Override
    public void loop(){
        UpdateTelemetry();
        telemetry.addData("11", "Timer: " + timer2);
        telemetry.addData("12", "State: " + move_state);
        if (Color1 != null)
        {
            //If there is more blue than red
            if (Color1Blue > Color1Red)
            {
                //Displays the color of the beacon as blue
                telemetry.addLine("Beacon Color: Blue");
                //Sets 'blue' as true and 'red' as false
                blue = true;
                red = false;
            }
            //Else if there is more red than blue
            else if (Color1Red > Color1Blue)
            {
                //Displays the color of the beacon as red
                telemetry.addLine("Beacon Color: Red");
                //Sets 'blue' as false and 'red' as true
                blue = false;
                red = true;
            }
            //Else if they are the same value but not zero
            else if (Color1Blue == Color1Red && Color1Blue != 0 && Color1Red != 0)
            {
                //Displays the color of the beacons as both
                telemetry.addLine("Beacon Color: Both");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }
            //Else (if none apply)
            else
            {
                //Display the color of the beacon as neither
                telemetry.addLine("Beacon Color: Neither");
                //Sets 'blue' as false and 'red' as false
                blue = false;
                red = false;
            }
        }
        Timer2Reset();
        if (move_state == 0)
        {
            int MiddlePosition = 0;

            MiddleDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            MiddleDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            MiddlePosition = -4000;

            while (MiddleDrivePosition > MiddlePosition)
            {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);

            }
            ChangeState(1);
        }
        if (move_state == 1)
        {
            if (ShootState == 0)
            {
                ShooterEncoderPosition = BallShooterPosition;
                BallShooter.setPower(1);
                BallShooter.setTargetPosition(ShooterEncoderRotation + BallShooterPosition);
                ShootState = 1;
            }
            else if (ShootState == 1 && (BallShooterPosition == ShooterEncoderPosition + ShooterEncoderRotation || BallShooterPosition >= (ShooterEncoderPosition + ShooterEncoderRotation) - 12 && BallShooterPosition <= (ShooterEncoderPosition + ShooterEncoderRotation) + 12) && BallShooterPosition != ShooterEncoderPosition)
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
            else if (ShootState == 3 && (BallShooterPosition == ShooterEncoderPosition + ShooterEncoderRotation || BallShooterPosition >= (ShooterEncoderPosition + ShooterEncoderRotation) - 12 && BallShooterPosition <= (ShooterEncoderPosition + ShooterEncoderRotation) + 12) && BallShooterPosition != ShooterEncoderPosition)
            {
                MoveReleaseDrive(false);    // Close Ball Servo
                //Move to the next state
                // State Option A: Diagonal         -> ChangeState(2)
                // State Option B: Side & Forward   -> ChangeState(3)
                ChangeState(3);
                LeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                ShootState = 0;
            }
        }
        if (move_state == 3)
        {
            MoveRobot(0.7, -0.7);
            if (Gyro1Heading < 55)       // Stop robot when reaching position
            {
                MoveRobot(0, 0);
                ChangeState(4);
            }
        }
        if (move_state == 4)
        {
            int MiddlePosition = 0;
            if (timer2 < 2)
                MiddleDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            MiddleDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            MiddlePosition = -2000;

            while (MiddleDrivePosition > MiddlePosition)
            {
                MiddleDrive.setPower(-.7);
                MiddleDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MiddleDrive.setTargetPosition(MiddlePosition);

            }
            ChangeState(5);

        }
    }
}

