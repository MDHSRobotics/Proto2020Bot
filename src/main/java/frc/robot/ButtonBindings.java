
package frc.robot;

import frc.robot.consoles.Logger;

// Configures all the button->command bindings for the robot.
public class ButtonBindings {

    // Check controllers and configure button bindings
    public static void configure() {
        Logger.setup("Configuring ButtonBindings...");

        if (!OIDevices.isDriveXboxConnected()) {
            Logger.error("Drive XBox controller not plugged in!");
        } else {
            configureDriveXBoxButtons();
        }
    }

    // Configure "drive" xbox buttons
    private static void configureDriveXBoxButtons() {
        Logger.setup("Configure Buttons -> Drive Xbox Controller...");

        OIDevices.driveXboxBtnA.whenPressed(RobotCommands.cmdLightCycle);
        OIDevices.driveXboxBtnB.whenPressed(RobotCommands.cmdLightToggle);
    }

}
