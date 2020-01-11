
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.diffdriver.*;
import frc.robot.commands.hatcher.*;
import frc.robot.commands.lighter.*;
import frc.robot.commands.manager.*;
import frc.robot.commands.mecdriver.*;
import frc.robot.commands.omnidriver.*;
import frc.robot.consoles.Logger;

// Contains singleton instances of all the commands on the robot.
public class BotCommands {

    // DiffDriver
    public static DriveDifferentialTank driveDifferentialTank;

    // Hatcher
    public static CloseHatchClaw closeHatchClaw;
    public static OpenHatchClaw openHatchClaw;
    public static StopHatchClaw stopHatchClaw;
    public static ToggleHatchClawPosition toggleHatchClawPosition;

    // Lighter
    public static CycleLights cycleLights;
    public static ToggleLights toggleLights;

    // Manager
    public static SetGameModeToClimb setGameModeToClimb;
    public static SetGameModeToDelivery setGameModeToDelivery;

    // MecDriver
    public static AlignMecanumDriveToGyro alignMecanumDriveToGyro;
    public static DriveMecanumCartesian driveMecanumCartesian;
    public static DriveMecanumFrontWheel driveMecanumFrontWheel;
    public static DriveMecanumPolar driveMecanumPolar;
    public static DriveMecanumSlowForward driveMecanumSlowForward;
    public static DriveMecanumSlowOrbitInwardClockwise driveMecanumSlowOrbitInwardClockwise;
    public static DriveMecanumSlowOrbitOutwardClockwise driveMecanumSlowOrbitOutwardClockwise;
    public static DriveMecanumSlowTurnRight driveMecanumSlowTurnRight;
    public static DriveMecanumStraightUntilDistance driveMecanumStraightUntilDistance;
    public static DriveMecanumTank driveMecanumTank;
    public static FlipMecanumDriveControlStick flipMecanumDriveControlStick;
    public static RotateMecanumDriveToAngle rotateMecanumDriveToAngle;
    public static ToggleMecanumDriveOrientation toggleMecanumDriveOrientation;

    // OmniDriver
    public static DriveOmniArcade driveOmniArcade;

    // Initialize all robot commands
    public static void initializeCommands() {
        Logger.setup("Initializing BotCommands...");

        // DiffDriver
        driveDifferentialTank = new DriveDifferentialTank(BotSubsystems.diffDriver);

        // Hatcher
        closeHatchClaw = new CloseHatchClaw(BotSubsystems.hatcher);
        openHatchClaw = new OpenHatchClaw(BotSubsystems.hatcher);
        stopHatchClaw = new StopHatchClaw(BotSubsystems.hatcher);
        toggleHatchClawPosition = new ToggleHatchClawPosition(BotSubsystems.hatcher);

        // Lighter
        cycleLights = new CycleLights(BotSubsystems.lighter);
        toggleLights = new ToggleLights(BotSubsystems.lighter);

        // Manager
        setGameModeToClimb = new SetGameModeToClimb();
        setGameModeToDelivery = new SetGameModeToDelivery();

        // MecDriver
        alignMecanumDriveToGyro = new AlignMecanumDriveToGyro(BotSubsystems.mecDriver);
        driveMecanumCartesian = new DriveMecanumCartesian(BotSubsystems.mecDriver);
        driveMecanumFrontWheel = new DriveMecanumFrontWheel(BotSubsystems.mecDriver);
        driveMecanumPolar = new DriveMecanumPolar(BotSubsystems.mecDriver);
        driveMecanumSlowForward = new DriveMecanumSlowForward(BotSubsystems.mecDriver);
        driveMecanumSlowOrbitInwardClockwise = new DriveMecanumSlowOrbitInwardClockwise(BotSubsystems.mecDriver);
        driveMecanumSlowOrbitOutwardClockwise = new DriveMecanumSlowOrbitOutwardClockwise(BotSubsystems.mecDriver);
        driveMecanumSlowTurnRight = new DriveMecanumSlowTurnRight(BotSubsystems.mecDriver);
        driveMecanumStraightUntilDistance = new DriveMecanumStraightUntilDistance(BotSubsystems.mecDriver);
        driveMecanumTank = new DriveMecanumTank(BotSubsystems.mecDriver);
        flipMecanumDriveControlStick = new FlipMecanumDriveControlStick(BotSubsystems.mecDriver);
        rotateMecanumDriveToAngle = new RotateMecanumDriveToAngle(BotSubsystems.mecDriver);
        toggleMecanumDriveOrientation = new ToggleMecanumDriveOrientation(BotSubsystems.mecDriver);

        // OmniDriver
        driveOmniArcade = new DriveOmniArcade(BotSubsystems.omniDriver);
    }

    // Return the command to run in autonomous mode
    public static Command getAutonomousCommand() {
        return cycleLights;
    }

}
