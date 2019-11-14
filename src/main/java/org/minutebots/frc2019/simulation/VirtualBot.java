package org.minutebots.frc2019.simulation;

public class VirtualBot implements VirtualBotConfigs, SimUtils {
    // Robot attributes
    private String drivetrain = "";
    private String imagePath = "";
    private double x;
    private double y;
    private double centerX;
    private double centerY;
    private double angle;
    private double speed;
    
    // Team and Misc attributes
    private int teamNumber;
    private String teamName = "";
    private String robotName = "";
    
    public VirtualBot() {
        
    }
    
    public VirtualBot(String robotName, int teamNumber, String driveType) {
        setRobotName(robotName);
        setTeamNumber(teamNumber);
        setDrivetrain(driveType);
        createSimProperties();
    }
    
    private void createSimProperties() {
        if (replitTesting) {
            switch(drivetrain) {
                case "mecanum":
                    imagePath = "bin/ucpd_mecanum.jpeg";
                    return;
                case "swerve":
                    imagePath = "bin/ucpd_drivetrain.jpeg";
                    return;
                case "omni":
                    imagePath = "bin/ucpd_hdrive.jpeg";
                    return;
                case "tank":
                    imagePath = "bin/ucpd_tankdrive.jpeg";
                    return;
                case "differential":
                    imagePath = "bin/ucpd_drivetrain.jpeg";
                    return;
                default:
                    imagePath = "bin/ucpd_drivetrain.jpeg";
                    return;
            }
        } else if (!replitTesting) {
            switch(drivetrain) {
                case "mecanum":
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_mecanum.jpeg"; 
                    return;
                case "swerve":
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_drivetrain.jpeg";
                    return;
                case "omni":
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_hdrive.jpeg";
                    return;
                case "tank":
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_tankdrive.jpeg";
                    return;
                case "differential":
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_minibot.jpeg";
                    return;
                default:
                    imagePath = "src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\ucpd_drivetrain.jpeg";
                    return;
            }
        } else {

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
    
    public double getCenterX() {return centerX;}
    public double getCenterY() {return centerY;}
    public void setCenterX(double x) {centerX = x;}
    public void setCenterY(double y) {centerY = y;}
    
    /*
     * @return returns the angle of the virtual robot
     */
    public double getAngle() {
        return angle;
    }
    
    public void rotate(double angle) {
        // this.angle = Math.toRadians(this.angle + angle);
        this.angle += angle;
    }
    
    public void setPosition(double x, double y) {
        setPosition(x, y, 0);
    }
    public void setPosition(double inputX, double inputY, double inputAngle) {
        if (inputX > (float)0.9 || inputY > (float)0.9 || inputAngle > (float)0.9 || inputX < (float)-0.9 || inputY < (float)-0.9 || inputAngle < (float)-0.9) {
            if (drivetrain == "mecanum") {
                x += (inputX*speed);
                y += (inputY*speed);
                rotate(inputAngle);
            }
            if (drivetrain == "omni") {
                x += (inputX*speed);
                y += (inputY*speed);
            }
            if (drivetrain == "differential") {
                x += (speed * Math.sin(inputAngle));
                y += (speed * Math.cos(inputAngle));
            }
            if (drivetrain == "swerve") {
                x += (speed * Math.sin(inputAngle));
                y += (speed * Math.cos(inputAngle));
            }
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
    public void setTeamNumber(int newTeamNumber) {
        teamNumber = newTeamNumber;
    }
    public int getTeamNumber() {
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