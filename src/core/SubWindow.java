package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

public class SubWindow extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private String FileName=null;
	private MojPanel tempPanel=null;

	/**
	 * This is the default constructor
	 */
	public SubWindow() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		try {
			this.setIcon(false);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setBackground(Color.gray);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusable(false);
		this.setIconifiable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setResizable(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane(JPanel p) {
		if (jContentPane == null) {
			jContentPane = p;
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			//jScrollPane.setViewportView(getJTextPane());
		}
		return jScrollPane;
	}

	public void setFileName(String s){
		FileName=s;
	}
	
	public String getFileName(){
		return FileName;
	}
	
	/*
	@Override
	public Component add(Component c){
		if (c instanceof MojPanel){ 
	           //add( (MojPanel)c );
	           tempPanel=(MojPanel)c;
		}
		return super.add(c);
	}
	*/
	
	public Component add(MojPanel mp){
	    tempPanel=mp;
		return super.add(mp);
	}
	
	public MojPanel getMP ()
	{
		return tempPanel;
	}
}
