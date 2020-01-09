
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import frc.robot.Brain;
import frc.robot.Constants.EncoderConstants;
import frc.robot.Constants.TalonConstants;
import frc.robot.EncoderUtils;
import frc.robot.SubsystemDevices;

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
        m_disabled = (SubsystemDevices.talonSrxHatcher == null);
        if (m_disabled) {
            Logger.error("Hatcher devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        SubsystemDevices.talonSrxHatcher.configFactoryDefault();

        SubsystemDevices.talonSrxHatcher.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

        SubsystemDevices.talonSrxHatcher.configNominalOutputForward(0);
        SubsystemDevices.talonSrxHatcher.configNominalOutputReverse(0);
        SubsystemDevices.talonSrxHatcher.configPeakOutputForward(0.5);
        SubsystemDevices.talonSrxHatcher.configPeakOutputReverse(-0.5);

        SubsystemDevices.talonSrxHatcher.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

        // Config TalonSRX Redline encoder
        SubsystemDevices.talonSrxHatcher.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.setSensorPhase(SENSOR_PHASE);
        SubsystemDevices.talonSrxHatcher.setInverted(MOTOR_INVERT);
        SubsystemDevices.talonSrxHatcher.configAllowableClosedloopError(0, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

        SubsystemDevices.talonSrxHatcher.config_kF(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.config_kP(EncoderConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.config_kI(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
        SubsystemDevices.talonSrxHatcher.config_kD(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

        // Initialize current encoder position as zero
        SubsystemDevices.talonSrxHatcher.setSelectedSensorPosition(0, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        SensorCollection sensorCol = SubsystemDevices.talonSrxHatcher.getSensorCollection();
        int absolutePosition = sensorCol.getPulseWidthPosition();
        absolutePosition &= 0xFFF;
        if (SENSOR_PHASE)
            absolutePosition *= -1;
        if (MOTOR_INVERT)
            absolutePosition *= -1;
        // Set the quadrature (relative) sensor to match absolute
        SubsystemDevices.talonSrxHatcher.setSelectedSensorPosition(absolutePosition, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
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
        SubsystemDevices.talonSrxHatcher.stopMotor();
    }

    // Open the Hatcher claw
    public void openClaw() {
        double angle = Brain.getHatchOpenAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to OPEN: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        SubsystemDevices.talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Close the Hatcher claw
    public void closeClaw() {
        double angle = Brain.getHatchCloseAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to CLOSE: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        SubsystemDevices.talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Get the current Hatcher claw motor velocity
    public int getVelocity() {
        if (m_disabled) return 0;
        return SubsystemDevices.talonSrxHatcher.getSelectedSensorVelocity();
    }

    // Get the current Hatcher claw motor position
    public int getPosition() {
        if (m_disabled) return 0;
        return SubsystemDevices.talonSrxHatcher.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (m_disabled) return;
        SubsystemDevices.talonSrxHatcher.set(0.2);
    }

}
