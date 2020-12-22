package CF_Generator;

import javax.swing.JOptionPane;

/**
 * Codice Fiscale Calculator
 * versione 1.0
 * 
 * Progetto completamente open source e libero
 * Licenza GPL (vedi About --> License)
 * 
 * @author Pasquale Puzio
 *
 */
public class CodFisCal {
	
	public static void main(String[] args)
	{
		/*try {*/
			if (args.length != 0)
				System.out.println("I don't want any arguments!!");
			
			new MainFrame();
		/*} catch (RuntimeException e)
		{
			JOptionPane.showMessageDialog(null, "Si è verificato un errore inaspettato\nRiprovare ad avviare il programma.", "Errore", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}*/
	}

}
