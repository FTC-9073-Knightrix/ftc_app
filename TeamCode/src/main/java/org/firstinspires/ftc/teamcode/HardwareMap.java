package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


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
   // ModernRoboticsI2cRangeSensor Range2wall;
    UltrasonicSensor LegoRange;
    ColorSensor Color1;
    AnalogInput FrontLine;
    AnalogInput RightLine;
    AnalogInput LeftLine;
    GyroSensor Gyro1;

    //Declare the variables
    public double ReleasePosition;
    public double LeftPosition;
    public double RightPosition;
    public int ballShooterPosition = 0;
    public static final int ONE_ROTATION = 950;
    public double releaseTime;
    public boolean blue;
    public boolean red;
    public boolean benny = false;
    public static final double LineTrackerVoltage = 2;
    public static final int ShooterEncoderRotation = 2900;
    public int ShooterEncoderPosition;
    public double move_state = 0;
    public double timer_state = -1;
    public double timer1 = getRuntime();
    public double timer2 = getRuntime() - timer1;
    public boolean left = false;
    public boolean right = false;
    public int degree = 0;
    public boolean calibrated = false;
    public boolean calibrate = false;
    public int BeaconNum = 1;

    //Sensor Values
    public double LegoRangeValue;
    public double Range1Value;
    public int Color1Red;
    public int Color1Green;
    public int Color1Blue;
    public double FrontLineVoltage;
    public double LeftLineVoltage;
    public double RightLineVoltage;
    public int Gyro1Heading;
    //Motor Powers
    public double MiddleDrivePower;
    public double LeftDrivePower;
    public double RightDrivePower;
    public double PickupDrivePower;
    //Motor Positions
    public int MiddleDrivePosition;
    public int LeftDrivePosition;
    public int RightDrivePosition;
    public int BallShooterPosition;

    @Override
    public void init(){
        //MiddleDrive
        MiddleDrive = hardwareMap.dcMotor.get("M1");
        MiddleDrive.setDirection(DcMotor.Direction.REVERSE);
        MiddleDrivePower = MiddleDrive.getPower();
        MiddleDrivePosition = MiddleDrive.getCurrentPosition();
        //vijay 3
        //LeftDrive
        LeftDrive = hardwareMap.dcMotor.get("M2");
        LeftDrive.setDirection(DcMotor.Direction.REVERSE);
        LeftDrivePosition = LeftDrive.getCurrentPosition();
        LeftDrivePower = LeftDrive.getPower();

        //RightDrive
        RightDrive = hardwareMap.dcMotor.get("M3");
        RightDrive.setDirection(DcMotor.Direction.FORWARD);
        RightDrivePosition = RightDrive.getCurrentPosition();
        RightDrivePower = RightDrive.getPower();
        //PickupDrive
        PickupDrive = hardwareMap.dcMotor.get("M4");
        PickupDrive.setDirection(DcMotor.Direction.FORWARD);
        PickupDrivePower = PickupDrive.getPower();

        //BallShooter
        BallShooter = hardwareMap.dcMotor.get("M5");
        BallShooter.setDirection(DcMotor.Direction.FORWARD);
        BallShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BallShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BallShooterPosition = BallShooter.getCurrentPosition();

        //ReleaseDrive
        ReleaseDrive = hardwareMap.servo.get("S1");
        ReleaseDrive.setPosition(1);
        //RightBeacon
        RightBeacon = hardwareMap.servo.get("S2");
        RightBeacon.setPosition(1);
        //LeftBeacon
        LeftBeacon = hardwareMap.servo.get("S3");
        LeftBeacon.setPosition(0);

        //Range1
        Range1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "R1");
        Range1Value = Range1.getDistance(DistanceUnit.CM);
        // Range2wall = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "R2");
        LegoRange = hardwareMap.get(UltrasonicSensor.class,"LR1");



        //Color1
        Color1 = hardwareMap.colorSensor.get("C1");
        Color1.enableLed(false);
        Color1Red = Color1.red();
        Color1Green = Color1.green();
        Color1Blue = Color1.blue();

        //Line Trackers
        FrontLine = hardwareMap.analogInput.get("L1");
        FrontLineVoltage = FrontLine.getVoltage();
        LeftLine = hardwareMap.analogInput.get("L2");
        LeftLineVoltage = LeftLine.getVoltage();
        RightLine = hardwareMap.analogInput.get("L3");
        RightLineVoltage = RightLine.getVoltage();

        //Gyro1
        Gyro1 = hardwareMap.gyroSensor.get("G1");
        // reset gyro sensor
        //Calibrate
        while (calibrated == false)
        {
            if (calibrate == false)
            {
                Gyro1.calibrate();
                calibrate = true;
            }
            if (Gyro1.isCalibrating() == false)
            {
                calibrated = true;
            }
        }
        Gyro1Heading = Gyro1.getHeading();


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
    void MoveRobot(double PowerLeft, double PowerRight){
        //If 'RightDrive' is not null
        if (RightDrive != null){
            //Set the power of 'RightDrive' to 'PowerRight'
            RightDrive.setPower (PowerRight/3);
        }
        //If 'LeftDrive' is not null
        if (LeftDrive != null){
            //Set the power of 'LeftDrive' to 'PowerLeft'
            LeftDrive.setPower (PowerLeft/3);
        }
    }
    void MoveRobotTeleOp(double PowerLeft, double PowerRight){
        //If 'RightDrive' is not null
        if (RightDrive != null){
            //Set the power of 'RightDrive' to 'PowerRight'
            RightDrive.setPower (PowerRight);
        }
        //If 'LeftDrive' is not null
        if (LeftDrive != null){
            //Set the power of 'LeftDrive' to 'PowerLeft'
            LeftDrive.setPower (PowerLeft);
        }
    }
    void MovePickupDrive(double Power){
        //If 'PickupDrive
        // ' is not null
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
                ReleaseDrive.setPosition(0.5);
            }
            //Else (if 'Move' is false
            else{
                //Set the position of 'ReleaseDrive' to 0.5
                ReleaseDrive.setPosition(1);
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
    void TimerReset(){
        timer1 = getRuntime();
        timer2 = getRuntime() - timer1;
    }
    void ChangeState(double state){
        move_state = state;
        timer1 = getRuntime();
        timer2 = getRuntime() - timer1;
        timer_state = state;
        //Stop the robot
        MoveRobot(0,0);
        MoveMiddleDrive(0);
        MoveBallShooter(0);
    }

    void Timer2Reset(){
        timer2 = getRuntime() - timer1;
    }
    void LineTrackerLeft()
    {
        //If LeftLine is black
        if (LeftLine.getVoltage() >= LineTrackerVoltage && right == false)
        {
            //Move MiddleDrive left by 0.15
            MoveMiddleDrive(0.27);
            left = true;
        }
        //If it no longer applies
        else if (LeftLine.getVoltage() < LineTrackerVoltage && left == true)
        {
            //Stop the middle wheel
            MoveMiddleDrive(0);
            left = false;
        }
        //While RightLine is black
        if (RightLine.getVoltage() >= LineTrackerVoltage && left == false)
        {
            //Move MiddleDrive right by 0.15
            MoveMiddleDrive(-0.27);
            right = true;
        }
        //If it no longer applies
        else if (RightLine.getVoltage() < LineTrackerVoltage && right == true)
        {
            //Stop the middle motor
            MoveMiddleDrive(0);
            right = false;
        }
    }
    void LineTrackerRight()
    {
        //If LeftLine is black
        if (LeftLine.getVoltage() >= LineTrackerVoltage)
        {
            //Move MiddleDrive left by 0.15
            MoveMiddleDrive(-0.27);
        }
        //If it no longer applies
        else if (LeftLine.getVoltage() < LineTrackerVoltage)
        {
            //Stop the middle wheel
            MoveMiddleDrive(0);
        }
        //While RightLine is black
        if (RightLine.getVoltage() >= LineTrackerVoltage)
        {
            //Move MiddleDrive right by 0.15
            MoveMiddleDrive(0.27);
        }
        //If it no longer applies
        else if (RightLine.getVoltage() < LineTrackerVoltage)
        {
            //Stop the middle motor
            MoveMiddleDrive(0);
        }
    }
    void NextState(double state)
    {
        //Stop the robot
        MoveRobot(0,0);
        MoveMiddleDrive(0);
        MoveBallShooter(0);
        //Move to the next state
        move_state = move_state + state;
    }
    void NextTimer()
    {
        timer_state = move_state;
        TimerReset();
    }
    void GetValues()
    {
        //Sensor Values
        LegoRangeValue = LegoRange.getUltrasonicLevel();
        Range1Value = Range1.getDistance(DistanceUnit.CM);
        Color1Red = Color1.red();
        Color1Green = Color1.green();
        Color1Blue = Color1.blue();
        FrontLineVoltage = FrontLine.getVoltage();
        LeftLineVoltage = LeftLine.getVoltage();
        RightLineVoltage = RightLine.getVoltage();
        Gyro1Heading = Gyro1.getHeading();
        //Motor Powers
        MiddleDrivePower = MiddleDrive.getPower();
        LeftDrivePower = LeftDrive.getPower();
        RightDrivePower = RightDrive.getPower();
        PickupDrivePower = PickupDrive.getPower();
        //Motor Encoder Positions
        MiddleDrivePosition = MiddleDrive.getCurrentPosition();
        LeftDrivePosition = LeftDrive.getCurrentPosition();
        RightDrivePosition = RightDrive.getCurrentPosition();
        BallShooterPosition = BallShooter.getCurrentPosition();
    }
}