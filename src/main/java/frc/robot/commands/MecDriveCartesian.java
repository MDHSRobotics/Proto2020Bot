
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.helpers.CartesianMovement;
import frc.robot.subsystems.MecDriver;
import frc.robot.OI;

// This command uses the controller input to mecanum drive using the cartesian method
public class MecDriveCartesian extends CommandBase {

    MecDriver m_mecDriver;

    public MecDriveCartesian(MecDriver mecDriver) {
        Logger.setup("Constructing Command: MecDriveCartesian...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MecDriveCartesian...");
    }

    @Override
    public void execute() {
        CartesianMovement move = OI.getCartesianMovement(m_mecDriver.controlStickDirectionFlipped);
        m_mecDriver.driveCartesian(move.ySpeed, move.xSpeed, move.zRotation);
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
            Logger.ending("Interrupting Command: MecDriveCartesian...");
        } else {
            Logger.ending("Ending Command: MecDriveCartesian...");
        }
    }

}
