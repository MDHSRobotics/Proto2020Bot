
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.helpers.TankMovement;
import frc.robot.subsystems.DiffDriver;
import frc.robot.OI;

// This command uses the xbox input to differential drive using the tank method
public class DiffDriveTank extends CommandBase {

    DiffDriver m_diffDriver;

    public DiffDriveTank(DiffDriver diffDriver) {
        Logger.setup("Constructing Command: DiffDriveTank...");

        // Add given subsystem requirements
        m_diffDriver = diffDriver;
        addRequirements(m_diffDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DiffDriveTank...");
    }

    @Override
    public void execute() {
        TankMovement move = OI.getTankMovementFromThumbsticks(m_diffDriver.controlStickDirectionFlipped);
        m_diffDriver.driveTank(move.leftSpeed, move.rightSpeed);
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
            Logger.ending("Interrupting Command: DiffDriveTank...");
        } else {
            Logger.ending("Ending Command: DiffDriveTank...");
        }
    }

}
