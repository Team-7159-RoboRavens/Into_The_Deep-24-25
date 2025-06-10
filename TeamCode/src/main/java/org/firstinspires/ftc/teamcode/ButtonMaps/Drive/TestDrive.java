package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.HolonomicDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.ComplexRobots.SkystoneRobot;

@Config
public class TestDrive extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 1;
    public static double dpadBumperMultiplier = 0.65;
    // public static double fodMultiplier = 0.85;
    public static double slowStrafeMultiplier = .5;
    public static double basePower = .9;
    public static double hdMultiplier = 0.85;
    public static double fodMultiplier = 0.85;
    private boolean buttonPressed = false;
    private boolean combineWithPivotTurn = false;

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);


    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = basePower;

        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_trigger * triggerMultipler,
                    -opMode.gamepad1.right_trigger * triggerMultipler,
                    -opMode.gamepad1.right_trigger * triggerMultipler,
                    -opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
        }

        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}