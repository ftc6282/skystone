package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class MecanumAutonomous extends LinearOpMode {

    MecanumHardware robot = new MecanumHardware();

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: HD Hex Motor Planetary 20:1
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double SLOW_DRIVE_SPEED  = 0.3;

    public void drive(double speed, double inches, double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
            robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
            robot.backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            robot.frontRightDrive.setPower(Math.abs(speed));
            robot.backRightDrive.setPower(Math.abs(speed));
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backRightDrive.isBusy())) {

                writeTargetPositionTelemetry(newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
            }
            stopDriveMotors();
        }
    }

    public void turn(double speed, double rightInches, double leftInches, double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
            robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
            robot.backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            robot.frontRightDrive.setPower(Math.abs(speed));
            robot.backRightDrive.setPower(Math.abs(speed));
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backRightDrive.isBusy())) {

                writeTargetPositionTelemetry(newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
            }
            stopDriveMotors();
        }
    }

    public void strafeLeft(double speed, double inches, double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (-inches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (-inches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
            robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
            robot.backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));
            robot.frontRightDrive.setPower(Math.abs(speed));
            robot.backRightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backRightDrive.isBusy())) {

                // Display it for the driver.
                writeTargetPositionTelemetry(newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
            }
            stopDriveMotors();
        }
    }

    public void strafeRight(double speed, double inches, double timeoutS, boolean extraFrontRightPower)
    {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (-inches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (-inches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
            robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
            robot.backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));
            if (extraFrontRightPower) {
                robot.frontRightDrive.setPower(Math.abs(speed + 0.05));
            } else {
                robot.frontRightDrive.setPower(Math.abs(speed));
            }

            robot.backRightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backRightDrive.isBusy())) {

                // Display it for the driver.
                writeTargetPositionTelemetry(newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
            }
            // Stop all motion;
            stopDriveMotors();
        }
    }

    public void grabStone (double hook){
        robot.skystoneGrabber.setPosition(hook);
    }

    public void capstoneDelivery (double hook){
        robot.capstoneDelivery.setPosition(hook);
    }

    private void stopDriveMotors() {
        robot.frontLeftDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.frontRightDrive.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void writeTargetPositionTelemetry(int newFrontLeftTarget, int newBackLeftTarget, int newFrontRightTarget, int newBackRightTarget) {
        telemetry.addData("Path1", "Running to %7d : %7d", newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
        telemetry.addData(
                "Path2",
                "Running at %7d : %7d : %7d : %7d",
                robot.frontLeftDrive.getCurrentPosition(),
                robot.backLeftDrive.getCurrentPosition(),
                robot.frontRightDrive.getCurrentPosition(),
                robot.backRightDrive.getCurrentPosition()
        );

        telemetry.update();
    }

    public boolean isSkystone(){
        NormalizedRGBA colors = robot.colorSensor.getNormalizedColors();

        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red   /= max;
        colors.green /= max;
        colors.blue  /= max;
        colors.alpha /= max;

        if (colors.red > .5 && colors.blue < .3) {
            telemetry.addLine("Stone");
            return false;
        }else{
            telemetry.addLine("Skystone");
            return true;

        }
    }

    int counter = 0;
    int distance = 80;

    public void grabSkystoneBlue(){
        while(counter <= 10){
            if(isSkystone()){
                drive(DRIVE_SPEED, 1,1);
                robot.skystoneGrabber.setPosition(1.0);
                sleep(800);
                break;
            }else{
                drive(DRIVE_SPEED, 2, 1);
                counter ++;
                distance = distance - 5;
            }
        }
    }

    public void grabSkystoneRed(){
        while(counter <= 10){
            if(isSkystone()){
                //drive(DRIVE_SPEED, 1, 1);
                robot.skystoneGrabber.setPosition(1.0);
                sleep(900);
                break;
            }else{
                drive(DRIVE_SPEED, 2, 1);
                counter++;
            }
        }
    }

    public void grabStoneBlue(){
        //Since, atm, the auton times out x amount of seconds after grabbing first skystone
        // and w the placement of the skystoneGrabber arm and pincher claws, it is going to
        //be difficult to go for another skystone w the remaining time. I was thinking we should just go for
        // another stone for an additional four points. Same goes for grabStoneRed.
    }

    public void grabStoneRed(){
        //See grabSkystoneBlue above :)
    }

    public void transformIntakeOut(){

        //If you are facing the spool, make sure the string is on the left side going over the top of the axel
        robot.linearSlide.slideUp();
        sleep(950);
        robot.linearSlide.slideStop();
        sleep(100);
        robot.pincher.setPower(0.5);
        sleep(1000);
        robot.pincher.setPower(0);
        sleep(100);
        robot.linearSlide.slideDown();
        sleep(1200);
        robot.linearSlide.slideStop();
    }

    public void checkWheels(boolean forward, boolean backwards) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;


            if (opModeIsActive()) {

                newFrontLeftTarget = 0;
                newBackLeftTarget = 0;
                newFrontRightTarget = 0;
                newBackRightTarget = 0;

                if (forward == true && backwards == false) {

                    newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
                    robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
                    robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
                    robot.backRightDrive.setTargetPosition(newBackRightTarget);

                }
                else if(forward == false && backwards == true){

                    newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
                    robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
                    robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
                    robot.backRightDrive.setTargetPosition(newBackRightTarget);

                }
                else if(forward == true && backwards == true){

                    newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (50 * COUNTS_PER_INCH);
                    robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
                    robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
                    robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
                    robot.backRightDrive.setTargetPosition(newBackRightTarget);

                    robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    runtime.reset();
                    robot.frontLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.backLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.frontRightDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.backRightDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);

                    newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (-50 * COUNTS_PER_INCH);
                    robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
                    robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
                    robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
                    robot.backRightDrive.setTargetPosition(newBackRightTarget);

                    runtime.reset();
                    robot.frontLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.backLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.frontRightDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                    robot.backRightDrive.setPower(Math.abs(DRIVE_SPEED));
                    sleep(5000);
                }

                // Turn On RUN_TO_POSITION


                robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.

                runtime.reset();
                robot.frontLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                sleep(5000);
                robot.backLeftDrive.setPower(Math.abs(DRIVE_SPEED));
                sleep(5000);
                robot.frontRightDrive.setPower(Math.abs(DRIVE_SPEED));
                sleep(5000);
                robot.backRightDrive.setPower(Math.abs(DRIVE_SPEED));
                sleep(5000);

                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (runtime.seconds() < 5) &&
                        (robot.frontLeftDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backRightDrive.isBusy())) {

                    // Display it for the driver.
                    writeTargetPositionTelemetry(newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                }
                // Stop all motion;
                stopDriveMotors();
            }
    }
}
