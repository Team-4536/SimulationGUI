package frc2019.lib;

public class TestSimulationMotor {
    private int port;
    private double speed = 0;
    private boolean inverted = false;
    private String name, subsystem = "";

    public TestSimulationMotor(String name, int port) {
        System.out.println("TestSimulationMotor  " + name + " at port " + port + " created");
        this.port = port;
        this.name = name;
    }

    public TestSimulationMotor(int port){
        this("", port);
    }

    public void set(double speed) {
        this.speed = speed;
    }

    public double get() {
        return speed;
    }

    public int getPort() {
        return port;
    }

    public void setInverted(boolean isInverted) {
        inverted = isInverted;
    }

    public boolean getInverted() {
        return inverted;
    }

    public void disable() {
        this.speed = 0;
    }

    public void stopMotor() {
        this.speed = 0;
    }

    public void pidWrite(double output) {
        this.speed = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(String subsystem, String name) {
        this.subsystem = subsystem;
        this.name = name;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }
}