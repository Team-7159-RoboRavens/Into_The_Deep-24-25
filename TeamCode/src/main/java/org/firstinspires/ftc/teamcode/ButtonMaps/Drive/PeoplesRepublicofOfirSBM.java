package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class PeoplesRepublicofOfirSBM extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);
    private double triggerMultipler = 0.7;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);



        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
