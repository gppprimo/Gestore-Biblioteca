package ExceptionHandler;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RicercaLibroUIException extends SQLException{


	public RicercaLibroUIException(){
		JOptionPane.showMessageDialog(null, "List contained 0 elements!", "Error",
                JOptionPane.ERROR_MESSAGE);
	}
	
}
