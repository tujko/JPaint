package dialogs;
import javax.swing.JDialog;
import javax.swing.JFrame;

import core.Brushes;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Toolbar extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 679876002645268566L;
	public Toolbar(Brushes brush) {
		
		final Brushes b = brush;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		MyButton penButton = new MyButton("pen.png");
		penButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b.setPen();
			}
		});
		getContentPane().add(penButton);
		
		MyButton felttippenButton = new MyButton("felttippen.png");
		felttippenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b.setFelttippen();
			}
		});
		getContentPane().add(felttippenButton);
		
		MyButton eraserButton = new MyButton("eraser.png");
		eraserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b.setEraser();
			}
		});
		getContentPane().add(eraserButton);
		
		pack();
		setResizable(false);
	}
}
