package iridium.javacalculator.event;

import java.util.EventObject;


public class CalculatorKeypadEvent extends EventObject
{
	
	private static final long serialVersionUID = 2878888908734714272L;
	public final static String MR = "MR";
	public final static String MC = "MC";
	public final static String MADD = "M+";
	public final static String MSUBTRACT = "M-";
	public final static String MS = "MS";
	public final static String SQRT = "SQRT";
	public final static String C = "C";
	public final static String AC = "AC";
	public final static String POW = "POW";
	public final static String DIVIDE= String.valueOf((char)247);
	public final static String MULTIPLY= String.valueOf((char)215);
	public final static String SUBTRACT = "-";
	public final static String CK7 = "7";
	public final static String CK8 = "8";
	public final static String CK9 = "9";
	public final static String SIGN= String.valueOf((char)177);
	public final static String CK4 = "4";
	public final static String CK5 = "5";
	public final static String CK6 = "6";
	public final static String ADD = "+";
	public final static String CK1 = "1";
	public final static String CK2 = "2";
	public final static String CK3 = "3";
	public final static String EQUALS = "=";
	public final static String CK0 = "0";
	public final static String DECIMAL = ".";
	
	private String key;
	
	public CalculatorKeypadEvent(Object source,String keyString)
	{
		super(source);
		key=keyString;
	}
	public String getKey()
	{
		return key;
	}
	public static boolean isNumerical(String keyValue)
	{
		try
		{
			Integer.parseInt(keyValue);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	public static boolean isOperational(String keyValue)
	{
		if(keyValue.equals(ADD)||keyValue.equals(SUBTRACT)||keyValue.equals(DIVIDE)
				||keyValue.equals(MULTIPLY)||keyValue.equals(POW)||keyValue.equals(SIGN)||keyValue.equals(SQRT))
		{
			return true;
		}
		return false;
	}
	public static boolean isMemOperational(String keyValue)
	{
		if(keyValue.equals(MADD)||keyValue.equals(MC)||keyValue.equals(MR)
				||keyValue.equals(MS)||keyValue.equals(MSUBTRACT))
		{
			return true;
		}
		return false;
	}
	public static boolean isOpBinary(String op)
	{
		return op.equals(ADD)||op.equals(DIVIDE)||op.equals(MULTIPLY)||op.equals(SUBTRACT)||op.equals(POW);
	}

}
