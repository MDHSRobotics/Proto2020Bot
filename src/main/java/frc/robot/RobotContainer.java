
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

// The container for the robot. This class is where the bulk of the robot should be declared.
// Contains subsystems, commands, and button mappings.
public class RobotContainer {

    // Subsystems
    private final MecDriver m_mecDriver = new MecDriver();

    // Commands
    private final MecDriveCartesian m_mecDriveCartesian = new MecDriveCartesian(m_mecDriver);

    // Constructor
    public RobotContainer() {
        setDefaultCommands();
        configureButtonBindings();
    }

    // Set all the subsystem default commands
    private void setDefaultCommands() {
        Logger.setup("Initializing MecDriver DefaultCommand -> MecDriveCartesian...");
        m_mecDriver.setDefaultCommand(m_mecDriveCartesian);
    }

    // Return the command to run in autonomous mode
    public Command getAutonomousCommand() {
        return m_mecDriveCartesian;
    }

    // Configure "drive" xbox buttons
    public void configureDriveXBoxButtons() {
        Logger.setup("Configure Buttons -> Drive Xbox Controller...");
        OIDevices.driveXboxBtnA.whenPressed(m_mecDriveCartesian);
    }

    // Check controllers and configure button bindings
    private void configureButtonBindings() {
        if (!OIDevices.isDriveXboxConnected()) {
            Logger.error("Drive XBox controller not plugged in!");
        } else {
            configureDriveXBoxButtons();
        }
    }

}
