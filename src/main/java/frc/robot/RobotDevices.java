
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;

// This class contains singleton instances of id mapped robot components, and utility methods.
public class RobotDevices {

    // Gyros
    public static final AHRS gyro = new AHRS(SPI.Port.kMXP);

    // Relays
    public static final Relay lighterRelay = new Relay(1);

    // Motor Controllers
    public static final WPI_TalonSRX talonSrxMecWheelFrontLeft = new WPI_TalonSRX(5); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearLeft = new WPI_TalonSRX(7); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelFrontRight = new WPI_TalonSRX(6); // 1 motor
    public static final WPI_TalonSRX talonSrxMecWheelRearRight = new WPI_TalonSRX(8); // 1 motor

    public static final WPI_TalonSRX talonSrxHatcher = new WPI_TalonSRX(9); // 1 motor
    public static final WPI_TalonSRX talonSrxBaller = new WPI_TalonSRX(10); // 1 motor
    public static final WPI_TalonSRX talonSrxLever = new WPI_TalonSRX(12);

    public static final WPI_TalonSRX talonSrxBackPulleyA = new WPI_TalonSRX(19); // 1 motor
    public static final WPI_TalonSRX talonSrxBackPulleyB = new WPI_TalonSRX(14); // 1 motor
    public static final WPI_TalonSRX talonSrxBackPulleyC = new WPI_TalonSRX(15); // 1 motor

    public static final WPI_TalonSRX talonSrxFrontPulleyA = new WPI_TalonSRX(1); // 1 motor
    public static final WPI_TalonSRX talonSrxFrontPulleyB = new WPI_TalonSRX(2); // 1 motor
    public static final WPI_TalonSRX talonSrxFrontPulleyC = new WPI_TalonSRX(13); // 1 motor

    // Drives
    public static MecanumDrive mecDrive = null;

    // Constructor
    public RobotDevices() {
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
