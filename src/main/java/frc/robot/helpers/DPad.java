
package frc.robot.helpers;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.GenericHID;

public class DPad extends Button {

    public GenericHID device;

    public DPad(final GenericHID humanInterfaceDevice) {
        this.device = humanInterfaceDevice;
    }

    @Override
    public boolean get() {
        final int angle = device.getPOV(0);
        return (angle != -1);
    }

}
