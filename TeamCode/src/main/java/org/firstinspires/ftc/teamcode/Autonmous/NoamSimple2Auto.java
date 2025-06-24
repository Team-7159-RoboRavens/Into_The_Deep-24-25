package org.firstinspires.ftc.teamcode.Autonmous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Autonomous(name = "NoamSimpleTWOAuto")
public class NoamSimple2Auto extends LinearOpMode {
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
        sleep(250);
        robot.driveSlidesTo(100, 0.7,-1);
        // back up from sub
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(15,0)).build()
        );
        // get close to parking
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-17.5,-100), -1*Math.PI).build()
        );
        // open the claw and lower the slides to prepare for grabbing, then park
        robot.driveSlidesTo(1, 0.7,-1);
        robot.specimenClaw.setPosition(0.8);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-10,100), -1*Math.PI).build()
        );
        // Wait for human player error, then close the claw.
        sleep(500);
        robot.specimenClaw.setPosition(0.4);
        sleep(250); // lets claw close fully before moving up
        // strafes to near the sub while raising slides
        //robot.driveSlidesTo(100, 0.5,1);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-30,60), -0.5*Math.PI).build()
        );
        // run across the field to the sub!
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-35,-25), -0.5*Math.PI).build()
        );
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-40,-20), 0*Math.PI).build()
        );
        // go forward to sub, while raising slides for scoring
        robot.driveSlidesTo(190, 0.5,1);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-33.75,-20), 0*Math.PI).build()
        );
        // score, and back off!
        robot.driveSlidesTo(140, 0.7,-1);
        robot.specimenClaw.setPosition(0.8);
        // skedaddle back to the parking zone!
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-32,80), 0.6*Math.PI).build()
        );
        robot.driveSlidesTo(1, 0.7,-1);
    }
}
