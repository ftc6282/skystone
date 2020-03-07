package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="BlueSkystoneParkWall", group="Pushbot")
public class BlueSkystoneParkWall extends MecanumAutonomous {

    @Override
    public void runOpMode() {
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initialize(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();


        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.frontLeftDrive.getCurrentPosition(),
                robot.backLeftDrive.getCurrentPosition(),
                robot.frontRightDrive.getCurrentPosition(),
                robot.backRightDrive.getCurrentPosition(),
                telemetry.update());

        //Wait for game to start (driver presses PLAY)
        waitForStart();

        //to allow transformIntakeOut
        strafeRight(DRIVE_SPEED, 4, 1, false);
        sleep(300);
        transformIntakeOut();

        //start going  for skystones
        drive(DRIVE_SPEED, -15, 2);
        sleep(300);
        strafeRight(DRIVE_SPEED,13.25,2, true);
        sleep(300);
        drive(DRIVE_SPEED, -3, 1);
        sleep(200);
        drive(DRIVE_SPEED, 3.5, 2);
        sleep(300);
        isSkystone();
        sleep(200);
        grabSkystoneBlue();
        strafeLeft(DRIVE_SPEED, 10, 2);
        drive(DRIVE_SPEED, distanceBlue, 2.5);
        robot.skystoneGrabber.setPosition(0.0); //claw up
        sleep(300);

        //going for second stone
        strafeLeft(DRIVE_SPEED, 12, 2);
        drive(DRIVE_SPEED, -30, 2.75);
        sleep(500);
        strafeRight(DRIVE_SPEED, 18, 2.5, true);
        sleep(500);
        robot.skystoneGrabber.setPosition(1.0); //claw down
        sleep(300);
        strafeLeft(DRIVE_SPEED, 10, 2);
        drive(DRIVE_SPEED, 15, 2);
        robot.skystoneGrabber.setPosition(0.0);
        sleep(300);
//        drive(DRIVE_SPEED, -3, 1);
//        sleep(300);
//        isSkystone();
//        sleep(200);
//        grabSkystoneBlue();
//        strafeLeft(DRIVE_SPEED, 14, 2);
//        drive(DRIVE_SPEED, 27 , 2);
//        robot.skystoneGrabber.setPosition(0.0); //claw up
//        sleep(300);

    }
}
//hehe i was here - elka
//sam is an idiot - sam
