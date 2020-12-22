package CF_Generator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.OperazioniDiPrestitoUI;

/**
 * 
 * @author Pasquale Puzio
 *
 */
public class CalcPanel extends JPanel implements ActionListener, Printable
{
	private final String[] months =  {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
	
	private JTextField name, surname, codfis, city;
	
	private JComboBox sex, month, day, year;
	
	private JButton chooseCity, calcola, cancella, copia;
	
	private String cityCode;
	
	private ComuniTable listaComuni;
	
	public CalcPanel(ComuniTable comuni)
	{
		setLayout(new GridLayout(7 , 1));
		listaComuni = comuni;
		createComponent();
	}
	
	/**
	 * metodo per creare e posizionare il contenuto del pannello
	 */
	private void createComponent()
	{
		JPanel first = new JPanel(new GridLayout(1, 6));
		JLabel nome = new JLabel("Nome", JLabel.CENTER);
		JLabel cognome = new JLabel("Cognome", JLabel.CENTER);
		JLabel sesso = new JLabel("Sesso", JLabel.CENTER);
		name = new JTextField();
		surname = new JTextField();
		sex = new JComboBox();
			sex.addItem("M");
			sex.addItem("F");
		first.add(nome);
		first.add(name);
		first.add(cognome);
		first.add(surname);
		first.add(sesso);
		first.add(sex);
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Dati Anagrafici", JLabel.CENTER);
		p1.add(l1);
		this.add(p1);
		this.add(first);
		JPanel second = new JPanel(new GridLayout(1, 6));
		JLabel giorno = new JLabel("Giorno", JLabel.CENTER);
		JLabel mese = new JLabel("Mese", JLabel.CENTER);
		JLabel anno = new JLabel("Anno", JLabel.CENTER);
		day = new JComboBox();
			for (int i = 1; i<=31; i++)
				day.addItem(i);
		month = new JComboBox();
			for (int i = 0; i < months.length; i++)
				month.addItem(months[i]);
			month.addActionListener(this);
		year = new JComboBox();
		// aggiunta di tutti i possibili anni di nascita
		int tempyear = Calendar.getInstance().getTime().getYear() + 1900;
			for (int i = tempyear; i >= 1900; i--)
				year.addItem(i);
			year.addActionListener(this);
		second.add(anno);
		second.add(year);
		second.add(mese);
		second.add(month);
		second.add(giorno);
		second.add(day);
		JPanel p2 = new JPanel();
		JLabel l2 = new JLabel("Luogo e Data di nascita", JLabel.CENTER);
		p2.add(l2);
		this.add(p2);
		this.add(second);
		JPanel third = new JPanel(new GridLayout(1, 4));
			chooseCity = new JButton("Comune di nascita");
			chooseCity.addActionListener(this);
			chooseCity.setToolTipText("Clicca qui per selezionare il comune di nascita");
			city = new JTextField("", JTextField.CENTER);
			city.setEditable(false);
			third.add(new JLabel());
			third.add(chooseCity);
			third.add(city);
			third.add(new JLabel());
			this.add(third);
		codfis = new JTextField();
			codfis.setEditable(false);
			codfis.setHorizontalAlignment(JTextField.CENTER);
			codfis.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			codfis.setVisible(false);
		this.add(codfis);
		JPanel commandPanel = new JPanel(new GridLayout(1, 4));
			copia = new JButton("Copia");
			copia.setToolTipText("Copia negli appunti");
			copia.setEnabled(false);
			copia.addActionListener(this);
			commandPanel.add(copia);
			commandPanel.add(new JLabel());
			cancella = new JButton("Cancella");
			cancella.addActionListener(this);
			commandPanel.add(cancella);
			calcola = new JButton("Calcola");
			calcola.addActionListener(this);
			commandPanel.add(calcola);
		this.add(commandPanel);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source.equals(month) || source.equals(year))
		{
			updateDay();
		}
		else if (source.equals(chooseCity))
		{
			new ComuniDialog(listaComuni, this);
		}
		else if (source.equals(cancella))
		{
			name.setText("");
			surname.setText("");
			city.setText("");
			sex.setSelectedIndex(0);
			year.setSelectedIndex(0);
			month.setSelectedIndex(0);
			day.setSelectedIndex(0);
			codfis.setText("");
			codfis.setVisible(false);
			copia.setEnabled(false);
		}
		else if (source.equals(calcola))
		{
			// controlla che nessun campo sia vuoto
			if (name.getText().equalsIgnoreCase("") || surname.getText().equalsIgnoreCase("") || city.getText().equalsIgnoreCase(""))
				JOptionPane.showMessageDialog(null, "Uno o più campi sono vuoti", "Errore", JOptionPane.ERROR_MESSAGE);
			else
			{
				// calcola il codice fiscale
				String gg = day.getSelectedItem().toString();
				if (Integer.parseInt(day.getSelectedItem().toString()) < 10)
				{
					gg = 0 + gg; // aggiungiamo il carattere 0
				}
				String result = CodiceFiscale.calculateCodiceFiscale(name.getText().toUpperCase(), surname.getText().toUpperCase(), sex.getSelectedItem().toString().charAt(0), cityCode, gg, month.getSelectedIndex(), year.getSelectedItem().toString().substring(2, 4));
				if (result == null)
					JOptionPane.showMessageDialog(null, "Controllare che nel nome o nel cognome non siano presenti numeri o caratteri speciali!", "Errore", JOptionPane.ERROR_MESSAGE);
				else
				{
					codfis.setText(result);
					codfis.setVisible(true);
					copia.setEnabled(true);
				}
			}
		}
		else if (source.equals(copia))
		{
			codfis.selectAll();
			codfis.copy();
			codfis.select(0, 0);
		}
	}
	/**
	 * metodo per settare comune e relativo codice
	 * @param comune Comune di Nascita
	 * @param codice Codice del Comune di Nascita
	 */
	public void setComune(String comune, String codice)
	{
		city.setText(comune);
		cityCode = codice;
	}
	
