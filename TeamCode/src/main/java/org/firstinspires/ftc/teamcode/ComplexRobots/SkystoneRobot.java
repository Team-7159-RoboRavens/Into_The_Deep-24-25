package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
public class SkystoneRobot extends MecanumDrive {
    enum Direction {
        UP,DOWNw
    }

    public final DcMotorEx horizontalSlideMotor;
    public final DcMotorEx verticalSlideMotor1;
    public final DcMotorEx verticalSlideMotor2;
    public final Servo clawServo;
    public final Servo clawRotationServo;

    public SkystoneRobot(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);
        //Linear Slide Motor
        horizontalSlideMotor = hardwareMap.get(DcMotorEx.class, "horizontalSlideMotor");

        //Setup
        horizontalSlideMotor.setDirection(DcMotor.Direction.REVERSE);
        horizontalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalSlideMotor.setTargetPositionTolerance(15);
        horizontalSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Bucket Motor
        verticalSlideMotor1 = hardwareMap.get(DcMotorEx.class, "verticalSlide1");
        verticalSlideMotor2 = hardwareMap.get(DcMotorEx.class, "verticalSlide2");

        //Setup
        verticalSlideMotor1.setDirection(DcMotor.Direction.FORWARD);
        verticalSlideMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalSlideMotor1.setTargetPositionTolerance(15);
        verticalSlideMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlideMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        verticalSlideMotor2.setDirection(DcMotor.Direction.FORWARD);
        verticalSlideMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalSlideMotor2.setTargetPositionTolerance(15);
        verticalSlideMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlideMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Servos
        clawServo = hardwareMap.get(Servo.class, "brushServo");
        clawRotationServo = hardwareMap.get(Servo.class, "elbowServo");

        //Initialize Output Servo
        clawServo.scaleRange(0,0.35);
        clawRotationServo.setPosition(0);

    }


    //TODO: Linear slide helper methods for auto (later)
//    public Action setSlideHeightAction(int targetPosition){
//        return new SlideHeight(targetPosition);
//    }

    public MotorPowers setAllMotorPowers(int i) {
        return new MotorPowers(0,0,0,0);
    }
    public void driveSlidesTo(int targetPosition, double motorPower, int direction){
        int avgSlidesPos = (verticalSlideMotor1.getCurrentPosition() + verticalSlideMotor2.getCurrentPosition())/2;
        if(direction == -1) {
            while (((verticalSlideMotor1.getCurrentPosition() + verticalSlideMotor2.getCurrentPosition()) / 2) >= targetPosition){
                verticalSlideMotor1.setPower(-motorPower);
                verticalSlideMotor2.setPower(motorPower);
            }
        } else if(direction == 1) {
            while (((verticalSlideMotor1.getCurrentPosition() + verticalSlideMotor2.getCurrentPosition()) / 2) <= targetPosition){
                verticalSlideMotor1.setPower(motorPower);
                verticalSlideMotor2.setPower(-motorPower);
            }
        }
        verticalSlideMotor1.setPower(0);
        verticalSlideMotor2.setPower(0);
    }

    public MotorPowers pivotTurn(double currentMotorPower, boolean rightBumper, boolean leftBumper) {
        double rightTopMotorPower = currentMotorPower;
        double rightBotMotorPower = currentMotorPower;
        double leftTopMotorPower = currentMotorPower;
        double leftBotMotorPower = currentMotorPower;
        if (leftBumper) {

            rightBotMotorPower *= -1;
            rightTopMotorPower *= -1;
        }
        else if (rightBumper) {
            leftBotMotorPower *= -1;
            leftTopMotorPower *= -1;
        }
        return new MotorPowers(leftTopMotorPower,rightTopMotorPower,leftBotMotorPower,rightBotMotorPower);
    }

    public void setMotorTo(DcMotorEx motor, int targetPos, double power) {
        if (motor.getCurrentPosition() < targetPos) {
            while (motor.getCurrentPosition() <= targetPos) {
                motor.setPower(power);
            }
        }
        else if (motor.getCurrentPosition() > targetPos) {
            while (motor.getCurrentPosition() >= targetPos) {
                motor.setPower(-power);
            }
        }
    }
    //Possible method, no use

//    class SlideHeight implements Action {
//        private boolean initialized;
//        private int targetPosition;
//        private boolean raise;
//        SlideHeight(int targetPosition){
//            initialized = false;
//            this.targetPosition = targetPosition;
//            if(linearSlidesMotor1.getCurrentPosition() < targetPosition) raise = true;
//            else raise = false;
//        }
//
//
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//            if(!initialized){
//                if(raise){
//                    linearSlidesMotor1.setPower(0.5);
//                    linearSlidesMotor1.setPower(0.5);
//                }else{
//                    linearSlidesMotor1.setPower(-0.3);
//                    linearSlidesMotor1.setPower(-0.3);
//                }
//                initialized = true;
//                return true;
//            }else{
//                int avgCurrentPosition = (linearSlidesMotor1.getCurrentPosition()+linearSlidesMotor2.getCurrentPosition())/2;
//                int difference;
//                if(raise){
//                    difference = targetPosition - avgCurrentPosition;
//                    if(difference <= 7 && difference >= -7){
//                        //STOP
//                        linearSlidesMotor1.setPower(0);
//                        linearSlidesMotor2.setPower(0);
//                        //Exit out, we're done
//                        return false;
//                    }else if (difference < -7) {
//                        //Reverse
//                        linearSlidesMotor1.setPower(-0.15);
//                        linearSlidesMotor2.setPower(-0.15);
//                    }else if(difference < 100 && difference > 7){
//                        //Soft stop
//                        linearSlidesMotor1.setPower(0.25);
//                        linearSlidesMotor2.setPower(0.25);
//                    }
//                }else {
//                    difference = avgCurrentPosition - targetPosition;
//                    if (difference <= 7 && difference >= -7) {
//                        //STOP
//                        linearSlidesMotor1.setPower(0);
//                        linearSlidesMotor2.setPower(0);
//                        //Exit out, we're done
//                        return false;
//                    } else if (difference < -7) {
//                        //Reverse
//                        linearSlidesMotor1.setPower(0.25);
//                        linearSlidesMotor2.setPower(0.25);
//                    } else if (difference < 100 && difference > 7) {
//                        //Soft stop
//                        linearSlidesMotor1.setPower(-0.15);
//                        linearSlidesMotor2.setPower(-0.15);
//                    }
//                }
//                return true;
//            }
//        }
    }
