package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TrikeDrive;


@Config
public class TrikeRobot extends TrikeDrive {
    enum Direction {
        UP,DOWNw
    }

//    public final DcMotorEx horizontalSlideMotor;
    public final Servo turnServo;
//    public final SensorLimelight3A limelight;

    public TrikeRobot(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);

        //Servos
        turnServo = hardwareMap.get(Servo.class, "turnServo");

        //Initialize Output Servo
        turnServo.scaleRange(-1,1);
    }


    //TODO: Linear slide helper methods for auto (later)
    public MotorPowers setAllMotorPowers(int i) {
        return new MotorPowers(0,0,0,0);
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
