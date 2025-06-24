package org.firstinspires.ftc.teamcode.Autonmous;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous()
public class StrafeParkingAuto extends LinearOpMode {
    IntoTheDeepRobot robot;
    public Encoder par0;

    enum Direction {FORWARD, BACKWARD, LEFT, RIGHT}

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0),0));

        robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        driveAllMotorsTo(Direction.FORWARD, 300, System.currentTimeMillis(), 0.5);
        strafeMotorsTo(Direction.RIGHT, 1800, System.currentTimeMillis(), 0.5);
        driveAllMotorsTo(Direction.BACKWARD, 100, System.currentTimeMillis(), 0.5);
        robot.specimenClaw.setPosition(0.5);

    }


    public void strafeMotorsTo(Direction direction, int millisDelay, long startTime, double motorPower){
        if(direction == Direction.LEFT){
            while((System.currentTimeMillis() - startTime) < millisDelay){
                robot.setMotorPower(-motorPower, motorPower, motorPower, -motorPower);
            }
        } else if(direction == Direction.RIGHT){
            while((System.currentTimeMillis() - startTime) < millisDelay){
                robot.setMotorPower(motorPower, -motorPower, -motorPower, motorPower);
            }
        }
    }
    public void driveAllMotorsTo(Direction direction, int millisDelay, long startTime, double motorPower){
        if(direction == Direction.FORWARD){
            while((System.currentTimeMillis() - startTime) < millisDelay){
                robot.setAllMotorPowers(motorPower);
            }
        } else if(direction == Direction.BACKWARD){
            while((System.currentTimeMillis() - startTime) < millisDelay){
                robot.setAllMotorPowers(-motorPower);
            }
        }
    }
}
