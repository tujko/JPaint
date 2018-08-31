package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JDesktopPane;

import jpen.PKind;
import jpen.PLevel;
import jpen.PLevelEvent;
import jpen.PenManager;
import jpen.event.PenAdapter;
import jpen.owner.multiAwt.AwtPenToolkit;

public class Brushes extends PenAdapter{
	private MojPanel 	panel=null;
	private float       brushSize;
	private float       opacity;
	private BasicStroke stroke;
	private int 		maxSize;
	private int 		mode;
	
	private Point2D.Float prevLoc=new Point2D.Float();// previous location of cursor
	private Point2D.Float loc=new Point2D.Float();// current location of cursor
	
	private Color brushColor = Color.black;
	
	private JDesktopPane jdp = null;
	
	Brushes(JDesktopPane dp){
		jdp=dp;
		brushSize=1;
		opacity = 0;
		maxSize = 10;
		mode=0;
	}
	
	Brushes(MojPanel p) {
		panel=p;
		brushSize=1;
		opacity = 0;
		maxSize = 10;
		mode=0;
		
		// Use the AwtPenToolkit to register a PenListener on the panel:
		AwtPenToolkit.addPenListener(panel, this);
		// setup the mouse to cause a pressure level event when the left button
		// is pressed:
		PenManager pm = AwtPenToolkit.getPenManager();
		pm.pen.levelEmulator.setPressureTriggerForLeftCursorButton(1.0f);
	}
	
	Brushes() {
		brushSize=1;
		opacity = 0;
		maxSize = 10;
		mode=0;
	}
	
	public void setPanel(MojPanel p) {
		panel = p;

		// Use the AwtPenToolkit to register a PenListener on the panel:
		AwtPenToolkit.addPenListener(panel, this);
		// setup the mouse to cause a pressure level event when the left button
		// is pressed:
		PenManager pm = AwtPenToolkit.getPenManager();
		pm.pen.levelEmulator.setPressureTriggerForLeftCursorButton(1.0f);
	}
	
	public void setColor(Color c){
		brushColor=c;
	}
	
	public void setOpacity(int op)
	{
		if(op<255 && op>=0)
			opacity=op;
		else if(op>255)
			opacity=255;
		else opacity=0;
	}
	
	public void setFelttippen(){
		mode=0;
	}
	
	public void setPen(){
		mode=1;
	}
	
	public void setEraser(){
		mode=3;
	}
	
	public void setMaxSize(int s){
		maxSize=s;
	}
	
	@Override
	public void penLevelEvent(PLevelEvent ev)
	{
		panel = (MojPanel) jdp.getSelectedFrame().getContentPane();
		
		Graphics2D g2d = (Graphics2D) panel.getImage().getGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		//ako event nije pokret onda ne radi nista
		if(!ev.isMovement()){
			return;
		}
		
		//namestanje velicine i providnosti linije
		float pressure=ev.pen.getLevelValue(PLevel.Type.PRESSURE);//od 0 do 1
		brushSize = pressure * maxSize;
		opacity = pressure * 255;

		//dobijanje trenutne lokacije kursora
		loc.x = ev.pen.getLevelValue(PLevel.Type.X);
		loc.y = ev.pen.getLevelValue(PLevel.Type.Y);

		if (brushSize>0)
		{
			if (ev.pen.getKind() == PKind.valueOf(PKind.Type.ERASER))//koriscenje "gumice"
			{
				g2d.setColor(Color.white);
				stroke = new BasicStroke(brushSize * 2);
			}else//crtanje linije
			{
				if(mode==0){
					// set the opacity, and create the stroke
					g2d.setColor(new Color(brushColor.getRed(), brushColor.getGreen(), brushColor.getBlue(), (int)opacity));
					//g2d.setColor(brushColor);
					stroke=new BasicStroke(maxSize,
							 BasicStroke.CAP_ROUND, //okrugli krajevi linije
							 BasicStroke.JOIN_MITER
															);
				}
				if(mode==1){
					g2d.setColor(new Color(brushColor.getRed(), brushColor.getGreen(), brushColor.getBlue(), 255));
					stroke=new BasicStroke(brushSize,
							 BasicStroke.CAP_ROUND,
							 BasicStroke.JOIN_MITER
															);
				}
				if(mode==3){
					g2d.setColor(Color.white);
					stroke = new BasicStroke(brushSize * 2);
				}
			}
			//crta liniju izmedju trenutne i prethodne lokacije
			g2d.setStroke(stroke);
			g2d.draw(new Line2D.Float(prevLoc, loc));
		}

		// set the current position to the previous position
		prevLoc.setLocation(loc);

		// paint pane to draw brush info on
		/*g2d.setColor(Color.white);
		g2d.fillRect(0, 5, 155, 35);

		// draw the brush info in the top-left corner
		g2d.setColor(Color.black);
		g2d.drawString(("Brush size: " + brushSize), 5, 20);
		g2d.drawString(("Opacity: " + opacity), 5, 35);
		g2d.drawString(("maxSize: " + maxSize), 5, 50);*/
		panel.repaint();
	}
}
