
package frc.robot.commands.hatcher;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;

// This command stops the Hatcher motor
public class StopHatchClaw extends CommandBase {

    private Hatcher m_hatcher;

    public StopHatchClaw(Hatcher hatcher) {
        Logger.setup("Constructing Command: StopHatchClaw...");

        // Add given subsystem requirements
        m_hatcher = hatcher;
        addRequirements(m_hatcher);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: StopHatchClaw...");
    }

    @Override
    public void execute() {
        m_hatcher.stop();
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: StopHatchClaw...");
        } else {
            Logger.ending("Ending Command: StopHatchClaw...");
        }

        m_hatcher.stop();
    }

}
