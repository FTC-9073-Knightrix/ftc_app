package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//adds the file to the FtcOpmodeRegister
@TeleOp(name = "H Drive", group = "H Drive Programs")

/**
 * Created by Nicolas Bravo & Vijay Rudraraju on 10/24/16.
 * Testing
 * Moves the motors on the H Drive
 */

public class HDRIVE extends Telemetry{
    //Start
    @Override
    public void start(){
        super.start();
    }

    //Main loop
    @Override
    public void loop(){
        //If gamepad 2's left bumper is pressed
        if(gamepad2.left_bumper){
            //Stop the ball release
            MoveReleaseDrive(true);
        }
        //Else if gamepad 2's left bumper is not pressed
        else if(!gamepad2.left_bumper){
            //Move the ball release
            MoveReleaseDrive(false);
        }

        if(gamepad2.right_bumper && (BallShooterPosition == ShooterEncoderPosition || (BallShooterPosition >= (ShooterEncoderPosition + ShooterEncoderRotation) - 5 && BallShooterPosition <= (ShooterEncoderPosition + ShooterEncoderRotation) + 5)))
        {
            ShooterEncoderPosition = BallShooterPosition;
            BallShooter.setPower(1);
            BallShooter.setTargetPosition(ShooterEncoderRotation + BallShooterPosition);
        }
        /*
        //If gamepad 2's right bumper is pressed and 'rightBumperBoolean' is false
        if(gamepad2.right_bumper && rightBumperBoolean == false){
            //Shoot the ball
            MoveBallShooter();
            //Set 'rightBumperBoolean' to true
            rightBumperBoolean = true;
        }
        //Else if gamepad 2's right bumper is not pressed and 'rightBumperBoolean' is true
        else if(!gamepad2.right_bumper && rightBumperBoolean == true){
            //Set 'rightBumperBoolean' to false
            rightBumperBoolean = false;
        }
        */
        //If gamepad 2's 'A' button is pressed
        if(gamepad2.a || gamepad1.a){
            //Move the ball pickup at full speed
            MovePickupDrive(1);
        }
        //Else if gamepad 2's 'A' button is pressed
        else if(gamepad2.b || gamepad1.b){
            //Move the ball pickup backwards at full speed
            MovePickupDrive(-1);
        }
        else if(gamepad2.a && gamepad1.a){
            MovePickupDrive(1);
        }
        //Else (if gamepad 1's 'A' button is not pressed)
        else{
            //Stop the ball pickup
            MovePickupDrive(0);
        }
        //If gamepad 1's left bumper is pressed
        if(gamepad1.left_bumper){
            //Move the left beacon presser
            MoveLeftBeacon(true);
        }
        //Else if gamepad 1's left bumper is not being pressed
        else if(!gamepad1.left_bumper){
            //Move the left beacon presser back to its original position
            MoveLeftBeacon(false);
        }
        //If gamepad 1's right bumper is pressed
        if(gamepad1.right_bumper){
            //Move the right beacon presser
            MoveRightBeacon(true);
        }
        //Else if gamepad 1's right bumper is not being pressed
        else if(!gamepad1.right_bumper){
            //Move the right beacon presser back to its original position
            MoveRightBeacon(false);
        }
        //Move the left wheel at the speed of gamepad 1's right stick
        MoveLeftDrive(-gamepad1.left_stick_y);
        //Move the right wheel at the speed of gamepad 1's left stick
        MoveRightDrive(-gamepad1.right_stick_y);

        if (gamepad1.back){
            benny = true;
            if (gamepad1.dpad_right){
                MoveMiddleDrive(1);
            }
            else if (gamepad1.dpad_left){
                MoveMiddleDrive(-1);
            }
            if (gamepad1.left_trigger > 0){
                MoveRobotTeleOp(-1,-1);
            }
            else if (gamepad1.right_trigger > 0){
                MoveRobotTeleOp(1,1);
            }
            else if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0){
                MoveRobotTeleOp(0,0);
            }
            else{
                MoveRobotTeleOp(0,0);
            }
        }
        else if (benny == false || gamepad1.start) {
            //Move the middle wheel at the speed of the right trigger minus the left trigger (the right trigger being a positive value and the left being negative)
            MoveMiddleDrive(gamepad1.right_trigger - gamepad1.left_trigger);
        }

        //Displays telemetry data
        UpdateTelemetry();
    }
}

