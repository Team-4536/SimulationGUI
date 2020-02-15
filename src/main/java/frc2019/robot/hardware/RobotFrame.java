package frc2019.robot.hardware;

import com.kauailabs.navx.frc.AHRS;
import frc2019.lib.IEncoderMotor;

public interface RobotFrame {
    IEncoderMotor getDrivetrainRightMotor();
    IEncoderMotor getDrivetrainLeftMotor();
    AHRS getDrivetrainNavX();
    RobotConstants getConstants();
}