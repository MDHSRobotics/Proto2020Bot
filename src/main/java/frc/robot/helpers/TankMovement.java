
package frc.robot.helpers;

// The values needed to drive using tank mode
public class TankMovement {

    public double leftSpeed = 0; // x left Forward & Backward
    public double rightSpeed = 0; // x right Forward & Backward

    public TankMovement() {
    }

    public TankMovement(double xLeftSpeed, double xRightSpeed) {
        leftSpeed = xLeftSpeed;
        rightSpeed = xRightSpeed;
    }

}