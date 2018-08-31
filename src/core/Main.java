package core;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import dialogs.ColorPicker;
import dialogs.Toolbar;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu fileMenu = null;

	private JMenu editMenu = null;
	
	private JMenu effectMenu = null;

	private JMenu helpMenu = null;
	
	private JMenu imageMenu = null;
	
	private JMenu viewMenu = null;

	private JMenuItem exitMenuItem = null;

	private JMenuItem aboutMenuItem = null;

	private JMenuItem cutMenuItem = null;

	private JMenuItem copyMenuItem = null;

	private JMenuItem pasteMenuItem = null;
	
	private JMenuItem openMenuItem = null;

	private JMenuItem saveMenuItem = null;
	
	private JMenuItem saveasMenuItem = null;
	
	private JMenuItem newMenuItem = null;
	
	private JMenuItem InvertMenuItem = null;
	
	private JMenuItem colorMenuItem = null;
	
	private JMenuItem toolboxMenuItem = null;

	private static JDesktopPane jDesktopPane = null;

	private SubWindow platnoFrame = null;
	
	private ColorPicker CPicker  = null;
	
	private Color MainColor = Color.black;
	
	private Brushes brush = null;
	
	private int fileNum = 1;
	
	private JFileChooser fc = null;
	
	private BufferedImage tempImage = null;
	
	private static Toolbar tools = null;
	
	/**
	 * Konstruktor
	 */
	public Main() {
		super();
		initialize();
	}

	/**
	 * Ovaj metod sve inicializuje
	 * 
	 * @return void
	 */
	private void initialize() {
		//prozorce = new HashMap<String, MojPanel>();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJDesktopPane());
		this.setJMenuBar(getJJMenuBar());
		this.setSize(640, 480);
		this.setTitle("JPaint");
		this.getContentPane().setBackground(Color.GRAY);
		
		try {
			if (getClass().getResource("/icon.gif")!= null)
				setIconImage(new ImageIcon(ImageIO.read(getClass().getResource("/icon.gif"))).getImage());
			else
				setIconImage(new ImageIcon("icon.gif").getImage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CPicker = new ColorPicker();
		
		brush = new Brushes(getJDesktopPane());
		
		tools = new Toolbar(brush);
		
		if(!tools.isVisible())
			toolboxMenuItem.setText("Hide Toolbox");
		else
			toolboxMenuItem.setText("Show Toolbox");
		
		fc = new JFileChooser();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ovaj metod inicializuje jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getViewMenu());
			jJMenuBar.add(getImageMenu());
			jJMenuBar.add(getEffectMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getNewMenuItem());
			fileMenu.add(getOpenMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getSaveAsMenuItem());
			fileMenu.add(getExitMenuItem());
			
		}
		return fileMenu;
	}
	
	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getImageMenu() {
		if (imageMenu == null) {
			imageMenu = new JMenu();
			imageMenu.setText("Image");
			imageMenu.add(getColorMenuItem());
		}
		return imageMenu;
	}
	
	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEffectMenu() {
		if (effectMenu == null) {
			effectMenu = new JMenu();
			effectMenu.setText("Effect");
			effectMenu.add(getInvertMenuItem());
		}
		return effectMenu;
	}
	
	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}
	
	/**
	 * Ovaj metod inicializuje jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getViewMenu() {
		if (viewMenu == null) {
			viewMenu = new JMenu();
			viewMenu.setText("View");
			viewMenu.add(getToolboxMenuItem());
		}
		return viewMenu;
	}
	
	/**
	 * Ovaj metod inicializuje InvertMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getToolboxMenuItem() {
		if (toolboxMenuItem == null) {
			toolboxMenuItem = new JMenuItem();
			toolboxMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!tools.isVisible()){
						tools.setVisible(true);
						toolboxMenuItem.setText("Hide Toolbox");
					}
					else{
						tools.setVisible(false);
						toolboxMenuItem.setText("Show Toolbox");
					}
				}
			});
		}
		return toolboxMenuItem;
	}
	
	/**
	 * Ovaj metod inicializuje InvertMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getInvertMenuItem() {
		if (InvertMenuItem == null) {
			InvertMenuItem = new JMenuItem();
			InvertMenuItem.setText("Invert");
			InvertMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BufferedImage img = MojPanel.Negative(jDesktopPane.getSelectedFrame().getContentPane());
					MojPanel panel = new MojPanel(img.getWidth(), img.getHeight());
					panel.setImage(img);
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
					brush.setColor(MainColor);
					brush.setPanel(panel);
					jDesktopPane.getSelectedFrame().setContentPane(panel);
				}
			});
		}
		
		InvertMenuItem.setEnabled(false);
		
		return InvertMenuItem;
	}

	/**
	 * TOvaj metod inicializuje exitMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * Ovaj metod inicializuje aboutMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//new JDialog(Main.this, "About", true).setVisible(true);
					final JFrame AFrame = new JFrame("About");
					AFrame.setBounds(0, 0, 240, 160);
					AFrame.setLayout(new BoxLayout(AFrame.getContentPane(), BoxLayout.PAGE_AXIS));
					
					JLabel tekst = new JLabel("JPaint v0.0.1 by Nikola Tujkovi\u0107", JLabel.CENTER);
					tekst.setVerticalAlignment(JLabel.TOP);
					
					AFrame.add(tekst);
					
					AFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JButton dugme = new JButton ("Ok");
					dugme.addActionListener(new java.awt.event.ActionListener() {  
			            public void actionPerformed(java.awt.event.ActionEvent e) {  
			                	AFrame.dispose();  
			                }  
			        }); 
					//dugme.setBounds(100, 80, 200, 100);
					dugme.setHorizontalAlignment(JButton.CENTER);
					dugme.setPreferredSize(new Dimension(64, 24));
					AFrame.add(dugme);
					dugme.setVisible(true);
					//AFrame.pack();
					AFrame.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * Ovaj metod inicializuje cutMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
			cutMenuItem.setEnabled(false);
		}
		return cutMenuItem;
	}

	/**
	 * Ovaj metod inicializuje copyMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
			copyMenuItem.setEnabled(false);
		}
		return copyMenuItem;
	}

	/**
	 * Ovaj metod inicializuje pasteMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
			pasteMenuItem.setEnabled(false);
		}
		return pasteMenuItem;
	}

	/**
	 * Ovaj metod inicializuje openMenuItem klase jMenuItem
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getOpenMenuItem() {
		if (openMenuItem == null) {
			openMenuItem = new JMenuItem();
			openMenuItem.setText("Open");
			openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
					Event.CTRL_MASK, true));
			
			openMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int returnVal = fc.showOpenDialog(getParent());

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						try {
							tempImage = ImageIO.read(file);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jDesktopPane.add(getPlatnoFrame(file.getName(), tempImage));
						platnoFrame.setVisible(true);
						saveasMenuItem.setEnabled(true);
						InvertMenuItem.setEnabled(true);
					}
				}
			});
		}
		return openMenuItem;
	}
	
	/**
	 * Ovaj metod inicializuje saveMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			saveMenuItem.setEnabled(false);
		}
		return saveMenuItem;
	}

	/**
	 * Ovaj metod inicializuje saveMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveAsMenuItem() {
		if (saveasMenuItem == null) {
			saveasMenuItem = new JMenuItem();
			saveasMenuItem.setText("Save As");
			saveasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK | Event.SHIFT_MASK, true));
			saveasMenuItem.setEnabled(false);
			
			saveasMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//int returnVal = fc.showSaveDialog(getParent());

					JFileChooser saveChooser = new JFileChooser();
			        saveChooser.setFileFilter(new FileNameExtensionFilter("JPEG File", "jpg"));
			        saveChooser.setFileFilter(new FileNameExtensionFilter("PNG File", "png"));
			        saveChooser.setFileFilter(new FileNameExtensionFilter("GIF File", "gif"));
			        if(saveChooser.showSaveDialog(getParent())==JFileChooser.APPROVE_OPTION){
			            String name = saveChooser.getSelectedFile().getAbsolutePath();
			            String name1 = saveChooser.getFileFilter().getDescription();
			            if (name1.equals("JPEG File")){
			                String ext = ".jpg";
			                name = name + ext;
			                System.out.println(name);
			            }
			            else if(name1.equals("PNG File")){
			                String ext = ".png";
			                name = name + ext;
			                System.out.println(name);
			            }
			            else if(name1.equals("GIF File")){
			                String ext = ".gif";
			                name = name + ext;
			                System.out.println(name);
			            }
			            else if(name1.equals("All Files")){
			                 
			                System.out.println(name);
			            }
			            else{
			                JOptionPane.showMessageDialog(getParent(), "Error in saving file", "ERROR", JOptionPane.ERROR_MESSAGE);
			            }
			            try {
			            	JPanel panel = (JPanel)jDesktopPane.getSelectedFrame().getContentPane();
			        		BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);;
			        		panel.paint(img.getGraphics());
			        		ImageIO.write(img, name.substring(name.length()-3), new File(name));
			            } catch (IOException ex) {
			                ex.printStackTrace();
			            }
			        }
				}
			});
		}
		return saveasMenuItem;
	}
	
	/**
	 * Ovaj metod inicializuje newMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNewMenuItem(){
		if (newMenuItem == null) {
			newMenuItem = new JMenuItem();
			newMenuItem.setText("New");
			newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
					Event.CTRL_MASK, true));
			newMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed(), tj. novo platno"); // TODO Auto-generated Event stub actionPerformed()
					jDesktopPane.add(getPlatnoFrame());
					platnoFrame.setVisible(true);
					saveasMenuItem.setEnabled(true);
					InvertMenuItem.setEnabled(true);
				}
			});
		}
		return newMenuItem;
	}
	
	/**
	 * Ovaj metod inicializuje colorMenuItem klase jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getColorMenuItem() {
		if (colorMenuItem == null) {
			colorMenuItem = new JMenuItem();
			colorMenuItem.setText("Color");
			//saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
			//		Event.CTRL_MASK, true));
			colorMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					CPicker.setVisible(true);
					MainColor = CPicker.getColor();
					if (brush != null) brush.setColor(MainColor);
				}
			});
		}
		return colorMenuItem;
	}
	
	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jDesktopPane = new JDesktopPane();
		}
		return jDesktopPane;
	}

	private JInternalFrame getPlatnoFrame(){//Kreira novu radnu povrsinu
		if (platnoFrame == null) {
			platnoFrame = new SubWindow();
			platnoFrame.setFileName("Untitled-"+(fileNum++));
			platnoFrame.setTitle(platnoFrame.getFileName());
			try {
				if (getClass().getResource("/icon.gif")!= null)
					platnoFrame.setFrameIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icon.gif"))));
				else
					platnoFrame.setFrameIcon(new ImageIcon ("icon.gif"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			MojPanel panel = new MojPanel(400, 280);
			panel.setBackground(Color.white);
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			panel.setVisible(true);
			
			brush.setColor(MainColor);
			brush.setPanel(panel);
			
			platnoFrame.setContentPane(panel);
		}else{
			platnoFrame = new SubWindow();
			platnoFrame.setFileName("Untitled-"+(fileNum++));
			platnoFrame.setTitle(platnoFrame.getFileName());
			try {
				if (getClass().getResource("/icon.gif")!= null)
					platnoFrame.setFrameIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icon.gif"))));
				else
					platnoFrame.setFrameIcon(new ImageIcon ("icon.gif"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			MojPanel panel1 = new MojPanel(400, 280);
			panel1.setBackground(Color.white);
			panel1.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			panel1.setVisible(true);
			
			brush.setColor(MainColor);
			brush.setPanel(panel1);
			
			platnoFrame.setContentPane(panel1);
		}
		
		int tmpX,tmpY;
		if(280 > jDesktopPane.getWidth())
			tmpX=jDesktopPane.getWidth();
		else
			tmpX=280;
		if(400 > jDesktopPane.getHeight())
			tmpY=jDesktopPane.getHeight();
		else
			tmpY=400;
		platnoFrame.setSize(tmpX,tmpY);
		platnoFrame.setResizable(true);
		platnoFrame.pack();
				
		return platnoFrame;
	}
	
	private JInternalFrame getPlatnoFrame(String fname, BufferedImage img) {
		platnoFrame = new SubWindow();
		platnoFrame.setFileName(fname);
		platnoFrame.setTitle(platnoFrame.getFileName());
		try {
			if (getClass().getResource("/icon.gif")!= null)
				platnoFrame.setFrameIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icon.gif"))));
			else
				platnoFrame.setFrameIcon(new ImageIcon ("icon.gif"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MojPanel panel = new MojPanel(img.getWidth(), img.getHeight());
		panel.setImage(img);
		panel.setBackground(Color.white);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		panel.setVisible(true);

		brush.setColor(MainColor);
		brush.setPanel(panel);

		platnoFrame.setContentPane(panel);
		int tmpX,tmpY;
		if(img.getWidth() > jDesktopPane.getWidth())
			tmpX=jDesktopPane.getWidth();
		else
			tmpX=img.getWidth();
		if(img.getHeight() > jDesktopPane.getHeight())
			tmpY=jDesktopPane.getHeight();
		else
			tmpY=img.getHeight();
		platnoFrame.setSize(tmpX, tmpY) ;
		platnoFrame.setResizable(true);

		return platnoFrame;
	}
	
	public static Point TPosition(JFrame frame){
		int x = frame.getX()+4;
		//int y = frame.getY()+(int)JMenuBar.HEIGHT;
		//int y = (int) jDesktopPane.getBounds().y;
		//int y = (int) jDesktopPane.getBounds().y+32;
		int y = frame.getY()+64;
		
		//return SwingUtilities.convertPoint(tools, new Point(frame.getX(),frame.getY()+JMenuBar.HEIGHT), frame);
		return new Point(x,y);
	}
	
	/**
	 * Pokrece aplikaciju
	 */
	public static void main(String[] args) {
		final Main application = new Main();
		application.setVisible(true);
		Point p = TPosition(application);
		tools.setLocation(p.x, p.y);
		//tools.setLocationRelativeTo(jJMenuBar);
		tools.setAlwaysOnTop(true);
		//tools.toBack();
		
		ComponentListener cl=new ComponentAdapter()  
		{  
		 public void componentMoved(ComponentEvent event)  
		 {  
			 Point p = TPosition(application);
			 tools.setLocation(p.x, p.y);
		 }  
		};  
		 application.addComponentListener(cl);  
		
		tools.setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
