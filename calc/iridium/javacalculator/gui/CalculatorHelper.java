package iridium.javacalculator.gui;

import iridium.javacalculator.event.*;

public class CalculatorHelper
{
	private CalculatorModel model;
	private StringBuffer auditTrail;
	private boolean opPerformed;
	private int decimalPos;
	
	public CalculatorHelper()
	{
		this(20);
	}
	public CalculatorHelper(int decimalAlignmentPos)
	{
		 model=new CalculatorModel();
		 auditTrail=new StringBuffer();
		 decimalPos=decimalAlignmentPos;
	}
	private CalculatorModel performUnaryOperation(String op)
	{
		double value;
		if(op.equals(CalculatorKeypadEvent.SQRT))
		{
			value=sqrt();
		}
		else if(op.equals(CalculatorKeypadEvent.SIGN))
		{
			value=sign();
		}
		else
			return model;
		
		auditTrail.append(align("["+op+" "+model.getCurrentDisplayValue()+" "+"]"));
		model.setCurrentDisplayValue(value);
		setOperand(value);
		return model;
	}
	public CalculatorModel performOperation()
	{
		double value;
		
		if(CalculatorKeypadEvent.isOpBinary(model.getCurrentOp()))
		{
			auditTrail.append(align(model.getCurrentOp()+" "+model.getOperand2())+"\n");
		}
		else if(!model.getCurrentOp().equals(CalculatorModel.NONE))
		{
			auditTrail.append(align(model.getCurrentOp()+"\n"));
		}
		String op=model.getCurrentOp();
		
		if(op.equals(CalculatorKeypadEvent.ADD))
		{
			value= add();
		}
		else if(op.equals(CalculatorKeypadEvent.SUBTRACT))
		{
			value= subtract();
		}
		else if(op.equals(CalculatorKeypadEvent.MULTIPLY))
		{
			value= multiply();
		}
		else if(op.equals(CalculatorKeypadEvent.DIVIDE))
		{
			value= divide();
		}
		else if(op.equals(CalculatorKeypadEvent.POW))
		{
			value= pow();
		}
		else if(op.equals(CalculatorKeypadEvent.SIGN))
		{
			value= sign();
		}
		else if(op.equals(CalculatorKeypadEvent.SQRT))
		{
			value= sqrt();
		}
		else
		{	
		value=	model.getCurrentDisplayValue();	
		model.setOperand1(value);
		model.setOperand2(0.0);
		}
		
		model.setCurrentDisplayValue(value);
		model.setOperand1(value);
		opPerformed=true;
		auditTrail.append("\n================================\n"+align(String.valueOf(value))+"\n");
		
		return model;
	}
	public CalculatorModel getCaluclatorModel()
	{
		return model;
	}
	public CalculatorModel setOperand(double value)
	{
		if(CalculatorKeypadEvent.isOpBinary(model.getCurrentOp())&&!opPerformed)
		{
			model.setOperand2(value);
		}
		else
		{
			auditTrail.append("\n"+align(String.valueOf(value))+"\n");
			model.setOperand1(value);
			model.setOperand2(0.0);
			model.setCurrentOp(CalculatorModel.NONE);
		}
		model.setCurrentDisplayValue(value);
		
		return model;
		
	}
	public CalculatorModel setOp(String op)
	{
		if(!CalculatorKeypadEvent.isOpBinary(op))
		{
			performUnaryOperation(op);
		}
		if(opPerformed==false&&model.getCurrentOp()!=CalculatorModel.NONE)
		{
			performOperation();
		}
		opPerformed=false;
		model.setCurrentOp(op);
		return model;
	}
	public CalculatorModel memAdd()
	{
		model.setMem(model.getMem()+model.getCurrentDisplayValue());
		return model;
	}
	public CalculatorModel memSubtract()
	{
		model.setMem(model.getMem()-model.getCurrentDisplayValue());
		return model;
		
	}
	public CalculatorModel memRecall()
	{
		setOperand(model.getMem());
		return model;
	}
	public CalculatorModel memSwap()
	{
		double swapv=model.getMem();
		model.setMem(model.getCurrentDisplayValue());
		setOperand(swapv);
		return model;
	}
	public CalculatorModel memClear()
	{
		model.setMem(0.0);
		return model;
	}
	public CalculatorModel clear()
	{
		setOperand(0.0);
		return model;
	}
	public CalculatorModel clearAll()
	{
		model.setOperand1(0.0);
		model.setOperand2(0.0);
		model.setCurrentDisplayValue(0.0);
		model.setCurrentOp(CalculatorModel.NONE);
		opPerformed =false;
		return model;
	}
	public String getAudit()
	{
		return auditTrail.toString();
	}
	
	public String align(String decimalStr)
	{
		String aligned=decimalStr;
		int currPos=aligned.indexOf('.');
		
		for(int pos=currPos;pos<decimalPos;pos++)
		{
			aligned=" "+aligned;
		}
		return aligned;	
	}
	private double add()
	{
		return model.getOperand1()+model.getOperand2();
	}
	private double subtract()
	{
		return model.getOperand1()-model.getOperand2();
	}
	private  double divide()

	{
		return model.getOperand1()/model.getOperand2();
	}
	private double multiply()
	{
		return model.getOperand1()*model.getOperand2();
	}
	private double pow()
	{
		return Math.pow(model.getOperand1(),model.getOperand2());
	}
	private double sign()
	{
		return model.getCurrentDisplayValue()*-1;
	}
	private double sqrt()
	{
		return Math.sqrt(model.getCurrentDisplayValue());
	}
	
}
