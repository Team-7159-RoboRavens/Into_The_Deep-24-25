package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class PeoplesRepublicofOfirSBM extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);
    private double triggerMultipler = 0.7;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);


//forwards
        if (opMode.gamepad1.dpad_up){
            mp=new MotorPowers(0.8, 0.8, 0.8, 0.8);
        }

//backwards
        if (opMode.gamepad1.dpad_down){
            mp=new MotorPowers(-0.8, -0.8, -0.8, -0.8);
        }

//left_strafe
        if (opMode.gamepad1.dpad_left){
            mp=new MotorPowers(-1, 1, 1, -1);
        }

//right_strafe
        if (opMode.gamepad1.dpad_right){
            mp=new MotorPowers(1, -1, -1, 1);
        }

//Backward
        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_trigger,
                    -opMode.gamepad1.right_trigger,
                    -opMode.gamepad1.right_trigger,
                    -opMode.gamepad1.right_trigger);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right:", opMode.gamepad1.right_trigger);
        }

//Forward
        if (opMode.gamepad1.left_trigger > 0.1) {
            mp = new MotorPowers(opMode.gamepad1.left_trigger,
                    opMode.gamepad1.left_trigger,
                    opMode.gamepad1.left_trigger,
                    opMode.gamepad1.left_trigger);
            opMode.telemetry.addLine("Trigger Left (left turn) active!");
        }

        //Joystick Turn
        if (opMode.gamepad1.right_stick_x > 0.1) {
            mp = new MotorPowers(opMode.gamepad1.right_stick_x,
                    -opMode.gamepad1.right_stick_x,
                    opMode.gamepad1.right_stick_x,
                    -opMode.gamepad1.right_stick_x);
        }

//Open claw

        if (opMode.gamepad1.a) {
            robot.servo1.setPosition(.7);
        }

//close claw
        if (opMode.gamepad1.b) {
            robot.servo1.setPosition(1);

        }


        //Reverse if needed
        mp = new MotorPowers(-mp.leftFront*1.2,
                -mp.rightFront*1.2,
                mp.leftBack*1.2,
                -mp.rightBack*1.2);



        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
