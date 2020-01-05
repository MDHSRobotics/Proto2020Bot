
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Relay;

import frc.robot.consoles.Logger;
import frc.robot.RobotDevices;

// Lighter Subsystem, for turning lights on and off.
public class Lighter extends SubsystemBase {

    public Lighter() {
        Logger.setup("Constructing Subsystem: Lighter...");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void turnOnBoth() {
        RobotDevices.lighterRelay.set(Relay.Value.kOn);
    }

    public void turnOffBoth() {
        RobotDevices.lighterRelay.set(Relay.Value.kOff);
    }

    public void turnOnWhiteOnly() {
        RobotDevices.lighterRelay.set(Relay.Value.kForward);
    }

    public void turnOnRedOnly() {
        RobotDevices.lighterRelay.set(Relay.Value.kReverse);
    }

}
