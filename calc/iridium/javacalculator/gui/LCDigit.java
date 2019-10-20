package iridium.javacalculator.gui;

import java.awt.*;

public class LCDigit extends LCComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 983208381319699729L;
	public static final char EMPTY=' ';
	public static final int E=99;
	protected boolean decimalPoint;
	protected int digit;
	
	public LCDigit()
	{
		this(EMPTY);
	}
	public LCDigit(int digitToDisplay)
	{
		super();
		litPattern=new boolean[8];
		setDigit(digitToDisplay);
	}
	public LCDigit(int digitToDisplay,Color bg,Color unlit,Color lit)
	{
		super(bg,unlit,lit);
		litPattern=new boolean[8];
		setDigit(digitToDisplay);
	}
	protected void setDigit(int digitToDisplay)
	{
		digit=isDigitValid(digitToDisplay)?digitToDisplay:E;
		buildLitPattern();
		repaint();
	}
	protected boolean isDigitValid(int digitChar)
	{
		if((digitChar>=0&&digitChar<=9)||digitChar==EMPTY||digitChar==E)
			return true;
		return false;
	}
	public int getDigit()
	{
		return digit;
	}
	public void setDecimal(boolean on)
	{
		decimalPoint=on;
		buildLitPattern();
		repaint();
	}
	public boolean hasDecimalPoint()
	{
		return decimalPoint;
	}
	protected void buildLitPattern()
	{
		clearPattern();
		switch(digit)
		{
		case 0:
			lightPattern( new int[] { 0, 1, 2, 4, 5, 6 } );
			break;
			case 1:
			lightPattern( new int[] { 2, 5 } );
			break;
			case 2:
			lightPattern( new int[] { 0, 2, 3, 4, 6 } );
			break;
			case 3:
			lightPattern( new int[] { 0, 2, 3, 5, 6 } );
			break;
			case 4:
			lightPattern( new int[] { 1, 2, 3, 5 } );
			break;
			case 5:
			lightPattern( new int[] { 0, 1, 3, 5, 6} );
			break;
			case 6:
			lightPattern( new int[] { 0, 1, 3, 4, 5, 6 } );
			break;
			case 7:
			lightPattern( new int[] { 0, 2, 5 } );
			break;
			case 8:
			lightPattern( new int[] { 0, 1, 2, 3, 4, 5, 6 } );
			break;
			case 9:
			lightPattern( new int[] { 0, 1, 2, 3, 5, 6 } );
			break;
			case E:
			lightPattern( new int[] { 0, 1, 3, 4, 6 } );
			break;
			case EMPTY:

			default:
			return;
			}
			litPattern[7] = decimalPoint;
		
		
	}
	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getPieceColor(0));
		g.fillRect(5, 5, 11, 3);
		g.setColor(getPieceColor(1));
		g.fillRect(2, 8, 3, 18);
		g.setColor(getPieceColor(2));
		g.fillRect(16, 8, 3, 18);
		g.setColor(getPieceColor(3));
		g.fillRect(5, 26, 11, 3);
		g.setColor(getPieceColor(4));
		g.fillRect(2, 29, 3, 18);
		g.setColor(getPieceColor(5));
		g.fillRect(16, 29, 3, 18);
		g.setColor(getPieceColor(6));
		g.fillRect(5, 47, 11, 3);
		g.setColor(getPieceColor(7));
		g.fillRect(20, 47, 5, 3);
		}
}