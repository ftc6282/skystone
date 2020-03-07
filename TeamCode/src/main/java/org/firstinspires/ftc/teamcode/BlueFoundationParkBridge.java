package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "BlueFoundationParkBridge", group = "Pushbot")
public class BlueFoundationParkBridge extends MecanumAutonomous{

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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        strafeLeft(DRIVE_SPEED, 17.02, 2.5);
        //TURN TO FOUNDATION ANGLE
        drive(DRIVE_SPEED,-5.5, 2);
        strafeRight(DRIVE_SPEED, 1.0, 1, false);
        sleep(700);
        transformIntakeOut();
        sleep(700);
        //Corrects orientation of foundation mover before lowering
        turn(DRIVE_SPEED, 1.125, -1.125, 1 );
        robot.foundationMover.grabFoundation();
        sleep(700);
        strafeRight(SLOW_DRIVE_SPEED, 46.52, 4.5, false);
        turn(DRIVE_SPEED, -4, 1.5, 1.5);
        robot.foundationMover.releaseFoundation();
        sleep(700);
        turn(DRIVE_SPEED, 2, -1.5, 1);

        //Drives forward to park touching wall
        //drive(DRIVE_SPEED, 22.0, 3);

        //Drives forward to park on bridge side
        drive(DRIVE_SPEED, 15, 2);
        strafeLeft(DRIVE_SPEED, 12, 2);
        drive(DRIVE_SPEED, 7, 1.5);


    }
}

