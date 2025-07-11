package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class Team2BM extends AbstractButtonMap {

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        double servoPosition = 0.0;
        double triggerMultiplier = 0.75;
        boolean triggerIsPressed = false;

//forward
        if (opMode.gamepad1.dpad_up) {
            mp = new MotorPowers(-0.75,
                    -0.75,
                    -0.75,
                    -0.75);
            opMode.telemetry.addLine("(forward) active");
        }

//backward
        else if (opMode.gamepad1.dpad_down) {
            mp = new MotorPowers(0.75,
                    0.75,
                    0.75,
                    0.75);
            opMode.telemetry.addLine("(backward) active");
        }

//left
        else if (opMode.gamepad1.dpad_left) {
            mp = new MotorPowers(0.75,
                    -0.75,
                    -0.75,
                    0.75);
            opMode.telemetry.addLine("left active");
        }

//right
        else if (opMode.gamepad1.dpad_right) {
            mp = new MotorPowers(-0.75,
                    0.75,
                    0.75,
                    -0.75);
            opMode.telemetry.addLine("(right) active");
        }

//right turn
        else if (opMode.gamepad1.right_stick_x > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_stick_x,
                    opMode.gamepad1.right_stick_x,
                    -opMode.gamepad1.right_stick_x,
                    opMode.gamepad1.right_stick_x);
            opMode.telemetry.addLine(" (right turn)active");
        }
//left turn
        else if (opMode.gamepad1.right_stick_x < -0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_stick_x,
                    opMode.gamepad1.right_stick_x,
                    -opMode.gamepad1.right_stick_x,
                    opMode.gamepad1.right_stick_x);
            opMode.telemetry.addLine("(left turn)active");
        }
//servo open
        if (opMode.gamepad1.right_trigger > 0.1) {
            servoPosition = 1;

        }

//Servo close
        else if (opMode.gamepad1.left_trigger > 0.1) {
            servoPosition = -1;

        }
        mp = new MotorPowers(mp.leftFront, mp.rightFront, mp.leftBack, mp.rightBack);
        robot.servo1.setPosition(servoPosition);

        opMode.telemetry.update();

        robot.setMotorPowers(mp);
}
}
