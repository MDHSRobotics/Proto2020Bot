
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Relay;

import frc.robot.consoles.Logger;

// This class contains singleton instances of id mapped subsystem components, and utility methods.
// IMPORTANT: It is imperative that ONLY subsystems control any interactive device.
// Also, only ONE subsystem should control any given device.
public class SubsystemDevices {

    // Relays
    public static Relay relayLighter = new Relay(1);

    // Motor Controllers
    public static WPI_TalonSRX talonSrxDiffWheelFrontLeft = new WPI_TalonSRX(21); // 1 motor
    public static WPI_TalonSRX talonSrxDiffWheelRearLeft = new WPI_TalonSRX(22); // 1 motor
    public static WPI_TalonSRX talonSrxDiffWheelFrontRight = new WPI_TalonSRX(23); // 1 motor
    public static WPI_TalonSRX talonSrxDiffWheelRearRight = new WPI_TalonSRX(24); // 1 motor

    public static WPI_TalonSRX talonSrxMecWheelFrontLeft = new WPI_TalonSRX(1); // 1 motor
    public static WPI_TalonSRX talonSrxMecWheelRearLeft = new WPI_TalonSRX(2); // 1 motor
    public static WPI_TalonSRX talonSrxMecWheelFrontRight = new WPI_TalonSRX(3); // 1 motor
    public static WPI_TalonSRX talonSrxMecWheelRearRight = new WPI_TalonSRX(4); // 1 motor

    public static WPI_TalonSRX talonSrxOmniWheelFrontLeft = new WPI_TalonSRX(5); // 1 motor
    public static WPI_TalonSRX talonSrxOmniWheelRearLeft = new WPI_TalonSRX(17); // 1 motor
    public static WPI_TalonSRX talonSrxOmniWheelFrontRight = new WPI_TalonSRX(6); // 1 motor
    public static WPI_TalonSRX talonSrxOmniWheelRearRight = new WPI_TalonSRX(8); // 1 motor
    public static WPI_TalonSRX talonSrxOmniWheelFront = new WPI_TalonSRX(7); // 1 motor
    public static WPI_TalonSRX talonSrxOmniWheelRear = new WPI_TalonSRX(18); // 1 motor

    public static WPI_TalonSRX talonSrxHatcher = new WPI_TalonSRX(90); // 1 motor

    // Drives
    public static DifferentialDrive diffDrive;
    public static MecanumDrive mecDrive;
    public static DifferentialDrive omniDrive;

    // Intialize the subsystem devices
    public static void initializeDevices() {
        Logger.setup("Initializing SubsystemDevices...");

        initDiffDriverDevices();
        initMecDriverDevices();
        initOmniDriverDevices();
        initHatcherDevices();
    }

    // Differential Drive
    private static void initDiffDriverDevices() {
        boolean talonSrxDiffWheelFrontLeftIsConnected = isConnected(talonSrxDiffWheelFrontLeft);
        boolean talonSrxDiffWheelFrontRightIsConnected = isConnected(talonSrxDiffWheelFrontRight);
        boolean talonSrxDiffWheelRearLeftIsConnected = isConnected(talonSrxDiffWheelRearLeft);
        boolean talonSrxDiffWheelRearRightIsConnected = isConnected(talonSrxDiffWheelRearRight);

        boolean talonsAreConnected = true;
        if (!talonSrxDiffWheelFrontLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("DiffWheelFrontLeft talon is not connected!");
        }
        if (!talonSrxDiffWheelFrontRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("DiffWheelFrontRight talon is not connected!");
        }
        if (!talonSrxDiffWheelRearLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("DiffWheelRearLeft talon is not connected!");
        }
        if (!talonSrxDiffWheelRearRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("DiffWheelRearRight talon is not connected!");
        }

        if (!talonsAreConnected) {
            Logger.error("DiffDriver devices not all connected! Disabling...");
            talonSrxDiffWheelFrontLeft = null;
            talonSrxDiffWheelFrontRight = null;
            talonSrxDiffWheelRearLeft = null;
            talonSrxDiffWheelRearRight = null;
        } else {
            diffDrive = new DifferentialDrive(talonSrxDiffWheelFrontLeft, talonSrxDiffWheelFrontRight);
        }
    }

