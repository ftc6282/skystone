package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumHardware {
    public DcMotor frontRightDrive = null;
    public DcMotor frontLeftDrive = null;
    public DcMotor backRightDrive = null;
    public DcMotor backLeftDrive = null;
    public Servo foundationMover = null;
    public DcMotor pincher = null;
    public DcMotor linearSlideFront = null;
    public DcMotor linearSlideBack = null;
    public DigitalChannel topSensor = null;
    public DigitalChannel bottomSensor = null;

    public void initialize(HardwareMap hardwareMap) {
        // initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone)
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);


        foundationMover = hardwareMap.get(Servo.class, "foundationMover");
        // servoTwo  = hardwareMap.get(Servo.class, "servoTwo");

        pincher = hardwareMap.get(DcMotor.class, "pincher");
        linearSlideFront = hardwareMap.get(DcMotor.class, "linearSlideFront");
        linearSlideBack = hardwareMap.get(DcMotor.class, "linearSlideBack");

        foundationMover.setPosition(0.9);

        topSensor = hardwareMap.get(DigitalChannel.class, "topSensor");
        topSensor.setMode(DigitalChannel.Mode.INPUT);

        bottomSensor = hardwareMap.get(DigitalChannel.class, "bottomSensor");
        bottomSensor.setMode(DigitalChannel.Mode.INPUT);

    }


}
