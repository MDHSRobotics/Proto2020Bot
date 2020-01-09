
package frc.robot.oi;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.oi.DPadButton.Direction;

// This class contains human interface id mappings, singleton input device instances, and utility methods.
public class ControlDevices {

    public enum ControlStick {
        JOYSTICK, XBOX
    }

    private static final int DRIVE_XBOX_STICK_NUM = 0;
    private static final int CLIMB_XBOX_STICK_NUM = 1;
    private static final int JSTICK_STICK_NUM = 2;

    // Joysticks
    public static final Joystick jstick = new Joystick(JSTICK_STICK_NUM);
    public static final JoystickButton jstickBtn1 = new JoystickButton(jstick, 1); // Trigger
    public static final JoystickButton jstickBtn2 = new JoystickButton(jstick, 2);
    public static final JoystickButton jstickBtn3 = new JoystickButton(jstick, 3);
    public static final JoystickButton jstickBtn4 = new JoystickButton(jstick, 4);
    public static final JoystickButton jstickBtn5 = new JoystickButton(jstick, 5);
    public static final JoystickButton jstickBtn6 = new JoystickButton(jstick, 6);
    public static final JoystickButton jstickBtn7 = new JoystickButton(jstick, 7);
    public static final JoystickButton jstickBtn8 = new JoystickButton(jstick, 8);
    public static final JoystickButton jstickBtn9 = new JoystickButton(jstick, 9);
    public static final JoystickButton jstickBtn10 = new JoystickButton(jstick, 10);
    public static final JoystickButton jstickBtn11 = new JoystickButton(jstick, 11);
    public static final JoystickButton jstickBtn12 = new JoystickButton(jstick, 12);

    // Xbox Controller - Drive & Delivery
    public static final XboxController driveXbox = new XboxController(DRIVE_XBOX_STICK_NUM);
    public static final JoystickButton driveXboxBtnA = new JoystickButton(driveXbox, 1);
    public static final JoystickButton driveXboxBtnB = new JoystickButton(driveXbox, 2);
    public static final JoystickButton driveXboxBtnX = new JoystickButton(driveXbox, 3);
    public static final JoystickButton driveXboxBtnY = new JoystickButton(driveXbox, 4);
    public static final JoystickButton driveXboxBtnBumperLeft = new JoystickButton(driveXbox, 5);
    public static final JoystickButton driveXboxBtnBumperRight = new JoystickButton(driveXbox, 6);
    public static final JoystickButton driveXboxBtnBack = new JoystickButton(driveXbox, 7);
    public static final JoystickButton driveXboxBtnStart = new JoystickButton(driveXbox, 8);
    public static final JoystickButton driveXboxBtnStickLeft = new JoystickButton(driveXbox, 9);
    public static final JoystickButton driveXboxBtnStickRight = new JoystickButton(driveXbox, 10);
    public static final DPad driveXboxBtnDpad = new DPad(driveXbox);
    public static final DPadButton driveXboxBtnDpadUp = new DPadButton(driveXbox, Direction.UP);
    public static final DPadButton driveXboxBtnDpadDown = new DPadButton(driveXbox, Direction.DOWN);
    public static final DPadButton driveXboxBtnDpadLeft = new DPadButton(driveXbox, Direction.LEFT);
    public static final DPadButton driveXboxBtnDpadRight = new DPadButton(driveXbox, Direction.RIGHT);
    public static final DPadButton driveXboxBtnDpadUpLeft = new DPadButton(driveXbox, Direction.UP_LEFT);
    public static final DPadButton driveXboxBtnDpadUpRight = new DPadButton(driveXbox, Direction.UP_RIGHT);
    public static final DPadButton driveXboxBtnDpadDownLeft = new DPadButton(driveXbox, Direction.DOWN_LEFT);
    public static final DPadButton driveXboxBtnDpadDownRight = new DPadButton(driveXbox, Direction.DOWN_RIGHT);

    // Xbox Controller - Climb
    public static final XboxController climbXbox = new XboxController(CLIMB_XBOX_STICK_NUM);
    public static final JoystickButton climbXboxBtnA = new JoystickButton(climbXbox, 1);
    public static final JoystickButton climbXboxBtnB = new JoystickButton(climbXbox, 2);
    public static final JoystickButton climbXboxBtnX = new JoystickButton(climbXbox, 3);
    public static final JoystickButton climbXboxBtnY = new JoystickButton(climbXbox, 4);
    public static final JoystickButton climbXboxBtnBumperLeft = new JoystickButton(climbXbox, 5);
    public static final JoystickButton climbXboxBtnBumperRight = new JoystickButton(climbXbox, 6);
    public static final JoystickButton climbXboxBtnBack = new JoystickButton(climbXbox, 7);
    public static final JoystickButton climbXboxBtnStart = new JoystickButton(climbXbox, 8);
    public static final JoystickButton climbXboxBtnStickLeft = new JoystickButton(climbXbox, 9);
    public static final JoystickButton climbXboxBtnStickRight = new JoystickButton(climbXbox, 10);
    public static final DPad climbXboxBtnDpad = new DPad(climbXbox);
    public static final DPadButton climbXboxBtnDpadUp = new DPadButton(climbXbox, Direction.UP);
    public static final DPadButton climbXboxBtnDpadDown = new DPadButton(climbXbox, Direction.DOWN);
    public static final DPadButton climbXboxBtnDpadLeft = new DPadButton(climbXbox, Direction.LEFT);
    public static final DPadButton climbXboxBtnDpadRight = new DPadButton(climbXbox, Direction.RIGHT);
    public static final DPadButton climbXboxBtnDpadUpLeft = new DPadButton(climbXbox, Direction.UP_LEFT);
    public static final DPadButton climbXboxBtnDpadUpRight = new DPadButton(climbXbox, Direction.UP_RIGHT);
    public static final DPadButton climbXboxBtnDpadDownLeft = new DPadButton(climbXbox, Direction.DOWN_LEFT);
    public static final DPadButton climbXboxBtnDpadDownRight = new DPadButton(climbXbox, Direction.DOWN_RIGHT);

    // Determine if a given stick is connected
    public static boolean isStickConnected(int stickNumber) {
        int numberOfButtons = DriverStation.getInstance().getStickButtonCount(stickNumber);
        return numberOfButtons > 0;
    }

    // Determine if the Drive XBox controller is connected
    public static boolean isDriveXboxConnected() {
        return isStickConnected(DRIVE_XBOX_STICK_NUM);
    }

    // Determine if the Climb XBox controller is connected
    public static boolean isClimbXboxConnected() {
        return isStickConnected(CLIMB_XBOX_STICK_NUM);
    }

}
