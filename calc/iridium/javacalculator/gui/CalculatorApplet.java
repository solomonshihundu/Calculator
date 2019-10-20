package iridium.javacalculator.gui;

import iridium.javacalculator.calculator.*;
import iridium.javacalculator.event.*;
import iridium.javacalculator.exception.*;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorApplet extends JApplet implements CalculatorViewer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3003854907465122567L;
	private DigitalDisplay display;
	private CalculatorKeypad keypad;
	
	CalculatorMenu menu;
	AuditTrailViewer trail;
	
	public CalculatorApplet()
	{
		super();
		menu=new CalculatorMenu(Calculator.APPLET);
		setJMenuBar(menu);
	}
	public void init()
	{
		Container content=getContentPane();
		
		display=new DigitalDisplay(9,0.0,true,Color.black,new Color(0,25,0),Color.GREEN);
		content.add(display,BorderLayout.NORTH);
		
		keypad=new CalculatorKeypad();
		content.add(keypad,BorderLayout.CENTER);
		
		try
		{
		      new Calculator(this);
		}
		catch(CalculatorException e)
		{
			e.printStackTrace();
			System.out.println("CalculatorException :"+e.getMessage());
			showStatus(e.getMessage());
		}
	}
	public void setDisplayValue(double value)
	{
		try
		{
			display.setDoubleValue(value);
		}
		catch(DigitalDisplayOverflowException e)
		{
			System.out.println("DigitalDisplayOverflowException : "+e.getMessage());
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
		keypad.addCalculatorKeypadListener(ckl);
	}
	public void addMenuActionListener(ActionListener al)
	{
		menu.addMenuActionListener(al);
	}
	public void showAuditTrail(String atText)
	{
		if(trail==null)
		{
			trail=new AuditTrailViewer(null,atText);
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


