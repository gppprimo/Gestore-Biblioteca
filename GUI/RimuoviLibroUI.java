package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Libro.GestoreLibro;
import Libro.Libro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;

public class RimuoviLibroUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codLibBar;

	/**
	 * Launch the application.
	 */
	public static void RimuoviLibroUI() {
		try {
			RimuoviLibroUI dialog = new RimuoviLibroUI();
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
	public RimuoviLibroUI() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Rimozione Libro");
		setBounds(100, 100, 423, 163);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCodiceLibro = new JLabel("Codice Libro");
			lblCodiceLibro.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			lblCodiceLibro.setBounds(6, 17, 97, 30);
			contentPanel.add(lblCodiceLibro);
		}
		{
			codLibBar = new JTextField();
			codLibBar.setBounds(6, 45, 287, 20);
			contentPanel.add(codLibBar);
			codLibBar.setColumns(10);
		}
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
						
						String codice=codLibBar.getText().toUpperCase();
						
						Libro l=new Libro(null, null, null, null, null, null, codice , null);
						
						GestoreLibro gl=new GestoreLibro();
						
						ArrayList<Libro> listaLibri=gl.ricercaLibro(l);
						
						if (codice.isEmpty())
						{
							JOptionPane.showMessageDialog(okButton, "Inserisci il codice del libro da Rimuovere","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if (listaLibri.isEmpty())
							{
								JOptionPane.showMessageDialog(okButton, "Libro non presente nel Database","Errore!",JOptionPane.ERROR_MESSAGE);
								
							}
							else
							{
								if (gl.rimuoviLibro(l)==true)
								{
									Object[] options={"Si", "No"};
									int n=JOptionPane.showOptionDialog(okButton, "Rimozione Effettuata\nVuoi Rimuovere un altro libro?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
															
									if (n==JOptionPane.YES_OPTION)
									{
										codLibBar.setText(null);
									}
									else
									{
										dispose();
									}
								}
								else
								{
									JOptionPane.showMessageDialog(okButton, "Rimozione fallita","Errore!",JOptionPane.ERROR_MESSAGE);
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
	}

}
