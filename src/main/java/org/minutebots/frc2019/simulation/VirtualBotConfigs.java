package org.minutebots.frc2019.simulation;

public interface VirtualBotConfigs {
    public String getImagePath();
    public double getX();
    public double getY();
    public double getAngle();
    public void rotate(double angle);
    public void setPosition(double x, double y);
    public void setPosition(double inputX, double inputY, double angle);
    public void setSpeed(int newSpeed);
    public void setDrivetrain(String type);
    public String getDrivetrain();
    
    // MISC FUNCTIONS
    public void setTeamNumber(String teamNumber);
    public String getTeamNumber();
    public void setTeamName(String name);
    public String getTeamName();
}
