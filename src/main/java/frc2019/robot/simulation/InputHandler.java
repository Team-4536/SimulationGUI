package frc2019.robot.simulation;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class InputHandler {
    private final ArrayList<Joystick> joysticks = new ArrayList<>();
    private final ArrayList<XboxController> controllers = new ArrayList<>();

    public InputHandler(Object input) {
        if (input instanceof Joystick) create((Joystick)input);
        if (input instanceof XboxController) create((XboxController)input);
    }

    public void create(Joystick joystick) {
        joysticks.add(joystick);
    }

    public void create(XboxController controller) {
        controllers.add(controller);
    }

    // public XboxController get(int port) {
    //     return 
    // }

    public Joystick get(int port) {
        for (Joystick stick : joysticks) {
            System.out.println(stick.getPort());
        }
        return null;
    }

}