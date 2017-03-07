package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Shooter Encoder", group = "TeleOp")

/**
 * Created by nicolasbravo on 3/6/17.
 * For use in the [Driver Controlled/Autonomous] Portion of the match
 * [Description]
 */

public class ShooterEncoder extends Telemetry
{
    @Override
    public void start()

    {
        super.start();
    }

    @Override
    public void loop()
    {
        BallShooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ShooterEncoderPosition = BallShooter.getCurrentPosition();
        if ((gamepad1.right_bumper || gamepad2.right_bumper) && (BallShooter.getCurrentPosition() == ShooterEncoderPosition || BallShooter.getCurrentPosition() == ShooterEncoderPosition + ShooterEncoderRotation))
        {
            /**BallShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);*/
            BallShooter.setPower(1);
            BallShooter.setTargetPosition(ShooterEncoderRotation + BallShooter.getCurrentPosition());
        }

        UpdateTelemetry();
    }
}
