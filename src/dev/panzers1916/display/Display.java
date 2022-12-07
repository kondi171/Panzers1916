package dev.panzers1916.display;

import javax.swing.*;
import java.awt.*;

/** Represents a displaying window
 * @author Konrad Nowak */

public class Display {
	/** declaration a JFrame object for creating a frame */
	private JFrame frame;
	/** declaration a Canvas object for drawing primitives */
	private Canvas canvas;
	/** variable for storing title on frame */
	private String title;
	/** variable for storing width on frame */
	private int width;
	/** variable for storing height on frame */
	private int height;
	/** Constructor which call <b>createDisplay()</b> method and set starting values
	 * @param title set the title
	 * @param width set width
	 * @param height set height */
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	/** create a frame, add canvas to frame, lock resize, etc.*/
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}
	/** set the fullscreen on option
	 * @param fullscreen boolean statement which method can define fullscreen or window screen */
	public void setFullscreen(boolean fullscreen){
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			frame.setExtendedState(JFrame.NORMAL);
		}
	}
	/** getter for Canvas
	 * @return declared canvas element */
	public Canvas getCanvas(){
		return canvas;
	}
	/** getter for Frame
	 * @return declared frame element */
	public JFrame getFrame(){ return frame; }
}
