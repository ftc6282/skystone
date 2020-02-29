package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumHardware {
    public DcMotor frontRightDrive = null;
    public DcMotor frontLeftDrive = null;
    public DcMotor backRightDrive = null;
    public DcMotor backLeftDrive = null;
    public FoundationMover foundationMover = null;
    public Servo pincherLeft = null;
    public Servo pincherRight = null;
    public DcMotor pincher = null;
    public LinearSlide linearSlide = null;
    public Servo skystoneGrabber = null;
    public DigitalChannel topSensor = null;
    public DigitalChannel bottomSensor = null;
    public Servo capstoneDelivery = null;
    public NormalizedColorSensor colorSensor;

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


        foundationMover = new FoundationMover(hardwareMap.get(Servo.class, "foundationMover"));


        pincherLeft = hardwareMap.get(Servo.class, "pincherLeft");
        pincherRight = hardwareMap.get(Servo.class, "pincherRight");


        pincher = hardwareMap.get(DcMotor.class, "pincher");

        linearSlide = new LinearSlide(
                hardwareMap.get(DcMotor.class, "linearSlideFront"),
                hardwareMap.get(DcMotor.class, "linearSlideBack")
        );
        //linearSlideFront = hardwareMap.get(DcMotor.class, "linearSlideFront");
        //linearSlideBack = hardwareMap.get(DcMotor.class, "linearSlideBack");

        foundationMover.releaseFoundation();
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");


        topSensor = hardwareMap.get(DigitalChannel.class, "topSensor");
        topSensor.setMode(DigitalChannel.Mode.INPUT);

        bottomSensor = hardwareMap.get(DigitalChannel.class, "bottomSensor");
        bottomSensor.setMode(DigitalChannel.Mode.INPUT);

        skystoneGrabber = hardwareMap.get(Servo.class, "skystoneGrabber");

        capstoneDelivery = hardwareMap.get(Servo.class, "capstoneDelivery");

    }


}
