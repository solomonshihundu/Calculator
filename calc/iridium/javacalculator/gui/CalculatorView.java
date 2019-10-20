package iridium.javacalculator.gui;

import iridium.javacalculator.calculator.*;
import iridium.javacalculator.event.*;
import iridium.javacalculator.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorView  extends JFrame implements CalculatorViewer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4236533457291411849L;
	DigitalDisplay display;
	CalculatorKeypad keys;
	
	CalculatorMenu menu;
	AuditTrailViewer trail;
	
	

	public CalculatorView()
	{
		super("Java Calculator");
		
		Color bg=Color.black;
	    Color unlit=new Color(0,25,0);
	    Color lit=Color.green;
	    
		menu=new CalculatorMenu(Calculator.APPLICATION);
		setJMenuBar(menu);
		
		Container content=getContentPane();
		
		display=new DigitalDisplay(9,0.0,true,bg,unlit,lit);
		content.add(display,BorderLayout.NORTH);
		
		keys=new CalculatorKeypad();
		content.add(keys,BorderLayout.CENTER);
		
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    pack();
	}
	public void getInfo()
	{
		System.out.println((String)display.getBackground().toString());
	}
	public void setDisplayValue(double value)
	{
		try
		{
			display.setDoubleValue(value);
		}
		catch(DigitalDisplayOverflowException e)
		{
			System.out.println("DigitalDisplayOverflowException :"+e.getMessage());
		}
		finally
		{
			display.updateDisplay();
		}
	}
	public double getDisplayValue()
	{
		return display.getDoubleValue();
	}
	public void appendDisplayDigit(int digit)
	{
		display.appendInt(digit);
	}
	public void appendDisplayDecimal()
	{
		display.appendDecimal();
	}
	public void addCalculatorKeypadListener(CalculatorKeypadListener ckl)
	{
		keys.addCalculatorKeypadListener(ckl);
	}
	public void addMenuActionListener(ActionListener al)
	{
		menu.addMenuActionListener(al);
	}
	public void showAuditTrail(String atText)
	{
		if(trail==null)
		{
			trail=new AuditTrailViewer(this,atText);
		}
		else
		{
			trail.setText(atText);
		}
		trail.setVisible(true);
		
	}
	public void updateAuditTrail(String atText)
	{
		if(trail!=null && trail.isVisible())
		{
			trail.setText(atText);
		}
	}
	public DigitalDisplay getDigitalDisplay()
	{
		return display;
	}
	public void showInfo(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
}
