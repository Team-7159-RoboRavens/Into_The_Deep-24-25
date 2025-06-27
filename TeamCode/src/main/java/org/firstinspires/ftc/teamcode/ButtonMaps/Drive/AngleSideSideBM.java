package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.HolonomicDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class AngleSideSideBM extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);
    private final double triggerMultipler = 0.7;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
//Forward
        if (opMode.gamepad1.y) {
            mp = new MotorPowers(-.7,
                    .7,
                    -.7,
                    .7);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.y);

//Backward
        } else if (opMode.gamepad1.a) {
            mp = new MotorPowers(.7,
                    -.7,
                    .7,
                    -.7);
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.a);

//Right
        } else if (opMode.gamepad1.b) {
            mp = new MotorPowers(.7,
                    .7,
                    .7,
                    .7);
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.b);

//Left
        } else if (opMode.gamepad1.x) {
            mp = new MotorPowers(-.7,
                    -.7,
                    -.7,
                    -.7;
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.x);


            if (opMode.gamepad1.left_trigger>0.05) {
                robot.servo1.setPosition(.7);

            } else if (opMode.gamepad1.right_trigger > 0.05) {
                robot.servo1.setPosition(-0.3);



            }
        }
        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
