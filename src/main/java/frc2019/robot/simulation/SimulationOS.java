package frc2019.robot.simulation;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;

public class SimulationOS implements SimUtils {
    private SimulationGUI simulation;
    private static String definiteOS;

    /**
     * Creates a {@code SimulationOS} and a {@code SimulationGUI} object which can be retreived by {@code getSimulationInstance()}.
     * @param os The operating system name in which the simulation is going to be on. Current options are {@code windows} or {@code mac}.
     * @since v3
     * @see #getSimulationInstance()
     * @see #getOs()
     */
    public SimulationOS(String os) {
        new SimulationOS(os, new VirtualBot("Test Robot", 0000, "d"));
    }

    /**
     * 
     * @param os The operating system name in which the simulation is going to be on. Current options are {@code windows} or {@code mac}.
     * @param inputBot The robot for {@code VirtualBot} to use
     */
    public SimulationOS(String os, VirtualBot inputBot) {
        switch (os) {
            case "mac": initMacOS(inputBot);
            case "windows": initWindows(inputBot);
            default: initMacOS(inputBot);
        }
        definiteOS = os;
    }

    /**
     * 
     * @param os The operating system name in which the simulation is going to be on. Current options are {@code windows} or {@code mac}.
     * @param useKeyboard Weather to use the keyboard
     */
    public SimulationOS(String os, boolean useKeyboard) {
        VirtualBot defaultBot = new VirtualBot("Test Robot", 0000, "d");
        defaultBot.setSpeed(5);
        new SimulationOS(os, defaultBot, useKeyboard);
    }

    /**
     * 
     * @param os
     * @param inputBot
     * @param useKeyboard
     */
    public SimulationOS(String os, VirtualBot inputBot, boolean useKeyboard) {
        switch (os) {
            case "mac": initMacOS(inputBot, useKeyboard);
            case "windows": initWindows(inputBot, useKeyboard);
            default: initMacOS(inputBot, useKeyboard);
        }
        definiteOS = os;
    }

    /// Windows init functions
    private void initWindows(VirtualBot inputBot) { initWindows(inputBot, false); }
    private void initWindows(VirtualBot inputBot, boolean useKeyboard) { finalize(inputBot, useKeyboard); }

    private void initMacOS(VirtualBot inputBot) { initMacOS(inputBot, false); }
    private void initMacOS(VirtualBot inputBot, boolean useKeyboard) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", macOSToolBarUsage);
            System.setProperty("apple.awt.application.name", "SimulationGUI");
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }
        } catch (Exception e) { e.printStackTrace(); }

        finalize(inputBot, useKeyboard);
    }

    private void finalize(VirtualBot inputBot, boolean useKeyboard) {
        EventQueue.invokeLater(() -> {
			simulation = new SimulationGUI(inputBot, useKeyboard);
			simulation.setVisible(true);
		});
    }

    /**
     * Returns the OS initially set.
     * @return {@code String} the OS set.
     * @since v3
     */
    public static String getOs() { return definiteOS; }

    /**
     * Returns a SimulationGUI object.
     * @return SimulationGUI
     * @since v3
     */
    public SimulationGUI getSimulationInstance() { return simulation; }
}