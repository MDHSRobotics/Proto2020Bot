
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import frc.robot.SubsystemDevices;

// Omni-directional driver subsystem
public class OmniDriver extends SubsystemBase {

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public OmniDriver() {
        Logger.setup("Constructing Subsystem: OmniDriver...");

        // Configure wheel speed controllers
        boolean talonFrontLeftIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelFrontLeft);
        boolean talonRearLeftIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelRearLeft);
        boolean talonFrontRightIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelFrontRight);
        boolean talonRearRightIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelRearRight);
        boolean talonFrontIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelFront);
        boolean talonRearIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxOmniWheelRear);
        m_talonsAreConnected = (talonFrontLeftIsConnected && talonRearLeftIsConnected && talonFrontRightIsConnected
                && talonRearRightIsConnected && talonFrontIsConnected && talonRearIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("OmniDriver talons not all connected! Disabling OmniDriver...");
        } else {
            SubsystemDevices.talonSrxOmniWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxOmniWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxOmniWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxOmniWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxOmniWheelFront.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxOmniWheelRear.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        }
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
        if (!m_talonsAreConnected) {
            SubsystemDevices.omniDrive.feed();
            return;
        }

        SubsystemDevices.omniDrive.stopMotor();
    }

    // Drive straight at the given speed
    public void driveStraight(double speed) {
        if (!m_talonsAreConnected) {
            SubsystemDevices.omniDrive.feed();
            return;
        }

        SubsystemDevices.omniDrive.arcadeDrive(speed, 0, false);
    }

    // Rotate at the given speed
    public void rotate(double rotation) {
        if (!m_talonsAreConnected) {
            SubsystemDevices.omniDrive.feed();
            return;
        }

        SubsystemDevices.omniDrive.arcadeDrive(0, rotation, false);
    }

    // Drive using the arcade method
    public void driveArcade(double speed, double rotation, boolean squareInputs, double strafe) {
        if (!m_talonsAreConnected) {
            SubsystemDevices.omniDrive.feed();
            return;
        }

        SubsystemDevices.omniDrive.arcadeDrive(speed, rotation, squareInputs);
        SubsystemDevices.talonSrxOmniWheelFront.set(strafe);
    }

}
