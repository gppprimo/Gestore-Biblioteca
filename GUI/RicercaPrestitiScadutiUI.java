package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Libro.GestoreLibro;
import Libro.Libro;
import UtilizzatoreServizio.GestoreUtilizzatoreServizio;
import UtilizzatoreServizio.Prestito;
import UtilizzatoreServizio.UtilizzatoreServizio;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class RicercaPrestitiScadutiUI extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaPrestitiScadutiUI dialog = new RicercaPrestitiScadutiUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x, y;
			y = (screenSize.height/2) - (dialog.getHeight()/2);
			x = (screenSize.width/2) - (dialog.getWidth()/2);
			dialog.setLocation(x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaPrestitiScadutiUI() {
		getContentPane().setBackground(new Color(173, 216, 230));
		setAlwaysOnTop(true);
		setTitle("Cerca Prestiti Scaduti");
		setBounds(100, 100, 525, 525);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 6, 492, 421);
			getContentPane().add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			textArea.setLocation(9, 0);
			scrollPane.setViewportView(textArea);
		
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(16, 439, 485, 39);
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Cerca");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestoreUtilizzatoreServizio gus=new GestoreUtilizzatoreServizio();
						GestoreLibro gl=new GestoreLibro();	
						
						ArrayList<Prestito> listaPrestitiScaduti=gus.cercaPrestitiScaduti();
						ArrayList<UtilizzatoreServizio> listaUtilizzatori=new ArrayList<UtilizzatoreServizio>();
						Libro listaLibriInPossesso=new Libro (null, null, null, null, null, null, null, null);
						
						Integer numeroPrestiti=listaPrestitiScaduti.size();
						
						textArea.append("\n**********************************\n");
						textArea.append(" Prestiti SCADUTI: "+numeroPrestiti);
						textArea.append("\n**********************************\n");	
						textArea.append("\n*****************************************************\n\n");
						
						for (int i=0;i<numeroPrestiti;i++)
						{
							Long IDUtilizzatore=listaPrestitiScaduti.get(i).getIDUtilizzatore();
							String CodiceLibroInPossesso=listaPrestitiScaduti.get(i).getCodiceLibro();
							listaUtilizzatori.add(gus.ricercaUtilizzatore(IDUtilizzatore));
							
							Libro l=new Libro (null, null, null, null, null, null, CodiceLibroInPossesso, null);
							listaLibriInPossesso=(gl.ricercaLibro2(l));
							
							textArea.append("  --- Dati relativi al BENEFICIARIO ---\n");
							textArea.append(" Nome: "+listaUtilizzatori.get(i).getNome()+"\n");
							textArea.append(" Cognome: "+listaUtilizzatori.get(i).getCognome()+"\n");
							textArea.append(" Codice Fiscale: "+listaUtilizzatori.get(i).getCF()+"\n");
							textArea.append(" Carta Identita`: "+listaUtilizzatori.get(i).getCI()+"\n");
							textArea.append(" Numero Patente: "+listaUtilizzatori.get(i).getNumPatente()+"\n");
							textArea.append(" Numero Telefono: "+listaUtilizzatori.get(i).getNumTelefono()+"\n");
							textArea.append(" Indirizzo: "+listaUtilizzatori.get(i).getIndirizzo()+"\n");
							textArea.append(" Numero Libri in possesso: "+listaUtilizzatori.get(i).getNumeroLibriPossesso()+"\n\n");
							textArea.append("  --- Dati relativi al LIBRO ---\n");
							textArea.append(" Titolo: "+listaLibriInPossesso.getTitolo()+"\n");
							textArea.append(" Autore: "+listaLibriInPossesso.getArgomento()+"\n");
							textArea.append(" Casa Editrice:"+listaLibriInPossesso.getCasaEditrice()+"\n");
							textArea.append(" Anno di pubblicazione: "+listaLibriInPossesso.getAnnoPubblicazione()+"\n");
							textArea.append(" Argomento: "+listaLibriInPossesso.getArgomento()+"\n");
							textArea.append(" Codice: "+listaLibriInPossesso.getCodice().toString()+"\n");
							textArea.append(" Collocazione: "+listaLibriInPossesso.getCollocazione()+"\n\n");
							textArea.append("  --- Dati relativi al PRESTITO ---\n");
							textArea.append(" Data Inizio prestito: "+listaPrestitiScaduti.get(i).getDataInizioPrestito()+"\n");
							textArea.append(" Data Fine prestito: "+listaPrestitiScaduti.get(i).getDataFinePrestito());
							textArea.append("\n\n******************************************************\n");
					
						}
					}
				
				});
				okButton.setBackground(new Color(0, 0, 255));
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setActionCommand("");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Esci");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}		
	}
}
