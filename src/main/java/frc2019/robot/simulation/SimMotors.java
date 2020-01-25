package frc2019.robot.simulation;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import frc2019.lib.VirtualMotor;

public class SimMotors {
    private final ArrayList<VirtualMotor> motors = new ArrayList<VirtualMotor>();

    public ArrayList<VirtualMotor> getMotors() {
        return motors;
    }

    public VirtualMotor get(int CAN_id) {
        return motors.get(CAN_id);
    }

    public int index(VirtualMotor motor) {
        return motors.indexOf(motor);
    }

    public int size() {
        return motors.size();
    }

    // private VirtualMotor newMotor(String name, int port) {return new VirtualMotor(name, port);}
    // private VirtualMotor newMotor(int port) {return newMotor("", port);}
    
    public void addMotor(VirtualMotor motor) {
        motors.add(motor);
    }

    public void addMotor(int CAN_id, VirtualMotor motor) {
        motors.add(CAN_id, motor);
    }

    public void removeMotor(int CAN_id) {
        VirtualMotor motorToRemove = null;
        for (VirtualMotor motor : motors) {
            if (motor.getPort() == CAN_id) motorToRemove = motor;
        }
        if (motorToRemove != null) motors.remove(motorToRemove);
        else throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return motors.isEmpty();
    }

    public void clear() {
        motors.clear();
    }
}