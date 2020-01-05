
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Distance;
import frc.robot.sensors.Vision;
import frc.robot.subsystems.Lighter;
import frc.robot.subsystems.MecDriver;
import frc.robot.OI;

// This command toggles the "Lighter" lights from certain sensor states
public class LightToggle extends CommandBase {

    Lighter m_lighter = null;

    public LightToggle(Lighter lighter) {
        Logger.setup("Constructing Command: LightToggle...");

        // Add given subsystem requirements
        m_lighter = lighter;
        addRequirements(m_lighter);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: LightToggle...");
    }

    @Override
    public void execute() {
        boolean frontLineDetected = Vision.frontLineDetected();
        boolean rightLineDetected = Vision.rightLineDetected();
        boolean leftLineDetected = Vision.leftLineDetected();
        if (frontLineDetected || leftLineDetected || rightLineDetected) {
            int dpadAngle = OI.getDpadAngleForGyro();
            boolean isAligned = MecDriver.isAligned(dpadAngle);
            if (isAligned) {
                boolean closeEnough = Distance.distanceReached();
                if (closeEnough) {
                    m_lighter.turnOnBoth();
                } else {
                    m_lighter.turnOnRedOnly();
                }
            } else {
                m_lighter.turnOnWhiteOnly();
            }
        } else {
            m_lighter.turnOffBoth();
        }
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("--");
            Logger.ending("Interrupting Command: LightToggle...");
        } else {
            Logger.ending("Ending Command: LightToggle...");
        }

        m_lighter.turnOffBoth();
    }

}
