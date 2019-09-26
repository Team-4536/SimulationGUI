package org.minutebots.frc2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import org.minutebots.frc2019.simulation.SimulationGUI;

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
  SimulationGUI simulation = new SimulationGUI("4536 FRC SimulatorGUI");

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {    
    simulation.getInstance();
    simulation.enableFrame();
  }

  @Override
  public void teleopPeriodic() {
    simulation.rotate(joystick.getTwist());
    simulation.setPosition(0, joystick.getY()*4);
    System.out.println(joystick.getX() + " " + joystick.getY() + " " + joystick.getDirectionDegrees() + " " + joystick.getTwist());
    simulation.run();
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
