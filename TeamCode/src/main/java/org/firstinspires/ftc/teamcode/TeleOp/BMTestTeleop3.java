//package org.firstinspires.ftc.teamcode.TeleOp;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
//import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.THEArmBM;
//import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.NoamAndrewDriveBM;
//import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
//
//@TeleOp(name="TeleOp: Noam/Andrew Drive & Common Arm")
//public class BMTestTeleop3 extends OpMode {
//    //Global Variables
//    IntoTheDeepRobot robot;
//
//    //Button Maps
//    AbstractButtonMap buttonMap;
//    AbstractButtonMap slidesButtonMap;
//    @Override
//    public void init() {
//        telemetry.addLine("Initializing, please wait...");
//        telemetry.update();
//        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(0,0,0), this);
//        buttonMap = new NoamAndrewDriveBM();
//        slidesButtonMap = new THEArmBM();
//        telemetry.addLine("Ready.");
//        telemetry.update();
//    }
//
//    @Override
//    public void start(){
//
//    }
//
//
//    @Override
//    public void loop() {
//        buttonMap.loop(robot, this);
//        slidesButtonMap.loop(robot, this);
//        telemetry.update();
//    }
//}
