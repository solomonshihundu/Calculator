package iridium.javacalculator.gui;

import iridium.javacalculator.event.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CalculatorKeypad extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4637052480454447868L;
	private JButton[]buttons;
	private Vector listeners;
	
	public CalculatorKeypad()
	{
		super();
		
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.ipadx=gbc.ipady=5;
		gbc.weightx=gbc.weighty=1.0;
		
		int cols=4;
		String []bLabels={
				CalculatorKeypadEvent.MR,
				CalculatorKeypadEvent.MC,
				CalculatorKeypadEvent.MADD,
				CalculatorKeypadEvent.MSUBTRACT,
				CalculatorKeypadEvent.MS,
				CalculatorKeypadEvent.SQRT,
				CalculatorKeypadEvent.C,
				CalculatorKeypadEvent.AC,
				CalculatorKeypadEvent.POW,
				CalculatorKeypadEvent.DIVIDE,
				CalculatorKeypadEvent.MULTIPLY,
				CalculatorKeypadEvent.SUBTRACT,
				CalculatorKeypadEvent.CK7,
				CalculatorKeypadEvent.CK8,
				CalculatorKeypadEvent.CK9,
				CalculatorKeypadEvent.SIGN,
				CalculatorKeypadEvent.CK4,
				CalculatorKeypadEvent.CK5,
				CalculatorKeypadEvent.CK6,
				CalculatorKeypadEvent.ADD,
				CalculatorKeypadEvent.CK1,
				CalculatorKeypadEvent.CK2,
				CalculatorKeypadEvent.CK3,
				CalculatorKeypadEvent.EQUALS,
				CalculatorKeypadEvent.CK0,
				CalculatorKeypadEvent.DECIMAL
				};
		
		buttons=new JButton[bLabels.length];
		setLayout(gbl);
		
		ActionEventHandler actionListener=new ActionEventHandler();
		KeyEventListener keyListener=new KeyEventListener();
		
		for(int b=0;b<buttons.length;b++)
		{
			buttons[b]=new JButton(bLabels[b]);
		
		if(bLabels[b].equals("="))
		{
			gbc.gridheight=2;
			gbc.gridwidth=GridBagConstraints.REMAINDER;
			
		}
		else if(bLabels[b].equals("0"))
		{
			gbc.gridy=6;
			gbc.gridx=0;
			gbc.gridwidth=2;
			gbc.gridheight=1;
			
		}
		else if(bLabels[b].equals("3"))
		{
			gbc.gridwidth=GridBagConstraints.RELATIVE;
			gbc.gridheight=1;
		}
		else if((b+1)%cols==0)
		{
			gbc.gridwidth=GridBagConstraints.REMAINDER;
			gbc.gridheight=1;
		
		}
		else
		{
			gbc.gridx=GridBagConstraints.RELATIVE;
			gbc.gridheight=1;
			gbc.gridwidth=1;
		}
		
		buttons[b].setFont(new Font("Algerian",Font.BOLD,16));
		buttons[b].addActionListener(actionListener);
		buttons[b].addKeyListener(keyListener);
		
		gbl.setConstraints(buttons[b], gbc);
		add(buttons[b]);
		listeners=new Vector();
		}
	}
	private class ActionEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			CalculatorKeypadEvent cke=new CalculatorKeypadEvent(event.getSource(),((JButton)event.getSource()).getText());
			Iterator i=listeners.iterator();
			CalculatorKeypadListener ckl;
			while(i.hasNext())
			{
				ckl=(CalculatorKeypadListener)i.next();
				ckl.calculatorKeyPressed(cke);
			}
			
		}
	}
	private class KeyEventListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent event)
		{
			String key=CalculatorKeyMap.map(event);
			if(key!=CalculatorKeyMap.UNKNOWN)
			{
				CalculatorKeypadEvent cke=new CalculatorKeypadEvent(event.getSource(),key);
				CalculatorKeypadListener ckl;
				Iterator i=listeners.iterator();
				while(i.hasNext())
				{
					ckl=(CalculatorKeypadListener)i.next();
					ckl.calculatorKeyPressed(cke);
				}
			}
		}
	}
	
	public void addCalculatorKeypadListener(CalculatorKeypadListener ckl)
	{
		if(ckl==null)
			return ;
		listeners.add(ckl);
	}
	public CalculatorKeypadListener[] getCalculatorKeypadListeners()
	{
		return (CalculatorKeypadListener[])listeners.toArray();
	}


}
