package frc2019.robot.simulation;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.swing.ImageIcon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc2019.lib.SparkMAX;
import frc2019.lib.VirtualMotor;


/**
 * VirtualBot
 * 
 * This class is the heart of the simulation. 
 * It provides core methods for creating and obtaining virtusl robot attributes.
 * 
 * @author Ridley Nelson
 */

public class VirtualBot implements SimUtils {
    private double x = 100, y = 100, lastX = 100, lastY = 100;
    private int width, height, teamNumber;
    private double lastRotation, rotation = -90; // we set this so this is facing upwards
    private String drivetrain, teamName = "", robotName = "";
    private double speed;

    private InputHandler io;

    private ImageIcon icon;
    private Image image;
    private URL imageURL;
    private SimMotors motors = new SimMotors();

    /**
     * @param robotName - This is just the robot's name, it CAN be left empty.
     * @param teamNumber - This is your FRC team number, if you on't have one, just put in 0000.
     * @param driveType - This is a high demand, it is rewuired to make the robot work. It determines the drivetrain.
     */
    public VirtualBot(String robotName, int teamNumber, String driveType) {
        setRobotName(robotName);
        setTeamNumber(teamNumber);
        setDrivetrain(driveType);

        // Vertigo: using SPARKS: 8 meters/second
        try {
            motors.addMotor(new VirtualMotor("Virtual Motor 1", 1)); // top left
            motors.addMotor(new VirtualMotor("Virtual Motor 2", 2)); // top right
            motors.addMotor(new VirtualMotor("Virtual Motor 3", 3)); // bottom right
            motors.addMotor(new VirtualMotor("Virtual Motor 4", 4)); // bottom left

            System.out.println("Motor list size: " + motors.size());
        } catch(NoSuchElementException e) { e.printStackTrace(); }
    }

    public VirtualBot(String robotName, int teamNumber, String driveType, Joystick joystick) {
        new VirtualBot(robotName, teamNumber, driveType);

        io = new InputHandler(joystick);
        
    }

    public VirtualBot(String robotName, int teamNumber, String driveType, XboxController controller) {
        new VirtualBot(robotName, teamNumber, driveType);

        io = new InputHandler(controller);
    }

