
package frc.robot.helpers;

// The position values obtained from a Joystick
public class JoystickPosition {

    public double forwardBackPosition = 0; // y Forward & Backward
    public double sideToSidePosition = 0; // x Side to Side
    public double rotationPosition = 0; // z Rotate

    public JoystickPosition() {
    }

    public JoystickPosition(double forwardBack, double sideToSide, double rotation) {
        forwardBackPosition = forwardBack;
        sideToSidePosition = sideToSide;
        rotationPosition = rotation;
    }

}
