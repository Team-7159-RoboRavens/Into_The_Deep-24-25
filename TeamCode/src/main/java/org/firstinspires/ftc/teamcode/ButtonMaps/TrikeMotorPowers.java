package org.firstinspires.ftc.teamcode.ButtonMaps;

public class TrikeMotorPowers {
    public double leftBack, rightBack;

    public TrikeMotorPowers(double leftBack, double rightBack){
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }
    public TrikeMotorPowers(double all){
        this.leftBack = all;
        this.rightBack = all;
    }

    public boolean isNotZero() {
        return this.leftBack != 0 || this.rightBack != 0;
    }

    public void setMotorPowers(double allPower){
        this.leftBack = allPower;
        this.rightBack = allPower;
    }

    public void combineWith(TrikeMotorPowers other){
        double leftBackMax = Math.max(Math.abs(this.leftBack + other.rightBack), 1);
        double rightBackMax = Math.max(Math.abs(this.rightBack + other.rightBack), 1);
        double denominator = Math.max(leftBackMax, rightBackMax);
        this.leftBack = (other.leftBack + this.leftBack) / denominator;
        this.rightBack = (other.rightBack + this.rightBack) / denominator;
    }
}