    private void loadImage() {
        if (imageURL != null) icon = new ImageIcon(imageURL);
        // The next two lines scale down the image
        image = icon.getImage().getScaledInstance(icon.getImage().getWidth(null) / 6, icon.getImage().getHeight(null) / 6,  Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        // we set the image to the image
        image = icon.getImage();

        // now we set the width and height of the image
        height = image.getHeight(null);
        width = image.getWidth(null);
    }
    
    public Image getImage() { return image; }

    /**
     * Updates the Virtual Robot's position using 2 or 3 inputs.
     * @param inputX - This is the x input, if you are using a differential drivetrain, you only use this and the {@code inputZ}
     * @param inputY - This either moves the robot side to side or does nothing depending on what mode you're in.
     * @param inputZ - This is used to rotate the robot.
     */
    public void setLocation(final int inputX, final int inputY, final int inputZ) {
        if (drivetrain == "t") {
            final double radians = Math.toRadians(rotation);
            final double dx = Math.cos(radians) * inputX;
            final double dy = Math.sin(radians) * inputX;
            lastX = x; lastY = y;
            x += dx;
            y += dy;
            rotate(inputZ);
        }
        if (drivetrain == "m") {
            lastX = x; lastY = y;
            x += (inputX*speed);
            y += (inputY*speed);
            rotate(inputZ);
        }
        if (drivetrain == "2x2") {
            lastX = x; lastY = y;
            x += (inputX*speed);
            y += (inputY*speed);
            rotate(inputZ);
        }
        if (drivetrain == "d") {
            final double radians = Math.toRadians(rotation);
            final double dx = Math.cos(radians) * inputX;
            final double dy = Math.sin(radians) * inputX;
            lastX = x; lastY = y;
            x += dx;
            y += dy;
            rotate(inputZ);
        }
    }

    /**
     * Updates the robot's rotation given the input rotation.
     * @param input - {@code double} the amount the robot should turn (clockwise).
     * @since v1
     */
    public void rotate(final double input) {
        rotation += input;
    }
    
    /**
     * Returns the current angle in which the Virtual Robot is facing.
     * @return {@code double} the most recent rotation.
     */
    public double getAngle() { return rotation; }
    
    public void setSpeed(final int newSpeed) { speed = newSpeed; }

    /**
     * Returns the current x position of the Virtual Robot.
     * @return {@code double} X Position
     * @since v1
     */
    public double getX() { return x; }
    
    /**
     * Returns the current y position of the Virtual Robot.
     * @return {@code double} Y Position
     * @since v1
     */
    public double getY() { return y; }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setDrivetrain(final String driveType) {
        drivetrain = driveType;
        
        if (drivetrain == "d") imageURL = getClass().getResource("drivetrain-img-dict/New/other.jpeg"); 
        else if (driveType == "t") imageURL = getClass().getResource("drivetrain-img-dict/New/tank.jpeg");
        else if (driveType == "m") imageURL = getClass().getResource("drivetrain-img-dict/New/mecanum.jpeg");
        else if (driveType == "2x2") imageURL = getClass().getResource("drivetrain-img-dict/New/2x2.jpeg"); 
        else {
            System.err.println("Please choose a drive type. Your current one is none due to fallbacks.");
            imageURL = getClass().getResource("drivetrain-img-dict/New/drivetrain.jpeg");
        }
        loadImage();
    }

    public String getDrivetrain() {return drivetrain;}
    public void setTeamNumber(final int newTeamNumber) {teamNumber = newTeamNumber;}
    public int getTeamNumber() {return teamNumber;}
    public void setTeamName(final String name) {teamName = name;}
    public String getTeamName() {return teamName;}
    public void setRobotName(final String newName) {robotName = newName;}
    public String getRobotName() {return robotName;}

    // test thing to interact with virtual motors
    public void horizontallyTurnMotors(int input) {
        for (int m = 0; m <= (motors.size() - 1); m++) {
            if (motors.get(m) instanceof VirtualMotor) {
                VirtualMotor motor = (VirtualMotor) motors.get(m);
                System.out.println("Max motor acc: " + -motor.getMaximumAcceleration() + " " + motor.get() + " " + input);
                String print = "Spinning Motors: ";

                if (input < 0 && motor.get() == motor.getMaximumAcceleration()) {
                    if (drivetrain == "2x2" || drivetrain == "d") {
                        if (motor.getPort() == 1 || motor.getPort() == 3) {
                            motor.set(motor.get() + input);
                            print += "| [1,3] (+): "+motor.getName()+": "+motor.get()+" |";
                        }
                        if (motor.getPort() == 2 || motor.getPort() == 4) {
                            motor.set(motor.get() - input);
                            print += "| [2,4] (-): "+motor.getName()+": "+motor.get()+" |";
                        }
                    } else if (drivetrain == "t") {
                        if (motor.getPort() == 1 || motor.getPort() == 2 || motor.getPort() == 3) {
                            motor.set(motor.get() + input);
                            print += "| [1,3] (+): "+motor.getName()+": "+motor.get()+" |";
                        }
                        if (motor.getPort() == 4 || motor.getPort() == 5 || motor.getPort() == 6) {
                            motor.set(motor.get() - input);
                            print += "| [2,4] (-): "+motor.getName()+": "+motor.get()+" |";
                        }
                    }
                } else {
                    if (drivetrain == "2x2" || drivetrain == "d") {
                        if (motor.getPort() == 1 && motor.getMaximumAcceleration() > motor.get() || motor.getPort() == 3 && motor.getMaximumAcceleration() > motor.get()) {
                            motor.set(motor.get() + input);
                            print += "| [1,3] (+): "+motor.getName()+": "+motor.get()+" |";
                        }
                        if (motor.getPort() == 2 && -motor.getMaximumAcceleration() < motor.get() || motor.getPort() == 4 && -motor.getMaximumAcceleration() < motor.get()) {
                            motor.set(motor.get() - input);
                            print += "| [2,4] (-): "+motor.getName()+": "+motor.get()+" |";
                        }
                    } else if (drivetrain == "t") {
                        if (motor.getPort() == 1 && motor.getMaximumAcceleration() > motor.get() || motor.getPort() == 2 && motor.getMaximumAcceleration() > motor.get() || motor.getPort() == 3 && motor.getMaximumAcceleration() > motor.get()) {
                            motor.set(motor.get() + input);
                            print += "| [1,3] (+): "+motor.getName()+": "+motor.get()+" |";
                        }
                        if (motor.getPort() == 4 && -motor.getMaximumAcceleration() < motor.get() || motor.getPort() == 5 && -motor.getMaximumAcceleration() < motor.get() || motor.getPort() == 6 && -motor.getMaximumAcceleration() < motor.get()) {
                            motor.set(motor.get() - input);
                            print += "| [2,4] (-): "+motor.getName()+": "+motor.get()+" |";
                        }
                    }
                        
                }
                System.out.println(print);
            } else if (motors.get(m) instanceof SparkMAX) {
                // TODO: fill in here
            }
        }
    }

    // Moving forward and Backwards
    public void verticallyTurnMotors(int input) {
        System.out.println(motors.size());
        for (int m = 0; m <= (motors.size() - 1); m++) {
            if (motors.get(m) instanceof VirtualMotor) {
                VirtualMotor motor = (VirtualMotor) motors.get(m);
                if (motor.getMaximumAcceleration() >= motor.get()) {
                    motor.set(motor.get() + input);
                    System.out.println("Spinning Motor: " + motor.getName() + ": " + motor.get());
                }
            } else if (motors.get(m) instanceof SparkMAX) {
                // TODO: fill in here
            }
        }
    }

    // Self Explanitory, TODO: Implement NEO Smart Motors
    public Map getVMotorData() {
        Map tempMotorArr = new Hashtable();
        for (int m = 0; m <= (motors.size() - 1); m++) {
            if (motors.get(m) instanceof VirtualMotor) {
                VirtualMotor motor = (VirtualMotor) motors.get(m);
                tempMotorArr.put(motor.getName(), motor.getPort() + ", " + motor.get());
            } else if (motors.get(m) instanceof SparkMAX) {
                // TODO: fill in here
            }
        }
        return tempMotorArr;
    }

    /**
     * KeyboardBinder
     * This void binds together controls, it replaces a USB controller for testing purposes.
     * 
     * @apiNote The {@code KeyEvent.VK_UP} and {@code KeyEvent.VK_DOWN} are flopped for some reason, don't know 
     * if this is supposed to be that way, but it works.
    */
    public void keyboardBinder(final KeyEvent e) {
        final int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            // rotate(-10);
            if (drivetrain == "d" || drivetrain == "t") setLocation(0, 0, -10);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(-5, 0, 0);
            horizontallyTurnMotors(-5);
        }

        if (key == KeyEvent.VK_RIGHT) {
            // rotate(10);
            if (drivetrain == "d" || drivetrain == "t") setLocation(0, 0, 10);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(5, 0, 0);
            horizontallyTurnMotors(5);
        }

        if (key == KeyEvent.VK_UP) {
            // move(5);
            if (drivetrain == "d" || drivetrain == "t") setLocation(5, 0, 0);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(0, -5, 0);
            verticallyTurnMotors(5);
        }

        if (key == KeyEvent.VK_DOWN) {
            // move(-5);
            if (drivetrain == "d" || drivetrain == "t") setLocation(-5, 0, 0);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(0, 5, 0);
            verticallyTurnMotors(-5);
        }
    }

    public void joystickInput(Joystick controlStick) {
        double controlStickX = controlStick.getX(), controlStickY = controlStick.getY();
        if (controlStickX < 0) {
            if (drivetrain == "d" || drivetrain == "t") setLocation(0, 0, (int)controlStickX * 2);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation((int)controlStickX, 0, 0);
        }
        if (controlStickX > 0) {
            if (drivetrain == "d" || drivetrain == "t") setLocation(0, 0, (int)controlStickX * 2);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation((int)controlStickX, 0, 0);
        }
        if (controlStickY < 0) {
            if (drivetrain == "d" || drivetrain == "t") setLocation((int)controlStickY, 0, 0);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(0, (int)controlStickY, 0);
        }
        if (controlStickY > 0) {
            if (drivetrain == "d" || drivetrain == "t") setLocation((int)controlStickY, 0, 0);
            if (drivetrain == "m" || drivetrain == "2x2") setLocation(0, (int)controlStickY, 0);
        }
    }
}