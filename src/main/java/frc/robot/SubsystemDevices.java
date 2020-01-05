
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Relay;

import frc.robot.consoles.Logger;

// This class contains singleton instances of id mapped subsystem components, and utility methods.
// IMPORTANT: It is imperative that ONLY subsystems control any interactive device.
// Also, only ONE subsystem should control any given device.
public class SubsystemDevices {

    // Relays
    public static final Relay relayLighter = new Relay(1);

    // Motor Controllers
    public static final WPI_TalonSRX talonSrxMecWheelFrontLeft = new WPI_TalonSRX(5); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearLeft = new WPI_TalonSRX(7); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelFrontRight = new WPI_TalonSRX(6); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearRight = new WPI_TalonSRX(8); // 1 motor

    public static final WPI_TalonSRX talonSrxHatcher = new WPI_TalonSRX(9); // 1 motor
    public static final WPI_TalonSRX talonSrxBaller = new WPI_TalonSRX(10); // 1 motor

    // Drives
    public static MecanumDrive mecDrive;

    // Intialize the subsystem devices
    public static void initializeDevices() {
        Logger.setup("Initializing SubsystemDevices...");

        // TODO: Investigate why these motor controllers have to be inverted. Are all TalonSRX Motor Controllers backwards?
        talonSrxMecWheelFrontLeft.setInverted(true);
        talonSrxMecWheelRearLeft.setInverted(true);
        talonSrxMecWheelFrontRight.setInverted(true);
        talonSrxMecWheelRearRight.setInverted(true);
        mecDrive = new MecanumDrive(talonSrxMecWheelFrontLeft,
                                    talonSrxMecWheelRearLeft,
                                    talonSrxMecWheelFrontRight,
                                    talonSrxMecWheelRearRight);
    }

    // Determines if the Talon SRX is connected
    public static boolean isConnected(WPI_TalonSRX talon) {
        int firmVer = talon.getFirmwareVersion();
        boolean connected = (firmVer != -1);
        return connected;
    }

}
