package iridium.javacalculator.gui;

import iridium.javacalculator.event.*;

public class CalculatorModel 
{
	private double operand1;
	private double operand2;
	private double mem;
	private double currentDisplayValue;
	private String currentOp;
	public static final String NONE="?";
	
	public CalculatorModel()
	{
		currentOp=CalculatorModel.NONE;
	}
	
	public void setOperand1(double value)
	{
		operand1=value;
		currentDisplayValue=operand1;
	}
	public double getOperand1()
	{
		return operand1;
	}
	public void setOperand2(double value)
	{
		operand2=value;
		currentDisplayValue=operand1;
	}
	public double getOperand2()
	{
		return operand2;
	}
	public void setMem(double value)
	{
		mem=value;
	}
	public double getMem()
	{
		return mem;
	}
	public void setCurrentDisplayValue(double value)
	{
		currentDisplayValue=value;
	}
	public double getCurrentDisplayValue()
	{
		return currentDisplayValue;
	}
	public void setCurrentOp(String op)
	{
		if(op.equals(CalculatorKeypadEvent.ADD)||op.equals(CalculatorKeypadEvent.SUBTRACT)||op.equals(CalculatorKeypadEvent.MULTIPLY)||
				op.equals(CalculatorKeypadEvent.DIVIDE)||op.equals(CalculatorKeypadEvent.POW))
		{
		currentOp=op;
		}
		else
		currentOp=CalculatorModel.NONE;


	}
	public String getCurrentOp()
	{
		return currentOp;
	}
	
	public String printStr()
	{
		String s="operand 1 "+operand1+"\nOperand 2 "+operand2+"\nCurrent operation"+currentOp
				+"\nCurrent display value"+currentDisplayValue+"\nMemory"+mem;
		return s;
	}

}
