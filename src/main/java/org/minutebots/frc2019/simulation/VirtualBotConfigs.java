package org.minutebots.frc2019.simulation;

import java.awt.Image;
import javax.swing.ImageIcon;

public interface VirtualBotConfigs {
    public String getImagePath();
    public double getX();
    public double getY();
    public double getAngle();
    public double getWidth();
    public double getHeight();
    public Image getImage();
    public void rotate(double angle);
    public void setPosition(double x, double y);
    public void setPosition(double inputX, double inputY, double inputAngle);
    public void setSpeed(int newSpeed);
    public void setDrivetrain(String type);
    public String getDrivetrain();
    
    // MISC FUNCTIONS
    public void setTeamNumber(int teamNumber);
    public int getTeamNumber();
    public void setTeamName(String name);
    public String getTeamName();
}