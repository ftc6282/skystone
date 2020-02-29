package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="BlueFoundationParkWall", group="Pushbot")
public class BluePullFoundationParkWall extends MecanumAutonomous {

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

        strafeLeft(DRIVE_SPEED, 17.02, 3);
        //TURN TO FOUNDATION ANGLE
        drive(DRIVE_SPEED,-5.5, 3);
        strafeRight(DRIVE_SPEED, 1.0, 3, false);
        sleep(800);
        transformIntakeOut();
        sleep(800);
        //Corrects orientation of foundation mover before lowering
        turn(DRIVE_SPEED, 1.125, -1.125, 2 );
        robot.foundationMover.grabFoundation();
        sleep(8000);
        strafeRight(SLOW_DRIVE_SPEED, 46.52, 4.5, false);
        turn(DRIVE_SPEED, -4, 1.5, 2);
        robot.foundationMover.releaseFoundation();
        sleep(800);
        turn(DRIVE_SPEED, 2, -1.5, 2);
        //Drives forward to park under bridge and  park touching wall
        drive(DRIVE_SPEED, 22.0, 3);


    }
}

