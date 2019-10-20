package iridium.javacalculator.gui;

import iridium.javacalculator.exception.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.DecimalFormat;

public class DigitalDisplay extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1440871134679327621L;
	protected LCDigit[] digits;
	protected LCNegative myNegative;
	protected DigitalDisplayFormat displayFormat;
	public Color offColor;
	
	
	public DigitalDisplay(int lcd_digits)
	{
		this(lcd_digits,0.0);
	}
	public DigitalDisplay(int lcd_digits,double number)
	{
		this(lcd_digits,number,false);
	}
	public DigitalDisplay(int lcd_digits,double number,boolean isNegative)
	{
		this(lcd_digits,number,false,Color.black,new Color(0,80,0),Color.green);
	}
	public DigitalDisplay(int lcd_digits,double number,boolean isNegative,Color bg,Color unlit,Color lit)
	{
		digits=new LCDigit[lcd_digits];
		displayFormat=new DigitalDisplayFormat();
	    /*setUnlitColor(unlit);
	    setBackground(bg);
		setForeground(lit);
	*/
		
		setLayout(new GridLayout(1,0,0,0));
		
		if(isNegative)
		{
			myNegative=new LCNegative(false,bg,unlit,lit);
			add(myNegative);
		}
		
		for(int d=0;d<digits.length;d++)
		{
			digits[d]=new LCDigit(0,bg,unlit,lit);
			add(digits[d]);
		}
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		displayFormat.parseAndSetValue(number);
		updateDisplay();
		
	}
	public void setUnlitColor(Color unlit)
	{
		offColor=unlit;
	}
	public Color getUnlitColor()
	{
		return offColor;
	}
	public void setDoubleValue(double dValue) throws DigitalDisplayOverflowException
	{
		DigitalDisplayOverflowException de=displayFormat.parseAndSetValue(dValue);
		
		if(de!=null)
		{
			throw de;
		}
		
	}
	public double getDoubleValue()
	{
		return displayFormat.getDoubleValue();
	}
	protected void updateDisplay()
	{
		for(int d=0;d<digits.length;d++)
		{
			digits[d].setDigit(displayFormat.getIntAt(d));
			digits[d].setDecimal(displayFormat.decimalIndex==d);
		}
		repaint();
	}
	public void appendInt(int num)
	{
		displayFormat.tryAppend(num);
		updateDisplay();
	}
	public void appendDecimal()
	{
		if(displayFormat.decimalIndex==DigitalDisplayFormat.NO_DECIMAL)
		{
			displayFormat.decimalIndex=digits.length-1;
			updateDisplay();
		}
	}
	protected int getNumDigits()
	{
		return digits.length;
	}
	
	private class DigitalDisplayFormat
	{
		private DecimalFormat formatter;
		private int decimalIndex;
		private double largestValue;
		private StringBuffer value;
		private boolean negative;
		private static final int NO_DECIMAL =-2;
		
		private DigitalDisplayFormat()
		{
			formatter=new DecimalFormat();
			
			formatter.setMaximumFractionDigits(digits.length);
			formatter.setDecimalSeparatorAlwaysShown(false);
			formatter.setMinimumIntegerDigits(1);
			formatter.setGroupingUsed(false);
			formatter.setMinimumFractionDigits(0);
			
			StringBuffer large=new StringBuffer();
			
			for(int i=0;i<digits.length;i++)
			{
				large.append('9');
			}
			
			largestValue=Double.parseDouble(large.toString());
			
		}
			
			private DigitalDisplayOverflowException parseAndSetValue(double number)
			{
				setNegative(number<0);
				
				number=Math.abs(number);
				if(number>largestValue)
				{
					setNegative(false);
					decimalIndex=NO_DECIMAL;
					value=new StringBuffer(getErrorString());
					return new DigitalDisplayOverflowException("The number "+number+" cannot be " +
							"displayed with "+digits.length+" digits");
				}
				value=new StringBuffer(formatter.format(number));
				
				decimalIndex=value.toString().indexOf('.')-1;
				if(decimalIndex!=NO_DECIMAL)
				{
					value.deleteCharAt(decimalIndex+1);
				}
				if(value.length()>digits.length)
				{
					while(value.length()>digits.length+1)
					{
						value.deleteCharAt(value.length()-1);
					}
				
				double roundOff=Double.parseDouble(value.toString())/10.0;
				String rounded=formatter.format(Math.rint(roundOff));
				value=new StringBuffer(rounded);
				
				
				}
				
				
				while(value.length()<digits.length)
				{
					value.insert(0," ");
					if(decimalIndex!=NO_DECIMAL)
					{
						decimalIndex++;
					}
				}
				
				return null;
				
			}
			private void tryAppend(int num)
			{
				if((value.toString()!=getErrorString())&&getDoubleValue()==0.0&&decimalIndex==NO_DECIMAL)
						{
					value.setCharAt(value.toString().indexOf('0'), ' ');
						}
				if(value.toString().trim().length()<getNumDigits())
				{
					value.deleteCharAt(0);
					value.append(num);
					
					if(decimalIndex!=NO_DECIMAL)
					{
						decimalIndex--;
					}
				}
			}
			
			private void setNegative(boolean isNegative)
			{
				negative=isNegative;
				if(myNegative!=null)
				{
					myNegative.setNegative(isNegative);		
		     }
			}
			private String getErrorString()
			{
				String error="";
				for(int e=0;e<digits.length-1;e++)
				{
					error+=" ";
				}
				error+="E";
				return error;
			}
			private double getDoubleValue()
			{
				StringBuffer parseValue=new StringBuffer(value.toString());
				if(parseValue.toString().equals(getErrorString()))
				{
					return 0.0;
				}
				if(decimalIndex!=NO_DECIMAL)
				{
					parseValue.insert(decimalIndex+1,'.');
				}
				
				double dValue=Double.parseDouble(parseValue.toString().trim());
				if(negative)
					dValue*=-1;
				
				return dValue;
			}
			private int getIntAt(int index)
			{
				String charStrVal=""+value.charAt(index);
				if(charStrVal.equals(" "))
				{
					return LCDigit.EMPTY;
				}
				else if(charStrVal.equals("E"))
				{
					return LCDigit.E;
				}
				return Integer.parseInt(charStrVal);
			}
		
		
		}

}
