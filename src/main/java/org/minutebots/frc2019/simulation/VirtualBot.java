package org.minutebots.frc2019.simulation;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * VirtualBot
 * 
 * This class is the heart of the simulation. 
 * It provides core methods for creating and obtaining virtusl robot attributes.
 * 
 * @author Ridley Nelson
 */

public class VirtualBot implements SimUtils {
    private int x = 40;
    private int y = 60;
    private int width;
    private int height;
    private double rotation = -90;
    private String drivetrain = "";
    private double speed;

    private ImageIcon icon;
    private Image image;
    private URL imageURL;

    // Team and Misc attributes
    private int teamNumber;
    private String teamName = "";
    private String robotName = "";

    /**
     * @param robotName - This is just the robot's name, it CAN be left empty.
     * @param teamNumber - This is your FRC team number, if you on't have one, just put in 0000.
     * @param driveType - This is a high demand, it is rewuired to make the robot work. It determines the drivetrain.
     */
    public VirtualBot(final String robotName, final int teamNumber, final String driveType) {
        
        setRobotName(robotName);
        setTeamNumber(teamNumber);

        if (driveType.equals("d")) {
            imageURL = getClass().getResource("drivetrain-img-dict/New/other.jpeg"); 
            setDrivetrain("d");
        } else if (driveType == "t") {
            imageURL = getClass().getResource("drivetrain-img-dict/New/tank.jpeg"); 
            setDrivetrain("t");
        } else if (driveType == "o") {
            imageURL = getClass().getResource("drivetrain-img-dict/New/hdrive.jpeg"); 
            setDrivetrain("o");
        } else {
            System.out.println("Please choose a drive type. Your current one is none due to fallbacks.");
            imageURL = getClass().getResource("drivetrain-img-dict/New/drivetrain.jpeg");
            setDrivetrain("d");
        }

        loadImage();
    }

    private void loadImage() {
        if (imageURL != null) icon = new ImageIcon(imageURL);
        // The next two lines scale down the image
        image = icon.getImage().getScaledInstance(icon.getImage().getWidth(null) / 3, icon.getImage().getHeight(null) / 3,  Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        // we set the image to the image
        image = icon.getImage();

        // now we set the width and height of the image
        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public Image getImage() {return image;}

    /**
     * Updates the Virtual Robot's position using 2 or 3 inputs.
     * @param inputX - This is the x input, if you are using a differential drivetrain, you only use this and the {@code inputZ}
     * @param inputY - This either moves the robot side to side or does nothing depending on what mode you're in.
     * @param inputZ - This is used to rotate the robot.
     */
    public void setLocation(final int inputX, final int inputY, final int inputZ) {
        if (drivetrain == "t") {
            x += (inputX*speed);
            y += (inputY*speed);
            rotate(inputZ);
        }
        if (drivetrain == "o") {
            x += (inputX*speed);
            y += (inputY*speed);
            rotate(inputZ);
        }
        if (drivetrain == "d") {
            final double radians = Math.toRadians(rotation);
            final int dx = (int) Math.round(Math.cos(radians) * inputX);
            final int dy = (int) Math.round(Math.sin(radians) * inputX);
            x += dx;
            y += dy;
            rotate(inputZ);
        }
    }

    public void rotate(final double inputAngle) {rotation += inputAngle;}
    
    /**
     * Returns the current angle in which the Virtual Robot is facing.
     * @return {@code double} rotation (probably in radians)
     */
    public double getAngle() {return rotation;}
    
    public void setSpeed(final int newSpeed) {speed = newSpeed;}

    /**
     * Returns the current x position of the Virtual Robot.
     * @return {@code int} X Position
     */
    public int getX() {return x;}
    /**
     * Returns the current y position of the Virtual Robot.
     * @return {@code int} Y Position
     */
    public int getY() {return y;}
    
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void setDrivetrain(final String driveType) {drivetrain = driveType;}
    public String getDrivetrain() {return drivetrain;}
    public void setTeamNumber(final int newTeamNumber) {teamNumber = newTeamNumber;}
    public int getTeamNumber() {return teamNumber;}
    public void setTeamName(final String name) {teamName = name;}
    public String getTeamName() {return teamName;}
    public void setRobotName(final String newName) {robotName = newName;}
    public String getRobotName() {return robotName;}

    public void keyboardBinder(final KeyEvent e) {
        /**
         * KeyboardBinder
         * This void binds together controls, it replaces a USB controller for testing purposes.
         * 
         * Issues: the KeyEvent.VK_UP and KeyEvent.VK_DOWN are flip flopped for some reason, don't know 
         * if this is supposed to be that way, but it works.
        */

        final int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            // rotate(-10);
            if (drivetrain == "d") setLocation(0, 0, -10);
            else setLocation(-5, 0, 0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            // rotate(10);
            if (drivetrain == "d") setLocation(0, 0, 10);
            else setLocation(5, 0, 0);
        }

        if (key == KeyEvent.VK_UP) {
            // move(5);
            if (drivetrain == "d") setLocation(5, 0, 0);
            else setLocation(0, -5, 0);
        }

        if (key == KeyEvent.VK_DOWN) {
            // move(-5);
            if (drivetrain == "d") setLocation(-5, 0, 0);
            else setLocation(0, 5, 0);
        }
    }
}