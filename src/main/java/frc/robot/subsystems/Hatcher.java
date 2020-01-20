
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.brains.HatcherBrain;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.constants.EncoderConstants.*;
import static frc.robot.subsystems.constants.TalonConstants.*;
import frc.robot.subsystems.utils.EncoderUtils;
import static frc.robot.subsystems.Devices.talonSrxHatcher;

// Hatcher subsystem, for grabbing and releasing hatches
public class Hatcher extends SubsystemBase {

    // The public property to determine the Hatcher's claw state
    public boolean clawIsClosed = false;

    // Position constants
    private final double GEAR_RATIO = 16;

    // Encoder constants
    private final boolean SENSOR_PHASE = true; // So that Talon does not report sensor out of phase
    private final boolean MOTOR_INVERT = false; // Which direction you want to be positive; this does not affect motor invert

    // If not all the talons are initialized, this should be true
    private boolean m_disabled = false;

    public Hatcher() {
        Logger.setup("Constructing Subsystem: Hatcher...");

        // Determine whether or not to disable the subsystem
        m_disabled = (talonSrxHatcher == null);
        if (m_disabled) {
            Logger.error("Hatcher devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        talonSrxHatcher.configFactoryDefault();

        talonSrxHatcher.configPeakCurrentDuration(PEAK_AMPERAGE_DURATION, TIMEOUT_MS);
        talonSrxHatcher.configPeakCurrentLimit(PEAK_AMPERAGE, TIMEOUT_MS);
        talonSrxHatcher.configContinuousCurrentLimit(CONTINUOUS_AMPERAGE_LIMIT, TIMEOUT_MS);

        talonSrxHatcher.configNominalOutputForward(0);
        talonSrxHatcher.configNominalOutputReverse(0);
        talonSrxHatcher.configPeakOutputForward(0.5);
        talonSrxHatcher.configPeakOutputReverse(-0.5);

        talonSrxHatcher.configMotionAcceleration(3000, TIMEOUT_MS);
        talonSrxHatcher.configMotionCruiseVelocity(8000, TIMEOUT_MS);

        // Config TalonSRX Redline encoder
        talonSrxHatcher.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_LOOP_PRIMARY, TIMEOUT_MS);
        talonSrxHatcher.setSensorPhase(SENSOR_PHASE);
        talonSrxHatcher.setInverted(MOTOR_INVERT);
        talonSrxHatcher.configAllowableClosedloopError(0, PID_LOOP_PRIMARY, TIMEOUT_MS);

        talonSrxHatcher.config_kF(PID_LOOP_PRIMARY, 0.0, TIMEOUT_MS);
        talonSrxHatcher.config_kP(PID_LOOP_PRIMARY, 0.32, TIMEOUT_MS);
        talonSrxHatcher.config_kI(PID_LOOP_PRIMARY, 0.0, TIMEOUT_MS);
        talonSrxHatcher.config_kD(PID_LOOP_PRIMARY, 0.0, TIMEOUT_MS);

        // Initialize current encoder position as zero
        talonSrxHatcher.setSelectedSensorPosition(0, PID_LOOP_PRIMARY, TIMEOUT_MS);
        SensorCollection sensorCol = talonSrxHatcher.getSensorCollection();
        int absolutePosition = sensorCol.getPulseWidthPosition();
        absolutePosition &= 0xFFF;
        if (SENSOR_PHASE) absolutePosition *= -1;
        if (MOTOR_INVERT) absolutePosition *= -1;
        // Set the quadrature (relative) sensor to match absolute
        talonSrxHatcher.setSelectedSensorPosition(absolutePosition, PID_LOOP_PRIMARY, TIMEOUT_MS);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // Toggle the clawIsClosed state
    public void toggleClawPosition() {
        clawIsClosed = !clawIsClosed;
    }

    // Stop the Hatcher claw motor
    public void stop() {
        if (m_disabled) return;
        talonSrxHatcher.stopMotor();
    }

    // Open the Hatcher claw
    public void openClaw() {
        double angle = HatcherBrain.getHatchOpenAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to OPEN: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Close the Hatcher claw
    public void closeClaw() {
        double angle = HatcherBrain.getHatchCloseAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to CLOSE: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Get the current Hatcher claw motor velocity
    public int getVelocity() {
        if (m_disabled) return 0;
        int velocity = talonSrxHatcher.getSelectedSensorVelocity();
        return velocity;
    }

    // Get the current Hatcher claw motor position
    public int getPosition() {
        if (m_disabled) return 0;
        int position = talonSrxHatcher.getSelectedSensorPosition();
        return position;
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (m_disabled) return;
        talonSrxHatcher.set(0.2);
    }

}
