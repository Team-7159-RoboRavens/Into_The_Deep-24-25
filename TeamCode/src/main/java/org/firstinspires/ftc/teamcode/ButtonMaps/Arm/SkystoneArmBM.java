package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;
//
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
//
import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.SkystoneAbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.ComplexRobots.SkystoneRobot;

public abstract class SkystoneArmBM extends SkystoneAbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = .4;
    public static double linearSlidesUpMultiplier = .4;
    public static double bucketMotor1Multiplier = 0.85;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private long specimenTime;
    private long startTime = System.currentTimeMillis();
    private long horizontalSlideTime;
    private int timeDelay = 500;
    private long elbowTimeDelay = System.currentTimeMillis();
    private double intakeOutTime = 0;
    private long sampleServoTime = System.currentTimeMillis();
    private long brushServoTimeDelay = 250;
    private long elbowServoTime = System.currentTimeMillis();
//
//
//    //Boolean Toggle Memory
    boolean aIsPressed = false;
    static int STOPPED = 0;
    static int FORWARD = 1;
    static int REVERSE = 2;
    int yState = STOPPED;
    boolean yIsPressed = false;
    boolean bIsPressed = false;
    boolean rbIsPressed = false;
    boolean lbIsPressed = false;
    boolean xIsPressed = false;
    double stageOfBrushServo = 0;

    int bucketMotorsAvgPostiion;


//        @Override
    public void loop(SkystoneRobot robot, OpMode opMode) {
        bucketMotorsAvgPostiion = (robot.verticalSlideMotor1.getCurrentPosition() + robot.verticalSlideMotor2.getCurrentPosition())/2;

        // Wrist Servo (elbow)
        if (opMode.gamepad2.a && !aIsPressed && ((System.currentTimeMillis() - elbowTimeDelay) > timeDelay)) {
            robot.clawRotationServo.setPosition(1);
            aIsPressed = !aIsPressed;
            elbowTimeDelay = System.currentTimeMillis();
        } else if (opMode.gamepad2.a && aIsPressed && ((System.currentTimeMillis() - elbowTimeDelay) > timeDelay)) {
            robot.clawRotationServo.setPosition(0.5);
            aIsPressed = !aIsPressed;
            elbowTimeDelay = System.currentTimeMillis();
        }

        opMode.telemetry.addData("ES Position: ", robot.clawRotationServo.getPosition());
        // Bucket Motors (on triggers)
        opMode.telemetry.addData("Bucket Encoder Avg: ", (robot.verticalSlideMotor1.getCurrentPosition() + robot.verticalSlideMotor2.getCurrentPosition())/2);

        if (opMode.gamepad2.b && ((System.currentTimeMillis() - elbowServoTime) > timeDelay)) {
            // one servo to spin brush one servo to angle brush - the other other a elbow servo
            robot.clawRotationServo.setPosition(0.8);
            elbowServoTime = System.currentTimeMillis();
        }

//
//        // Bucket Motors
        if (opMode.gamepad2.left_trigger > 0.1) {
                robot.verticalSlideMotor1.setPower(-opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
                robot.verticalSlideMotor2.setPower(opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
        } else if (opMode.gamepad2.right_trigger > 0.1) {
                robot.verticalSlideMotor1.setPower(opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);
                robot.verticalSlideMotor2.setPower(-opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);

                opMode.telemetry.addData("Bucket Encoder:", bucketMotorsAvgPostiion);
        } else {
            robot.verticalSlideMotor1.setPower(0);
            robot.verticalSlideMotor2.setPower(0);
        }
//
//
//
////            Horizontal Slides Motor                                                                     // (Penis)
//        if (opMode.gamepad2.b && !bIsPressed && ((System.currentTimeMillis()- startTime) > timeDelay)) {
//            robot.setMotorTo(robot.horizontalSlideMotor, -10, linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//            horizontalSlideTime = System.currentTimeMillis();
//        } else if (opMode.gamepad2.b && bIsPressed && ((System.currentTimeMillis()- horizontalSlideTime) > timeDelay)) {
//            robot.setMotorTo(robot.horizontalSlideMotor, -2340, linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//            startTime = System.currentTimeMillis();
//        } else {
//            robot.horizontalSlideMotor.setPower(0);
//        }
//        if (opMode.gamepad2.b && !bIsPressed) {
//            robot.setMotorTo(robot.horizontalSlideMotor, 0, 1 * linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//        } else if (/*opMode.gamepad2.left_stick_button*/opMode.gamepad2.b && bIsPressed) {
//            robot.horizontalSlideMotor.setPower(-1 * linearSlidesDownMultiplier);
//            bIsPressed = !bIsPressed;
//        }
//
//        // Horizontal slides in/out
//        int horizontalSlideMaxPos = -2200;
//        int horizontalSlideMinPos = 100;
//
//        if(opMode.gamepad2.dpad_up) { // Move horizontal slide out
//            if(robot.horizontalSlideMotor.getCurrentPosition() > horizontalSlideMaxPos) {
//                robot.horizontalSlideMotor.setPower(-.5);
//            } else{
//                robot.horizontalSlideMotor.setPower(0);
//            }
//        } else if(opMode.gamepad2.dpad_down) { // Move horizontal slide in
//             if(robot.horizontalSlideMotor.getCurrentPosition() < 0) {
//                 robot.horizontalSlideMotor.setPower(.5);
//             } else{
//                 robot.horizontalSlideMotor.setPower(0);
//             }
//        } else {
//             robot.horizontalSlideMotor.setPower(0);
//        }
//
//        // opMode.telemetry.addLine("Horizontal Motor Encoder: ");
//        opMode.telemetry.addData("Horz. Motor Encoder: ", robot.horizontalSlideMotor.getCurrentPosition());
//
//
//        // Specimen Claw
        if (opMode.gamepad2.x && !xIsPressed && ((System.currentTimeMillis() - startTime) > timeDelay)) {
            xIsPressed = !xIsPressed;

            robot.clawServo.setPosition(0.5);
            robot.clawServo2.setPosition(-0.5);
            specimenTime = System.currentTimeMillis();

            opMode.telemetry.addLine("Servo Closed");
        } else if (opMode.gamepad2.x && xIsPressed && ((System.currentTimeMillis() - specimenTime) > timeDelay)) {
            xIsPressed = !xIsPressed;

            robot.clawServo.setPosition(1.1);
            robot.clawServo2.setPosition(-1.1);
            startTime = System.currentTimeMillis();

            opMode.telemetry.addLine("Servo Open");
        }

        opMode.telemetry.update();
    }
}