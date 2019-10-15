package org.minutebots.frc2019.simulation;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Point;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.*;
import java.net.URL;

public class SimulationGUI {
    private static SimulationGUI instance;
    
    private JFrame frame;
    private JPanel panel;
    
    static double rotation = 0;
    
    static int robotX = 0;
    static int robotY = 0;
	
    public SimulationGUI() {
        
    }
    
    private static BufferedImage blurImageBorder(BufferedImage input, double border) {
        int w = input.getWidth();
        int h = input.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);

        g.setComposite(AlphaComposite.DstOut);
        Color c0 = new Color(0x000000FF);

        // Left
        g.setPaint(new GradientPaint(new Point2D.Double(0, border), c0, new Point2D.Double(border, border), c0));
        g.fill(new Rectangle2D.Double(0, border, border, h- border - border));

        // Right
        g.setPaint(new GradientPaint(new Point2D.Double(w - border, border), c0, new Point2D.Double(w, border), c0));
        g.fill(new Rectangle2D.Double(w- border, border, border, h- border - border));

        // Top
        g.setPaint(new GradientPaint(new Point2D.Double(border, 0), c0, new Point2D.Double(border, border), c0));
        g.fill(new Rectangle2D.Double(border, 0, w - border - border, border));

        // Bottom
        g.setPaint(new GradientPaint(new Point2D.Double(border, h - border), c0, new Point2D.Double(border, h), c0));
        g.fill(new Rectangle2D.Double(border, h - border, w - border - border, border));

        final float[] floatArray = new float[]{ 0, 1 };
        final Color[] colorArray = new Color[]{ c0, c0 };

        // Top Left
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(0, 0, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, 0, border, border));

        // Top Right
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(w - border - border, 0, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, 0, border, border));

        // Bottom Left
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(0, h - border - border, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, h - border, border, border));

        // Bottom Right
        g.setPaint(new RadialGradientPaint(
                new Rectangle2D.Double(w - border - border, h - border - border, border + border, border + border),
                floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, h - border, border, border));

        g.dispose();

        return output;
    }
    
    public SimulationGUI(String windowTitle, String robotImgPath) throws IOException {
        BufferedImage rawChasis = ImageIO.read(new File("src\\main\\java\\org\\minutebots\\frc2019\\simulation\\drivetrain-img-dict\\" + robotImgPath));
        BufferedImage drivetrain = blurImageBorder(rawChasis, 1);
        
        frame = new JFrame(windowTitle);
        frame.add(panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;
                
                g2d.setColor(Color.BLUE);
                
                // Change the origin point
                g2d.translate(getWidth() / 2, getHeight() / 2);
                
                // Draw the Center point
                g.drawOval(0, 0, 3, 3);
                
                // Rotate
                //AffineTransform backup = g2d.getTransform();
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.rotate(Math.toRadians(rotation), robotX + 50, robotY + 50);
                g2d.setTransform(affineTransform);
                
                // Set the rendering settings
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                //Draw our image like normal
                if (drivetrain != null) g2d.drawImage(drivetrain, robotX, robotY, 100, 100, null);
                
                //Reset our graphics object so we can draw it again
                g2d.dispose();
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on “close”
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setUndecorated(true); // Undecorated
        
        frame.setLocationRelativeTo(null); // Use if you know what this is
        frame.setVisible(true);
    }
    
    public SimulationGUI getInstance() {
        if (instance == null) { instance = new SimulationGUI(); }
        return instance;
    }
    
    public void enableFrame() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    public void disableFrame() {
        if (frame != null) {
            frame.setVisible(false);
        }
    }
    
    public void rotate(double joystickX) {
        rotation = rotation + joystickX;
    }
    
    public void setPosition(double joystickX, double joystickY) {
        System.out.println(rotation%360);
        // robotX and robotY are the robot's cordinates, rotaion is the angle that the robot should turn to
        robotX += (int)joystickX;
        robotY += (int)joystickY;
    }
    
    public void refresh() {
        if (frame != null) {
            frame.repaint();
            panel.repaint();
        }
	}
}
