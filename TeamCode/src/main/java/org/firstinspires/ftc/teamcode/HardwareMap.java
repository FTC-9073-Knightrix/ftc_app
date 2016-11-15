package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Nicolas Bravo on 10/8/16.
 * Program Hierarchy
 * Defines the motors
 */

public abstract class HardwareMap extends OpMode{
    //Declare the motors
    DcMotor MiddleDrive;
    DcMotor LeftDrive;
    DcMotor RightDrive;
    DcMotor PickupDrive;
    DcMotor BallShooter;
    //Declare the servos
    Servo ReleaseDrive;
    Servo RightBeacon;
    Servo LeftBeacon;
    //Declare the sensors
    ModernRoboticsI2cRangeSensor Range1;
    ColorSensor Color1;
    AnalogInput Line1;
    //Declare the variables
    public double ReleasePosition;
    public double LeftPosition;
    public double RightPosition;
    public int ballShooterPosition = 0;
    public static final int ONE_ROTATION = 950;
    public double releaseTime;
    public boolean blue;
    public boolean red;

    @Override
    public void init(){
        //MiddleDrive
        MiddleDrive = hardwareMap.dcMotor.get("M1");
        MiddleDrive.setDirection(DcMotor.Direction.REVERSE);
        //LeftDrive
        LeftDrive = hardwareMap.dcMotor.get("M2");
        LeftDrive.setDirection(DcMotor.Direction.REVERSE);
        //RightDrive
        RightDrive = hardwareMap.dcMotor.get("M3");
        RightDrive.setDirection(DcMotor.Direction.FORWARD);
        //PickupDrive
        PickupDrive = hardwareMap.dcMotor.get("M4");
        PickupDrive.setDirection(DcMotor.Direction.FORWARD);
        //BallShooter
        BallShooter = hardwareMap.dcMotor.get("M5");
        BallShooter.setDirection(DcMotor.Direction.FORWARD);
        /*
        BallShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */

        BallShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //ReleaseDrive
        ReleaseDrive = hardwareMap.servo.get("S1");
        ReleaseDrive.setPosition(0.5);
        //RightBeacon
        RightBeacon = hardwareMap.servo.get("S2");
        RightBeacon.setPosition(1);
        //LeftBeacon
        LeftBeacon = hardwareMap.servo.get("S3");
        LeftBeacon.setPosition(0);

        /*
        //Range1
        Range1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "R1");
        */

        //Color1
        Color1 = hardwareMap.colorSensor.get("C1");
        Color1.enableLed(false);

        /*
        //Line1
        Line1 = hardwareMap.analogInput.get("L1");
        */
    }
    //Classes
    void MoveMiddleDrive(double Power){
        //If 'MiddleDrive' is not null
        if (MiddleDrive != null){
            //Set the power of 'MiddleDrive' to 'Power'
            MiddleDrive.setPower (Power);
        }
    }
    void MoveLeftDrive(double Power){
        //If 'LeftDrive' is not null
        if (LeftDrive != null){
            //Set the power of 'LeftDrive' to 'Power'
            LeftDrive.setPower (Power);
        }
    }
    void MoveRightDrive(double Power){
        //If 'RightDrive' is not null
        if (RightDrive != null){
            //Set the power of 'RightDrive' to 'Power'
            RightDrive.setPower (Power);
        }
    }
    void MovePickupDrive(double Power){
        //If 'PickupDrive' is not null
        if (PickupDrive != null){
            //Set the power of 'PickupDrive' to 'Power'
            PickupDrive.setPower (Power);
        }
    }

    void MoveBallShooter(double Power){
        if (BallShooter != null){
            BallShooter.setPower(Power);
        }
    }
    /*
    void MoveBallShooter(){
        //If 'BallShooter' is not null
        if (BallShooter != null){
            //Set the target position of 'BallShooter' to one rotation more than the current position
            BallShooter.setTargetPosition(ballShooterPosition + ONE_ROTATION);
            //Set the position of 'ballShooterPosition' to the current position of 'BallShooter'
            ballShooterPosition = BallShooter.getCurrentPosition();
            //While 'BallShooter' has a position less than one rotation more than its original position
            while (BallShooter.getCurrentPosition() < ballShooterPosition + ONE_ROTATION){
                //Set the power of 'BallShooter' to 50%
                BallShooter.setPower(0.5);
                //Set the mode of 'BallShooter' to 'RUN_TO_POSITION'
                BallShooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            //While 'BallShooter' has a position greater than or equal to one rotation more than its original position
            while (BallShooter.getCurrentPosition() >= ballShooterPosition + ONE_ROTATION){
                //Set the power of 'BallShooter' to 0
                BallShooter.setPower(0);
                //Set 'ballShooterPosition' as the current position of 'BallShooter'
                ballShooterPosition = BallShooter.getCurrentPosition();
            }
        }
    }
    */
    void MoveReleaseDrive(boolean Move){
        //If 'ReleaseDrive' is not null
        if (ReleaseDrive != null){
            //If 'Move' is true
            if (Move == true){
                //Set the position of 'ReleaseDrive' to 1
                ReleaseDrive.setPosition(1);
            }
            //Else (if 'Move' is false
            else{
                //Set the position of 'ReleaseDrive' to 0.5
                ReleaseDrive.setPosition(0.5);
            }
        }
    }
    void MoveRightBeacon(boolean Move){
        //If 'RightBeacon' is not null
        if (RightBeacon != null){
            //If 'Move' is true
            if (Move == true){
                //Set 'RightBeacon' to 0.4
                RightBeacon.setPosition(0.4);
            }
            //Else (if 'Move' is false
            else{
                //Set 'RightBeacon' to 1
                RightBeacon.setPosition(1);
            }
        }
    }
    void MoveLeftBeacon(boolean Move){
        //If 'LeftBeacon' is not null
        if (LeftBeacon != null){
            //If 'Move' is true
            if (Move == true){
                //Set 'LeftBeacon' to 0.6
                LeftBeacon.setPosition(0.6);
            }
            //Else (if 'Move' is false
            else{
                //Set 'LeftBeacon' to 0
                LeftBeacon.setPosition(0);
            }
        }
    }
}