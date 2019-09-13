import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JPanel implements ActionListener {
	// The frame dimensions
	static int frameWidth = 600;
	static int frameHeight = 600;
    
    // The robot’s rotation
    static int rotation = 0;
    
    // The robot's cordinates, use these to move it/turn it
    static int robotX = 0;
    static int robotY = 0;
	
	// Create a timer for the drawing update delay (originally "5, this")
	Timer timer = new Timer(1, this);
	
	public Main() {
		timer.start();
	}
	
	// Use this to start drawing all the lines
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(Color.blue);
        
        Graphics2D g2 = (Graphics2D)g;
        
        // Change the origin point
		g2.translate(this.getWidth()/3 + 100, this.getHeight()/2); //105
		
		// rotate the graphics board
		//System.out.println(Math.toRadians(rotation)+" "+rotation%360);
		g2.rotate(Math.toRadians(rotation));
		
		newMeccanumBot(g2);
	}
	
	// Draw the Meccanum drivetrain
	public void newMeccanumBot(Graphics2D g) {
		// Proper use: X, Y, Width, Height
		int[] chassis  = {-75, -100, 150, 200}; //{-75, -100, 150, 200}
		int[] upperRightWheel = {75, -100, 25, 60}; //{75, -100, 25, 60}
		int[] lowerRightWheel = {75, 40, 25, 60}; //{75, 40, 25, 60}
		int[] upperLeftWheel = {-100, -100, 25, 60}; //{-100, -100, 25, 60}
		int[] lowerLeftWheel = {-100, 40, 25, 60}; //{-100, 40, 25, 60}
		newRect(g, chassis);
		newRect(g, upperRightWheel);
		newRect(g, lowerRightWheel);
		newRect(g, upperLeftWheel);
        newRect(g, lowerLeftWheel);
        
        robotX = chassis[0];
        robotY = chassis[1];
        
        // Draw the center point
        g.drawOval(0, 0, 3, 3);
        
        // To get rid of ...not now
        //int[] chassis  = {this.getWidth()/3 + 25, this.getHeight()/2 + 5, 150, 200}; // X Y Width Height
        //int[] upperRightWheel = {this.getWidth()/3 + 175, this.getHeight()/2 + 5, 25, 60};
        //int[] lowerRightWheel = {this.getWidth()/3 + 175, this.getHeight()/2 + 145, 25, 60};
        //int[] upperLeftWheel = {this.getWidth()/3, this.getHeight()/2 + 5, 25, 60};
        //int[] lowerLeftWheel = {this.getWidth()/3, this.getHeight()/2 + 145, 25, 60};
	}
	
	// (Graphics2D) Draw a new line at given cordinates
	public void newLine(Graphics2D g, int[] points) {
		if (points.length < 4 || points.length > 4) {
			System.err.println("Not enough integers");
		} else {
			g.drawLine(points[0], points[1], points[2], points[3]);
		}
	}
	
	// (Graphics) Draw a new line at given cordinates
	public void newLine(Graphics g, int[] points) {
		if (points.length < 4 || points.length > 4) {
			System.err.println("Not enough integers");
		} else {
			g.drawLine(points[0], points[1], points[2], points[3]);
		}
	}
	
	// (Graphics2D) Draw a new Rectangle at given cordinates with a specified width and height
	public void newRect(Graphics2D g, int[] points) {
		if (points.length < 4 || points.length > 4) {
			System.err.println("Not enough integers");
		} else {
			g.drawRect(points[0], points[1], points[2], points[3]);
		}
	}
	
	// (Graphics) Draw a new Rectangle at given cordinates with a specified width and height
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
	
	public static void main(String[] args) {
		Main main = new Main();
		JFrame frame = new JFrame("Robot Stimulator");
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main); // You could use: frame.add(main);
        
        while(true) {
            try {
                Thread.sleep(8);
                rotation = rotation+1;
                frame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}
