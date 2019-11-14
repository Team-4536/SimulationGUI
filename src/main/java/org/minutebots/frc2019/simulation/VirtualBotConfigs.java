package org.minutebots.frc2019.simulation;

public interface VirtualBotConfigs {
    public String getImagePath();
    public double getX();
    public double getY();
<<<<<<< HEAD
    public double getCenterX();
    public double getCenterY();
    public void setCenterX(double x);
    public void setCenterY(double y);
=======
>>>>>>> 2e15b62bafcde326503d03747b977d75912f0947
    public double getAngle();
    public void rotate(double angle);
    public void setPosition(double x, double y);
    public void setPosition(double inputX, double inputY, double angle);
    public void setSpeed(int newSpeed);
    public void setDrivetrain(String type);
    public String getDrivetrain();
    
    // MISC FUNCTIONS
<<<<<<< HEAD
    public void setTeamNumber(int teamNumber);
    public int getTeamNumber();
    public void setTeamName(String name);
    public String getTeamName();
}
=======
    public void setTeamNumber(String teamNumber);
    public String getTeamNumber();
    public void setTeamName(String name);
    public String getTeamName();
}
>>>>>>> 2e15b62bafcde326503d03747b977d75912f0947
