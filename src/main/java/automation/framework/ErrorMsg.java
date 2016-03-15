package automation.framework;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ErrorMsg {
	
	//Displays an error message with the input string
	public static void showErrorMessage(String infoMessage)
    {  
        JOptionPane Error = new JOptionPane();
        Error.setMessageType(JOptionPane.ERROR_MESSAGE);
        JDialog dialog = Error.createDialog("");
        dialog.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(dialog, infoMessage, "Automation Failed", JOptionPane.ERROR_MESSAGE);
        
    }

}	
