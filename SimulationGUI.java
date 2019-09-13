import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimulationGUI extends JPanel implements ActionListener {
    static int frameWidth = 600;
    static int frameHeight = 600;
    
    static int rotation = 0;
    
    // Create a timer for the refresh delay
    Timer timer = new Timer(5, this);
    
    public SimulationGUI() {
        timer.start();
    }
  
    public SimulationGUI(double joystickX, double joystickY) {
        SimulationGUI GUI = new SimulationGUI();
        JFrame frame = new JFrame("Robot Stimulator");
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(GUI); // You could use: frame.add(main);
        
        while(true) {
            try {
                Thread.sleep(6);
                rotation = rotation+1;
                frame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Use this to start drawing all the lines
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        
        Graphics2D g2 = (Graphics2D)g;
        //g2.rotate(Math.toRadians(rotation));
        
        int[] chassis  = {this.getWidth()/2 + 30, this.getHeight()/2 + 5, 150, 200}; // X Y Width Height
        newRect(g2, chassis);

        int[] upperRightWheel = {this.getWidth()/2 + 180, this.getHeight()/2 + 5, 25, 60};
        newRect(g2, upperRightWheel);
        
        int[] lowerRightWheel = {this.getWidth()/2 + 180, this.getHeight()/2 + 145, 25, 60};
        newRect(g2, lowerRightWheel);
        
        int[] upperLeftWheel = {this.getWidth()/2 + 5, this.getHeight()/2 + 5, 25, 60};
        newRect(g2, upperLeftWheel);
        
        int[] lowerLeftWheel = {this.getWidth()/2 + 5, this.getHeight()/2 + 145, 25, 60};
        newRect(g2, lowerLeftWheel);
        
        g2.translate(this.getWidth()/2 + 100, this.getHeight()/2 + 125)
        
        //newLine(g, points);
    }
    
    // Draw a new line at given cordinates
    public void newLine(Graphics g, int[] points) {
        if (points.length < 4 || points.length > 4) {
            System.err.println("Not enough integers");
        } else {
            g.drawLine(points[0], points[1], points[2], points[3]);
        }
    }
    
    // Draw a new Rectangle at given cordinates with a specified width and height
    public void newRect(Graphics g, int[] points) {
        if (points.length < 4 || points.length > 4) {
            System.err.println("Not enough integers");
        } else {
            g.drawRect(points[0], points[1], points[2], points[3]);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
