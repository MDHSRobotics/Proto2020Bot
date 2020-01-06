
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.helpers.ArcadeMovement;
import frc.robot.subsystems.OmniDriver;
import frc.robot.OI;

// This command uses the controller input to omni drive using the arcade method
public class OmniDriveArcade extends CommandBase {

    OmniDriver m_omniDriver;

    public OmniDriveArcade(OmniDriver omniDriver) {
        Logger.setup("Constructing Command: OmniDriveArcade...");

        // Add given subsystem requirements
        m_omniDriver = omniDriver;
        addRequirements(m_omniDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: OmniDriveArcade...");
    }

    @Override
    public void execute() {
        ArcadeMovement move = OI.getArcadeMovement(m_omniDriver.controlStickDirectionFlipped);
        m_omniDriver.driveArcade(move.straightSpeed, move.rotationSpeed, move.strafeSpeed);
    }

    // This command continues until interrupted.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: OmniDriveArcade...");
        } else {
            Logger.ending("Ending Command: OmniDriveArcade...");
        }
    }

}
