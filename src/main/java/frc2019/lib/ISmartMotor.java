package frc2019.lib;

import edu.wpi.first.wpilibj.SpeedController;

public interface ISmartMotor extends SpeedController{
    void setVolt(double i);
    void setSpeed(double i);
    double getSpeed();
    double getDistance();
    double getSetpoint();
    void resetEncoder();
}