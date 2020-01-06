
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.consoles.Logger;

// Contains instances of all the commands on the robot.
public class RobotCommands {

    public static LightCycle cmdLightCycle;
    public static LightToggle cmdLightToggle;
    public static MecDriveCartesian cmdMecDriveCartesian;
    public static OmniDriveArcade cmdOmniDriveArcade;

    // Initialize all robot commands
    public static void initializeCommands() {
        Logger.setup("Initializing RobotCommands...");

        cmdLightCycle = new LightCycle(RobotSubsystems.ssLighter);
        cmdLightToggle = new LightToggle(RobotSubsystems.ssLighter);
        cmdMecDriveCartesian = new MecDriveCartesian(RobotSubsystems.ssMecDriver);
        cmdOmniDriveArcade = new OmniDriveArcade(RobotSubsystems.ssOmniDriver);
    }

    // Return the command to run in autonomous mode
    public static Command getAutonomousCommand() {
        return cmdLightCycle;
    }

}
