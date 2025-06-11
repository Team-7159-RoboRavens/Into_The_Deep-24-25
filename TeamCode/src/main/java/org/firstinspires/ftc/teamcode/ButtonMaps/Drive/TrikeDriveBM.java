package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.TrikeAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.TrikeMotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.TrikeRobot;

@Config
public class TrikeDriveBM extends TrikeAbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 1;
    public static double basePower = .5;
    private double currentMotorPower;
    private TrikeMotorPowers mp;// = new MotorPowers(0);


    @Override
    public void loop(TrikeRobot robot, OpMode opMode) {
        mp = new TrikeMotorPowers(0);
        currentMotorPower = basePower;

        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new TrikeMotorPowers(
                    opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
        }

        if (opMode.gamepad1.left_trigger > 0.1) {
            mp = new TrikeMotorPowers(
                    opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
        }

        if (Math.abs(opMode.gamepad1.left_stick_x) > .15) {
            robot.turningServo.setPosition(robot.turningServo.getPosition() + opMode.gamepad1.left_stick_x * .02);
            opMode.telemetry.addData("Turning: ", opMode.gamepad1.left_stick_x);
            opMode.telemetry.addData("Current Position: ", robot.turningServo.getPosition());

        }

        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}