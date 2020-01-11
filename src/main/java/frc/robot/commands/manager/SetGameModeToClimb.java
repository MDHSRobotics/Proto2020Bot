
package frc.robot.commands.manager;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.BotCommands;
import frc.robot.RobotManager;

// This command sets the Game Mode to CLIMB
public class SetGameModeToClimb extends InstantCommand {

    public SetGameModeToClimb() {
        Logger.setup("Constructing InstantCommand: SetGameModeToClimb...");
    }

    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: SetGameModeToClimb...");

        if (RobotManager.botGameMode == RobotManager.GameMode.CLIMB) {
            if (RobotManager.botClimbMode == RobotManager.ClimbMode.HAB2) {
                RobotManager.botClimbMode = RobotManager.ClimbMode.HAB3;
                Logger.info("Robot Climb Mode is now HAB3");
            }
            else if (RobotManager.botClimbMode == RobotManager.ClimbMode.HAB3){
                RobotManager.botClimbMode = RobotManager.ClimbMode.HAB2;
                Logger.info("Robot Climb Mode is now HAB2");
            }
        }
        else {
            RobotManager.botGameMode = RobotManager.GameMode.CLIMB;
            Logger.info("Robot Game Mode is now CLIMB; Climb Mode is " + RobotManager.botClimbMode);
            BotCommands.driveMecanumFrontWheel.schedule();
        }
    }

}
