
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.oi.ControlDevices;
import frc.robot.oi.DPadButton;
import frc.robot.subsystems.MecDriver;

// Automatically control the MecDrive to align the Robot with the gyro, and the line seen by the vision system
public class AlignMecanumDriveToGyro extends CommandBase {

    private MecDriver m_mecDriver;
    private int m_targetAngle = -1;

    public AlignMecanumDriveToGyro(MecDriver mecDriver) {
        Logger.setup("Constructing Command: AlignMecanumDriveToGyro...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing Command: AlignMecanumDriveToGyro...");

        m_targetAngle = DPadButton.getDpadAngleForGyro(ControlDevices.driveXbox);
    }

    @Override
    public void execute() {
        if (m_targetAngle != -1) {
            m_mecDriver.driveAlign(m_targetAngle);
        }
    }

    // This finishes immediately, but is intended to be continually restarted while a button is held
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: AlignMecanumDriveToGyro...");
        } else {
            Logger.ending("Ending Command: AlignMecanumDriveToGyro...");
        }
    }

}
