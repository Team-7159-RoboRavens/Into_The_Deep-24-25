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
            mp=new MotorPowers(-0.4, 0.4, 0.4, -0.4);
        }

//right_strafe
        if (opMode.gamepad1.dpad_right){
            mp=new MotorPowers(0.4, -0.4, -0.4, 0.4);
        }

//Turn Right
        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(opMode.gamepad1.right_trigger,
                    -opMode.gamepad1.right_trigger,
                    opMode.gamepad1.right_trigger,
                    -opMode.gamepad1.right_trigger);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right:", opMode.gamepad1.right_trigger);
        }

//Turn Left
        if (opMode.gamepad1.left_trigger > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.left_trigger,
                    opMode.gamepad1.left_trigger,
                    -opMode.gamepad1.left_trigger,
                    opMode.gamepad1.left_trigger);
            opMode.telemetry.addLine("Trigger Left (left turn) active!");
        }


//Open claw

        if (opMode.gamepad1.a) {
            robot.servo1.setPosition(0);
        }

//close claw
        if (opMode.gamepad1.b) {
            robot.servo1.setPosition(1);

        }
        mp = new MotorPowers(mp.leftFront,
                mp.rightFront,
                mp.leftBack,
                mp.rightBack);



        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
