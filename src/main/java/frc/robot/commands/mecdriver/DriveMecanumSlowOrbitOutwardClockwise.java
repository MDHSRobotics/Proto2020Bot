
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.MecDriver;

// Tests the MecDrive slowly orbitting, pointing outward, clockwise
public class DriveMecanumSlowOrbitOutwardClockwise extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumSlowOrbitOutwardClockwise(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumSlowOrbitOutwardClockwise...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumSlowOrbitOutwardClockwise...");
    }

    @Override
    public void execute() {
        m_mecDriver.orbitOutward(1, .5);
    }

    // This command finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: DriveMecanumSlowOrbitOutwardClockwise...");
            m_mecDriver.stop();
        } else {
            Logger.ending("Ending Command: DriveMecanumSlowOrbitOutwardClockwise...");
        }
    }

}