	/**
	 * metodo per aggiornare la lista dei giorni
	 * alla scelta di un determinato mese
	 */
	private void updateDay()
	{
		int s = day.getSelectedIndex();
		int n = month.getSelectedIndex();
		int i;
		
		if (n == 0 || n == 2 || n == 4 || n == 6 || n == 7 || n == 9 || n == 11)
			i = 31;
		else if (n == 3 || n == 5 || n == 8 || n == 10)
			i = 30;
		else if ((Math.abs(2008 - Integer.parseInt(year.getSelectedItem().toString()))%4) == 0)
			i = 29;
		else
			i = 28;
		day.removeAllItems();
		
		for (int j = 1; j <= i; j++)
			day.addItem(j);
		
		if (s < i) day.setSelectedIndex(s);
		else day.setSelectedIndex(i-1);
	}
	
	/**
	 * metodo per stampare un breve documento
	 */
	public void printPanel()
	{
		if (name.getText().equalsIgnoreCase("") || surname.getText().equalsIgnoreCase("") || city.getText().equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Uno o più campi sono vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			PrinterJob pj = PrinterJob.getPrinterJob();
			pj.setJobName("Codice Fiscale Calculator");
			pj.setPrintable(this);
			
			try {
				if (pj.printDialog())
				{
					try {
						pj.print();
					} catch (PrinterException e) {
						JOptionPane.showMessageDialog(null, "Ci è stato un errore durante la stampa", "Errore di stampa", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception e)
			{
				try {
					pj.print();
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(null, "Ci è stato un errore durante la stampa", "Errore di stampa", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	// ancora da completare
	public int print(Graphics grap, PageFormat pageFormat, int pageIndex)
	{
		if(pageIndex > 0)
		    return NO_SUCH_PAGE;
		return PAGE_EXISTS;
	}
	
}
