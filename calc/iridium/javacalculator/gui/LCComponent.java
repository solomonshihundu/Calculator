package iridium.javacalculator.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public abstract class  LCComponent extends Component
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6305807993896191745L;
	protected boolean []litPattern;
	protected Color offColor;
	
	protected LCComponent()
	{
		this(Color.BLACK,new Color(0,80,0),Color.BLACK);
	}
	protected LCComponent(Color bg,Color unlit,Color lit)
	{
		super();
		setBackground(bg);
		setUnlitColor(unlit);
		setForeground(lit);
	}
	protected void setUnlitColor(Color off)
	{
		offColor=off;
	}
	public Color getUnlitColor()
	{
		return offColor;
	}
	
	protected abstract void buildLitPattern();
	
	protected void lightPattern(int []pattern)
	{
	    for(int b=0;b<pattern.length;b++)
	    {
	    	litPattern[pattern[b]]=true;
	    }
	}
	protected void clearPattern()
	{
		for(int b=0;b<litPattern.length;b++)
		{
			litPattern[b]=false;
		}
	}
	protected Color getPieceColor(int index)
	{
		if(litPattern[index])
		{
			return getForeground();
		}
		return offColor;
	}
	public Dimension getMinimumSize() {
		return new Dimension(22, 55);
		
		}
		public Dimension getPreferredSize() {
		return getMinimumSize();

}
}

