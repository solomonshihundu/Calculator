package iridium.javacalculator.calculator;
/**
 * @author SolomonShihundu
 */
import iridium.javacalculator.event.*;
import iridium.javacalculator.gui.*;
import iridium.javacalculator.exception.*;

import java.awt.*;
import java.applet.Applet;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.colorchooser.ColorSelectionModel;

public class Calculator implements CalculatorKeypadListener,ActionListener
{
	
	private CalculatorViewer view;
	private CalculatorHelper helper;
	private boolean needNewNum=false;
	private int mode;
	public static final int APPLICATION=1;
	public static final int APPLET=2;
	
	
	public Calculator(CalculatorViewer ui) throws CalculatorException
	{
		view=ui;
		helper=new CalculatorHelper();
		view.addCalculatorKeypadListener(this);
		view.addMenuActionListener(this);
		
		if(view instanceof Frame)
		{
			((Frame)view).setVisible(true);
			mode=APPLICATION;
			
			try
			{
			FileInputStream colorPrefs=new FileInputStream("colors.prefs");
			ObjectInputStream colorIn=new ObjectInputStream(colorPrefs);
			
			DigitalDisplay display=view.getDigitalDisplay();
			
			display.setBackground((Color)colorIn.readObject());
			display.offColor=((Color)colorIn.readObject());
			display.setForeground((Color)colorIn.readObject());
			
			colorIn.close();
			
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("Color preferences not found using default values");
			}
			catch(Exception e)
			{
				System.out.println("Error occured using default values : ");
			}
		}
		else if(view instanceof Applet)
		{
			mode=APPLET;
		}
		else
		{
			throw new CalculatorException("PROGRAM HAS TO START AS APPLICATION OR APPLET !\n");
		}
		
		System.out.println("Program started in "+((mode==APPLET)?"Applet":"Application "+"mode"));
	
	}
	
