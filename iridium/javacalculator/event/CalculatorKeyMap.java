package iridium.javacalculator.event;

import java.awt.event.KeyEvent;


public class CalculatorKeyMap 
{
	public static final String UNKNOWN="?";
	
	private CalculatorKeyMap(){};
	
			public static String map(KeyEvent ke)
			{
				switch(ke.getKeyCode())
				{
				case KeyEvent.VK_0:
				case KeyEvent.VK_NUMPAD0:
				return CalculatorKeypadEvent.CK0;
				
				case KeyEvent.VK_1:
				case KeyEvent.VK_NUMPAD1:
				return CalculatorKeypadEvent.CK1;
				
				case KeyEvent.VK_2:
				case KeyEvent.VK_NUMPAD2:
				return CalculatorKeypadEvent.CK2;
				
				case KeyEvent.VK_3:
				case KeyEvent.VK_NUMPAD3:
				return CalculatorKeypadEvent.CK3;
				
				case KeyEvent.VK_4:
				case KeyEvent.VK_NUMPAD4:
				return CalculatorKeypadEvent.CK4;
				
				case KeyEvent.VK_5:
				case KeyEvent.VK_NUMPAD5:
				return CalculatorKeypadEvent.CK5;
				
				case KeyEvent.VK_6:
				case KeyEvent.VK_NUMPAD6:
				return CalculatorKeypadEvent.CK6;
				
				case KeyEvent.VK_7:
				case KeyEvent.VK_NUMPAD7:
				return CalculatorKeypadEvent.CK7;
				
				case KeyEvent.VK_8:
				case KeyEvent.VK_NUMPAD8:
				return CalculatorKeypadEvent.CK8;
				
				case KeyEvent.VK_9:
				case KeyEvent.VK_NUMPAD9:
				return CalculatorKeypadEvent.CK9;
				
				case KeyEvent.VK_DECIMAL:
				case KeyEvent.VK_PERIOD:
				return CalculatorKeypadEvent.DECIMAL;
				
				case KeyEvent.VK_ADD:
				return CalculatorKeypadEvent.ADD;
				
				case KeyEvent.VK_SUBTRACT:
				if (ke.getModifiers() == KeyEvent.ALT_MASK) {
				return CalculatorKeypadEvent.SIGN;
				}
				else {
				return CalculatorKeypadEvent.SUBTRACT;
				}
				
				case KeyEvent.VK_MULTIPLY:
				return CalculatorKeypadEvent.MULTIPLY;
				
				case KeyEvent.VK_DIVIDE:
				return CalculatorKeypadEvent.DIVIDE;
				
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_EQUALS:
				return CalculatorKeypadEvent.EQUALS;
				
				case KeyEvent.VK_DELETE:
				if (ke.getModifiers() == KeyEvent.ALT_MASK) {
				return CalculatorKeypadEvent.AC;
				}
				else {
				return CalculatorKeypadEvent.C;
				}
				
				case KeyEvent.VK_F2:
				return CalculatorKeypadEvent.POW;
				
				case KeyEvent.VK_F3:
				return CalculatorKeypadEvent.SQRT;
				
				case KeyEvent.VK_F4:
				return CalculatorKeypadEvent.MS;
				
				case KeyEvent.VK_F5:
				return CalculatorKeypadEvent.MR;
				
				case KeyEvent.VK_F6:
				return CalculatorKeypadEvent.MC;
				
				case KeyEvent.VK_F7:
				return CalculatorKeypadEvent.MADD;
				
				case KeyEvent.VK_F8:
				return CalculatorKeypadEvent.MSUBTRACT;
				
				default:
				return UNKNOWN;
				}
			
	
		}

    
}