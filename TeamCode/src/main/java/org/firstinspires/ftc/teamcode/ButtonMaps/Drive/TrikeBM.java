package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.HolonomicDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ButtonMaps.SkystoneAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.TrikeAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.SkystoneRobot;
import org.firstinspires.ftc.teamcode.ComplexRobots.TrikeRobot;

@Config
public class TrikeBM extends TrikeAbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 1;

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);
    private double timeSince = 0.0;
    private double timeWhen = System.currentTimeMillis();
    @Override
    public void loop(TrikeRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = .9;

        /*
         * Normal Drive
         */
        MotorPowers triggerMotorPowers;
        //Forward
        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
        }
        //Backward
        else if (opMode.gamepad1.left_trigger > 0.1) {
            //Backward
            mp = new MotorPowers(-opMode.gamepad1.left_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.left_trigger);
        }

        /*
         * Button Y - Complete break
         */
        if (opMode.gamepad1.y) {
            robot.setAllMotorPowers(0);
            robot.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            opMode.telemetry.addLine("Brake!!");
        }
        //Slow Strafe Button

        //test by putting at beginning
        if (opMode.gamepad1.x) {
            mp = new MotorPowers(mp.leftFront * .5);
            opMode.telemetry.addLine("Slow Multiplier Active!");
        }

        if(Math.abs(opMode.gamepad1.left_stick_x) > .2 &&  (System.currentTimeMillis() - timeWhen >= 50.0)) {
            robot.turnServo.setPosition(robot.turnServo.getPosition() + opMode.gamepad1.left_stick_x * .2);
            opMode.telemetry.addLine("Turn! " + opMode.gamepad1.left_stick_x);
            opMode.telemetry.addLine("Position of Servo " + robot.turnServo.getPosition());
        }

        if(opMode.gamepad1.dpad_left) {
            robot.turnServo.setPosition(1);
        }
        if(opMode.gamepad1.dpad_right) {
            robot.turnServo.setPosition(1);
        }

        mp = new MotorPowers(mp.leftFront);
        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
