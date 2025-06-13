package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class Team1Drive extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 1;
    public static double basePower = .9;
    float movementMulti = 1;
    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);


    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = basePower;

//Forward
        if (opMode.gamepad1.dpad_up){
            mp = new MotorPowers(movementMulti, movementMulti,movementMulti, movementMulti);
            opMode.telemetry.addLine("Dpad Up (Forward) active!");

        }
//Backward
else if (opMode.gamepad1.dpad_down) {
            mp = new MotorPowers(-movementMulti,-movementMulti,-movementMulti, -movementMulti);
            opMode.telemetry.addLine("Dpad Down (Backward) active!");
//            opMode.telemetry.addData("Dpad Up");
        }
//RightWay
        else if (opMode.gamepad1.dpad_right) {
            mp = new MotorPowers(movementMulti,-movementMulti,-movementMulti,
                    movementMulti);

//            opMode.telemetry.addData("Dpad Right (RightWay) active!");
        }
//LeftWay
        else if (opMode.gamepad1.dpad_left) {
            mp = new MotorPowers(-movementMulti, movementMulti, movementMulti, -movementMulti);
        }
//TurnRight

        else if (opMode.gamepad1.b) {
            mp = new MotorPowers(movementMulti, -movementMulti, movementMulti, -movementMulti);
        }

//TurnLeft

        else if (opMode.gamepad1.x) {
            mp = new MotorPowers(-movementMulti, movementMulti, -movementMulti, movementMulti);
        }
//ServoUp

        if (opMode.gamepad1.right_trigger > 0.2){
            robot.servo1.setPosition (0);
        }
//ServeoDown
        else if (opMode.gamepad1.left_trigger > 0.2){
            robot.servo1.setPosition (0.4);
        }
//Servo: robot.Servo1.setPosition(1 through -1);




        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}