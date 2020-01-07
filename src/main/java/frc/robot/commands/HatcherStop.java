
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;

// This command stops the Hatcher motor
public class HatcherStop extends CommandBase {

    private Hatcher m_hatcher;

    public HatcherStop(Hatcher hatcher) {
        Logger.setup("Constructing Command: HatcherStop...");

        // Add given subsystem requirements
        m_hatcher = hatcher;
        addRequirements(m_hatcher);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: HatcherStop...");
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
            Logger.ending("Interrupting Command: HatcherStop...");
        } else {
            Logger.ending("Ending Command: HatcherStop...");
        }

        m_hatcher.stop();
    }

}
