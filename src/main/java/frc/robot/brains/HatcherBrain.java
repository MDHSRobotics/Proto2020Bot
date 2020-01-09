
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

// This class contains all the shared NetworkTableEntries for the Hatcher Subsystem,
// their default values, and methods for retrieving their current values
public class HatcherBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double hatchOpenAngleDefault = 45;
    public static double hatchCloseAngleDefault = 75;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry hatchOpenAngleEntry;
    public static NetworkTableEntry hatchCloseAngleEntry;

    //---------//
    // Setters //
    //---------//



    //---------//
    // Getters //
    //---------//

    public static double getHatchOpenAngle() {
        return hatchOpenAngleEntry.getDouble(hatchOpenAngleDefault);
    }

    public static double getHatchCloseAngle() {
        return hatchCloseAngleEntry.getDouble(hatchCloseAngleDefault);
    }

}
