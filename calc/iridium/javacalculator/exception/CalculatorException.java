package iridium.javacalculator.exception;

public class CalculatorException extends Exception
{
	
	private static final long serialVersionUID = 3162357966894495794L;
	public CalculatorException()
	{
		this("Exception Ocuured");
	}
	public CalculatorException(String msg)
	{
		super(msg);
	}

}
