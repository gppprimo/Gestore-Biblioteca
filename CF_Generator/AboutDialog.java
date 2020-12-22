package CF_Generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JDialog;
import javax.swing.JTextPane;

/**
 * 
 * @author Pasquale Puzio
 *
 */
public class AboutDialog extends JDialog
{
	
	private static final String LICENSE = "license/gpl.txt";
	
	private static final Dimension DIALOG_SIZE = new Dimension(500, 600);
	
	private JTabbedPane tabbedPane;
	
	private JButton okButton;
	
	public AboutDialog()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
		setTitle("Licenza");
		setSize(DIALOG_SIZE);
		//	 il codice seguente serve per centrare la finestra nello schermo
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x, y;
		y = (screenSize.height/2) - (getHeight()/2);
		x = (screenSize.width/2) - (getWidth()/2);
		setLocation(x, y);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		createComponent();
		this.setVisible(true);
	}
	
	/**
	 * metodo per creare e posizionare i componenti nel pannello
	 */
	private void createComponent()
	{		
		JTextPane license = new JTextPane();
		license.setEditable(false);
		
		String result = "";
		try {
			InputStream is = getClass().getResourceAsStream(LICENSE);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String inputLine;
			
			inputLine = br.readLine();
			
			while (inputLine != null)
			{
				result += inputLine + "\n";
				inputLine = br.readLine();
			}
			
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) { result = "Errore durante la lettura del file";};
		
		license.setText(result);
		license.setCaretPosition(0);
		JScrollPane scroll = new JScrollPane(license);
		tabbedPane.add("Licenza GPL", scroll);
		add(tabbedPane, BorderLayout.CENTER);
		okButton = new JButton("OK");
		// definizione dell'azione associata alla pressione di okButton
		okButton.addActionListener(new ActionListener() 
	  	{
	  		public void actionPerformed (ActionEvent e) 
	  		{
	  			dispose();
	  		}
	  	});
		
		JPanel panel = new JPanel(new GridLayout(1, 3));
			panel.add(new JLabel());
			panel.add(okButton);
			panel.add(new JLabel());
		add(panel, BorderLayout.SOUTH);
	}
	
}
