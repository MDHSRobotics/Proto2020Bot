
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Cameras;

// This class contains singleton instances of id mapped sensors.
public class RobotSensors {

    // Cameras
    public static UsbCamera cameraSight;

    // Gyros
    public static AHRS gyro;

    public static void initializeSensors() {
        Logger.setup("Initializing RobotSensors...");

        boolean cam0connected = Cameras.testConnection(0);
        if (cam0connected)
            cameraSight = Cameras.captureCamera(0);

        gyro = new AHRS(SPI.Port.kMXP);
        if (!gyro.isConnected())
            Logger.error("Gyro not connected!");
    }

}
