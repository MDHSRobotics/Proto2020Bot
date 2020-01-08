
package frc.robot.commands.hatcher;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;

// This command opens the Hatcher claw via encoder to grab the hatch, and keeps it there
public class OpenHatchClaw extends CommandBase {

    private Hatcher m_hatcher;

    public OpenHatchClaw(Hatcher hatcher) {
        Logger.setup("Constructing Command: OpenHatchClaw...");

        // Add given subsystem requirements
        m_hatcher = hatcher;
        addRequirements(m_hatcher);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: OpenHatchClaw...");

        // Set encoded position
        m_hatcher.openClaw();
    }

    @Override
    public void execute() {
        // int position = m_hatcher.getPosition();
        // int velocity = m_hatcher.getVelocity();
        // Logger.info("OpenHatchClaw -> Position: " + position + "; Velocity: " + velocity);
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
            Logger.ending("Interrupting Command: OpenHatchClaw...");
        } else {
            Logger.ending("Ending Command: OpenHatchClaw...");
        }

        m_hatcher.stop();
    }

}
