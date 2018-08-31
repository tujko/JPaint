package core;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class MojPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130571722241412284L;
	private BufferedImage image;
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
        if (image != null){
        	g2d.drawImage(image,0,0,null);  // paints the contents of img to the component's graphics context.
        }
   }
	
	public MojPanel(int x, int y){
		this.setPreferredSize(new Dimension(x,y));
		image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void setImage(BufferedImage img){
		image = img;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public static Image toImage(BufferedImage bufferedImage) {
	    return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
	}
	
	public void Negative() {
        Color col;
        for (int x = 0; x < image.getWidth(); x++) { 		//width
            for (int y = 0; y < image.getHeight(); y++) { 	//height
                int RGBA = image.getRGB(x, y); 				//gets RGBA data for the specific pixel
                col = new Color(RGBA, true); 				//get the color data of the specific pixel
                col = new Color(Math.abs(col.getRed() - 255),
                        Math.abs(col.getGreen() - 255), Math.abs(col.getBlue() - 255)); //Swaps values
                image.setRGB(x, y, col.getRGB()); 			//set the pixel to the altered colors
            }
        }
        repaint();
    }
	
	public static BufferedImage Negative(Component p) {
		JPanel panel = (JPanel)p;
		BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);;
		//Graphics2D g2d =(Graphics2D) panel.getGraphics();
		panel.paint(img.getGraphics());
        Color col;
        for (int x = 0; x < img.getWidth(); x++) { 		//width
            for (int y = 0; y < img.getHeight(); y++) { 	//height
                int RGBA = img.getRGB(x, y); 				//gets RGBA data for the specific pixel
                col = new Color(RGBA, true); 				//get the color data of the specific pixel
                col = new Color(Math.abs(col.getRed() - 255),
                        Math.abs(col.getGreen() - 255), Math.abs(col.getBlue() - 255)); //Swaps values
                img.setRGB(x, y, col.getRGB()); 			//set the pixel to the altered colors
            }
        }
        Graphics2D g2d =(Graphics2D) panel.getGraphics();
        g2d.drawImage(img,0,0,null);
        
        return img;
    }
}