    // Mecanum Drive
    private static void initMecDriverDevices() {
        boolean talonSrxMecWheelFrontLeftIsConnected = isConnected(talonSrxMecWheelFrontLeft);
        boolean talonSrxMecWheelFrontRightIsConnected = isConnected(talonSrxMecWheelFrontRight);
        boolean talonSrxMecWheelRearLeftIsConnected = isConnected(talonSrxMecWheelRearLeft);
        boolean talonSrxMecWheelRearRightIsConnected = isConnected(talonSrxMecWheelRearRight);

        boolean talonsAreConnected = true;
        if (!talonSrxMecWheelFrontLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("MecWheelFrontLeft talon is not connected!");
        }
        if (!talonSrxMecWheelFrontRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("MecWheelFrontRight talon is not connected!");
        }
        if (!talonSrxMecWheelRearLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("MecWheelRearLeft talon is not connected!");
        }
        if (!talonSrxMecWheelRearRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("MecWheelRearRight talon is not connected!");
        }

        if (!talonsAreConnected) {
            Logger.error("MecDriver devices not all connected! Disabling...");
            talonSrxMecWheelFrontLeft = null;
            talonSrxMecWheelRearLeft = null;
            talonSrxMecWheelFrontRight = null;
            talonSrxMecWheelRearRight = null;
        } else {
            mecDrive = new MecanumDrive(talonSrxMecWheelFrontLeft,
                                        talonSrxMecWheelRearLeft,
                                        talonSrxMecWheelFrontRight,
                                        talonSrxMecWheelRearRight);
        }
    }

    // Omni Drive
    private static void initOmniDriverDevices() {
        boolean talonSrxOmniWheelFrontLeftIsConnected = isConnected(talonSrxOmniWheelFrontLeft);
        boolean talonSrxOmniWheelFrontRightIsConnected = isConnected(talonSrxOmniWheelFrontRight);
        boolean talonSrxOmniWheelRearLeftIsConnected = isConnected(talonSrxOmniWheelRearLeft);
        boolean talonSrxOmniWheelRearRightIsConnected = isConnected(talonSrxOmniWheelRearRight);
        boolean talonSrxOmniWheelFrontIsConnected = isConnected(talonSrxOmniWheelFront);
        boolean talonSrxOmniWheelRearIsConnected = isConnected(talonSrxOmniWheelRear);

        boolean talonsAreConnected = true;
        if (!talonSrxOmniWheelFrontLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelFrontLeft talon is not connected!");
        }
        if (!talonSrxOmniWheelFrontRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelFrontRight talon is not connected!");
        }
        if (!talonSrxOmniWheelRearLeftIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelRearLeft talon is not connected!");
        }
        if (!talonSrxOmniWheelRearRightIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelRearRight talon is not connected!");
        }
        if (!talonSrxOmniWheelFrontIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelFront talon is not connected!");
        }
        if (!talonSrxOmniWheelRearIsConnected) {
            talonsAreConnected = false;
            Logger.error("OmniWheelRear talon is not connected!");
        }

        if (!talonsAreConnected) {
            Logger.error("OmniDriver devices not all connected! Disabling...");
            talonSrxOmniWheelFrontLeft = null;
            talonSrxOmniWheelFrontRight = null;
            talonSrxOmniWheelRearLeft = null;
            talonSrxOmniWheelRearRight = null;
            talonSrxOmniWheelFront = null;
            talonSrxOmniWheelRear = null;
        } else {
            omniDrive = new DifferentialDrive(talonSrxOmniWheelFrontLeft,
                                              talonSrxOmniWheelFrontRight);
        }
    }

    // Hatcher
    private static void initHatcherDevices() {
        boolean talonSrxHatcherIsConnected = isConnected(talonSrxHatcher);

        if (!talonSrxHatcherIsConnected) {
            talonSrxHatcher = null;
            Logger.error("Hatcher talon is not connected! Disabling...");
        }
    }

    // Determines if the Talon SRX is connected
    public static boolean isConnected(WPI_TalonSRX talon) {
        int firmVer = talon.getFirmwareVersion();
        boolean connected = (firmVer != -1);
        return connected;
    }

}
