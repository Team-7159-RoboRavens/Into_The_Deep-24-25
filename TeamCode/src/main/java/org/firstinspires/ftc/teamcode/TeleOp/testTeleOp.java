package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.SkystoneDriveBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.SkystoneAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.SkystoneRobot;

@TeleOp(name="SkystoneTest TeleOp")
public class testTeleOp extends OpMode {
    //Global Variables
    SkystoneRobot robot;

    //Button Maps
    SkystoneAbstractButtonMap driveButtonMap;
    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new SkystoneRobot(hardwareMap, new Pose2d(0,0,0));
        driveButtonMap = new SkystoneDriveBM();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        driveButtonMap.loop(robot, this);
        telemetry.update();
    }
}
