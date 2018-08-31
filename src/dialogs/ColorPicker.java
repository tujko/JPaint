package dialogs;
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
    protected Color color;
    
    public ColorPicker() {
        //super(new BorderLayout());
        
        color = Color.black;
        
      //Create and set up the window.
        this.setName("Color Picker");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
        
        //color=foreground;
        
        //Set up color chooser for setting text color
        tcc = new JColorChooser(color);
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Color"));
        add(tcc, BorderLayout.PAGE_END);
        
        //Display the window.
        pack();
        //setVisible(false);
    }
    
    public Color getColor()
    {
    	return color;
    }
   
    public void setColor(Color c)
    {
    	color = c;
    }
    
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        
        color = newColor;
    }
}