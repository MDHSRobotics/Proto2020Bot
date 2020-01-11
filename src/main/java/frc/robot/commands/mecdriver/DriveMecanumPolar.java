
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.oi.movements.PolarMovement;
import frc.robot.subsystems.MecDriver;

// This command uses the controller input to mecanum drive using the polar method
public class DriveMecanumPolar extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumPolar(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumPolar...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumPolar...");
    }

    @Override
    public void execute() {
        PolarMovement move = PolarMovement.getPolarMovement(m_mecDriver.controlStickDirectionFlipped);
        m_mecDriver.drivePolar(move.angleSpeed, move.angleDegrees, move.rotationSpeed);
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
            Logger.ending("Interrupting Command: DriveMecanumPolar...");
        } else {
            Logger.ending("Ending Command: DriveMecanumPolar...");
        }
    }

}
