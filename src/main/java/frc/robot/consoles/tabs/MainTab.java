
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.Map;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.sensors.Vision;
import frc.robot.RobotManager;
import frc.robot.brains.ShufflerBrain;
import frc.robot.brains.VisionBrain;


// The Shuffleboard Main Tab
public class MainTab {

    // Tab, layout, and widget objects
    private ShuffleboardTab m_tab;
    private ComplexWidget m_autoCmdWidget;
    private SimpleWidget m_matchTimeWidget;
    private SimpleWidget m_frontLineDetectedWidget;
    private SimpleWidget m_gameModeWidget;
    private SimpleWidget m_climbModeWidget;

    // Constructor
    public MainTab() {
        ShuffleLogger.logTrivial("Constructing MainTab...");

        m_tab = Shuffleboard.getTab("Main");
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Match Time
        m_matchTimeWidget = m_tab.add("Match Time", ShufflerBrain.matchTimeDefault);
        ShufflerBrain.matchTimeEntry = m_matchTimeWidget.getEntry();

        // Front Line Detected
        m_frontLineDetectedWidget = m_tab.add("Front Line Detected", VisionBrain.frontLineDetectedDefault);
        VisionBrain.frontLineDetectedEntry = m_frontLineDetectedWidget.getEntry();

        m_gameModeWidget = m_tab.add("Game Mode", RobotManager.botGameMode.toString());
        m_climbModeWidget = m_tab.add("Climb Mode", RobotManager.botClimbMode.toString());
    }

    // Create all other Widgets
    public void initialize() {
        // Autonomous Command
        m_autoCmdWidget = m_tab.add("Auto Command", RobotManager.autoCommandChooser);
    }

    // Configure all Widgets
    public void configure() {
        m_autoCmdWidget.withPosition(0, 0);
        m_autoCmdWidget.withSize(2, 1);

        m_matchTimeWidget.withPosition(2, 0);
        m_matchTimeWidget.withWidget(BuiltInWidgets.kDial);
        m_matchTimeWidget.withProperties(Map.of("min", -1, "max", 135)); // this property setting isn't working

        m_frontLineDetectedWidget.withPosition(3, 0);

        m_gameModeWidget.withPosition(0, 2);
        m_climbModeWidget.withPosition(1, 2);
    }

    // This will be called in the robotPeriodic
    public void update() {
        // Match time
        DriverStation ds = DriverStation.getInstance();
        double matchTime = ds.getMatchTime();
        ShufflerBrain.matchTimeEntry.setDouble(matchTime);

        // Front Line Detector
        boolean frontLineDetected = Vision.frontLineDetected();
        VisionBrain.frontLineDetectedEntry.setBoolean(frontLineDetected);

        // updating the value of the game mode
        NetworkTableEntry gameModeEntry = m_gameModeWidget.getEntry();
        gameModeEntry.setString(RobotManager.botGameMode.toString());

        // updating the value of the climb mode
        NetworkTableEntry climbModeEntry = m_climbModeWidget.getEntry();
        climbModeEntry.setString(RobotManager.botClimbMode.toString());
    }

}
