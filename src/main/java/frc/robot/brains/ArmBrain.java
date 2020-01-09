
package frc.robot.brains;

import edu.wpi.first.networktables.NetworkTableEntry;

// This class contains all the shared NetworkTableEntries for the Arm Subsystem,
// their default values, and methods for retrieving their current values
public class ArmBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double armHAB2AngleDefault = 55;
    public static double armHAB3AngleDefault = 110;
    public static double armFullAngleDefault = 220;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static NetworkTableEntry armHAB2AngleEntry;
    public static NetworkTableEntry armHAB3AngleEntry;
    public static NetworkTableEntry armFullAngleEntry;

    //---------//
    // Setters //
    //---------//



    //---------//
    // Getters //
    //---------//

    // Subystems - Arm
    public static double getArmHAB2Angle() {
        return armHAB2AngleEntry.getDouble(armHAB2AngleDefault);
    }

    public static double getArmHAB3Angle() {
        return armHAB3AngleEntry.getDouble(armHAB3AngleDefault);
    }

    public static double getArmFullAngle() {
        return armFullAngleEntry.getDouble(armFullAngleDefault);
    }

}
