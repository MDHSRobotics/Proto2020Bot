
package frc.robot.helpers;

// The values needed to drive using arcade mode
public class ArcadeMovement {

    public double straightSpeed = 0; // x Forward & Backward
    public double rotationSpeed = 0; // z Rotate
    public double strafeSpeed = 0; // y Side to Side

    public ArcadeMovement() {
    }

    public ArcadeMovement(double xStraightSpeed, double zRotationSpeed, double yStrafeSpeed) {
        straightSpeed = xStraightSpeed;
        rotationSpeed = zRotationSpeed;
        strafeSpeed = yStrafeSpeed;
    }

}
