
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import frc.robot.SubsystemDevices;

// Differential driver subsystem
public class DiffDriver extends SubsystemBase {

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // The Talon connection state, to prevent watchdog warnings during testing
    private boolean m_talonsAreConnected = false;

    public DiffDriver() {
        Logger.setup("Constructing Subsystem: DiffDriver...");

        // Configure wheel speed controllers
        boolean talonFrontLeftIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxDiffWheelFrontLeft);
        boolean talonRearLeftIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxDiffWheelRearLeft);
        boolean talonFrontRightIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxDiffWheelFrontRight);
        boolean talonRearRightIsConnected = SubsystemDevices.isConnected(SubsystemDevices.talonSrxDiffWheelRearRight);
        m_talonsAreConnected = (talonFrontLeftIsConnected &&
                                talonRearLeftIsConnected &&
                                talonFrontRightIsConnected &&
                                talonRearRightIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("DiffDriver talons not all connected! Disabling DiffDriver...");
        } else {
            SubsystemDevices.talonSrxDiffWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxDiffWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxDiffWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            SubsystemDevices.talonSrxDiffWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling DiffDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("DiffDriver control stick direction is now flipped.");
        } else {
            Logger.info("DiffDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }

    // Stop all the drive motors
    public void stop() {
        if (!m_talonsAreConnected) {
            SubsystemDevices.diffDrive.feed();
            return;
        }

        SubsystemDevices.diffDrive.stopMotor();
    }

    // Drive using the tank method
    public void driveTank(double leftSpeed, double rightSpeed) {
        if (!m_talonsAreConnected) {
            SubsystemDevices.diffDrive.feed();
            return;
        }

        SubsystemDevices.diffDrive.tankDrive(leftSpeed, rightSpeed);
    }
}
