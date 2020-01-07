
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Hatcher;

// This command closes the Hatcher claw via encoder to release, or in preparating to grab, the hatch, and keeps it closed
public class HatchClawClose extends CommandBase {

    private Hatcher m_hatcher;

    public HatchClawClose(Hatcher hatcher) {
        Logger.setup("Constructing Command: HatchClawClose...");

        // Add given subsystem requirements
        m_hatcher = hatcher;
        addRequirements(m_hatcher);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: HatchClawClose...");

        // Set encoded position
        m_hatcher.closeClaw();
    }

    @Override
    public void execute() {
        // int position = m_hatcher.getPosition();
        // int velocity = m_hatcher.getVelocity();
        // Logger.info("HatchClawClose -> Position: " + position + "; Velocity: " + velocity);
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
            Logger.ending("Interrupting Command: HatchClawClose...");
        } else {
            Logger.ending("Ending Command: HatchClawClose...");
        }

        m_hatcher.stop();
    }

}
