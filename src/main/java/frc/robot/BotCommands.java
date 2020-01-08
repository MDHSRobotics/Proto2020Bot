
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.diffdriver.*;
import frc.robot.commands.hatcher.*;
import frc.robot.commands.lighter.*;
import frc.robot.commands.mecdriver.*;
import frc.robot.commands.omnidriver.*;
import frc.robot.consoles.Logger;

// Contains singleton instances of all the commands on the robot.
public class BotCommands {

    public static CycleLights cycleLights;
    public static ToggleLights toggleLights;

    public static DriveDifferentialTank driveDifferentialTank;
    public static DriveMecanumCartesian driveMecanumCartesian;
    public static DriveOmniArcade driveOmniArcade;

    public static StopHatchClaw stopHatchClaw;
    public static OpenHatchClaw openHatchClaw;
    public static CloseHatchClaw closeHatchClaw;
    public static ToggleHatchClawPosition toggleHatchClawPosition;

    // Initialize all robot commands
    public static void initializeCommands() {
        Logger.setup("Initializing BotCommands...");

        cycleLights = new CycleLights(BotSubsystems.lighter);
        toggleLights = new ToggleLights(BotSubsystems.lighter);

        driveDifferentialTank = new DriveDifferentialTank(BotSubsystems.diffDriver);
        driveMecanumCartesian = new DriveMecanumCartesian(BotSubsystems.mecDriver);
        driveOmniArcade = new DriveOmniArcade(BotSubsystems.omniDriver);

        stopHatchClaw = new StopHatchClaw(BotSubsystems.hatcher);
        openHatchClaw = new OpenHatchClaw(BotSubsystems.hatcher);
        closeHatchClaw = new CloseHatchClaw(BotSubsystems.hatcher);
        toggleHatchClawPosition = new ToggleHatchClawPosition(BotSubsystems.hatcher);
    }

    // Return the command to run in autonomous mode
    public static Command getAutonomousCommand() {
        return cycleLights;
    }

}
