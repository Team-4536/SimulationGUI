import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel implements ActionListener {
	private static Main instance;
	
	// The frame dimensions
	static int frameWidth = 600;
	static int frameHeight = 600;
	
	// The robot’s rotation
	static int rotation = 0;
	
	static int robotX = 0;
	static int robotY = 0;
	
	// Create a timer for the refresh delay
	Timer timer = new Timer(5, this);
	
	private Main() { timer.start(); }
	
	// Method for using USB Joysticks
	public Main(double joystickX, double joystickY) {
		
	}
	
	// Get instance
	public static Main getInstance() {
		if (instance == null) { instance = new Main(); }
        return instance;
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
	
	// Method to draw the Meccanum drivetrain
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
		JFrame frame = new JFrame("4536 FRC SimulatorGUI");
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); // Set Size
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
		frame.setUndecorated(true); // Undecorated
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(main); // You could use: frame.add(main);
        
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        
        // Drag and drop Start
		DragPanel dragPanel = new DragPanel();
        JPanel actionPanel = new JPanel();
        actionPanel.setOpaque(false);
        DragAction action = new DragAction(dragPanel);
        actionPanel.addMouseListener(action);
        actionPanel.addMouseMotionListener(action);
        JPanel panel = new JPanel();
        OverlayLayout overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);
        panel.add(actionPanel);
        panel.add(dragPanel);
        // Drag and drop End
        
        Point p = new Point();
        MouseInfo.getPointerInfo().translate(frame.getWidth() / 3 + 100, frame.getHeight() / 2);
        
        while(true) {
            try {
                Thread.sleep(8);
                //rotation = rotation+1;
                System.out.println(MouseInfo.getPointerInfo().getLocation().x + ", " + MouseInfo.getPointerInfo().getLocation().y);
                //frame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}

// Drag and Drop
class DragPanel extends JPanel {
    DragAction action;
  
    public DragPanel() {
        initializePanel();
        setLayout(null);
        setBackground(Color.white);
    }
  
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        if(action.dragging) {
            g2.setPaint(Color.red);
            g2.draw(action.r);
        }
    }
  
    private void initializePanel() {
        int deltaX = 60;
        int deltaY = 40;
        int x = 50;
        int y = 50;
        Dimension d = new Dimension(75,25);
        for(int i = 0; i < 3; i++) {
            JLabel label = new JLabel("Label " + (i + 1), JLabel.CENTER);
            label.setBorder(BorderFactory.createEtchedBorder());
            add(label);
            label.setBounds(x, y, d.width, d.height);
            JButton button = new JButton("Button " + (i + 1));
            add(button);
            Dimension b = button.getPreferredSize();
            button.setBounds(x, y + 100, b.width, b.height);
            x += deltaX;
            y += deltaY;
        }
    }
  
    public void register(DragAction da) {
        action = da;
    }
}

class DragAction extends MouseInputAdapter {
    DragPanel dragPanel;
    Rectangle r;
    Point offset;
    boolean dragging;
    Component selectedComponent;
  
    public DragAction(DragPanel dp) {
        dragPanel = dp;
        r = new Rectangle();
        offset = new Point();
        dragging = false;
        dragPanel.register(this);
    }
  
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
        Point p = e.getPoint();
        Component[] c = dragPanel.getComponents();
        Rectangle bounds;
        for(int i = 0; i < c.length; i++) {
            bounds = c[i].getBounds();
            if(bounds.contains(p)) {
                selectedComponent = c[i];
                r = bounds;
                offset.x = p.x - r.x;
                offset.y = p.y - r.y;
                dragging = true;
            }
        }
    }
  
    public void mouseReleased(MouseEvent e) {
        if(dragging) {
            selectedComponent.setBounds(r);
            dragPanel.repaint();
            dragging = false;
        }
    }
  
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            r.setLocation(e.getX() - offset.x, e.getY() - offset.y);
            dragPanel.repaint();
        }
    }
}
