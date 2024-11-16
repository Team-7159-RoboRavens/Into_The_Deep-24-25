package org.firstinspires.ftc.teamcode.Autonmous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



import org.firstinspires.ftc.teamcode.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous()
public class ObservationPark extends LinearOpMode {
    public IntoTheDeepRobot robot;
    public Encoder par0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0),0));

        robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        par0 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftBack")));
        int initialTick = par0.getPositionAndVelocity().rawPosition;
        int currentTick = 0;
        telemetry.addData("init:", initialTick);
        telemetry.update();

        while(Math.abs(currentTick) < (robot.tiles(2)) && opModeIsActive()){
            robot.leftFront.setPower(0.3);
            robot.leftBack.setPower(0.3);
            robot.rightFront.setPower(0.3);
            robot.rightBack.setPower(0.3);
            currentTick = par0.getPositionAndVelocity().rawPosition - initialTick;

            telemetry.addData("Encoder Value: ", currentTick);
            telemetry.update();
        }

        telemetry.update();

        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);


    }
}
