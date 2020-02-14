package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class FoundationMover {
    public Servo servo;

    public FoundationMover(Servo s){
        servo = s;
    }

    public void grabFoundation(){
        servo.setPosition(0.0);
    }

    public void releaseFoundation(){
        servo.setPosition(0.75);
    }

}