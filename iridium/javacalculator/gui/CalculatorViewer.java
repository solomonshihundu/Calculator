package iridium.javacalculator.gui;

import java.awt.event.ActionListener;
import iridium.javacalculator.event.*;

public interface CalculatorViewer 
{
	public void setDisplayValue(double value);
	public double getDisplayValue();
	public void appendDisplayDigit(int displayDigit);
	public void appendDisplayDecimal();
	public void addCalculatorKeypadListener(CalculatorKeypadListener ckl);
	
	public void addMenuActionListener(ActionListener al);
	public DigitalDisplay getDigitalDisplay();
	public void showAuditTrail(String atText);
	public void updateAuditTrail(String atText);
	public void showInfo(String message);

}
