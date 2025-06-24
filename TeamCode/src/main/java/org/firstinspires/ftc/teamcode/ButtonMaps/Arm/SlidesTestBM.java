package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

public class SlidesTestBM extends AbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = 0.35;
    public static double linearSlidesUpMultiplier = 0.5;

    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double intakeOutTime = 0;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
       if(opMode.gamepad1.left_trigger > 0.1){
           robot.horizontalSlideMotor.setPower(-linearSlidesDownMultiplier * opMode.gamepad1.left_trigger);
       } else if(opMode.gamepad1.right_trigger > 0.1){
            robot.horizontalSlideMotor.setPower(linearSlidesUpMultiplier * opMode.gamepad1.right_trigger);
        }

    }
}