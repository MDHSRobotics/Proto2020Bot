
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Devices;
import frc.robot.commands.interactive.DiffDriveTank;
import frc.robot.consoles.Logger;

// Diff driver subsystem
public class DiffDriver extends Subsystem {

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
        boolean talonFrontLeftIsConnected = Devices.isConnected(Devices.talonSrxDiffWheelFrontLeft);
        boolean talonRearLeftIsConnected = Devices.isConnected(Devices.talonSrxDiffWheelRearLeft);
        boolean talonFrontRightIsConnected = Devices.isConnected(Devices.talonSrxDiffWheelFrontRight);
        boolean talonRearRightIsConnected = Devices.isConnected(Devices.talonSrxDiffWheelRearRight);
        m_talonsAreConnected = (talonFrontLeftIsConnected &&
                                talonRearLeftIsConnected &&
                                talonFrontRightIsConnected &&
                                talonRearRightIsConnected);

        if (!m_talonsAreConnected) {
            Logger.error("DiffDriver talons not all connected! Disabling DiffDriver...");
        }
        else {
            Devices.talonSrxDiffWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxDiffWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxDiffWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
            Devices.talonSrxDiffWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        }
    }

    // Initialize Default Command
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DiffDriveTank());
    }

    // Flip the control direction of the joystick in Y (or Y Left for Xbox thumbsticks)
    public Boolean flipControlStickDirection() {
        Logger.action("Toggling DiffDriver control stick direction...");

        controlStickDirectionFlipped = !controlStickDirectionFlipped;

        if (controlStickDirectionFlipped) {
            Logger.info("DiffDriver control stick direction is now flipped.");
        }
        else {
            Logger.info("DiffDriver control stick direction is now standard (not flipped).");
        }

        return controlStickDirectionFlipped;
    }


    // Stop all the drive motors
    public void stop() {
        if (!m_talonsAreConnected) {
            Devices.diffDrive.feed();
            return;
        }

        Devices.diffDrive.stopMotor();
    }

    // Drive using the tank method
    public void driveTank(double yLeft, double yRight) {
        if (!m_talonsAreConnected) {
            Devices.diffDrive.feed();
            return;
        }
        Devices.diffDrive.tankDrive(yLeft, yRight);
    }

}
