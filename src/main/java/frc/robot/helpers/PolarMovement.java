
package frc.robot.helpers;

// The values (magnitude, angle, rotation) needed to drive using polar coordinates
public class PolarMovement {

    public double angleSpeed = 0;
    public double angleDegrees = 0;
    public double rotationSpeed = 0;

    public PolarMovement() {
    }

    public PolarMovement(double xStraightSpeed, double yStrafeSpeed, double zRotationSpeed) {
        angleSpeed = PolarMovement.calculateMagnitude(xStraightSpeed, yStrafeSpeed);
        angleDegrees = PolarMovement.calculateAngle(xStraightSpeed, yStrafeSpeed);
        rotationSpeed = zRotationSpeed;
    }

    public static double calculateMagnitude(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double calculateAngle(double x, double y) {
        return Math.atan2(x, y);
    }

}
