
package frc.robot.commands.manager;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.BotCommands;
import frc.robot.RobotManager;

// This command sets the Game Mode to DELIVERY
public class SetGameModeToDelivery extends InstantCommand {

    public SetGameModeToDelivery() {
        Logger.setup("Constructing InstantCommand: SetGameModeToDelivery...");
    }

    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: RobotGameModeDelivery...");

        if (RobotManager.botGameMode == RobotManager.GameMode.DELIVERY) {
            Logger.info("Robot Game Mode is still DELIVERY");
        } else {
            Logger.info("Robot Game Mode is now DELIVERY");
            RobotManager.botGameMode = RobotManager.GameMode.DELIVERY;
            BotCommands.driveMecanumCartesian.schedule();
        }
    }

}
