
package frc.robot.helpers;

// The position values obtained from Xbox Thumbsticks
public class ThumbStickPosition {

    public double leftForwardBackPosition = 0; // y left Forward & Backward
    public double leftSideToSidePosition = 0; // x left Side to Side
    public double rightForwardBackPosition = 0; // y right Forward & Backward
    public double rightSideToSidePosition = 0; // x right Side to Side

    public ThumbStickPosition() {
    }

    public ThumbStickPosition(double leftForwardBack, double leftSideToSide, double rightForwardBack, double rightSideToSide) {
        leftForwardBackPosition = leftForwardBack;
        leftSideToSidePosition = leftSideToSide;
        rightSideToSidePosition = rightForwardBack;
        rightSideToSidePosition = rightSideToSide;
    }

}
