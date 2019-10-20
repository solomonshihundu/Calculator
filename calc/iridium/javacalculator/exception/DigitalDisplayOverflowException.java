package iridium.javacalculator.exception;

public class DigitalDisplayOverflowException extends Exception
{
	
	private static final long serialVersionUID = 7124411236006159580L;
	public DigitalDisplayOverflowException()
	{
		this("Overflow exception occured");
	}
	public DigitalDisplayOverflowException(String msg)
	{
		super(msg);
	}

}
