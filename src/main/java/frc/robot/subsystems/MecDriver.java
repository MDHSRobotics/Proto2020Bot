
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.brains.MecDriverBrain;
import frc.robot.consoles.Logger;
import frc.robot.sensors.Gyro;
import frc.robot.sensors.Vision;
import frc.robot.BotSensors;
import frc.robot.SubsystemDevices;

// Mecanum driver subsystem
public class MecDriver extends SubsystemBase {

    public enum DriveOrientation {
        ROBOT, FIELD
    }

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // If not all the talons are initialized, this should be true
    private boolean m_disabled = false;

    public MecDriver() {
        Logger.setup("Constructing Subsystem: MecDriver...");

        // Determine whether or not to disable the subsystem
        m_disabled = (SubsystemDevices.mecDrive == null);
        if (m_disabled) {
            Logger.error("MecDriver devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        // TODO: Investigate why these motor controllers have to be inverted. Are all TalonSRX Motor Controllers backwards?
        SubsystemDevices.talonSrxMecWheelFrontLeft.setInverted(true);
        SubsystemDevices.talonSrxMecWheelRearLeft.setInverted(true);
        SubsystemDevices.talonSrxMecWheelFrontRight.setInverted(true);
        SubsystemDevices.talonSrxMecWheelRearRight.setInverted(true);

        SubsystemDevices.talonSrxMecWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        SubsystemDevices.talonSrxMecWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        SubsystemDevices.talonSrxMecWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        SubsystemDevices.talonSrxMecWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling MecDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("MecDriver control stick direction is now flipped.");
        } else {
            Logger.info("MecDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }

    // Toggle the drive orientation for the mecanum drive
    public DriveOrientation toggleDriveOrientation() {
        Logger.action("Toggling MecDriver drive orientation...");

        DriveOrientation orientation = MecDriverBrain.getDriveOrientation();
        if (orientation == DriveOrientation.FIELD) {
            orientation = DriveOrientation.ROBOT;
            Logger.info("MecDriver drive orientation is now ROBOT.");
        } else if (orientation == DriveOrientation.ROBOT) {
            orientation = DriveOrientation.FIELD;
            Logger.info("MecDriver drive orientation is now FIELD.");
        }
        MecDriverBrain.setDriveOrientation(orientation);

        return orientation;
    }

    // Stop all the drive motors
    public void stop() {
        if (m_disabled) return;
        SubsystemDevices.mecDrive.stopMotor();
    }

    // Drive straight with just the front two wheels at the given speed
    public void frontWheelDrive(double straightSpeed) {
        if (m_disabled) return;

        SubsystemDevices.talonSrxMecWheelFrontLeft.set(straightSpeed);
        SubsystemDevices.talonSrxMecWheelFrontRight.set(straightSpeed);
        SubsystemDevices.talonSrxMecWheelRearLeft.set(0);
        SubsystemDevices.talonSrxMecWheelRearRight.set(0);
        SubsystemDevices.mecDrive.feed();
    }

    // Strafe at the given speed
    public void strafe(double strafeSpeed) {
        if (m_disabled) return;
        SubsystemDevices.mecDrive.driveCartesian(strafeSpeed, 0, 0);
    }

    // Drive straight at the given speed
    public void driveStraight(double straightSpeed) {
        if (m_disabled) return;
        SubsystemDevices.mecDrive.driveCartesian(0, straightSpeed, 0);
    }

    // Rotate at the given speed
    public void rotate(double rotationSpeed) {
        if (m_disabled) return;
        SubsystemDevices.mecDrive.driveCartesian(0, 0, rotationSpeed);
    }

    // Orbit at the given speed, with the robot always looking inward
    // Positive is clockwise, negative is counter-clockwise
    public void orbitInward(double angleSpeed, double rotationSpeed) {
        if (m_disabled) return;
        // TODO: Test that this does what the method description says it does
        SubsystemDevices.mecDrive.drivePolar(angleSpeed, -90, rotationSpeed);
    }

    // Orbit at the given speed, with the robot always looking outward
    public void orbitOutward(double angleSpeed, double rotationSpeed) {
        if (m_disabled) return;
        // TODO: Test that this does what the method description says it does
        SubsystemDevices.mecDrive.drivePolar(angleSpeed, 90, rotationSpeed);
    }

    // Drive as if this were a tank drive
    public void driveTank(double leftSpeed, double rightSpeed) {
        if (m_disabled) return;

        SubsystemDevices.talonSrxMecWheelFrontLeft.set(leftSpeed);
        SubsystemDevices.talonSrxMecWheelRearLeft.set(leftSpeed);
        SubsystemDevices.talonSrxMecWheelFrontRight.set(rightSpeed);
        SubsystemDevices.talonSrxMecWheelRearRight.set(rightSpeed);
        // Even if you are periodically setting every drive motor,
        // feeding the Mecanum Drive object is necessary to prevent
        // motor safety warnings which will periodically disable your motors
        SubsystemDevices.mecDrive.feed();
    }

    // Drive using the cartesian method, using the current control orientation
    public void driveCartesian(double strafeSpeed, double straightSpeed, double rotationSpeed) {
        DriveOrientation orientation = MecDriverBrain.getDriveOrientation();
        driveCartesian(strafeSpeed, straightSpeed, rotationSpeed, orientation);
    }

    // Drive using the cartesian method, using the given control orientation
    public void driveCartesian(double strafeSpeed, double straightSpeed, double rotationSpeed, DriveOrientation orientation) {
        if (m_disabled) return;

        if (orientation == DriveOrientation.ROBOT) {
            // Logger.info("Cartesian Movement: " + strafeSpeed + ", " + straightSpeed + ", " + rotationSpeed);
            SubsystemDevices.mecDrive.driveCartesian(strafeSpeed, straightSpeed, rotationSpeed);
        } else if (orientation == DriveOrientation.FIELD) {
            double gyroAngle = BotSensors.gyro.getYaw();
            // Logger.info("Cartesian Movement: " + strafeSpeed + ", " + straightSpeed + ", " + rotationSpeed + ", " + gyroAngle);
            SubsystemDevices.mecDrive.driveCartesian(strafeSpeed, straightSpeed, rotationSpeed, gyroAngle);
        }
    }

    // Drive using the polar method
    public void drivePolar(double angleSpeed, double angle, double rotationSpeed) {
        if (m_disabled) return;
        // Logger.info("Polar Movement: " + angleSpeed + ", " + angle + ", " + rotationSpeed);
        SubsystemDevices.mecDrive.drivePolar(angleSpeed, angle, rotationSpeed);
    }

    // Drive to align the Robot to a detected line at the given yaw
    public void driveAlign(double targetYaw) {
        Logger.setup("##");

        // Get the correction yaw needed to align the Robot with the target yaw
        double yaw = BotSensors.gyro.getYaw();
        double correction = targetYaw - yaw;
        if (correction > 180)
            correction = correction - 360;
        if (correction < -180)
            correction = correction + 360;
        Logger.info("MecDriver -> Gyro -> Target Yaw: " + targetYaw + "; Current Yaw: " + yaw + "; Correction: " + correction);

        // Get the drive polar magnitude and angle needed to align the Robot's center with the appropriate detected line
        double magnitude = 0;
        double angle = 0;
        if (-45 <= correction && correction <= 45) {
            // Our target in in front of us, so look for a line in front to use to start centering
            boolean detected = Vision.frontLineDetected();
            if (detected) {
                boolean centered = Vision.isFrontCentered();
                if (!centered) {
                    double imageX = Vision.getFrontCorrectedX();
                    angle = correction + 90;
                    magnitude = MecDriverBrain.getAlignFrontMagnitude();
                    if (imageX < 0)
                        magnitude = -magnitude;
                    Logger.info("MecDriver -> Front Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        } else if (-135 < correction && correction < -45) {
            // Our target is to the left, so look for a line to the left use to start centering
            boolean detected = Vision.leftLineDetected();
            if (detected) {
                boolean centered = Vision.isLeftCentered();
                if (!centered) {
                    double imageX = Vision.getLeftCorrectedX();
                    angle = correction + 90;
                    magnitude = MecDriverBrain.getAlignSideMagnitude();
                    if (imageX < 0)
                        magnitude = -magnitude;
                    Logger.info("MecDriver -> Left Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        } else if (45 < correction && correction < 135) {
            // Our target is to the right, so look for a line to the right use to start centering
            boolean detected = Vision.rightLineDetected();
            if (detected) {
                boolean centered = Vision.isRightCentered();
                if (!centered) {
                    double imageX = Vision.getRightCorrectedX();
                    angle = correction + 90;
                    magnitude = MecDriverBrain.getAlignSideMagnitude();
                    if (imageX > 0)
                        magnitude = -magnitude;
                    Logger.info("MecDriver -> Right Camera -> Pixels to correct: " + imageX + "; Magnitude: " + magnitude + "; Angle: " + angle);
                }
            }
        }

        // Get the rotation speed to align the Robot with the target gyro yaw
        double zRotation = (correction / 180) * MecDriverBrain.getAlignZSensitivity();
        boolean isCloseEnough = Math.abs(correction) < MecDriverBrain.getAlignZTolerance();
        if (!isCloseEnough) {
            if (0 < zRotation && zRotation < MecDriverBrain.getAlignZSpeedMinimum())
                zRotation = MecDriverBrain.getAlignZSpeedMinimum();
            if (0 > zRotation && zRotation > -MecDriverBrain.getAlignZSpeedMinimum())
                zRotation = -MecDriverBrain.getAlignZSpeedMinimum();
        }

        Logger.action("MecDriver -> Drive Polar: " + magnitude + ", " + angle + ", " + zRotation);
        if (m_disabled) return;

        // TODO: Need to test this, to balance the speeds to produce the fastest and most reliable simultaneous alignment
        SubsystemDevices.mecDrive.drivePolar(magnitude, angle, zRotation);

        Logger.ending("^^");
    }

    // TODO: Use this to indicate to the driver that the Robot is aligned with the target (lights? Shuffleboard?)
    public static boolean isAligned(double targetAngle) {
        boolean straight = Gyro.isYawAligned(targetAngle);
        if (!straight)
            return false;

        boolean detected = Vision.frontLineDetected();
        if (!detected) {
            Logger.info("MecDriver -> Robot is straight, but no front line detected to center on!");
            return true;
        }

        boolean centered = Vision.isFrontCentered();
        if (!centered)
            return false;

        Logger.info("MecDriver -> Robot is straight, and centered, fully aligned!");
        return true;
    }

}
