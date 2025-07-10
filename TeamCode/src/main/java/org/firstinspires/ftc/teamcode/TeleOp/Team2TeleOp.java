package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.Team2BM;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@TeleOp(name="Team2TeleOp")
public class Team2TeleOp extends OpMode {
    //Global Variables
    IntoTheDeepRobot robot;

    //Button Maps
    AbstractButtonMap driveButtonMap;
    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(0,0,0));
        driveButtonMap = new Team2BM();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        driveButtonMap.loop(robot, this);
        telemetry.update();
    }
}
