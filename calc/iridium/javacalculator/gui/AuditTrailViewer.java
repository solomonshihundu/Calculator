package iridium.javacalculator.gui;

import javax.swing.*;
import java.awt.*;

public class AuditTrailViewer extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6483745727505803437L;
	private JTextArea trail;
	
	public AuditTrailViewer(Frame owner,String atText)
	{
		super(owner,"Audit Trail Viewer");
		trail=new JTextArea(atText,25,80);
		trail.setEditable(false);
		
		JScrollPane scroll=new JScrollPane(trail);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		pack();
		
	}
	public void setText(String text)
	{
		trail.setText(text);
	}
	public String getText()
	{
		return trail.getText();
	}

}
