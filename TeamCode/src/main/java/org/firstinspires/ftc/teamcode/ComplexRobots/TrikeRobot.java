package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.TrikeDriveBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.TrikeMotorPowers;
import org.firstinspires.ftc.teamcode.ThreeWheelDrive;


@Config
public class TrikeRobot extends ThreeWheelDrive {


    enum Direction {
        UP, DOWN
    }


    public final Servo turningServo;

    public TrikeRobot(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);

        //Servos

        turningServo = hardwareMap.get(Servo.class, "turningServo");

        //limelight

        //Initialize Output Servo
        turningServo.scaleRange(-.35, 0.35);
        turningServo.setPosition(0);
    }


    //TODO: Linear slide helper methods for auto (later)
//    public Action setSlideHeightAction(int targetPosition){
//        return new SlideHeight(targetPosition);
//    }

    public TrikeMotorPowers setAllMotorPowers(int i) {
        return new TrikeMotorPowers(i, i);
    }

    public void setMotorTo(DcMotorEx motor, int targetPos, double power) {
        if (motor.getCurrentPosition() < targetPos) {
            while (motor.getCurrentPosition() <= targetPos) {
                motor.setPower(power);
            }
        } else if (motor.getCurrentPosition() > targetPos) {
            while (motor.getCurrentPosition() >= targetPos) {
                motor.setPower(-power);
            }
        }
    }
}