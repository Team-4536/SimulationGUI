import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import javax.swing.Timer;

public class SimulationView extends JPanel implements ActionListener, KeyListener {
    VirtualBot robot;
    private Timer timer;
    private final int DELAY = 10;
    
    public SimulationView(VirtualBot inputRobot) {
        robot = inputRobot;
        System.out.println("view created");
        addKeyListener(this);
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.BLUE);
        
        // Rotate
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(robot.getAngle()), robot.getX() + robot.getWidth() / 2, robot.getY() + robot.getHeight() / 2);
        affineTransform.translate(robot.getX(), robot.getY());
        
        g2d.drawImage(robot.getImage(), affineTransform, null);
        g2d.dispose();
        
        System.out.println("painted");
    }
    
    /*
     * Method for updating the position.
    */
    public void setPosition(double inputX, double inputY) {
        setPosition(inputX, inputY, 0);
    }
    
    public void rotate(double inputAngle) {        
        robot.rotate(inputAngle);
    }
    
    /*
     * Method for updating the position and the rotation.
    */
    public void setPosition(double inputX, double inputY, int inputAngle) {
        robot.setSpeed(20);
        robot.setPosition(inputX, inputY, inputAngle);
        System.out.println("rX: "+robot.getX()+" rY: "+robot.getY()+" iX: "+inputX+" iY: "+inputY+" rotation: "+robot.getAngle()+" ir: "+inputAngle);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    // KeyListener functions
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // left
            rotate(-5);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { // up
            setPosition(5,0);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // right
            rotate(5);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // down
            setPosition(-5,0);
        }
        // System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 81) { // hopefully 'q'
            System.exit(0);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO nothing, just need this so this doesnt throw an error
    }
}