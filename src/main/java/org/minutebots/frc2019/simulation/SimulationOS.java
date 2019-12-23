package org.minutebots.frc2019.simulation;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;

public class SimulationOS {
    private SimulationGUI simulation;

    public SimulationOS(String os) {
        if (os == "mac") {
            initMacOS();
        } else if (os == "windows") {
            initWindows();
        } else {
            initMacOS();
        }
    }

    private void initWindows() {
        EventQueue.invokeLater(() -> {
			VirtualBot minibot = new VirtualBot("Minibot", 4536, "d");
			minibot.setSpeed(15);

			simulation = new SimulationGUI(minibot);
			simulation.setVisible(true);
		});
    }

    private void initMacOS() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "SimulationGUI");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
			VirtualBot minibot = new VirtualBot("Minibot", 4536, "d");
			minibot.setSpeed(5);

			simulation = new SimulationGUI(minibot);
			simulation.setVisible(true);
		});
    }

    public SimulationGUI getSimulationInstance() {
        return simulation;
    }
}