	public static void main(String args[])
	{
		
		try
		{
		new Calculator(new CalculatorView());
		}
		catch(CalculatorException e)
		{
				System.out.println("CalculatorException :"+e.getMessage());
		}
	}
	public void calculatorKeyPressed(CalculatorKeypadEvent event)
	{

				String key=event.getKey();
				CalculatorModel model;
				
				if(CalculatorKeypadEvent.isNumerical(key))
				{
					if(needNewNum)
					{
						view.setDisplayValue(0.0);
						needNewNum=false;
					}
					view.appendDisplayDigit(Integer.parseInt(key));
				}
				else if(key.equals(CalculatorKeypadEvent.DECIMAL))
				{
					view.appendDisplayDecimal();
				}
				else if(CalculatorKeypadEvent.isOperational(key))
				{
					helper.setOperand(view.getDisplayValue());
					helper.setOp(key);
					model=helper.getCaluclatorModel();
					
					view.setDisplayValue(model.getCurrentDisplayValue());
					needNewNum=!key.equals(CalculatorKeypadEvent.SIGN);
					
				}
				else if (key.equals(CalculatorKeypadEvent.EQUALS))
				{
					if(!needNewNum)
					{
						helper.setOperand(view.getDisplayValue());
					}
					model=helper.performOperation();
					view.setDisplayValue(model.getCurrentDisplayValue());
					needNewNum=true;
				}
				else if(key.equals(CalculatorKeypadEvent.C))
				{
					model=helper.clear();
					view.setDisplayValue(model.getCurrentDisplayValue());
				}
				else if(key.equals(CalculatorKeypadEvent.AC))
				{
					model=helper.clearAll();
					view.setDisplayValue(model.getCurrentDisplayValue());
				}
				else if(key.equals(CalculatorKeypadEvent.MC))
				{
					helper.setOperand(view.getDisplayValue());
					model=helper.memClear();
					view.setDisplayValue(model.getCurrentDisplayValue());
				}
				else if(key.equals(CalculatorKeypadEvent.MR))
				{
					helper.setOperand(view.getDisplayValue());
					model=helper.memRecall();
					view.setDisplayValue(model.getCurrentDisplayValue());
					
				}
				else if(key.equals(CalculatorKeypadEvent.MS))
				{
					helper.setOperand(view.getDisplayValue());
					model=helper.memSwap();
					view.setDisplayValue(model.getCurrentDisplayValue());
				}
				else if(key.equals(CalculatorKeypadEvent.MADD))
				{
					helper.setOperand(view.getDisplayValue());
					model=helper.memAdd();
					view.setDisplayValue(model.getCurrentDisplayValue());
				}
				else if(key.equals(CalculatorKeypadEvent.MSUBTRACT))
				{
					helper.setOperand(view.getDisplayValue());
					model=helper.memSubtract();
				}
				
		view.updateAuditTrail(helper.getAudit());
	}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() instanceof JMenuItem)
		{
			String selection=((JMenuItem)event.getSource()).getText();
			if(selection.equals(CalculatorMenu.FILE_EXIT))
			{
				System.exit(0);
			}
			else if(selection.equals(CalculatorMenu.VIEW_AUDIT_TRAIL))
			{
				view.showAuditTrail(helper.getAudit());
			}
			else if(selection.equals(CalculatorMenu.VIEW_COLOR_BG))
			{
				DigitalDisplay display=view.getDigitalDisplay();
				ColorUpdater colorUpd=new Calculator.ColorUpdater(Calculator.ColorUpdater.BG,display.getBackground());
				colorUpd.updateColor();
			}
			else if(selection.equals(CalculatorMenu.VIEW_COLOR_LIT))
			{
				DigitalDisplay display=view.getDigitalDisplay();
				ColorUpdater colorUpd=new Calculator.ColorUpdater(Calculator.ColorUpdater.LIT, display.getForeground());
				colorUpd.updateColor();
			}
			else if(selection.equals(CalculatorMenu.VIEW_COLOR_UNLIT))
			{
				DigitalDisplay display=view.getDigitalDisplay();
				ColorUpdater colorUpd=new Calculator.ColorUpdater(Calculator.ColorUpdater.UNLIT, display.getUnlitColor());
				colorUpd.updateColor();
			}
			else if(selection.equals(CalculatorMenu.FILE_SAVE_AUDIT_TRAIL))
			{
				StringBuffer at=new StringBuffer(helper.getAudit());
				String nl= System.getProperty("line.separator");
				
				int nlIndex;int place=0;
				
				while((nlIndex=at.toString().indexOf('\n',place))!=-1)
				{
					at.replace(nlIndex, nlIndex+1, nl);
					place=nlIndex+nl.length();
				}
				
				try
				{
					FileWriter writer=new FileWriter("audittrail.txt");
					writer.write(at.toString());
					writer.close();
					view.showInfo("Audit Trail was saved successfully as audittrail.txt");
				}
				catch(IOException e)
				{
					view.showInfo("Error occured: AuditTrail was not saved!");
				}
			}
			else if(selection.equals(CalculatorMenu.FILE_SAVE_COLORS))
			{
				try
				{
					DigitalDisplay display=view.getDigitalDisplay();
					
					FileOutputStream out=new FileOutputStream("colors.prefs");
					ObjectOutputStream colorOut=new ObjectOutputStream(out);
					colorOut.writeObject(display.getBackground());
					colorOut.writeObject(display.getUnlitColor());
					colorOut.writeObject(display.getForeground());
					colorOut.flush();
					colorOut.close();
					
					view.showInfo("Color preferences saved successfully");
				}
				catch(Exception e)
				{
					view.showInfo("Error occured :Colors could not be saved");
				}
				
			}
			
		}
		
	}
	private class ColorUpdater implements ActionListener,ChangeListener
	{
		private int type;
		private JColorChooser chooser;
		private DigitalDisplay display;
		private static final int BG=0;
		private static final int UNLIT=1;
		private static final int LIT=2;
		private Color initialColor;
		
		private ColorUpdater(int whichColor,Color initial)
		{
			type=whichColor;
			display=view.getDigitalDisplay();
			initialColor=new Color(initial.getRGB());
			chooser=new JColorChooser(initialColor);
			chooser.getSelectionModel().addChangeListener(this);
		}
		public void updateColor()
		{
			JDialog colorDialog=JColorChooser.createDialog((Component)view, "Choose a Color", true, chooser, null, this);
			colorDialog.setVisible(true);
			colorDialog.dispose();
		}
		public void stateChanged(ChangeEvent e)
		{
			if(e.getSource() instanceof ColorSelectionModel)
			{
				ColorSelectionModel csm=(ColorSelectionModel)e.getSource();
				updateDisplayColor(csm.getSelectedColor());
			}
		}
		public void actionPerformed(ActionEvent event)
		{
			updateDisplayColor(initialColor);
		}
		private void updateDisplayColor(Color newColor)
		{
			switch(type)
			{
			case BG:
				display.setBackground(newColor);
				break;
			case UNLIT:
				display.setUnlitColor(newColor);
				break;
			case LIT:
				display.setForeground(newColor);
			
			}
			
		}
	}
}