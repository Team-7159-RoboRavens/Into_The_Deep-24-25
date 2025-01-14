package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class HolonomicDrive {
    //thanks game manual 0!
    //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
    public static MotorPowers fieldOrientedDrive(Gamepad gamepad, double maxMotorPower){

        //Provide a deadzone of +-0.1
        double x = gamepad.left_stick_x > 0.1 || gamepad.left_stick_x < -0.1 ? gamepad.left_stick_x : 0;
        double y = gamepad.left_stick_y > 0.1 || gamepad.left_stick_y < -0.1 ? -gamepad.left_stick_y : 0;
        double rotate = gamepad.right_stick_x > 0.1 || gamepad.right_stick_x < -0.1 ? gamepad.right_stick_x : 0;
        double rotX = x * Math.cos(0) - y * Math.sin(0);
        double rotY = x * Math.sin(0) + y * Math.cos(0);
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate), 1);
        double frontLeftPower = maxMotorPower*((rotY + rotX + rotate) / denominator);
        double backLeftPower = maxMotorPower*((rotY - rotX + rotate) / denominator);
        double frontRightPower = maxMotorPower*((rotY - rotX - rotate) / denominator);
        double backRightPower = maxMotorPower*((rotY + rotX - rotate) / denominator);
//        frontLeftPower *= .5; backLeftPower *= .5; frontRightPower *= .5; backRightPower *= .5;
        return new MotorPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
}
