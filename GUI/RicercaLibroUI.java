package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import ExceptionHandler.RicercaLibroUIException;
import FunzioniSupporto.CheckClass;
import Libro.GestoreLibro;
import Libro.Libro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.SystemColor;

public class RicercaLibroUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tit;
	private JTextField aut;
	private JTextField cod;
	private JTextField argom;

	/**
	 * Launch the application.
	 */
	public static void ricerca() {
		try {
			RicercaLibroUI dialog = new RicercaLibroUI();
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
	public RicercaLibroUI() {
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Ricerca Libro");
		setBounds(100, 100, 587, 659);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblTitolo = new JLabel("Titolo");
			lblTitolo.setBounds(10, 11, 46, 30);
			lblTitolo.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			contentPanel.add(lblTitolo);
		}
		{
			JLabel lblAutore = new JLabel("Autore");
			lblAutore.setBounds(10, 63, 53, 30);
			lblAutore.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			contentPanel.add(lblAutore);
		}
		{
			JLabel lblNewLabel = new JLabel("Codice");
			lblNewLabel.setBounds(10, 119, 53, 30);
			lblNewLabel.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			contentPanel.add(lblNewLabel);
		}
		{
			tit = new JTextField();
			tit.setBounds(10, 37, 314, 20);
			contentPanel.add(tit);
			tit.setColumns(10);
		}
		{
			aut = new JTextField();
			aut.setBounds(10, 93, 314, 20);
			contentPanel.add(aut);
			aut.setColumns(10);
		}
		{
			cod = new JTextField();
			cod.setBounds(10, 149, 314, 20);
			contentPanel.add(cod);
			cod.setColumns(10);
		}
		
		
		JLabel lblNewLabel_1 = new JLabel("Argomento");
		lblNewLabel_1.setBounds(10, 179, 88, 30);
		lblNewLabel_1.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		contentPanel.add(lblNewLabel_1);
		
		argom = new JTextField();
		argom.setBounds(10, 209, 314, 20);
		contentPanel.add(argom);
		argom.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setBounds(10, 250, 551, 355);
		contentPanel.add(scrollPane);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textArea.setForeground(Color.BLUE);
		
		scrollPane.setViewportView(textArea);
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
						
						String titolo=tit.getText().toUpperCase();
						String titoloChecked=CheckClass.checkApostrophe(titolo);
						String autore=aut.getText().toUpperCase();
						String autoreChecked=CheckClass.checkApostrophe(autore);
						String codice=null;
						String codiceChecked=null;
								
						if (cod.getText().length()!=0)
						{
							codice=cod.getText().toUpperCase();
							codiceChecked=CheckClass.checkApostrophe(codice);
						}
						
						
						String argomento=argom.getText().toUpperCase();
						String argomentoChecked=CheckClass.checkApostrophe(argomento);
						
						if (titolo.equals("") && autore.equals("") && codice.equals("") && argomento.equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Non hai compilato alcun campo!", "Errore",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Libro l=new Libro (titoloChecked, autoreChecked, null, null, argomentoChecked, null, codiceChecked, null);
							
							GestoreLibro gl=new GestoreLibro();
							
							ArrayList<Libro> lista;
							
							lista = gl.ricercaLibro(l);
							
							
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
