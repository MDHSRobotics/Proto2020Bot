
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;
import frc.robot.BotCommands;

// Toggles the position of the Hatcher Claw
public class HatcherToggleClawPosition extends InstantCommand {

    private Hatcher m_hatcher;

    public HatcherToggleClawPosition(Hatcher hatcher) {
        super();
        Logger.setup("Constructing InstantCommand: HatcherToggleClawPosition...");

        m_hatcher = hatcher;
    }

    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: HatcherToggleClawPosition...");

        if (m_hatcher.clawIsClosed) {
            Logger.action("Hatcher -> Moving to OPEN...");
            BotCommands.cmdHatchClawOpen.schedule();
        } else {
            Logger.action("Hatcher -> Moving to CLOSED...");
            BotCommands.cmdHatchClawClose.schedule();
        }
        m_hatcher.toggleClawPosition();
    }

}
