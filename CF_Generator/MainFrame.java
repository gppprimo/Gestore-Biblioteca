package CF_Generator;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.UIManager;

/**
 * 
 * @author Pasquale Puzio
 *
 */
public class MainFrame extends JFrame  {
	
	private final Dimension FRAME_SIZE = new Dimension(700, 250);
	
	private JMenuItem calcItem, checkItem, exitItem, printItem;
	
	private JPanel panel;
	
	private ComuniTable listaComuni;
	
	public MainFrame()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Impossibile effettuare il caricamento delle impostazioni grafiche\nControllare che sia presente il file Quaqua.jar", "Errore", JOptionPane.WARNING_MESSAGE);
		}
		
		setTitle("Codice Fiscale Calculator 1.0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(FRAME_SIZE);
		// il codice seguente è per centrare la finestra nello schermo
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x, y;
		y = (screenSize.height/2) - (getHeight()/2);
		x = (screenSize.width/2) - (getWidth()/2);
		setLocation(x, y);
		listaComuni = new ComuniTable();
		panel = new CalcPanel(listaComuni);
		createComponent();
		setVisible(true);
	}
	
	private void createComponent()
	{
		JMenuBar bar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		
		exitItem = new JMenuItem("Esci");
			//exitItem.addActionListener(this);
		
		JMenu menuHelp = new JMenu("?");
		bar.add(menuFile);
		bar.add(menuHelp);
		getContentPane().add(bar, BorderLayout.NORTH);
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	/*public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source.equals(checkItem))
		{
			this.remove(panel);
			panel = new CheckPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
			checkItem.setEnabled(false);
			calcItem.setEnabled(true);
			printItem.setEnabled(false);
		}
		else if (source.equals(calcItem))
		{
			this.remove(panel);
			panel = new CalcPanel(listaComuni);
			getContentPane().add(panel, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
			checkItem.setEnabled(true);
			calcItem.setEnabled(false);
			printItem.setEnabled(true);
		}
		else if (source.equals(printItem))
		{
			int risp = JOptionPane.showOptionDialog(null, "La stampa verrà eseguita con il seguente formato\n\nNome e Cognome: ____________\nSesso: ______\nNato\\a a ______ il giorno ______\nCodice Fiscale: ______\n\nSei sicuro di voler proseguire?", "Stampa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (risp == 0)
			{
				if (panel instanceof CalcPanel)
					((CalcPanel)panel).printPanel();
			}
		}
		/*else if(source.equals(licenseItem))
			new AboutDialog();
		else if (source.equals(aboutItem))
		{
			JOptionPane.showMessageDialog(null, "Codice Fiscale Calculator 1.0\nProgettato e sviluppato da:\nPasquale Puzio\nPer ulteriori informazioni e/o contatti:\npasquale.puzio@gmail.com", "About", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("images/logo.png")));
		}
		else if (source.equals(exitItem))
			System.exit(0);
	}
*/
}
