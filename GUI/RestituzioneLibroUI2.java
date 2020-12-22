package GUI;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Label;


import Libro.GestoreLibro;
import Libro.Libro;
import UtilizzatoreServizio.GestoreUtilizzatoreServizio;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

public class RestituzioneLibroUI2 extends JDialog {
	private JTextField textCodice;
	private JTextField textCollocazione;

	/**
	 * Launch the application.
	 */
	public static void Restituzione() {
		try {
			RestituzioneLibroUI2 dialog = new RestituzioneLibroUI2();
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
	public RestituzioneLibroUI2() {
		getContentPane().setBackground(new Color(173, 216, 230));
		setLocationByPlatform(true);
		
		setRootPaneCheckingEnabled(false);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setForeground(new Color(0, 191, 255));
		setBackground(UIManager.getColor("Button.disabledForeground"));
		setTitle("Restituzione Libro\r\n");
		setType(Type.UTILITY);
		setBounds(100, 100, 409, 261);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setBounds(87, 193, 316, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(0, 0, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codice=textCodice.getText().toUpperCase();
						
						GestoreLibro gl=new GestoreLibro();
						GestoreUtilizzatoreServizio gu=new GestoreUtilizzatoreServizio();
						
						Libro l=new Libro (null, null, null, null, null, null, codice, null);
						
						if (codice.isEmpty())
						{
							JOptionPane.showMessageDialog(okButton, "Non hai inserito alcun codice.\nInserisci il codice del libro nel camo 'Codice' !","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							ArrayList<String> listaLibri=new ArrayList<String>();
							listaLibri=gu.ricercaCodiceLibro(codice);
							if (listaLibri.isEmpty())
							{
								JOptionPane.showMessageDialog(okButton, "Libro non in prestito","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								
								String codiceLibro=listaLibri.get(0);
								ArrayList<Libro> collocazione=gl.ricercaLibro(new Libro(null, null, null, null, null, null, codiceLibro, null));
								textCollocazione.setText(collocazione.get(0).getCollocazione());
														
								if (gl.restituzioneLibro(l)==true)
								{
									int n=JOptionPane.showOptionDialog(okButton, "Restituzione Effettuata\nVuoi effettuare un'altra restituzione?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
									
									if(n == JOptionPane.NO_OPTION)
									{
									    dispose();							    
									}
									else
									{
										textCodice.setText("");
									}
								}
								else
								{
									JOptionPane.showMessageDialog(okButton, "Restituzione fallita\nLibro non associato a nessun Utilizzatore o non presente nel Database","Errore!",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
			}
		}
		
		JLabel lblCodiceLibroDa = new JLabel("Codice Libro da restituire");
		lblCodiceLibroDa.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCodiceLibroDa.setBounds(6, 15, 194, 30);
		getContentPane().add(lblCodiceLibroDa);
		
		textCodice = new JTextField();
		textCodice.setBounds(6, 44, 130, 26);
		getContentPane().add(textCodice);
		textCodice.setColumns(10);
		{
			JLabel lblCollocazioneLibro = new JLabel("Collocazione Libro");
			lblCollocazioneLibro.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			lblCollocazioneLibro.setBounds(6, 101, 145, 30);
			getContentPane().add(lblCollocazioneLibro);
		}
		{
			textCollocazione = new JTextField();
			textCollocazione.setBounds(6, 130, 130, 26);
			getContentPane().add(textCollocazione);
			textCollocazione.setColumns(10);
		}
	}
}
