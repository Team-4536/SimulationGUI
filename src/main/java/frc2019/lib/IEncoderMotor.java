package frc2019.lib;

import edu.wpi.first.wpilibj.SpeedController;

public interface IEncoderMotor extends SpeedController{
    double getSpeed();
    double getDistance();
    void resetEncoder();
}