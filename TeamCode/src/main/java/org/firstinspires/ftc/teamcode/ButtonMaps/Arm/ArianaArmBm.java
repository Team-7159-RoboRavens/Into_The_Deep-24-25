package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

public class ArianaArmBm extends AbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = 0.35;
    public static double linearSlidesUpMultiplier = 0.5;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double intakeOutTime = 0;

    //Servo Positions
//    double x =
//    double y =
//    double a =
//    double b =
//    double e =
//    double f =

    //Boolean Toggle Memory
    boolean aIsPressed = false;
    boolean yIsPressed = false;
    boolean bIsPressed = false;

    //    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        //Wrist Servo (elbow)
        if (opMode.gamepad2.a && !aIsPressed) {
//            robot.elbowServo.setPosition(e);
            aIsPressed = !aIsPressed;
        } else if (opMode.gamepad2.a && aIsPressed) {
//            robot.elbowServo.setPosition(f);
            aIsPressed = !aIsPressed;
        }
        //Bucket Motors (on triggers)      
        if (opMode.gamepad2.left_trigger > 0.1) {
            if (robot.bucketMotor1.getCurrentPosition() < -5 || robot.bucketMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.bucketMotor1.setPower(-1);
                robot.bucketMotor2.setPower(-1);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.bucketMotor1.setPower(opMode.gamepad2.left_trigger);
                robot.bucketMotor2.setPower(opMode.gamepad2.left_trigger);
            }
        } else if (opMode.gamepad2.right_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.bucketMotor1.setPower(opMode.gamepad2.right_trigger);
            robot.bucketMotor2.setPower(opMode.gamepad2.right_trigger);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }
        //Brush Servos
        if (opMode.gamepad2.y && !yIsPressed) {
//            robot.brushServo1.setPosition(x);
//            robot.brushServo2.setPosition(x);
            yIsPressed = !yIsPressed;
        } else if (opMode.gamepad2.y && yIsPressed) {
//            robot.brushServo1.setPosition(y);
//            robot.brushServo2.setPosition(y);
            yIsPressed = !yIsPressed;

            //Linear Slides Motor
            if (opMode.gamepad2.b && !bIsPressed) {
                robot.horizontalSlideMotor.setPower(1 * linearSlidesUpMultiplier);
                bIsPressed = !bIsPressed;
            } else if (opMode.gamepad2.b && bIsPressed) {
                robot.horizontalSlideMotor.setPower(-1 * linearSlidesDownMultiplier);
                bIsPressed = !bIsPressed;
            } else {
                robot.horizontalSlideMotor.setPower(0);
            }
            //Claw Servo (ROSE)
//        if (opMode.gamepad2.left_bumper) {
//              robot.clawServo.setPower(opMode.gamepad2.left_bumper);
//              robot.clawServo.setPosition(a);
//        } else if (opMode.gamepad2.right_bumper) {
//              robot.clawServo.setPower(-1);
//              robot.clawServo.setPosition(b);
//        }

//        if(opMode.gamepad2.)
        }
    }
}