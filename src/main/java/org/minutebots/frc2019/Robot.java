package org.minutebots.frc2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;

import org.minutebots.frc2019.simulation.SimulationGUI;
import org.minutebots.frc2019.simulation.VirtualBot;
import java.awt.EventQueue;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	/**
	* This function is run when the robot is first started up and should be used
	* for any initialization code.
	*/

	Joystick joystick = new Joystick(0);
	SimulationGUI simulation;

	private void simulationInit() {
		EventQueue.invokeLater(() -> {
			VirtualBot minibot = new VirtualBot("Minibot", 4536, "d");
			minibot.setSpeed(1);

			final SimulationGUI simulation = new SimulationGUI(minibot);
			simulation.setVisible(true);
		});
	}

	@Override
	public void robotInit() {
		simulationInit();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			VirtualBot minibot = new VirtualBot("Minibot", 4536, "d");
			minibot.setSpeed(1);

			final SimulationGUI simulation = new SimulationGUI(minibot);
			simulation.setVisible(true);
		});
	}
}
