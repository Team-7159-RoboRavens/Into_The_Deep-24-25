package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

public class OutreachArmBMTestTeleOp extends AbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = .6;
    public static double linearSlidesUpMultiplier = .6;
    public static double bucketMotor1Multiplier = 0.85;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private long specimenTime;
    private long startTime = System.currentTimeMillis();
    private long horizontalSlideTime;
    private int timeDelay = 500;
    private double intakeOutTime = 0;

    //Boolean Toggle Memory
    boolean aIsPressed = false;
    boolean yIsPressed = false;
    boolean bIsPressed = false;
    boolean xIsPressed = false;

    //    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        //Bucket Motors (on triggers)      
        if (opMode.gamepad1.left_trigger > 0.1) {
            opMode.telemetry.addLine("Down: Code updated");
            robot.bucketMotor1.setPower(-opMode.gamepad1.left_trigger * linearSlidesUpMultiplier * 1);
            robot.bucketMotor2.setPower(opMode.gamepad1.left_trigger * linearSlidesUpMultiplier * 1);
        } else if (opMode.gamepad1.right_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.bucketMotor1.setPower(opMode.gamepad1.right_trigger * linearSlidesDownMultiplier * 1);
            robot.bucketMotor2.setPower(-opMode.gamepad1.right_trigger * linearSlidesDownMultiplier * 1);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }

        if (opMode.gamepad1.x && !xIsPressed && ((System.currentTimeMillis() - startTime) > timeDelay)) {
            robot.specimenClaw.setPosition(1);
            xIsPressed = !xIsPressed;
            specimenTime = System.currentTimeMillis();
            opMode.telemetry.addLine("Servo Open");
        } else if (opMode.gamepad1.x && xIsPressed && ((System.currentTimeMillis() - specimenTime) > timeDelay)) {
            robot.specimenClaw.setPosition(-1);
            xIsPressed = !xIsPressed;
            opMode.telemetry.addLine("Servo Closed");
            startTime = System.currentTimeMillis();
        }
        //Brush Servos

        opMode.telemetry.update();
    }
}