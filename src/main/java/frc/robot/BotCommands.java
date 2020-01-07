
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.consoles.Logger;

// Contains singleton instances of all the commands on the robot.
public class BotCommands {

    public static LightCycle cmdLightCycle;
    public static LightToggle cmdLightToggle;

    public static DiffDriveTank cmdDiffDriveTank;
    public static MecDriveCartesian cmdMecDriveCartesian;
    public static OmniDriveArcade cmdOmniDriveArcade;

    public static HatcherStop cmdHatcherStop;
    public static HatchClawOpen cmdHatchClawOpen;
    public static HatchClawClose cmdHatchClawClose;
    public static HatcherToggleClawPosition cmdHatcherToggleClawPosition;

    // Initialize all robot commands
    public static void initializeCommands() {
        Logger.setup("Initializing BotCommands...");

        cmdLightCycle = new LightCycle(BotSubsystems.ssLighter);
        cmdLightToggle = new LightToggle(BotSubsystems.ssLighter);

        cmdDiffDriveTank = new DiffDriveTank(BotSubsystems.ssDiffDriver);
        cmdMecDriveCartesian = new MecDriveCartesian(BotSubsystems.ssMecDriver);
        cmdOmniDriveArcade = new OmniDriveArcade(BotSubsystems.ssOmniDriver);

        cmdHatcherStop = new HatcherStop(BotSubsystems.ssHatcher);
        cmdHatchClawOpen = new HatchClawOpen(BotSubsystems.ssHatcher);
        cmdHatchClawClose = new HatchClawClose(BotSubsystems.ssHatcher);
        cmdHatcherToggleClawPosition = new HatcherToggleClawPosition(BotSubsystems.ssHatcher);
    }

    // Return the command to run in autonomous mode
    public static Command getAutonomousCommand() {
        return cmdLightCycle;
    }

}
