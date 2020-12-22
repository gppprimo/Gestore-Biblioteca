package CF_Generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * 
 * @author Pasquale Puzio
 *
 */
public class CheckPanel extends JPanel implements ActionListener 
{
	
	private JTextField codFis;
	
	private JButton checkCodFis;
	
	private JLabel ris;
	
	public CheckPanel()
	{
		createComponent();
	}
	
	/**
	 * metodo per creare e posizionare i componenti nel pannello
	*/
	private void createComponent()
	{
		setLayout(new GridLayout(6,1));
		codFis = new JTextField();
			codFis.setHorizontalAlignment(JTextField.CENTER);
			codFis.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
		checkCodFis = new JButton("Controlla");
			checkCodFis.addActionListener(this);
		ris = new JLabel();
			ris.setHorizontalAlignment(JLabel.CENTER);
			ris.setOpaque(true);
			ris.setVisible(false);
		this.add(new JLabel());
		JLabel label = new JLabel("Inserisci qui il codice fiscale", JLabel.CENTER);
		this.add(label);
		this.add(codFis);
		JPanel panel = new JPanel(new GridLayout(1,3));
			panel.add(new JLabel());
			panel.add(checkCodFis);
			panel.add(new JLabel());
		this.add(panel);
		this.add(ris);
		this.add(new JLabel());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		// per verificare se il codice è valido si esegue il calcolo del carattere
		// di controllo sui primi 15 caratteri e lo si confronta col sedicesimo
		if (source.equals(checkCodFis))
		{
			boolean flag;
			if (codFis.getText().length() != 16)
				flag = false;
			else
			{
				String c = String.valueOf(CodiceFiscale.getControlChar(codFis.getText().substring(0, 15).toUpperCase()));
				String d = codFis.getText().substring(15, 16).toUpperCase();
				 if (c.compareToIgnoreCase(d) == 0)
					 flag = true;
				 else
					 flag = false;
			}
			
			if (flag)
			{
				ris.setBackground(Color.GREEN);
				ris.setText("Il codice inserito è valido!!");
			}
			else
			{
				ris.setBackground(Color.RED);
				ris.setText("Il codice inserito non è valido!!");
			}
			
			ris.setVisible(true);
		}
	}
	
}
