package org.minutebots.frc2019.simulation;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Ridley Nelson
 */

@SuppressWarnings("serial")
public class SimulationGUI extends JFrame implements ActionListener {
    private VirtualBot robot;
    private JMenuBar menuBar = new JMenuBar(); 
    private JMenu optionsMenu = new JMenu("Options");
    private JMenu subMenu = new JMenu("Team Settings");
    private JMenuItem robotPositionSet, robotTypeSet, teamNumberSet, teamNameSet, showFeild, changeRobotSize;
    private JLabel teamNumber, robotType;

    public SimulationGUI(VirtualBot inputRobot) {
        initUI(inputRobot);
        robot = inputRobot;
    }
    
    private void initUI(VirtualBot inputRobot) {
        add(new SimulationViewDisplay(inputRobot));

        setTitle("Simulation GUI");
        setIconImage(new ImageIcon(getClass().getResource("drivetrain-img-dict/SimulationGUI.png")).getImage());
        setSize(900, 600);
        createMenuItems();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMenuItems() {
        robotPositionSet = new JMenuItem("Reset Robot Position"); 
        robotTypeSet = new JMenuItem("Select Robot type"); 
        teamNumberSet = new JMenuItem("Select Team Number"); 
        changeRobotSize = new JMenuItem("Change Robot Size"); 
        teamNameSet = new JMenuItem("Set Team Name"); 
        showFeild = new JMenuItem("Show Feild");
        
        teamNumber = new JLabel("     Team Number: 0000");
        robotType = new JLabel("     Drivetrain: ");

        optionsMenu.add(robotPositionSet); optionsMenu.add(changeRobotSize); optionsMenu.add(robotTypeSet); optionsMenu.add(showFeild); optionsMenu.add(subMenu);
        robotPositionSet.setMnemonic(KeyEvent.VK_R);
        subMenu.add(teamNameSet); subMenu.add(teamNumberSet); subMenu.add(teamNumber); subMenu.add(robotType);
        menuBar.add(optionsMenu);

        addMenuListeners();
    }

    /**
     * Autonomous way to add listeners to every menu object in the menubar.
     */
    private void addMenuListeners() {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu m = menuBar.getMenu(i);
            // System.out.println("Menu:" + m.getText());
            for (int j = 0; j < m.getMenuComponentCount(); j++) {
                java.awt.Component comp = m.getMenuComponent(j);
                if (comp instanceof JMenu) {
                    JMenu tempMenu = (JMenu)comp;
                    // System.out.println("Menu: " + tempMenu.getText());
                    
                    for (int j2 = 0; j2 < tempMenu.getMenuComponentCount(); j2++) {
                        java.awt.Component comp2 = tempMenu.getMenuComponent(j2);
                        if (comp2 instanceof JMenu) {
                            // JMenu tempMenu2 = (JMenu)comp2;
                            // System.out.println("Menu: " + tempMenu2.getText());
                        } else if (comp2 instanceof JMenuItem) {
                            JMenuItem menuItem2 = (JMenuItem)comp2;
                            menuItem2.addActionListener(this);
                            // System.out.println("MenuItem: " + menuItem2.getText());
                        }
                    }
                } else if (comp instanceof JMenuItem) {
                    JMenuItem menuItem = (JMenuItem)comp;
                    menuItem.addActionListener(this);
                    System.out.println("MenuItem: " + menuItem.getText());
                }
            }
        }
    }

    /**
     * A hacky while loop of java GUI getting input from the user.
     * @param parent - The JFrame parent element in which to show everything in.
     * @param message - The message to display.
     * @param toBeValue - An {@code int} that is used to compare with the result length.
     * @return the user input, should be 4 digits (numbers).
     */
    private String showTeamNumberInput(JFrame parent, String message, Integer toBeValue) {
        String input = JOptionPane.showInputDialog(parent, message, null);
        if (input.length() != toBeValue) showTeamNumberInput(parent, message, toBeValue);
        return input;
    }

    /**
     * Displays a dropdown with availible drivetrains supported by the simulator.
     * @param parent - The JFrame parent element in which to show everything in.
     * @param message - The message to display.
     */
    private void showDriveChangeInput(JFrame parent, String message) {
        JFrame frame = new JFrame("Drivetrain Changer");
        frame.setResizable(false);
        frame.setSize(250, 170);
        frame.setVisible(true);

        JLabel label = new JLabel("Please select a drive type.");
        label.setSize(400,100);
        label.setBounds(32, 5, 186, 30);

        JButton submit = new JButton("Done");
        submit.setBounds(5, 90, 60, 40);

        String options[] = {"differential","omni","tank"};
        final JComboBox comboBox = new JComboBox(options);
        comboBox.setBounds(5,60,240,25);

        frame.add(submit); frame.add(label); frame.add(comboBox);

        submit.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
                String data = (String)comboBox.getItemAt(comboBox.getSelectedIndex());  
                if (data == "differential") robot.setDrivetrain("d");
                if (data == "omni") robot.setDrivetrain("o");
                if (data == "tank") robot.setDrivetrain("t");
                robotType.setText("     Drivetrain: " + data);
                frame.dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == robotPositionSet) {
        }
        if (e.getSource() == robotTypeSet) {
            final JFrame parent = new JFrame();
            parent.setVisible(false);
            showDriveChangeInput(parent, "What would you like your new drivetrain to be? [d, o, t]");
        }
        if (e.getSource() == teamNumberSet) {
            final JFrame parent = new JFrame();
            parent.setVisible(false);
            String number = showTeamNumberInput(parent, "What is your 4 digit team number?", 4);
            robot.setTeamNumber(Integer.parseInt(number));
            teamNumber.setText("     Team Number: " + robot.getTeamNumber());
        }
        if (e.getSource() == teamNameSet) {

        }
        if (e.getSource() == showFeild) {
        }
    }
}