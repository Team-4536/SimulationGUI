package frc2019.robot.simulation;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import frc2019.lib.VirtualMotor;
import frc2019.lib.NEOSmartMotor;
import frc2019.lib.TestSimulationMotor;

public class SimMotors {
    // We create an array using the Object superclass
    private final ArrayList<Object> motors = new ArrayList<>();

    public ArrayList<Object> getMotors() {
        return motors;
    }

    public Object get(int CAN_id) {
        return motors.get(CAN_id);
    }

    public int index(Object motor) {
        return motors.indexOf(motor);
    }

    public int size() {
        return motors.size();
    }

    public void addMotor(VirtualMotor motor) { motors.add(motor); }
    public void addMotor(NEOSmartMotor motor) { motors.add(motor); }
    public void addMotor(TestSimulationMotor motor) { motors.add(motor); }

    public void addMotor(int CAN_id, VirtualMotor motor) { motors.add(CAN_id, motor); }
    public void addMotor(int CAN_id, NEOSmartMotor motor) { motors.add(CAN_id, motor); }
    public void addMotor(int CAN_id, TestSimulationMotor motor) { motors.add(CAN_id, motor); }

    public void removeMotor(int CAN_id) {
        Object motorToRemove = null;
        for (Object motor : motors) {
            if (motor instanceof TestSimulationMotor) if (((TestSimulationMotor) motor).getPort() == CAN_id) motorToRemove = (TestSimulationMotor) motor;
            if (motor instanceof VirtualMotor) if (((VirtualMotor) motor).getPort() == CAN_id) motorToRemove = (VirtualMotor) motor;
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

    public void test() {
        VirtualMotor m = new VirtualMotor("motor", 2);
        VirtualMotor jank = new VirtualMotor("motor", 3);
        addMotor(new VirtualMotor("motor", 0));
        addMotor(new VirtualMotor("motor", 1));
        addMotor(m);
        System.out.println("Size: " + size() + " correct index: " + index(m) + " incorrect index: " + index(jank));
        removeMotor(0);
        removeMotor(1);
        removeMotor(2);
    }
}