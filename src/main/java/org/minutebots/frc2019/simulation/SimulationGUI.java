package org.minutebots.frc2019.simulation;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.EventQueue;

public class SimulationGUI extends JFrame {
    private static SimulationGUI instance;
    
    public SimulationGUI() {}
    
    private static BufferedImage blurImageBorder(BufferedImage input, double border) {
        int w = input.getWidth();
        int h = input.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics(); // We create a new Graphics2D object
        g.drawImage(input, 0, 0, null); // We draw the object

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
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(0, 0, border + border, border + border), floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, 0, border, border));

        // Top Right
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(w - border - border, 0, border + border, border + border), floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, 0, border, border));

        // Bottom Left
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(0, h - border - border, border + border, border + border), floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(0, h - border, border, border));

        // Bottom Right
        g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(w - border - border, h - border - border, border + border, border + border), floatArray, colorArray, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g.fill(new Rectangle2D.Double(w - border, h - border, border, border));
        
        
        g.dispose(); // Get rid of the Graphics object

        return output;
    }
    
    public SimulationGUI(String windowTitle, VirtualBot inputRobot) {
        EventQueue.invokeLater(() -> {
            displayInterface(windowTitle, inputRobot);
            setVisible(true);
        });
    }
    
    private void displayInterface(String windowTitle, VirtualBot inputRobot) {
        setTitle(windowTitle);
        add(new SimulationView(inputRobot));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on “close”
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null); // Use if you know what this is
    }
    
    public SimulationGUI getInstance() {
        if (instance == null) instance = new SimulationGUI();
        return instance;
    }
    
    public void enable() {
        setVisible(true);
    }
    public void disable() {
        setVisible(false);
    }
    
    public void refresh() {
        repaint();
	}
}