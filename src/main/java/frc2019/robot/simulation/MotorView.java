package frc2019.robot.simulation;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Taskbar;

@SuppressWarnings("serial")
public class MotorView extends JFrame implements SimUtils {
    public MotorView(VirtualBot robot) {
        add(new MotorBoard(robot));
        setTitle("Simulation Motors");
        Taskbar taskbar = Taskbar.getTaskbar(); // set the task bar to the system's task bar
        try {taskbar.setIconImage(new ImageIcon(getClass().getResource("drivetrain-img-dict/SimulationGUI.png")).getImage());
        } catch (final UnsupportedOperationException e) {} catch (final SecurityException e) {}
        setIconImage(new ImageIcon(getClass().getResource("drivetrain-img-dict/SimulationGUI.png")).getImage()); // set the icon image
        setBackground(Color.WHITE);
        setResizable(true);
        setSize(200, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}