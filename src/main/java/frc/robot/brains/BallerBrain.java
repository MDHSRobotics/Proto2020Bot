
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

// This class contains all the shared NetworkTableEntries for the Baller Subsystem,
// their default values, and methods for retrieving their current values
public class BallerBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double ballTossAngleDefault = 40;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry ballTossAngleEntry;

    //---------//
    // Setters //
    //---------//



    //---------//
    // Getters //
    //---------//

    public static double getBallTossAngle() {
        return ballTossAngleEntry.getDouble(ballTossAngleDefault);
    }

}
