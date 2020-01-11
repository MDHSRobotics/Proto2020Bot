
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.MecDriver;

// This command drives the MecDrive straight until is reaches its target distance
public class DriveMecanumStraightUntilDistance extends CommandBase {

    // Subsystems
    private MecDriver m_mecDriver;

    // Constants
    private final double VELOCITY_AT_FULL_SPEED = 11.5; // Velocity (feet/second) at full speed - THIS IS A GUESS - CHECK IT!!
    private final static double DEFAULT_TARGET_DISTANCE_IN_FEET = 1; // Default desired distance to travel (in feet) - NOTE: Negative means move backwards
    private final static double DEFAULT_SPEED = .1; // Default speed setting for drive: 0.0 to +1.0

    // Private Member Variables
    private double m_targetDistanceInFeet; // Desired distance to travel (in feet) - NOTE: Negative means move backwards
    private double m_speed; // Speed setting for drive: 0.0 to +1.0
    private double m_distanceTraveled; // Distanced traveled thus far (in feet)
    private double m_velocity; // Velocity (feet/second) at current speed setting
    private double m_elapsedTime; // Time (in seconds) that this command has executed
    private boolean m_movingForward; // True if moving forward; False if moving backward
    private Timer m_timer; // Timer for this command
    private int m_counter; // Counter for the timer

    // Constructors
    public DriveMecanumStraightUntilDistance(MecDriver mecDriver) {
        this(mecDriver, DEFAULT_TARGET_DISTANCE_IN_FEET, DEFAULT_SPEED);
    }

    public DriveMecanumStraightUntilDistance(MecDriver mecDriver, double targetDistanceInFeet, double speed) {
        Logger.setup("Constructing Command: DriveMecanumStraightUntilDistance...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);

        m_targetDistanceInFeet = targetDistanceInFeet;
        // Use sign of distance to determine whether moving forward or backward
        m_movingForward = (targetDistanceInFeet > 0);
        m_speed = speed;
        // Scale velocity at full speed by the current speed (which is between 0 and 1.0)
        m_velocity = m_speed * VELOCITY_AT_FULL_SPEED;
        m_distanceTraveled = 0;
        m_elapsedTime = 0;
        m_timer = new Timer();
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MecDriveStraightDistance...");

        m_counter = 0;
        m_distanceTraveled = 0;
        m_elapsedTime = 0;
        m_timer.reset();
        m_timer.start();
        Logger.info("Target = " + m_targetDistanceInFeet + " feet" + "; Speed = " + m_speed);
    }

    @Override
    public void execute() {
        // Keep robot moving in the requested direction
        double speed = m_speed;
        if (!m_movingForward) {
            speed = -speed;
        }
        m_mecDriver.driveStraight(speed);

        // Return number of seconds since the timer was started
        m_elapsedTime = m_timer.get();
        // Distance traveled (feet) = elapsed time (seconds) * velocity (feet per second)
        m_distanceTraveled = m_elapsedTime * m_velocity;

        if (++m_counter >= 50) {
            Logger.info("Distance traveled = " + m_distanceTraveled + " feet; Elapsed time = " + m_elapsedTime + " seconds");
            m_counter = 0;
        }
    }

    // The command is finished when the target distance is estimated to have been reached
    // TODO: This should be determined by an encoder
    @Override
    public boolean isFinished() {
        return (m_distanceTraveled >= m_targetDistanceInFeet);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: DriveMecanumStraightUntilDistance...");
        } else {
            Logger.ending("Ending Command: DriveMecanumStraightUntilDistance...");
        }

        m_mecDriver.stop();
        m_timer.stop();
        Logger.info("Target = " + m_targetDistanceInFeet + " feet; Actual = " + m_distanceTraveled + " feet; Elapsed time = " + m_elapsedTime + " seconds");
    }

}
