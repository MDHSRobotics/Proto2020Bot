
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.consoles.Logger;
import frc.robot.consoles.Shuffler;

// This is where the robot state is initialized and persisted.
public class RobotManager {

    //--------------//
    // Robot States //
    //--------------//

    public enum Variant {
        TEST_BOARD, TEST_DRIVE, BUILD_HOME, BUILD_AWAY
    }

    public enum GameMode {
        DELIVERY, CLIMB
    }

    public enum DeliveryMode {
        GET_HATCH, ATTACH_HATCH, GET_BALL, TOSS_BALL
    }

    public enum ClimbMode {
        HAB2, HAB3
    }

    // Variant is used to configure different device mappings for different "robots"
    // TODO: This needs to be added to the Brain and Shuffleboard, so that it is settable on the fly
    public static Variant botVariant = Variant.TEST_BOARD;
    // Game Mode is used to activate/deactivate the Climb Xbox Controller
    public static GameMode botGameMode = GameMode.DELIVERY;
    // Delivery Mode is used to control vision processing actions, as well as xbox controller activation
    // TODO: We need to implement ways to set the Robot DeliveryMode, either manually, or automatically, or a combination
    // TODO: Determine the best default. What's the first action the Robot will take during Sandstorm?
    public static DeliveryMode botDeliveryMode = DeliveryMode.GET_HATCH;
    // Climb Mode tells the climb commands which system needs to be activated next
    public static ClimbMode botClimbMode = ClimbMode.HAB2;

    //-------------------------------//
    // Shuffleboard & SmartDashboard //
    //-------------------------------//

    // The robot Shuffler instance
    public static Shuffler botShuffler;

    // The auto command chooser to add to SmartDashboard
    public static SendableChooser<Command> autoCommandChooser;

    //----------------//
    // Initialization //
    //----------------//

    // It is important that the robot be initialized in exactly this order.
    public static void initialize() {
        Logger.setup("Initializing BotManager...");

        // Initialize SubsystemDevices
        SubsystemDevices.initializeDevices();

        // Pre-intialize the Shuffler
        botShuffler = new Shuffler();
        botShuffler.preInitialize();

        // Initialize BotSensors, BotSubsystems, and BotCommands
        BotSensors.initializeSensors();
        BotSubsystems.initializeSubsystems();
        BotCommands.initializeCommands();

        // Setup SmartDashboard
        setupSmartDashboard();

        // Intialize and configure the Shuffler
        botShuffler.initialize();
        botShuffler.configure();

        // Set default subsystem commands and configure button bindings
        BotSubsystems.setDefaultCommands();
        ButtonBindings.configure();
    }

    // Add the desired commands to the SmartDashboard
    private static void setupSmartDashboard() {
        Logger.setup("Adding AutoModes to SmartDashboard...");
        autoCommandChooser = new SendableChooser<>();
        autoCommandChooser.setDefaultOption("Lighter - CycleLights", BotCommands.cycleLights);
        autoCommandChooser.addOption("Lighter - ToggleLights", BotCommands.toggleLights);
        SmartDashboard.putData("AutoMode", autoCommandChooser);
    }

}
