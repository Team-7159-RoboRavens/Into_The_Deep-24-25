package org.firstinspires.ftc.teamcode.Autonmous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Autonomous(name = "NoamTempONEAuto")
public class NoamTemp1Auto extends LinearOpMode {
    enum Direction{
        UP, DOWN
    }

    IntoTheDeepRobot robot;
    int bucketMotorsAvgPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.update();
        // INITIALIZATION
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0), 0));
        bucketMotorsAvgPosition = (robot.bucketMotor1.getCurrentPosition() + robot.bucketMotor2.getCurrentPosition())/2;
        waitForStart();
        robot.specimenClaw.setPosition(.5);
        // go forward to sub
        robot.driveSlidesTo(140,0.5,1);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-32.75,0)).build()
        );
        sleep(500);
        robot.driveSlidesTo(100, 0.85,-1);
        // back up from sub
        sleep(500);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(30,-80), 0*Math.PI).build()
        );
        // open the claw and lower the slides to prepare for grabbing, then park
        robot.driveSlidesTo(1, 0.7,-1);
        sleep(200);
        robot.specimenClaw.setPosition(0.8);
    }
}
