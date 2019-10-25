package org.minutebots.frc2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import java.io.IOException;

import org.minutebots.frc2019.simulation.SimulationGUI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    // create the simulation object
    private SimulationGUI simulation;

    // get the 2 sparks
    private Spark backLeftWheel = new Spark(0),
        backRightWheel = new Spark(1);
    
    // get the joystick
    private Joystick joystick = new Joystick(0);

    // set the drive
    public DifferentialDrive differential = new DifferentialDrive(backLeftWheel, backRightWheel);

    private void simulationInit() {
        if (simulation == null) simulation = new SimulationGUI("4536 FRC SimulatorGUI", "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_drivetrain.jpeg");
    }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
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
        simulationInit();
        simulation.enable();

        // we initialize the camera server
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(600, 400);
    }

    @Override
    public void teleopPeriodic() {
        // we send the input to the robot
        differential.arcadeDrive(joystick.getY(), joystick.getX());

        // we send input to the simulator
        new Thread(() -> {
            simulationInit();
            simulation.getInstance();
            simulation.setPosition(joystick.getX(), joystick.getY(), joystick.getTwist());
            simulation.refresh();
        }).start();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {
        simulationInit();
        simulation.disable();
    }

    @Override
    public void disabledPeriodic() {
    }
}
