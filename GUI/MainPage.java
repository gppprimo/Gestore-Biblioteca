package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;

import Libro.GestoreLibro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Canvas;
import javax.swing.JPopupMenu;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import FunzioniSupporto.BackUp;

import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class MainPage {

	private JFrame frmGestoreBiblioteca;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmGestoreBiblioteca.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					int x, y;
					y = (screenSize.height/2) - (window.frmGestoreBiblioteca.getHeight()/2);
					x = (screenSize.width/2) - (window.frmGestoreBiblioteca.getWidth()/2);
					window.frmGestoreBiblioteca.setLocation(x, y);
					window.frmGestoreBiblioteca.setAlwaysOnTop(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestoreBiblioteca = new JFrame();
		frmGestoreBiblioteca.setResizable(false);
		frmGestoreBiblioteca.setBackground(Color.WHITE);
		frmGestoreBiblioteca.setFont(new Font("Dialog", Font.PLAIN, 14));
		frmGestoreBiblioteca.setTitle("Gestore Biblioteca");
		
		frmGestoreBiblioteca.getContentPane().setBackground(new Color(100, 149, 237));
		frmGestoreBiblioteca.getContentPane().setLayout(null);
	
		JButton btnNewButton = new JButton("Ricerca Libro");
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*RicercaLibroUI ricerca=new RicercaLibroUI();
				ricerca.ricerca();
				*/
				
				RicercaLibroSceltaUI ricercaLibroScelta=new RicercaLibroSceltaUI();
				ricercaLibroScelta.main(null);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-2.png")));
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnNewButton.setBounds(274, 42, 252, 76);
		frmGestoreBiblioteca.getContentPane().add(btnNewButton);
		
		JButton btnModificaLibro = new JButton("Modifica Libro");
		btnModificaLibro.setBackground(UIManager.getColor("Button.background"));
		btnModificaLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificaLibroUI modifica=new ModificaLibroUI();
				modifica.modifica();
			}
		});
		btnModificaLibro.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-4.png")));
		btnModificaLibro.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnModificaLibro.setForeground(new Color(0, 0, 255));
		btnModificaLibro.setBounds(537, 42, 252, 76);
		frmGestoreBiblioteca.getContentPane().add(btnModificaLibro);
		
		JButton btnAggiungiLibro = new JButton("Aggiungi Libro");
		btnAggiungiLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InserisciLibroUI i=new InserisciLibroUI();
				i.Inserisci();
				
			}
		});
		btnAggiungiLibro.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnAggiungiLibro.setBackground(UIManager.getColor("Button.background"));
		btnAggiungiLibro.setForeground(new Color(0, 0, 255));
		btnAggiungiLibro.setFocusPainted(false);
		btnAggiungiLibro.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-3.png")));
		btnAggiungiLibro.setBounds(11, 42, 252, 76);
		frmGestoreBiblioteca.getContentPane().add(btnAggiungiLibro);
		
		JButton btnRimuoviLibro = new JButton("Rimuovi Libro");
		btnRimuoviLibro.setBackground(UIManager.getColor("Button.background"));
		btnRimuoviLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RimuoviLibroUI rimuovi=new RimuoviLibroUI();
				rimuovi.RimuoviLibroUI();
				
			}
		});
		btnRimuoviLibro.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnRimuoviLibro.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-5.png")));
		btnRimuoviLibro.setForeground(new Color(0, 0, 255));
		btnRimuoviLibro.setBounds(800, 42, 252, 76);
		frmGestoreBiblioteca.getContentPane().add(btnRimuoviLibro);
		
		JButton btnOperazioniDiPrestito = new JButton("Operazioni di Prestito Libri");
		btnOperazioniDiPrestito.setBackground(UIManager.getColor("Button.background"));
		btnOperazioniDiPrestito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				OperazioniDiPrestitoUI OP=new OperazioniDiPrestitoUI();
				OP.OperazioniPrestito();
			}
		});
		btnOperazioniDiPrestito.setForeground(new Color(255, 140, 0));
		btnOperazioniDiPrestito.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnOperazioniDiPrestito.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-7.png")));
		btnOperazioniDiPrestito.setBounds(139, 170, 338, 76);
		frmGestoreBiblioteca.getContentPane().add(btnOperazioniDiPrestito);
		
		JButton btnRestituzioneLibri = new JButton("Restituzione Libri");
		btnRestituzioneLibri.setBackground(UIManager.getColor("Button.background"));
		btnRestituzioneLibri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RestituzioneLibroUI2 restituzione=new RestituzioneLibroUI2();
				restituzione.Restituzione();
			}
		});
		btnRestituzioneLibri.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-6.png")));
		btnRestituzioneLibri.setForeground(new Color(255, 140, 0));
		btnRestituzioneLibri.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnRestituzioneLibri.setBounds(616, 170, 308, 76);
		frmGestoreBiblioteca.getContentPane().add(btnRestituzioneLibri);
		
		JButton btnCercaPrestitiEffettuati = new JButton("Cerca Prestiti Effettuati");
		btnCercaPrestitiEffettuati.setBackground(UIManager.getColor("Button.background"));
		btnCercaPrestitiEffettuati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaPrestitiEffettuatiUI2 rpe=new RicercaPrestitiEffettuatiUI2();
				rpe.main(null);
			}
		});
		btnCercaPrestitiEffettuati.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-8.png")));
		btnCercaPrestitiEffettuati.setForeground(new Color(0, 128, 0));
		btnCercaPrestitiEffettuati.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnCercaPrestitiEffettuati.setBounds(137, 304, 338, 76);
		frmGestoreBiblioteca.getContentPane().add(btnCercaPrestitiEffettuati);
		
		JButton btnCercaPrestitiScaduti = new JButton("Cerca Prestiti Scaduti");
		btnCercaPrestitiScaduti.setBackground(UIManager.getColor("Button.background"));
		btnCercaPrestitiScaduti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RicercaPrestitiScadutiUI rps=new RicercaPrestitiScadutiUI();
				rps.main(null);
			}
		});
		btnCercaPrestitiScaduti.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/notebook-9.png")));
		btnCercaPrestitiScaduti.setForeground(new Color(0, 128, 0));
		btnCercaPrestitiScaduti.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnCercaPrestitiScaduti.setBounds(612, 304, 314, 76);
		frmGestoreBiblioteca.getContentPane().add(btnCercaPrestitiScaduti);
		
		JButton btnBackup = new JButton("Back-Up");
		btnBackup.setBackground(UIManager.getColor("Button.background"));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BackUp bu=new BackUp();
				boolean esito=false;
				if (esito=bu.BackUpBatabase()==true)
				{
					JOptionPane.showMessageDialog(btnBackup, "Back-Up Effettuato","Info!",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					Component okButton = null;
					JOptionPane.showMessageDialog(okButton, "Back-Up Fallito","Errore!",JOptionPane.ERROR_MESSAGE, null);
				}
				
			}
		});
		btnBackup.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/iconBU.png")));
		btnBackup.setForeground(new Color(255, 0, 0));
		btnBackup.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnBackup.setBounds(11, 422, 188, 76);
		frmGestoreBiblioteca.getContentPane().add(btnBackup);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBackground(UIManager.getColor("Button.background"));
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmGestoreBiblioteca.dispose();
			}
		});
		btnEsci.setIcon(new ImageIcon(MainPage.class.getResource("/Icons64x64/exit1.png")));
		btnEsci.setForeground(new Color(255, 0, 0));
		btnEsci.setFont(new Font("DecoType Naskh", Font.BOLD, 18));
		btnEsci.setBounds(908, 422, 144, 76);
		frmGestoreBiblioteca.getContentPane().add(btnEsci);
		frmGestoreBiblioteca.setBounds(100, 100, 1065, 567);
		frmGestoreBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.DARK_GRAY);
		frmGestoreBiblioteca.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmEsci = new JMenuItem("Esci");
		mnNewMenu.add(mntmEsci);
		
		mntmEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestoreBiblioteca.dispose();
			}
		});
	
		
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
