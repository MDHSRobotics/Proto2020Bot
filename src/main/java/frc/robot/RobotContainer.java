
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

// The container for the robot. This class is where the bulk of the robot should be declared.
// Contains subsystems, commands, and button mappings.
public class RobotContainer {

    // Subsystems
    private final Lighter m_lighter = new Lighter();
    private final MecDriver m_mecDriver = new MecDriver();

    // Commands
    private final LightCycle m_lightCycle = new LightCycle(m_lighter);
    private final LightToggle m_lightToggle = new LightToggle(m_lighter);
    private final MecDriveCartesian m_mecDriveCartesian = new MecDriveCartesian(m_mecDriver);

    // Constructor
    public RobotContainer() {
        setDefaultCommands();
        configureButtonBindings();
    }

    //------------------//
    // Default Commands //
    //------------------//

    // Set all the subsystem default commands
    private void setDefaultCommands() {
        Logger.setup("Lighter DefaultCommand -> LightToggle...");
        m_lighter.setDefaultCommand(m_lightToggle);

        Logger.setup("MecDriver DefaultCommand -> MecDriveCartesian...");
        m_mecDriver.setDefaultCommand(m_mecDriveCartesian);
    }

    // Return the command to run in autonomous mode
    public Command getAutonomousCommand() {
        return m_lightCycle;
    }

    //----------------------------//
    // Controller Button Bindings //
    //----------------------------//

    // Configure "drive" xbox buttons
    public void configureDriveXBoxButtons() {
        Logger.setup("Configure Buttons -> Drive Xbox Controller...");

        OIDevices.driveXboxBtnA.whenPressed(m_lightCycle);
        OIDevices.driveXboxBtnB.whenPressed(m_lightToggle);
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
