package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DigitalChannel;

public class LinearSlide {
    public DcMotor frontMotor;
    public DcMotor backMotor;

    public LinearSlide(DcMotor front, DcMotor back){
        frontMotor = front;
        backMotor = back;
    }

    public void slideUp(){
        frontMotor.setPower(-1);
        backMotor.setPower(-1);
    }

    public void slideDown(){
        frontMotor.setPower(1);
        backMotor.setPower(1);
    }

    public void slideStop(){
        frontMotor.setPower(0);
        backMotor.setPower(0);
    }

    public void slideSpeed(float speed){
        frontMotor.setPower(speed);
        backMotor.setPower(speed);
    }
}