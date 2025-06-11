package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.TestDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.TrikeDriveBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.TrikeAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.ComplexRobots.TrikeRobot;

@TeleOp(name="trikeTeleOp")
public class trikeTeleOp extends OpMode {
    //Global Variables
    TrikeRobot robot;

    //Button Maps
    TrikeAbstractButtonMap driveButtonMap;

    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new TrikeRobot(hardwareMap, new Pose2d(0, 0, 0));
        driveButtonMap = new TrikeDriveBM();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        driveButtonMap.loop(robot, this);
        telemetry.update();
    }
}
