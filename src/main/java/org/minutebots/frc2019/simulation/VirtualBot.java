package org.minutebots.frc2019.simulation;

public class VirtualBot implements VirtualBotConfigs, SimUtils {
    // Robot attributes
    private String drivetrain = "";
    private String imagePath = "";
    private double x;
    private double y;
    private double angle;
    private double speed;
    
    // Team and Misc attributes
    private String teamNumber = "";
    private String teamName = "";
    private String robotName = "";
    
    public VirtualBot() {
        
    }
    
    public VirtualBot(String robotName, String teamNumber, String driveType) {
        setRobotName(robotName);
        setTeamNumber(teamNumber);
        setDrivetrain(driveType);
        createSimProperties();
    }
    
    private void createSimProperties() {
        if (replitTesting == true) {
            if (drivetrain == "mecanum") {
                imagePath = "ucpd_mecanum.jpeg";
            } else if (drivetrain == "swerve") {
                imagePath = "ucpd_drivetrain.jpeg";
            } else if (drivetrain == "omni") {
                imagePath = "ucpd_hdrive.jpeg";
            } else if (drivetrain == "tank") {
                imagePath = "ucpd_tankdrive.jpeg";
            } else if (drivetrain == "differential") {
                imagePath = "robot.jpeg";
            } else {
                imagePath = "ucpd_drivetrain.jpeg";
            }
        } else {
            if (drivetrain == "mecanum") {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_mecanum.jpeg";
            } else if (drivetrain == "swerve") {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_drivetrain.jpeg";
            } else if (drivetrain == "omni") {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_hdrive.jpeg";
            } else if (drivetrain == "tank") {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_tankdrive.jpeg";
            } else if (drivetrain == "differential") {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_minibot.jpeg";
            } else {
                imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_drivetrain.jpeg";
            }
        }
    }
    /*
     * @return returns the robot image that we made
     */
    public String getImagePath() {
        return imagePath;
    }
    
    /*
     * @return returns the x position of the virtual robot
     */
    public double getX() {
        return x;
    }
    
    /*
     * @return returns the y position of the virtual robot
     */
    public double getY() {
        return y;
    }
    
    /*
     * @return returns the angle of the virtual robot
     */
    public double getAngle() {
        return angle;
    }
    public void rotate(double angle) {
        this.angle = Math.toRadians(this.angle + angle);
    }
    
    public void setPosition(double x, double y) {
        setPosition(x, y, 0);
    }
    public void setPosition(double inputX, double inputY, double inputAngle) {
        if (drivetrain == "mecanum") {
            x += (inputX*speed);
            y += (inputY*speed);
        } else if (drivetrain == "omni") {
            x += (inputX*speed);
            y += (inputY*speed);
        } else if (drivetrain == "differential") {
            x += (speed * Math.sin(inputAngle));
            y += (speed * Math.cos(inputAngle));
        } else if (drivetrain == "swerve") {
            x += (speed * Math.sin(inputAngle));
            y += (speed * Math.cos(inputAngle));
        } else {
        }
    }
    
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    
    /*
     * @params type: the input string that is used to set the drivetrain variable
     */
    public void setDrivetrain(String type) {
        if (type == "mecanum") {
            drivetrain = "mecanum";
        } else if (type == "swerve") {
            drivetrain = "swerve";
        } else if (type == "omni") {
            drivetrain = "omni";
        } else if (type == "tank") {
            drivetrain = "tank";
        } else if (type == "differential") {
            drivetrain = "differential";
        } else {
            System.err.println("[ERROR]: could not set drivetrain to value: " + type);
        }
    }
    public String getDrivetrain() {
        return drivetrain;
    }
    
    // MISC FUNCTIONS
    public void setTeamNumber(String newTeamNumber) {
        teamNumber = newTeamNumber;
    }
    public String getTeamNumber() {
        return teamNumber;
    }
    
    public void setTeamName(String name) {
        teamName = name;
    }
    public String getTeamName() {
        return teamName;
    }
    
    public void setRobotName(String newName) {
        robotName = newName;
    }
    public String getRobotName() {
        return robotName;
    }
}
