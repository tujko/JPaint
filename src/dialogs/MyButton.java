package dialogs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class MyButton extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7869397579148015463L;
	Image image;
    ImageObserver imageObserver;


    MyButton(String filename) {
            super();
            ImageIcon icon = new ImageIcon(filename);
            image = icon.getImage();
            imageObserver = icon.getImageObserver();
            setPreferredSize(new Dimension(16,16));
    }

     public void paint( Graphics g ) {
            super.paint(g);
            g.drawImage(image,  0 , 0 , getWidth() , getHeight() , imageObserver);
     }
}