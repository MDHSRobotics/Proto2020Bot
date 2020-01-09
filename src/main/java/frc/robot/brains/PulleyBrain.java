
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

// This class contains all the shared NetworkTableEntries for the Pulley Subsystem,
// their default values, and methods for retrieving their current values
public class PulleyBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double pulleyHAB2DistanceDefault = 120;
    public static double pulleyHAB3DistanceDefault = 240;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry pulleyHAB2DistanceEntry;
    public static NetworkTableEntry pulleyHAB3DistanceEntry;

    //---------//
    // Setters //
    //---------//



    //---------//
    // Getters //
    //---------//

    public static double getPulleyHAB2Distance() {
        return pulleyHAB2DistanceEntry.getDouble(pulleyHAB2DistanceDefault);
    }

    public static double getPulleyHAB3Distance() {
        return pulleyHAB3DistanceEntry.getDouble(pulleyHAB3DistanceDefault);
    }

}
