package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAO_Libro;
import FunzioniSupporto.CheckClass;
import Libro.Libro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;

public class ModificaLibroUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private static ModificaLibroUI dialog;
	
	/**
	 * Launch the application.
	 */
	public static void modifica() {
		try {
			dialog = new ModificaLibroUI();
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
	
	public ModificaLibroUI() {
		setAlwaysOnTop(true);
		
		setLocationByPlatform(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Modifica Libro");
		setBounds(100, 100, 424, 222);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodiceDelLibro = new JLabel("Codice del Libro da modificare");
		lblCodiceDelLibro.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCodiceDelLibro.setBounds(17, 29, 266, 20);
		contentPanel.add(lblCodiceDelLibro);
		
		textField = new JTextField();
		textField.setBounds(17, 61, 266, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(0, 0, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String cod=textField.getText().toUpperCase();
						String codiceChecked=CheckClass.checkApostrophe(cod);
						if (cod.isEmpty())
						{
							JOptionPane.showMessageDialog(okButton, "Nessun Codice inserito.\nInserisci il Codice e riprova!","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Libro l=new Libro (null, null, null, null, null, null, codiceChecked, null);
							
							ArrayList<Libro> listaLibri=DAO_Libro.find(l);
							
							if (listaLibri.isEmpty())
							{
								JOptionPane.showMessageDialog(okButton, "Nessun libro trovato, controlla il codice e riprova","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								ModificaLibro2UI modifica2=new ModificaLibro2UI(listaLibri, dialog, textField);
								modifica2.modifica(listaLibri, dialog, textField);
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
		
	}
}
