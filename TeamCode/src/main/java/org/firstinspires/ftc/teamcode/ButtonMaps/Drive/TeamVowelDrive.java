package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class TeamVowelDrive extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double basePower = .9;

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);


    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = basePower;

        double triggerMultipler = 0.7;
        MotorPowers triggerMotorPowers;
        //Forward
        if (opMode.gamepad1.right_trigger > 0.1 || opMode.gamepad1.dpad_up) {
            mp = new MotorPowers(opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("RT active :>");
        }
        //backward
        else if (opMode.gamepad1.left_trigger > 0.1 || opMode.gamepad1.dpad_down) {
            mp = new MotorPowers(-opMode.gamepad1.left_trigger * triggerMultipler,
                    -opMode.gamepad1.left_trigger * triggerMultipler,
                    -opMode.gamepad1.left_trigger * triggerMultipler,
                    -opMode.gamepad1.left_trigger * triggerMultipler);
        }
        //left turn
        if (opMode.gamepad1.dpad_left) {
            mp = new MotorPowers(-1, 1, -1, 1);
        }
        // right turn
        if (opMode.gamepad1.dpad_right) {
            mp = new MotorPowers(-1, 1, -1, 1);
        }
        if (opMode.gamepad1.right_stick_x > .1) {
            mp = new MotorPowers(1, -1, 1, -1);
        } else if (opMode.gamepad1.right_stick_x < -.1) {
            mp = new MotorPowers(1, -1, -1, 1);
        }
        //turn servo left
        if (opMode.gamepad1.x) {
            Servo:
            robot.servo1.setPosition(-0.5);
        }
        if (opMode.gamepad1.b) {
            Servo:
            robot.servo1.setPosition(-0.5);
        }

        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}