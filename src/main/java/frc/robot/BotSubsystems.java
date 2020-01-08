
package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

// Contains singleton instances of all the subsystems on the robot.
// IMPORTANT: When you make a new subsystem, you need to also set a default command.
public class BotSubsystems {

    public static Lighter lighter;
    public static DiffDriver diffDriver;
    public static MecDriver mecDriver;
    public static OmniDriver omniDriver;
    public static Hatcher hatcher;

    // Initialize all robot subsystems
    public static void initializeSubsystems() {
        Logger.setup("Initializing BotSubsystems...");

        lighter = new Lighter();
        diffDriver = new DiffDriver();
        mecDriver = new MecDriver();
        omniDriver = new OmniDriver();
        hatcher = new Hatcher();
    }

    // Set all the subsystem default commands
    public static void setDefaultCommands() {
        Logger.setup("Lighter DefaultCommand -> ToggleLights...");
        lighter.setDefaultCommand(BotCommands.toggleLights);

        Logger.setup("DiffDriver DefaultCommand -> DriveDifferentialTank...");
        diffDriver.setDefaultCommand(BotCommands.driveDifferentialTank);

        Logger.setup("MecDriver DefaultCommand -> DriveMecanumCartesian...");
        mecDriver.setDefaultCommand(BotCommands.driveMecanumCartesian);

        Logger.setup("OmniDriver DefaultCommand -> DriveOmniArcade...");
        omniDriver.setDefaultCommand(BotCommands.driveOmniArcade);

        Logger.setup("Hatcher DefaultCommand -> StopHatchClaw...");
        hatcher.setDefaultCommand(BotCommands.stopHatchClaw);
    }

}
