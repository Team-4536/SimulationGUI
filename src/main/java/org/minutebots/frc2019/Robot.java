package org.minutebots.frc2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;

import java.io.IOException;

import org.minutebots.frc2019.simulation.SimulationGUI;
import org.minutebots.frc2019.simulation.VirtualBot;

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
	int i = 0;

	private void simulationInit() {
	  if (simulation == null) simulation = new SimulationGUI("4536 FRC SimulatorGUI", new VirtualBot("Minibot", 4536, "differential"));
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
		simulation.enableFrame();
		simulation.getInstance();
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
		simulation.disableFrame();
	}

	@Override
	public void disabledPeriodic() {
	}
}
