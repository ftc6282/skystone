package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Grab Red Foundation and Park Near Wall", group="Pushbot")
public class RedPullFoundationParkWall extends MecanumAutonomous {

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

        strafeLeft(DRIVE_SPEED, 16.5, 3);
        //TURN TO FOUNDATION ANGLE
        drive(DRIVE_SPEED,4.0, 3.0);
        strafeRight(DRIVE_SPEED, 1.0, 3, true);
        sleep(800);
        transformIntakeOut();
        sleep(500);
        drive(DRIVE_SPEED, 0.5, 3);
        sleep(800);
        //Corrects orientation of foundation mover before lowering
        //turn(DRIVE_SPEED, 1.125, -1.125, 2 );
        robot.foundationMover.grabFoundation();
        sleep(2000);
        strafeRight(SLOW_DRIVE_SPEED, 48.0, 4.5, true);
        turn(DRIVE_SPEED, -1.5, 1.5, 2);
        robot.foundationMover.releaseFoundation();
        sleep(1000);
        turn(DRIVE_SPEED, 1.5, -1.5, 2);
        sleep(200);
        //Drives forward to park under bridge and  park touching wall
        drive(DRIVE_SPEED, -30.0, 3);
        strafeRight(DRIVE_SPEED,3,3, true);
        turn(DRIVE_SPEED,-2, - 2, 3);

    }
}


