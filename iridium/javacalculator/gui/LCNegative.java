package iridium.javacalculator.gui;

import java.awt.*;

public class LCNegative extends LCComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8375225594617462972L;
	protected boolean negative;
	
	public LCNegative() {
	this (false);
	}
	
	public LCNegative(boolean isNegative) {
	super();
	litPattern = new boolean[1];
	negative = isNegative;
	buildLitPattern();
	}
	public LCNegative(boolean isNegative,
	Color bg, Color unlit, Color lit) {
	super(bg, unlit, lit);
	litPattern = new boolean[1];
	negative = isNegative;
	buildLitPattern();
	}
	public void setNegative(boolean isNegative) {
	negative = isNegative;
	buildLitPattern();
	repaint();
	}
	public boolean isNegative() {
	return negative;
	
	}
	public void buildLitPattern() {
	clearPattern();
	if (negative) {
	litPattern[0] = true;
	}
	}
	public void paint(Graphics g) {
	g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
	g.setColor(getPieceColor(0));
	g.fillRect(5, 26, 11, 3);
	}
	

}
