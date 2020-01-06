
package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

// Contains instances of all the subsystems on the robot.
// IMPORTANT: When you make a new subsystem, you need to also set a default command.
public class RobotSubsystems {

    public static Lighter ssLighter;
    public static MecDriver ssMecDriver;
    public static OmniDriver ssOmniDriver;

    // Initialize all robot subsystems
    public static void initializeSubsystems() {
        Logger.setup("Initializing RobotSubsystems...");

        ssLighter = new Lighter();
        ssMecDriver = new MecDriver();
        ssOmniDriver = new OmniDriver();
    }

    // Set all the subsystem default commands
    public static void setDefaultCommands() {
        Logger.setup("Lighter DefaultCommand -> LightToggle...");
        ssLighter.setDefaultCommand(RobotCommands.cmdLightToggle);

        Logger.setup("MecDriver DefaultCommand -> MecDriveCartesian...");
        ssMecDriver.setDefaultCommand(RobotCommands.cmdMecDriveCartesian);

        Logger.setup("OmniDriver DefaultCommand -> OmniDriveArcade...");
        ssOmniDriver.setDefaultCommand(RobotCommands.cmdOmniDriveArcade);
    }

}
