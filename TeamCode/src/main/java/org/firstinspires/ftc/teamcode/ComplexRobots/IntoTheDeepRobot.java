package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorLimelight3A;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;


@Config
public class IntoTheDeepRobot extends MecanumDrive {


    enum Direction {
        UP,DOWN
    }

 public final Servo servo1;

    public IntoTheDeepRobot(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);
        //Linear Slide Motor

        servo1 =  hardwareMap.get(Servo.class, "servo1");

        //limelight
        //Initialize Output Servo
        servo1.scaleRange(-1,1);

    }


    //TODO: Linear slide helper methods for auto (later)
//    public Action setSlideHeightAction(int targetPosition){
//        return new SlideHeight(targetPosition);
//    }

    public MotorPowers setAllMotorPowers(int i) {
        return new MotorPowers(0,0,0,0);
    }
//    public void driveSlidesTo(int targetPosition, double motorPower, int direction){
//        int avgSlidesPos = (bucketMotor1.getCurrentPosition() + bucketMotor2.getCurrentPosition())/2;
//        if(direction == -1) {
//            while (((bucketMotor1.getCurrentPosition() + bucketMotor2.getCurrentPosition()) / 2) >= targetPosition){
//                bucketMotor1.setPower(-motorPower);
//                bucketMotor2.setPower(motorPower);
//            }
//        } else if(direction == 1) {
//            while (((bucketMotor1.getCurrentPosition() + bucketMotor2.getCurrentPosition()) / 2) <= targetPosition){
//                bucketMotor1.setPower(motorPower);
//                bucketMotor2.setPower(-motorPower);
//            }
//        }
//        bucketMotor1.setPower(0);
//        bucketMotor2.setPower(0);
//    }

    public MotorPowers pivotTurn(double currentMotorPower, boolean rightBumper, boolean leftBumper) {
        double rightTopMotorPower = currentMotorPower;
        double rightBotMotorPower = currentMotorPower;
        double leftTopMotorPower = currentMotorPower;
        double leftBotMotorPower = currentMotorPower;
        if (leftBumper) {

            rightBotMotorPower *= -1;
            rightTopMotorPower *= -1;
        }
        else if (rightBumper) {
            leftBotMotorPower *= -1;
            leftTopMotorPower *= -1;
        }
        return new MotorPowers(leftTopMotorPower,rightTopMotorPower,leftBotMotorPower,rightBotMotorPower);
    }

    public void setMotorTo(DcMotorEx motor, int targetPos, double power) {
        if (motor.getCurrentPosition() < targetPos) {
            while (motor.getCurrentPosition() <= targetPos) {
                motor.setPower(power);
            }
        }
        else if (motor.getCurrentPosition() > targetPos) {
            while (motor.getCurrentPosition() >= targetPos) {
                motor.setPower(-power);
            }
        }
    }
    }
