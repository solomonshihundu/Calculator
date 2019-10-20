package iridium.javacalculator.gui;

import iridium.javacalculator.calculator.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class CalculatorMenu extends JMenuBar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 749254501064507472L;
	private int appType;
	private Vector menuItems;
	
	public static final String FILE_EXIT="Exit";
	public static final String FILE_SAVE_AUDIT_TRAIL="Save AuditTrail";
	public static final String FILE_SAVE_COLORS="Save color preferences";
	public static final String VIEW_AUDIT_TRAIL="AuditTrail";
	public static final String VIEW_COLOR_BG="background";
	public static final String VIEW_COLOR_UNLIT="unlit";
	public static final String VIEW_COLOR_LIT="lit";
	
	public CalculatorMenu(int type)
	{
		super();
		
		switch(type)
		{
		case Calculator.APPLICATION:
		case Calculator.APPLET:
		
		appType=type;
		break;
		
		default:
			appType=Calculator.APPLET;
		}
		
		populateMenu();
	}
	public void populateMenu()
	{
		menuItems=new Vector();

		JMenu file=new JMenu("File");
		
		if(appType==Calculator.APPLET)
		{
			file.setEnabled(false);
		}
		
		JMenuItem fileSaveAt=new JMenuItem(FILE_SAVE_AUDIT_TRAIL);
		menuItems.add(fileSaveAt);
		file.add(fileSaveAt);
		
		JMenuItem fileSaveColors=new JMenuItem(FILE_SAVE_COLORS);
		menuItems.add(fileSaveColors);
		file.add(fileSaveColors);
		
		file.addSeparator();
		
		JMenuItem fileExit=new JMenuItem(FILE_EXIT);
		menuItems.add(fileExit);
		file.add(fileExit);
		
		add(file);
		
		JMenu view=new JMenu("View");
		
		JMenuItem viewAt=new JMenuItem(VIEW_AUDIT_TRAIL);
		menuItems.add(viewAt);
		view.add(viewAt);
		
		JMenu colors=new JMenu("Colors");
		
		JMenuItem bg=new JMenuItem(VIEW_COLOR_BG);
		menuItems.add(bg);
		colors.add(bg);
		
		JMenuItem unlit=new JMenuItem(VIEW_COLOR_UNLIT);
		menuItems.add(unlit);
		colors.add(unlit);
		
		JMenuItem lit=new JMenuItem(VIEW_COLOR_LIT);
		menuItems.add(lit);
		colors.add(lit);
		
		view.add(colors);
		add(view);
		
		
	}
	public void addMenuActionListener(ActionListener listener)
	{
		Iterator i=menuItems.iterator();
		
		while(i.hasNext())
		{
			((JMenuItem)i.next()).addActionListener(listener);
		}
		
	}

}
