
package frc.robot.helpers;

// The values needed to drive using cartesian coordinates
public class CartesianMovement {

    public double strafeSpeed = 0; // y Side to Side
    public double straightSpeed = 0; // x Forward & Backward
    public double rotationSpeed = 0; // z Rotate

    public CartesianMovement() {
    }

    public CartesianMovement(double yStrafeSpeed, double xStraightSpeed, double zRotationSpeed) {
        strafeSpeed = yStrafeSpeed;
        straightSpeed = xStraightSpeed;
        rotationSpeed = zRotationSpeed;
    }

}
