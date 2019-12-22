package org.minutebots.frc2019.simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.AffineTransform;

/**
 * @author Ridley Nelson
 */

@SuppressWarnings("serial")
public class SimulationViewDisplay extends JPanel implements ActionListener {
    private Timer timer;
    private VirtualBot robot;
    private final int DELAY = 10;

    public SimulationViewDisplay(VirtualBot inputRobot) {
        robot = inputRobot;
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new KeyboardBinder());
        setBackground(Color.WHITE);
        setFocusable(true);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        g.dispose();
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(robot.getAngle()), robot.getX() + robot.getWidth() / 2, robot.getY() + robot.getHeight() / 2);
        affineTransform.translate(robot.getX(), robot.getY());
        g2d.drawImage(robot.getImage(), affineTransform, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) repaint();
    }

    /**
     * KeyboardBinder provides key bindings and passes them to VirtualBot.
     */
    private class KeyboardBinder extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            robot.keyboardBinder(e);
        }
    }
}