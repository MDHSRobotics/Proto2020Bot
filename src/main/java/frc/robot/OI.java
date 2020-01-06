
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import frc.robot.helpers.*;
import frc.robot.Brain;

// This class contains logic for retrieving values from human interface devices,
// like joysticks and xbox controllers.
// IMPORTANT: This should be the only class that uses OIDevices to obtain human interface values.
// TODO: Consider splitting OI into multiple smaller classes.
public class OI {

    public enum ControlStick {
        JOYSTICK, XBOX
    }

    //----------------------//
    // Active Control Stick //
    //----------------------//

    // Determines the tank movement (up or down for Left, up or down for Right)
    // from the active control stick position(s)
    public static TankMovement getTankMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
        case XBOX:
            return getTankMovementFromThumbsticks(isYflipped);
        default:
            return null;
        }
    }

    // Determines the cartesian movement (forward/backward speed, side to side speed, rotation speed)
    // from the active control stick position(s)
    public static CartesianMovement getCartesianMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
        case JOYSTICK:
            return getCartesianMovementFromJoystick(isYflipped);
        case XBOX:
            return getCartesianMovementFromThumbsticks(isYflipped);
        default:
            return null;
        }
    }

    // Determines the polar movement (magnitude, angle, rotation)
    // from the active control stick position(s)
    public static PolarMovement getPolarMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
        case JOYSTICK:
            return getPolarMovementFromJoystick(isYflipped);
        case XBOX:
            return getPolarMovementFromThumbsticks(isYflipped);
        default:
            return null;
        }
    }

    // Determines the arcade movement (forward/backward speed, rotation speed, square inputs)
    // from the active control stick position(s)
    public static ArcadeMovement getArcadeMovement(boolean isYflipped) {
        ControlStick cStick = Brain.getControlStick();
        switch (cStick) {
        case JOYSTICK:
            return getArcadeMovementFromJoystick(isYflipped);
        case XBOX:
            return getArcadeMovementFromThumbsticks(isYflipped);
        default:
            return null;
        }
    }

    //----------//
    // Joystick //
    //----------//

    // Determines the cartesian movement (straight speed, strafe speed, rotation speed)
    // from the current joystick position
    public static CartesianMovement getCartesianMovementFromJoystick(boolean isYflipped) {
        JoystickPosition pos = getJoystickPosition(isYflipped);
        CartesianMovement move = new CartesianMovement(pos.sideToSidePosition, pos.forwardBackPosition, pos.rotationPosition);
        // Logger.info("Joystick Cartesian Movement: " + pos.sideToSidePosition + ", " + pos.forwardBackPosition + ", " + pos.rotationPosition);
        return move;
    }

    // Determines the polar movement (angle speed, angle degrees, rotation speed)
    // from the current joystick position
    public static PolarMovement getPolarMovementFromJoystick(boolean isYflipped) {
        JoystickPosition pos = getJoystickPosition(isYflipped);
        PolarMovement move = new PolarMovement(pos.forwardBackPosition, pos.sideToSidePosition, pos.rotationPosition);
        // Logger.info("Joystick Polar Movement: " + pos.forwardBackPosition + ", " + pos.sideToSidePosition + ", " + pos.rotationPosition);
        return move;
    }

    // Determines the arcade movement (straight speed, rotation speed, square inputs, strafe speed)
    // from the current joystick position
    public static ArcadeMovement getArcadeMovementFromJoystick(boolean isYflipped) {
        JoystickPosition pos = getJoystickPosition(isYflipped);
        ArcadeMovement move = new ArcadeMovement(pos.forwardBackPosition, pos.rotationPosition, pos.sideToSidePosition);
        // Logger.info("Joystick Arcade Movement: " + pos.forwardBackPosition + ", " + pos.rotationPosition + ", " + pos.sideToSidePosition);
        return move;
    }

    // Gets the joystick position and applies user-determined orientation, deadzones, and sensitivity
    private static JoystickPosition getJoystickPosition(boolean isYflipped) {
        double y = OIDevices.jstick.getY(); // Forward & backward, flipped
        double x = OIDevices.jstick.getX(); // Side to side
        double z = OIDevices.jstick.getZ(); // Rotate, flipped?

        // Forward/backward and rotation directions are both reversed from what is intuitive, so flip them
        y = -y;
        z = -z; // TODO: Low priority, but check to see if this should be deleted, like we did for thumbsticks

        // User-determined flipping of forward/backward orientation
        if (isYflipped) {
            y = -y;
        }

        // Deadzones
        double yDeadZone = Brain.getYdeadZone();
        double xDeadZone = Brain.getXdeadZone();
        double zDeadZone = Brain.getZdeadZone();

        if (Math.abs(y) <= yDeadZone)
            y = 0;
        if (Math.abs(x) <= xDeadZone)
            x = 0;
        if (Math.abs(z) <= zDeadZone)
            z = 0;

        if (y > 0)
            y = y - yDeadZone;
        if (y < 0)
            y = y + yDeadZone;
        if (x > 0)
            x = x - xDeadZone;
        if (x < 0)
            x = x + xDeadZone;
        if (z > 0)
            z = z - zDeadZone;
        if (z < 0)
            z = z + zDeadZone;

        // Sensitivity
        double ySensitivity = Brain.getYsensitivity();
        double xSensitivity = Brain.getXsensitivity();
        double zSensitivity = Brain.getXsensitivity();

        y = y * ySensitivity;
        x = x * xSensitivity;
        z = z * zSensitivity;

        JoystickPosition pos = new JoystickPosition(y, x, z);
        return pos;
    }

    //------------------//
    // Xbox Thumbsticks //
    //------------------//

    // Determines the front wheel drive movement (straight speed)
    // from the current xbox left-hand trigger axis
    public static double getFrontWheelDriveSpeed() {
        double triggerAxis = OIDevices.driveXbox.getTriggerAxis(Hand.kLeft);
        return triggerAxis;
    }

    // Determines the tank movement (left-side straight speed, right-side straight speed)
    // from the current xbox thumbstick positions
    public static TankMovement getTankMovementFromThumbsticks(boolean isYflipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYflipped);
        TankMovement move = new TankMovement(pos.leftForwardBackPosition, pos.rightForwardBackPosition);
        //Logger.info("Xbox Tank Movement: " + pos.leftForwardBackPosition + ", " + pos.rightForwardBackPosition);
        return move;
    }

    // Determines the cartesian movement (straight speed, strafe speed, rotation speed)
    // from the current xbox thumbstick positions
    public static CartesianMovement getCartesianMovementFromThumbsticks(boolean isYleftFlipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYleftFlipped);
        CartesianMovement move = new CartesianMovement(pos.leftSideToSidePosition, pos.leftForwardBackPosition, pos.rightSideToSidePosition);
        // Logger.info("Xbox Cartesian Movement: " + pos.leftSideToSidePosition + ", " + pos.leftForwardBackPosition + ", " + pos.rightSideToSidePosition);
        return move;
    }

    // Determines the polar movement (angle speed, angle degrees, rotation speed)
    // from the current xbox thumbstick positions
    public static PolarMovement getPolarMovementFromThumbsticks(boolean isYleftFlipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYleftFlipped);
        PolarMovement move = new PolarMovement(pos.leftForwardBackPosition, pos.leftSideToSidePosition, pos.rightSideToSidePosition);
        // Logger.info("Xbox Polar Movement: " + pos.leftForwardBackPosition + ", " + pos.leftSideToSidePosition + ", " + pos.rightSideToSidePosition);
        return move;
    }

    // Determines the arcade movement (straight speed, rotation speed, square inputs, strafe speed)
    // from the current xbox thumbstick positions
    public static ArcadeMovement getArcadeMovementFromThumbsticks(boolean isYleftFlipped) {
        ThumbStickPosition pos = getThumbstickPosition(isYleftFlipped);
        ArcadeMovement move = new ArcadeMovement(pos.leftForwardBackPosition, pos.leftSideToSidePosition, pos.rightSideToSidePosition);
        // Logger.info("Xbox Arcade Movement: " + pos.leftForwardBackPosition + ", " + pos.leftSideToSidePosition + ", " + pos.rightSideToSidePosition);
        return move;
    }

    // Gets the xbox thumbstick positions and applies user-determined orientation, deadzones, and sensitivity
    private static ThumbStickPosition getThumbstickPosition(boolean isYleftFlipped) {
        double yLeft = OIDevices.driveXbox.getY(Hand.kLeft); // Forward & backward, flipped
        double xLeft = OIDevices.driveXbox.getX(Hand.kLeft); // Strafe
        double yRight = OIDevices.driveXbox.getY(Hand.kRight); // Forward & backward, flipped
        double xRight = OIDevices.driveXbox.getX(Hand.kRight); // Rotate

        // Forward/backward direction is reversed from what is intuitive, so flip it
        yLeft = -yLeft;
        yRight = -yRight;

        // User-determined flipping of forward/backward orientation
        if (isYleftFlipped) {
            yLeft = -yLeft;
            yRight = -yRight;
        }

        // Deadzones
        double yLeftDeadZone = Brain.getYleftDeadZone();
        double xLeftDeadZone = Brain.getXleftDeadZone();
        double yRightDeadZone = Brain.getYrightDeadZone();
        double xRightDeadZone = Brain.getXrightDeadZone();

        if (Math.abs(yLeft) <= yLeftDeadZone)
            yLeft = 0;
        if (Math.abs(xLeft) <= xLeftDeadZone)
            xLeft = 0;
        if (Math.abs(yRight) <= yRightDeadZone)
            yRight = 0;
        if (Math.abs(xRight) <= xRightDeadZone)
            xRight = 0;

        if (yLeft > 0)
            yLeft = yLeft - yLeftDeadZone;
        if (yLeft < 0)
            yLeft = yLeft + yLeftDeadZone;
        if (xLeft > 0)
            xLeft = xLeft - xLeftDeadZone;
        if (xLeft < 0)
            xLeft = xLeft + xLeftDeadZone;
        if (yRight > 0)
            yRight = yRight - yRightDeadZone;
        if (yRight < 0)
            yRight = yRight + yRightDeadZone;
        if (xRight > 0)
            xRight = xRight - xRightDeadZone;
        if (xRight < 0)
            xRight = xRight + xRightDeadZone;

        // Sensitivity
        double yLeftSensitivity = Brain.getYleftSensitivity();
        double xLeftSensitivity = Brain.getXleftSensitivity();
        double yRightSensitivity = Brain.getYrightSensitivity();
        double xRightSensitivity = Brain.getXrightSensitivity();

        yLeft = yLeft * yLeftSensitivity;
        xLeft = xLeft * xLeftSensitivity;
        yRight = yRight * yRightSensitivity;
        xRight = xRight * xRightSensitivity;

        ThumbStickPosition pos = new ThumbStickPosition(yLeft, xLeft, yRight, xRight);
        return pos;
    }

    // Converts the Dpad Angle (0 to 360, clockwise) into a Gyro Angle (0 to 180, clockwise, 0 to -180 counter-clockwise)
    public static int getDpadAngleForGyro() {
        int angle = OIDevices.driveXbox.getPOV(0);
        if (angle > 180)
            angle = angle - 360;
        return angle;
    }

    // TODO: Also consider adding a "debouncer" for the buttons
    // https://frc-pdr.readthedocs.io/en/latest/user_input/joystick.html

    //----------------------//
    // Interactive Climbing //
    //----------------------//

    // Gets the Pulley speed from the climb xbox controller's Left Thumbstick Y axis position
    public static double getBackPulleyLiftSpeed() {
        double y = OIDevices.climbXbox.getY(Hand.kLeft);
        return y;
    }

    // Gets the Pulley speed from the climb xbox controller's Right Thumbstick Y axis position
    public static double getFrontPulleyLiftSpeed() {
        double y = OIDevices.climbXbox.getY(Hand.kRight);
        return y;
    }

    // Gets the Pulley speed from the drive Xbox controller's Right Thumbstick Y
    public static double getBallSpeed() {
        double triggerAxis = OIDevices.driveXbox.getTriggerAxis(Hand.kRight);
        return triggerAxis;
    }

}
