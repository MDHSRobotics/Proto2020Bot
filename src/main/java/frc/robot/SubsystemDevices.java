
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
    public static final Relay relayLighter = new Relay(1);

    // Motor Controllers
    public static final WPI_TalonSRX talonSrxDiffWheelFrontLeft = new WPI_TalonSRX(21); // 1 motor
    public static final WPI_TalonSRX talonSrxDiffWheelRearLeft = new WPI_TalonSRX(22); // 1 motor
    public static final WPI_TalonSRX talonSrxDiffWheelFrontRight = new WPI_TalonSRX(23); // 1 motor
    public static final WPI_TalonSRX talonSrxDiffWheelRearRight = new WPI_TalonSRX(24); // 1 motor

    public static final WPI_TalonSRX talonSrxMecWheelFrontLeft = new WPI_TalonSRX(1); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearLeft = new WPI_TalonSRX(2); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelFrontRight = new WPI_TalonSRX(3); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearRight = new WPI_TalonSRX(4); // 1 motor

    public static final WPI_TalonSRX talonSrxOmniWheelFrontLeft = new WPI_TalonSRX(5); // 1 motor
    public static final WPI_TalonSRX talonSrxOmniWheelRearLeft = new WPI_TalonSRX(17); // 1 motor
    public static final WPI_TalonSRX talonSrxOmniWheelFrontRight = new WPI_TalonSRX(6); // 1 motor
    public static final WPI_TalonSRX talonSrxOmniWheelRearRight = new WPI_TalonSRX(8); // 1 motor
    public static final WPI_TalonSRX talonSrxOmniWheelFront = new WPI_TalonSRX(7); // 1 motor
    public static final WPI_TalonSRX talonSrxOmniWheelRear = new WPI_TalonSRX(18); // 1 motor

    // Drives
    public static DifferentialDrive diffDrive;
    public static MecanumDrive mecDrive;
    public static DifferentialDrive omniDrive;

    // Intialize the subsystem devices
    public static void initializeDevices() {
        Logger.setup("Initializing SubsystemDevices...");

        // Differential Drive
        talonSrxDiffWheelFrontLeft.setInverted(true);
        talonSrxDiffWheelFrontRight.setInverted(true);
        talonSrxDiffWheelRearLeft.setInverted(true);
        talonSrxDiffWheelRearRight.setInverted(true);
        diffDrive = new DifferentialDrive(talonSrxDiffWheelFrontLeft, talonSrxDiffWheelFrontRight);
        talonSrxDiffWheelRearLeft.follow(talonSrxDiffWheelFrontLeft);
        talonSrxDiffWheelRearRight.follow(talonSrxDiffWheelFrontRight);

        // Mecanum Drive
        // TODO: Investigate why these motor controllers have to be inverted. Are all TalonSRX Motor Controllers backwards?
        talonSrxMecWheelFrontLeft.setInverted(true);
        talonSrxMecWheelRearLeft.setInverted(true);
        talonSrxMecWheelFrontRight.setInverted(true);
        talonSrxMecWheelRearRight.setInverted(true);
        mecDrive = new MecanumDrive(talonSrxMecWheelFrontLeft,
                                    talonSrxMecWheelRearLeft,
                                    talonSrxMecWheelFrontRight,
                                    talonSrxMecWheelRearRight);

        // Omni Drive
        talonSrxOmniWheelRearLeft.follow(talonSrxOmniWheelFrontLeft);
        talonSrxOmniWheelRearRight.follow(talonSrxOmniWheelFrontRight);
        talonSrxOmniWheelRear.follow(talonSrxOmniWheelFront);
        omniDrive = new DifferentialDrive(talonSrxOmniWheelFrontLeft,
                                          talonSrxOmniWheelFrontRight);
    }

    // Determines if the Talon SRX is connected
    public static boolean isConnected(WPI_TalonSRX talon) {
        int firmVer = talon.getFirmwareVersion();
        boolean connected = (firmVer != -1);
        return connected;
    }

}
