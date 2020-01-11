
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.oi.movements.TankMovement;
import frc.robot.subsystems.MecDriver;

// This command uses the controller input to mecanum drive using the tank method
public class DriveMecanumTank extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumTank(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumTank...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumTank...");
    }

    @Override
    public void execute() {
        TankMovement move = TankMovement.getTankMovement(m_mecDriver.controlStickDirectionFlipped);
        m_mecDriver.driveTank(move.leftSpeed, move.rightSpeed);
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
            Logger.ending("Interrupting Command: DriveMecanumTank...");
        } else {
            Logger.ending("Ending Command: DriveMecanumTank...");
        }
    }

}
