
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.oi.movements.CartesianMovement;
import frc.robot.subsystems.MecDriver;

// This command uses the controller input to mecanum drive using the cartesian method
public class DriveMecanumCartesian extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumCartesian(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumCartesian...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumCartesian...");
    }

    @Override
    public void execute() {
        CartesianMovement move = CartesianMovement.getCartesianMovement(m_mecDriver.controlStickDirectionFlipped);
        m_mecDriver.driveCartesian(move.strafeSpeed, move.straightSpeed, move.rotationSpeed);
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
            Logger.ending("Interrupting Command: DriveMecanumCartesian...");
        } else {
            Logger.ending("Ending Command: DriveMecanumCartesian...");
        }
    }

}
