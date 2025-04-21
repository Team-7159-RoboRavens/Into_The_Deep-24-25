package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.ArianaArmBm;
//import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.SkystoneArmBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.CommonDriveBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.SkystoneDriveBM;
import org.firstinspires.ftc.teamcode.ButtonMaps.SkystoneAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.ComplexRobots.SkystoneRobot;

@TeleOp(name="Skystone TeleOp")
public class SkystoneTeleOp extends OpMode {
    //Global Variables
    SkystoneRobot robot;

    //Button Maps
    SkystoneAbstractButtonMap driveButtonMap;
//    SkystoneAbstractButtonMap slidesButtonMap;

    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new SkystoneRobot(hardwareMap, new Pose2d(0,0,0));
        driveButtonMap = new SkystoneDriveBM();
//        slidesButtonMap = new SkystoneArmBM();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        driveButtonMap.loop(robot, this);
//        slidesButtonMap.loop(robot, this);
        telemetry.update();
    }
}
