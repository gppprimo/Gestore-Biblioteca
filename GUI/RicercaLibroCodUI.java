package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import FunzioniSupporto.CheckClass;
import Libro.GestoreLibro;
import Libro.Libro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;

public class RicercaLibroCodUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codText;

	/**
	 * Launch the application.
	 */
	public static void ricercaLibroCod() {
		try {
			RicercaLibroCodUI dialog = new RicercaLibroCodUI();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
	public RicercaLibroCodUI() {
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Ricerca Libro");
		setBounds(100, 100, 595, 659);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblTitolo = new JLabel("Codice");
			lblTitolo.setBounds(10, 11, 53, 30);
			lblTitolo.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			contentPanel.add(lblTitolo);
		}
		{
			codText = new JTextField();
			codText.setBounds(10, 37, 314, 20);
			contentPanel.add(codText);
			codText.setColumns(10);
		}
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textArea.setBounds(10, 139, 547, 351);
		contentPanel.add(textArea);
		textArea.setForeground(Color.BLUE);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 142, 579, 450);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.BLUE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						textArea.setText("");
						
						String codice=codText.getText().toUpperCase();
						String codiceChecked=CheckClass.checkApostrophe(codice);
						
		
						
						if (codice.equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Non hai compilato il campo 'Codice' !", "Errore",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Libro l=new Libro (null, null, null, null, null, null, codice, null);
							
							GestoreLibro gl=new GestoreLibro();
							
							ArrayList<Libro> lista;
							
							lista = gl.ricercaLibroByCod(l);
							
							Integer size=(lista.size());
							textArea.append("\n**********************************\n");
							textArea.append(" Libri trovati: "+size.toString());
							textArea.append("\n**********************************\n\n");
							
							for (int i=0;i<size;i++)
							{
								textArea.append("******************************************************\n");
								textArea.append(" ---Dati relativi al Libro trovato---\n");
								textArea.append(" Titolo: "+lista.get(i).getTitolo()+"\n Autore: "+lista.get(i).getAutore()+"\n Casa Editrice: "+lista.get(i).getCasaEditrice()
										+"\n Anno di pubblicazione: "+lista.get(i).getAnnoPubblicazione()+"\n Argomento: "+lista.get(i).getArgomento()+"\n Stato: "+lista.get(i).getStato()
										+"\n Codice: "+lista.get(i).getCodice()+"\n Collocazione: "+lista.get(i).getCollocazione()+"\n");
								textArea.append("******************************************************\n\n");
							}		
						}								
					}
				});
				okButton.setActionCommand("OK");
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
