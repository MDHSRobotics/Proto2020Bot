
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.omniDrive;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelFront;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelFrontLeft;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelFrontRight;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelRear;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelRearLeft;
import static frc.robot.subsystems.Devices.talonSrxOmniWheelRearRight;

// Omni-directional driver subsystem
public class OmniDriver extends SubsystemBase {

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // If not all the talons are initialized, this should be true
    private boolean m_disabled = false;

    public OmniDriver() {
        Logger.setup("Constructing Subsystem: OmniDriver...");

        // Determine whether or not to disable the subsystem
        m_disabled = (omniDrive == null);
        if (m_disabled) {
            Logger.error("DiffDriver devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        talonSrxOmniWheelRear.follow(talonSrxOmniWheelFront);
        talonSrxOmniWheelRearLeft.follow(talonSrxOmniWheelFrontLeft);
        talonSrxOmniWheelRearRight.follow(talonSrxOmniWheelFrontRight);

        talonSrxOmniWheelFront.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxOmniWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxOmniWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxOmniWheelRear.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxOmniWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxOmniWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling OmniDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;
        if (controlStickDirectionFlipped) {
            Logger.info("OmniDriver control stick direction is now flipped.");
        } else {
            Logger.info("OmniDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }

    // Stop all the drive motors
    public void stop() {
        if (m_disabled) return;

        omniDrive.stopMotor();
        talonSrxOmniWheelFront.stopMotor();
    }

    // Drive straight at the given speed
    public void driveStraight(double speed) {
        if (m_disabled) return;

        omniDrive.arcadeDrive(speed, 0, false);
        talonSrxOmniWheelFront.feed();
    }

    // Rotate at the given speed
    public void rotate(double rotation) {
        if (m_disabled) return;

        omniDrive.arcadeDrive(0, rotation, false);
        talonSrxOmniWheelFront.feed();
    }

    // Drive using the arcade method
    public void driveArcade(double straightSpeed, double rotationSpeed, double strafeSpeed) {
        if (m_disabled) return;

        omniDrive.arcadeDrive(straightSpeed, rotationSpeed, false);
        talonSrxOmniWheelFront.set(strafeSpeed);
    }

}
