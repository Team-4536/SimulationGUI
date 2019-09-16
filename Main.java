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

	static int robotX = 0;
	static int robotY = 0;

	// Create a timer for the refresh delay
	static Timer timer = new Timer(5, this);
	
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
		
		// Draw the robot
		newMeccanumBot(g2);
	}
	
	// Draw the Meccanum drivetrain
	public void newMeccanumBot(Graphics2D g) {
		// Proper use: X, Y, Width, Height
		int[] chassis_cords  = {-75, -100, 150, 200}; //{-75, -100, 150, 200}
		int[] upperRightWheel_cords = {75, -100, 25, 60}; //{75, -100, 25, 60}
		int[] lowerRightWheel_cords = {75, 40, 25, 60}; //{75, 40, 25, 60}
		int[] upperLeftWheel_cords = {-100, -100, 25, 60}; //{-100, -100, 25, 60}
		int[] lowerLeftWheel_cords = {-100, 40, 25, 60}; //{-100, 40, 25, 60}
		
		// We use “Rectangle” because we can get attributes from that
		Rectangle chassis = newRect(g, chassis_cords);
		Rectangle upperRightWheel = newRect(g, upperRightWheel_cords);
		Rectangle lowerRightWheel = newRect(g, lowerRightWheel_cords);
		Rectangle upperLeftWheel = newRect(g, upperLeftWheel_cords);
		Rectangle lowerLeftWheel = newRect(g, lowerLeftWheel_cords);
        
		// Set the local X and Y attributes
		robotX = (int)chassis.getX();
		robotY = (int)chassis.getY();
        
		// Draw the center Point
		g.drawOval(0, 0, 3, 3);
        
		// To get rid of ...not now
		/*int[] chassis  = {this.getWidth()/3 + 25, this.getHeight()/2 + 5, 150, 200}; // X Y Width Height
		int[] upperRightWheel = {this.getWidth()/3 + 175, this.getHeight()/2 + 5, 25, 60};
		int[] lowerRightWheel = {this.getWidth()/3 + 175, this.getHeight()/2 + 145, 25, 60};
		int[] upperLeftWheel = {this.getWidth()/3, this.getHeight()/2 + 5, 25, 60};
		int[] lowerLeftWheel = {this.getWidth()/3, this.getHeight()/2 + 145, 25, 60};*/
	}
	
	// (Graphics2D) Draw a new line at given cordinates
	public void newLine(Graphics2D g, int[] points) {
		if (points.length < 4 || points.length > 4) {
			System.err.println("Not enough integers");
		} else {
			g.drawLine(points[0], points[1], points[2], points[3]);
		}
	}
	
	// (Graphics2D) Draw a new Rectangle at given cordinates with a specified width and height
	public Rectangle newRect(Graphics2D g, int[] points) {
		if (points.length < 4 || points.length > 4) {
			System.err.println("Not enough integers");
		} else {
			Rectangle r = new Rectangle(points[0], points[1], points[2], points[3]);
			g.draw(r);
			return r;
		}
		// Fallback solution, return something, but nothing
		return new Rectangle(0, 0, 0, 0);
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		JFrame frame = new JFrame("Robot Stimulator");
		// Set Size
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		// Full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		// Undecorated
		frame.setUndecorated(true);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(null);
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
