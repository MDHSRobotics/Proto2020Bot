
package frc.robot.oi.movements;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;

// The values needed to climb
public class ClimbMovement {

    public double backPulleyLiftSpeed = 0;
    public double frontPulleyLiftSpeed = 0;
    public double tankerSpeed = 0;

    public ClimbMovement() {
    }

    public ClimbMovement(double backPulleyLift, double frontPulleyLift, double tanker) {
        backPulleyLiftSpeed = backPulleyLift;
        frontPulleyLiftSpeed = frontPulleyLift;
        tankerSpeed = tanker;
    }

    // Gets the Pulley speed from the climb xbox controller's Left Thumbstick Y axis position
    public static double getBackPulleyLiftSpeed(XboxController xbox) {
        double y = xbox.getY(Hand.kLeft);
        return y;
    }

    // Gets the Pulley speed from the climb xbox controller's Right Thumbstick Y axis position
    public static double getFrontPulleyLiftSpeed(XboxController xbox) {
        double y = xbox.getY(Hand.kRight);
        return y;
    }

    // Gets the Tanker speed from the drive Xbox controller's Right Trigger position
    public static double getTankerSpeed(XboxController xbox) {
        double triggerAxis = xbox.getTriggerAxis(Hand.kRight);
        return triggerAxis;
    }

}
