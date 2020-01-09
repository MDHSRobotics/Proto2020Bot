
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

// This class contains all the shared NetworkTableEntries for the Sight Camera,
// their default values, and methods for retrieving their current values
public class SightBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double brightnessDefault = 40;
    public static double exposureDefault = 30;
    public static double whiteBalanceDefault = 4500;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry brightnessEntry;
    public static NetworkTableEntry exposureEntry;
    public static NetworkTableEntry whiteBalanceEntry;

    //---------//
    // Setters //
    //---------//



    //---------//
    // Getters //
    //---------//

    public static double getBrightness() {
        return brightnessEntry.getDouble(brightnessDefault);
    }

    public static double getExposure() {
        return exposureEntry.getDouble(exposureDefault);
    }

    public static double getWhiteBalance() {
        return whiteBalanceEntry.getDouble(whiteBalanceDefault);
    }

}
