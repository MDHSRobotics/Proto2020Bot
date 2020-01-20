
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.brains.HatcherBrain;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.utils.EncoderUtils;
import frc.robot.Constants.EncoderConstants;
import frc.robot.Constants.TalonConstants;

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
        m_disabled = (Devices.talonSrxHatcher == null);
        if (m_disabled) {
            Logger.error("Hatcher devices not initialized! Disabling subsystem...");
            return;
        }

        // Configure the subsystem devices
        Devices.talonSrxHatcher.configFactoryDefault();

        Devices.talonSrxHatcher.configPeakCurrentDuration(TalonConstants.PEAK_AMPERAGE_DURATION, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.configPeakCurrentLimit(TalonConstants.PEAK_AMPERAGE, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.configContinuousCurrentLimit(TalonConstants.CONTINUOUS_AMPERAGE_LIMIT, TalonConstants.TIMEOUT_MS);

        Devices.talonSrxHatcher.configNominalOutputForward(0);
        Devices.talonSrxHatcher.configNominalOutputReverse(0);
        Devices.talonSrxHatcher.configPeakOutputForward(0.5);
        Devices.talonSrxHatcher.configPeakOutputReverse(-0.5);

        Devices.talonSrxHatcher.configMotionAcceleration(3000, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.configMotionCruiseVelocity(8000, TalonConstants.TIMEOUT_MS);

        // Config TalonSRX Redline encoder
        Devices.talonSrxHatcher.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.setSensorPhase(SENSOR_PHASE);
        Devices.talonSrxHatcher.setInverted(MOTOR_INVERT);
        Devices.talonSrxHatcher.configAllowableClosedloopError(0, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);

        Devices.talonSrxHatcher.config_kF(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.config_kP(EncoderConstants.PID_LOOP_PRIMARY, 0.32, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.config_kI(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);
        Devices.talonSrxHatcher.config_kD(EncoderConstants.PID_LOOP_PRIMARY, 0.0, TalonConstants.TIMEOUT_MS);

        // Initialize current encoder position as zero
        Devices.talonSrxHatcher.setSelectedSensorPosition(0, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
        SensorCollection sensorCol = Devices.talonSrxHatcher.getSensorCollection();
        int absolutePosition = sensorCol.getPulseWidthPosition();
        absolutePosition &= 0xFFF;
        if (SENSOR_PHASE)
            absolutePosition *= -1;
        if (MOTOR_INVERT)
            absolutePosition *= -1;
        // Set the quadrature (relative) sensor to match absolute
        Devices.talonSrxHatcher.setSelectedSensorPosition(absolutePosition, EncoderConstants.PID_LOOP_PRIMARY, TalonConstants.TIMEOUT_MS);
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
        Devices.talonSrxHatcher.stopMotor();
    }

    // Open the Hatcher claw
    public void openClaw() {
        double angle = HatcherBrain.getHatchOpenAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to OPEN: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        Devices.talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Close the Hatcher claw
    public void closeClaw() {
        double angle = HatcherBrain.getHatchCloseAngle();
        double ticks = EncoderUtils.translateAngleToTicks(angle, GEAR_RATIO);
        Logger.info("Hatcher -> Motion Magic to CLOSE: " + angle + " angle, " + ticks + " ticks");

        if (m_disabled) return;
        Devices.talonSrxHatcher.set(ControlMode.MotionMagic, ticks);
    }

    // Get the current Hatcher claw motor velocity
    public int getVelocity() {
        if (m_disabled) return 0;
        return Devices.talonSrxHatcher.getSelectedSensorVelocity();
    }

    // Get the current Hatcher claw motor position
    public int getPosition() {
        if (m_disabled) return 0;
        return Devices.talonSrxHatcher.getSelectedSensorPosition();
    }

    //---------//
    // Testing //
    //---------//

    public void testMotor() {
        if (m_disabled) return;
        Devices.talonSrxHatcher.set(0.2);
    }

}
