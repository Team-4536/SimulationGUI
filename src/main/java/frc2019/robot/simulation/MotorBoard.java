package frc2019.robot.simulation;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MotorBoard extends JPanel implements ActionListener {
    private final int DELAY = 10;
    private Timer timer;
    private VirtualBot robot;

    public MotorBoard(VirtualBot robot) {
        this.robot = robot;
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
        Map arr = robot.getVMotorData();
        int posx = 0;
        Iterator iterator = arr.keySet().iterator();
        while(iterator.hasNext()) {
            Object key = iterator.next();
            Object value = arr.get(key);
            g2d.drawString(key.toString() + ": " + value.toString(), 20, posx + 40);
            posx+=50;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) repaint();
    }
}