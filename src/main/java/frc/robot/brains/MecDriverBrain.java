
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

import frc.robot.subsystems.MecDriver.DriveOrientation;

// This class contains all the shared NetworkTableEntries for the MecDriver Subsystem,
// their default values, and methods for retrieving their current values
public class MecDriverBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static DriveOrientation driveOrientationDefault = DriveOrientation.ROBOT;
    public static double alignFrontMagnitudeDefault = .5;
    public static double alignSideMagnitudeDefault = .5;
    public static double alignZSensitivityDefault = .7;
    public static double alignZSpeedMinimumDefault = .25;
    public static double alignZToleranceDefault = 10;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry driveOrientationEntry;
    public static NetworkTableEntry alignFrontMagnitudeEntry;
    public static NetworkTableEntry alignSideMagnitudeEntry;
    public static NetworkTableEntry alignZSensitivityEntry;
    public static NetworkTableEntry alignZSpeedMinimumEntry;
    public static NetworkTableEntry alignZToleranceEntry;

    //---------//
    // Setters //
    //---------//

    // Subsystems - MecDriver
    public static void setDriveOrientation(DriveOrientation orientation) {
        String value = orientation.toString();
        driveOrientationEntry.setValue(value);
    }

    //---------//
    // Getters //
    //---------//

    public static DriveOrientation getDriveOrientation() {
        String defaultString = driveOrientationDefault.toString();
        String orientationString = driveOrientationEntry.getString(defaultString);
        return DriveOrientation.valueOf(orientationString);
    }

    public static double getAlignFrontMagnitude() {
        return alignFrontMagnitudeEntry.getDouble(alignFrontMagnitudeDefault);
    }

    public static double getAlignSideMagnitude() {
        return alignSideMagnitudeEntry.getDouble(alignSideMagnitudeDefault);
    }

    public static double getAlignZSensitivity() {
        return alignZSensitivityEntry.getDouble(alignZSensitivityDefault);
    }

    public static double getAlignZSpeedMinimum() {
        return alignZSpeedMinimumEntry.getDouble(alignZSpeedMinimumDefault);
    }

    public static double getAlignZTolerance() {
        return alignZToleranceEntry.getDouble(alignZToleranceDefault);
    }

}
