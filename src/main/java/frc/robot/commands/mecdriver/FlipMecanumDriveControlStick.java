
package frc.robot.commands.mecdriver;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.MecDriver;

// This command stops the mecanum drive, and flips the joystick or left thumbstick in Y
public class FlipMecanumDriveControlStick extends InstantCommand {

    private MecDriver m_mecDriver;

    public FlipMecanumDriveControlStick(MecDriver mecDriver) {
        Logger.setup("Constructing Command: FlipMecanumDriveControlStick...");

        // Add given subsystem requirements
        m_mecDriver = mecDriver;
        addRequirements(m_mecDriver);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("--");
        Logger.action("Initializing InstantCommand: FlipMecanumDriveControlStick...");

        m_mecDriver.stop();
        m_mecDriver.flipControlStickDirection();
    }

}
