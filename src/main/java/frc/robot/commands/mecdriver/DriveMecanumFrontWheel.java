
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.oi.movements.FrontWheelMovement;
import frc.robot.oi.ControlDevices;
import frc.robot.subsystems.MecDriver;

// This command uses the joystick input to front wheel drive
public class DriveMecanumFrontWheel extends CommandBase {

    private MecDriver m_mecDriver;

    public DriveMecanumFrontWheel(MecDriver mecDriver) {
        Logger.setup("Constructing Command: DriveMecanumFrontWheel...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: DriveMecanumFrontWheel...");
    }

    @Override
    public void execute() {
        double speed = FrontWheelMovement.getFrontWheelDriveSpeed(ControlDevices.driveXbox);
        m_mecDriver.frontWheelDrive(speed);
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
            Logger.ending("Interrupting Command: DriveMecanumFrontWheel...");
        } else {
            Logger.ending("Ending Command: DriveMecanumFrontWheel...");
        }
    }

}
