package frc2019.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc2019.robot.hardware.RobotFrame;
import frc2019.robot.hardware.SimulationBot;
import frc2019.robot.simulation.SimulationOS;

public class RobotContainer {
    private final Joystick k_stick = new Joystick(0);

    frc2019.robot.simulation.SimulationGUI simulation;

    public final RobotFrame k_robotHardware = new SimulationBot();

    public RobotContainer() {
        configureSimulation();
    }

    public void configureSimulation() {
        SimulationOS simOS = new SimulationOS("mac", true); // replace "mac" with "windows" for Windows 10
        simulation = simOS.getSimulationInstance();
    }

    public Joystick getJoystick() {
        return k_stick;
    }
}