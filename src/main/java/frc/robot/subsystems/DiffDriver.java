
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.diffDrive;
import static frc.robot.subsystems.Devices.talonSrxDiffWheelFrontLeft;
import static frc.robot.subsystems.Devices.talonSrxDiffWheelFrontRight;
import static frc.robot.subsystems.Devices.talonSrxDiffWheelRearLeft;
import static frc.robot.subsystems.Devices.talonSrxDiffWheelRearRight;

// Differential driver subsystem
public class DiffDriver extends SubsystemBase {

    // The direction of forward/backward via the controller
    public boolean controlStickDirectionFlipped = false;

    // Motor constants
    private final double SECONDS_FROM_NEUTRAL_TO_FULL = 0;
    private final int TIMEOUT_MS = 10;

    // If not all the talons are initialized, this should be true
    private boolean m_disabled = false;

    public DiffDriver() {
        Logger.setup("Constructing Subsystem: DiffDriver...");

        // Determine whether or not to disable the subsystem
        m_disabled = (diffDrive == null);
        if (m_disabled) {
            Logger.error("DiffDriver devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        // TODO: Investigate why these motor controllers have to be inverted. Are all TalonSRX Motor Controllers backwards?
        talonSrxDiffWheelFrontLeft.setInverted(true);
        talonSrxDiffWheelFrontRight.setInverted(true);
        talonSrxDiffWheelRearLeft.setInverted(true);
        talonSrxDiffWheelRearRight.setInverted(true);
        talonSrxDiffWheelRearLeft.follow(talonSrxDiffWheelFrontLeft);
        talonSrxDiffWheelRearRight.follow(talonSrxDiffWheelFrontRight);

        talonSrxDiffWheelFrontLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxDiffWheelRearLeft.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxDiffWheelFrontRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
        talonSrxDiffWheelRearRight.configOpenloopRamp(SECONDS_FROM_NEUTRAL_TO_FULL, TIMEOUT_MS);
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
        if (m_disabled) return;
        diffDrive.stopMotor();
    }

    // Drive using the tank method
    public void driveTank(double leftSpeed, double rightSpeed) {
        if (m_disabled) return;
        diffDrive.tankDrive(leftSpeed, rightSpeed);
    }

}
