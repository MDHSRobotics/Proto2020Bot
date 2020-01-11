
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.MecDriver;

// Tests the MecDrive slowly turning right
public class DriveMecanumSlowTurnRight extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumSlowTurnRight(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumSlowTurnRight...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumSlowTurnRight...");
    }

    @Override
    public void execute() {
        m_mecDriver.rotate(.5);
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
            Logger.ending("Interrupting Command: DriveMecanumSlowTurnRight...");
            m_mecDriver.stop();
        } else {
            Logger.ending("Ending Command: DriveMecanumSlowTurnRight...");
        }
    }

}
