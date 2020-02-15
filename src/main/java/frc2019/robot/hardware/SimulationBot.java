package frc2019.robot.hardware;

import com.kauailabs.navx.frc.AHRS;

import frc2019.lib.IEncoderMotor;

public class SimulationBot implements RobotFrame {

    @Override
    public IEncoderMotor getDrivetrainRightMotor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IEncoderMotor getDrivetrainLeftMotor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AHRS getDrivetrainNavX() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public RobotConstants getConstants() {
		// TODO Auto-generated method stub
		return null;
    }
}