package core;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/* ColorChooserDemo.java requires no other files. */
public class ColorPicker extends JFrame
                              implements ChangeListener {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -8682698489644267583L;
	protected JColorChooser tcc;
    protected Color foreground, background, color;
    protected String boja;
    
    public ColorPicker() {
        //super(new BorderLayout());
        
        foreground = Color.black;
        background = Color.green;
        
      //Create and set up the window.
        this.setName("Color Picker");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
        
        //color=foreground;
        
        //Set up color chooser for setting text color
        tcc = new JColorChooser(foreground);
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Color"));
        add(tcc, BorderLayout.PAGE_END);
        
        //Display the window.
        pack();
        //setVisible(false);
    }
    
    public Color getForeground()
    {
    	return foreground;
    }
    public Color getBackground()
    {
    	return background;
    }
    public void setForeground(Color f)
    {
    	foreground=f;
    }
    public void setBackground(Color b)
    {
    	background=b;
    }

    public void Color(String s){
    	boja = s;
    }
    
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        
        if(boja.equals("foreground")) foreground = newColor;
        
        if(boja.equals("background")) background = newColor;
    }
}