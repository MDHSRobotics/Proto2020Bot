
package frc.robot.commands.hatcher;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;
import frc.robot.BotCommands;

// Toggles the position of the Hatcher Claw
public class ToggleHatchClawPosition extends InstantCommand {

    private Hatcher m_hatcher;

    public ToggleHatchClawPosition(Hatcher hatcher) {
        super();
        Logger.setup("Constructing InstantCommand: ToggleHatchClawPosition...");

        m_hatcher = hatcher;
    }

    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: ToggleHatchClawPosition...");

        if (m_hatcher.clawIsClosed) {
            Logger.action("Hatcher -> Moving to OPEN...");
            BotCommands.openHatchClaw.schedule();
        } else {
            Logger.action("Hatcher -> Moving to CLOSED...");
            BotCommands.closeHatchClaw.schedule();
        }
        m_hatcher.toggleClawPosition();
    }

}
