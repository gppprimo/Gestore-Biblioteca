package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RicercaLibroSceltaUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaLibroSceltaUI dialog = new RicercaLibroSceltaUI();
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
	public RicercaLibroSceltaUI() {
		setBounds(100, 100, 563, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Titolo e Autore");
		btnNewButton.setFont(new Font("DecoType Naskh", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaLibroTitAutUI ricercaLibroTitAut=new RicercaLibroTitAutUI();
				ricercaLibroTitAut.ricercaLibroTitAut();
				
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(20, 55, 158, 62);
		contentPanel.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Codice");
		btnNewButton_1.setFont(new Font("DecoType Naskh", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaLibroCodUI ricercaLibroCodice=new RicercaLibroCodUI();
				ricercaLibroCodice.ricercaLibroCod();
				
			}
		});
		btnNewButton_1.setBounds(198, 55, 158, 62);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Argomento");
		btnNewButton_2.setFont(new Font("DecoType Naskh", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaLibroArgUI ricercaLibroArg=new RicercaLibroArgUI();
				ricercaLibroArg.ricercaLibroArg();
				
			}
		});
		btnNewButton_2.setBounds(376, 55, 158, 62);
		contentPanel.add(btnNewButton_2);
		
		JLabel lblCheTipoDi = new JLabel("Che tipo di ricerca vuoi fare?");
		lblCheTipoDi.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCheTipoDi.setBounds(24, 20, 215, 30);
		contentPanel.add(lblCheTipoDi);
	}
}
