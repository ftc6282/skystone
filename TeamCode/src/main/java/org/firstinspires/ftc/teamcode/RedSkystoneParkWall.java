package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="RedSkystoneParkWall", group="Pushbot")
public class RedSkystoneParkWall extends MecanumAutonomous {

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

        strafeRight(DRIVE_SPEED, 16, 2, true);
        sleep(300);
        isSkystone();
        sleep(300);
        transformIntakeOut();
        sleep(300);
        grabSkystoneRed();
        sleep(200);
        strafeLeft(DRIVE_SPEED, 10, 2);
        drive(DRIVE_SPEED, -24, 2.5);
        robot.skystoneGrabber.setPosition(0.0);
        sleep(300);


        //going for first / second stone
        strafeLeft(DRIVE_SPEED, 12, 2);
        drive(DRIVE_SPEED, 28, 2.5);
        sleep(300);
        strafeRight(DRIVE_SPEED, 16, 2, false);
        sleep(300);
        robot.skystoneGrabber.setPosition(1.0); //claw down
        sleep(300);
        strafeLeft(DRIVE_SPEED, 11, 1.5);
        sleep(300);
        drive(DRIVE_SPEED, -27, 2);
        sleep(300);
        robot.skystoneGrabber.setPosition(0.0);
        drive(DRIVE_SPEED, 5, 1);

    }
}
