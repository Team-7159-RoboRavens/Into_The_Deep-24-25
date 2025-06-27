package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.HolonomicDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class ParallelPlatesBM extends AbstractButtonMap {

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);
    double triggerMultiplier = 0.8;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = .9;






//what


//Sensitivity
        if (opMode.gamepad1.y) {
            triggerMultiplier = 0.95;
            opMode.telemetry.addLine("Sensitivity increased to 0.95!");
        }

        if (opMode.gamepad1.a) {
            triggerMultiplier = 0.65;
            opMode.telemetry.addLine("Sensitivity deceased to 0.65!");
        }

//Forward

        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_trigger * triggerMultiplier, -opMode.gamepad1.right_trigger * triggerMultiplier, -opMode.gamepad1.right_trigger * triggerMultiplier, -opMode.gamepad1.right_trigger * triggerMultiplier);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right:", opMode.gamepad1.right_trigger);
        }

//Backwards

        else if (opMode.gamepad1.left_trigger > 0.1) {

            mp = new MotorPowers(opMode.gamepad1.left_trigger * triggerMultiplier, opMode.gamepad1.left_trigger * triggerMultiplier, opMode.gamepad1.left_trigger * triggerMultiplier, opMode.gamepad1.left_trigger * triggerMultiplier);
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.left_trigger);
        }

//Strafe Left

        if (opMode.gamepad1.dpad_left) {
            mp = new MotorPowers(-triggerMultiplier, triggerMultiplier, triggerMultiplier, -triggerMultiplier);
            opMode.telemetry.addLine("Dpad Left (Strafe Left) active! ");
        }




//Strafe Right

        if (opMode.gamepad1.dpad_right) {
            mp = new MotorPowers(triggerMultiplier, -triggerMultiplier, -triggerMultiplier, triggerMultiplier);
            opMode.telemetry.addLine("Dpad Right (Strafe Right) active! ");
        }

//Strafe Forward

        if (opMode.gamepad1.dpad_up) {
            mp = new MotorPowers(triggerMultiplier, triggerMultiplier, triggerMultiplier, triggerMultiplier);
            opMode.telemetry.addLine("Dpad Right (forward) active!");
        }

//Strafe Backwards

        if (opMode.gamepad1.dpad_down) {
            mp = new MotorPowers(-triggerMultiplier, -triggerMultiplier, -triggerMultiplier, -triggerMultiplier);
            opMode.telemetry.addLine("Dpad Right (backward) active!");
        }


//Servo (down)

        if (opMode.gamepad1.left_bumper) {
            robot.servo1.setPosition(-1);
            opMode.telemetry.addLine("Servo is down!");
        }

//Servo (up)

        if (opMode.gamepad1.right_bumper) {
            robot.servo1.setPosition(1);
            opMode.telemetry.addLine("Servo Is up!");
        }

//Servo control speed (X and B)

        if (opMode.gamepad1.x) {
            robot.servo1.setPosition(-1);
            opMode.telemetry.addLine("Servo is up!");
        }

        if (opMode.gamepad1.b) {
            robot.servo1.setPosition(1);
            opMode.telemetry.addLine("Servo is down!");
        }

// RightJoystick (turning)

        if (Math.abs(opMode.gamepad1.right_stick_x)>0.1) {
            mp = new MotorPowers(opMode.gamepad1.right_stick_x * triggerMultiplier, -opMode.gamepad1.right_stick_x * triggerMultiplier, opMode.gamepad1.right_stick_x * triggerMultiplier, -opMode.gamepad1.right_stick_x * triggerMultiplier);
            opMode.telemetry.addLine("We is turning!");
//note: if the turning is incorrect, reverse the negatives.
        }

//to inverse the motors

        mp = new MotorPowers(mp.leftFront,- mp.rightFront, -mp.leftBack, -mp.rightBack);



//Gud boob 🙂

















        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
