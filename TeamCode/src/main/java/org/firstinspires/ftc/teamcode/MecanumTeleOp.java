/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="MecanumTeleOp", group="Linear Opmode")
public class MecanumTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public MecanumHardware robot = new MecanumHardware();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.initialize(hardwareMap);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        //frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            double left_stick_x = gamepad1.left_stick_x;
            double left_stick_y = gamepad1.left_stick_y;
//
//            // Manipulate joystick values to make Samantha a better driver
//            if(Math.abs(left_stick_x) < 0.1 && Math.abs(left_stick_y) > 0.1){
//                left_stick_x = 0.0;
//            }
//
//            if(Math.abs(left_stick_y) < 0.1 && Math.abs(left_stick_x) > 0.1){
//                left_stick_y  = 0.0;
//            }
//
//            telemetry.addData("dead zone", "%f %f %f %f", gamepad1.left_stick_x, gamepad1.left_stick_y, left_stick_x, left_stick_y);

            double r = Math.hypot(-left_stick_x/.707, -left_stick_y/ .707);
            double robotAngle = Math.atan2(-left_stick_y/ .707, -left_stick_x/ .707) - Math.PI / 4;
            double rightX = Math.pow(gamepad1.right_stick_x, 2);
            if(gamepad1.right_stick_x > 0){
                rightX *= -1;
            }

            final double v1 = r * Math.sin(robotAngle) - rightX;
            final double v2 = r * Math.cos(robotAngle) + rightX;
            final double v3 = r * Math.cos(robotAngle) - rightX;
            final double v4 = r * Math.sin(robotAngle) + rightX;

            telemetry.addData("r", "r at %f %f", r, robotAngle);
            telemetry.addData("Path0", "Power at %f %f %f %f", v1,v2,v3,v4);

            if(gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 &&  gamepad1.right_stick_x == 0) {
                robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                robot.frontLeftDrive.setTargetPosition(0);
                robot.frontRightDrive.setTargetPosition(0);
                robot.backLeftDrive.setTargetPosition(0);
                robot.backRightDrive.setTargetPosition(0);

                robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else{
                robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            if (gamepad1.right_bumper) {
                robot.frontLeftDrive.setPower(v1 * 0.5);
                robot.frontRightDrive.setPower(v2 * 0.5);
                robot.backLeftDrive.setPower(v3 * 0.5);
                robot.backRightDrive.setPower(v4 * 0.5);
            }
            else {
                robot.frontLeftDrive.setPower(v1);
                robot.frontRightDrive.setPower(v2);
                robot.backLeftDrive.setPower(v3);
                robot.backRightDrive.setPower(v4);
            }

            if (gamepad1.right_trigger >= 0.1){
                robot.foundationMover.grabFoundation();
            }
            if(gamepad1.left_trigger >= 0.1){
                robot.foundationMover.releaseFoundation();
            }


            if (gamepad2.right_trigger > 0){
                robot.pincher.setPower(Math.pow(gamepad2.right_trigger, 3));
            }else if(gamepad2.left_trigger > 0) {
                robot.pincher.setPower(-gamepad2.left_trigger/2);
            }else{
                robot.pincher.setPower(0);
            }

            // Don't allow the linear slide to go up if our button is pressed
            if (!robot.topSensor.getState() && gamepad2.left_stick_y < 0 && !gamepad2.a) {
                // Don't let the operator move up as our button is pressed
                robot.linearSlide.slideStop();
            } else if (!robot.bottomSensor.getState() && gamepad2.left_stick_y > 0 && !gamepad2.a) {
                // Button is pressed
                robot.linearSlide.slideStop();
            } else {
                robot.linearSlide.slideSpeed(gamepad2.left_stick_y);
            }

            if(gamepad2.a){
                robot.pincherLeft.setPosition(1);
                robot.pincherRight.setPosition(0);
            }else if(gamepad2.b) {
                robot.pincherLeft.setPosition(0);
                robot.pincherRight.setPosition(1);
            }else{
                robot.pincherLeft.setPosition(0.5);
                robot.pincherRight.setPosition(0.5);
            }

            if(gamepad2.right_bumper){
                robot.skystoneGrabber.setPosition(1.0);
            }

            if(gamepad2.left_bumper){
                robot.skystoneGrabber.setPosition(0.0);
            }

            //values are backward, 0.0  is all the  way down here instead of up
            if(gamepad2.y){
                robot.capstoneDelivery.setPosition(0.0); //hold to go down
            }else{
                //this  is start pos, im just too lazy to reprogram the servo
                robot.capstoneDelivery.setPosition(0.75); //start pos
            }



           /*
            if (top is pushed and the user is going up) {
                zero the motors
            } else if (bottom if pushed and user is going down) {
                zero the motors
            } else {
                do normal things
            }

            */
            telemetry.update();
        }
    }
}
