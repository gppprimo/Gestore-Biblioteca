package GUI;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import FunzioniSupporto.AnnoPubblicazioneException;
import FunzioniSupporto.CheckClass;


import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Label;


import Libro.GestoreLibro;
import Libro.Libro;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import javax.swing.DropMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class InserisciLibroUI extends JDialog {
	private JTextField tit;
	private JTextField aut;
	private JTextField casaE;
	private JFormattedTextField annoP;
	private JFormattedTextField cod;
	private JTextField Coll;
	private String categoria;
	final private String[] Categorie={"Arte", "Attualita`", "Biografico", "Dizionario", "Enciclopedia", "Fiaba", "Filosofia",
			"Geografia", "Letteratura", "Lingue", "Narrativa", "Poetico", "Politico", "Religioso",
			"Scienza", "Scienze Umane", "Sport", "Storico", "Vari"
			};
	final private String[] SottoCategorieArte={"Arte", "Cinema", "Fotografia", "Musica", "Pittura", "Teatro"};
	final private String[] SottoCategorieNarrativa={"Narrativa","Azione e Avventura", "Fantascienza", "Fumetti e Manga", "Gialli e Thriller", 
								 "Horror","Romanzo", "Saggio"
								 };
	final private String[] SottocategorieReligione={"Religione", "Liturgico", "Religioso", "Storico-Religioso", "Teologico"};
	final private String[] SottocategorieVari={"vari", "Diritto", "Giuridico"};
	final private String[] SottocategorieScienzeUmane={"Scienze Umane", "Filosofico-Pedagogico", "Pedagogia", "Psicologia", "Psicopedagogia",
									"Sociologia"
									};
	final private String[] SottocategorieScienza={"Anatomia", "Astronomia", "Botanica", "Chimica", "Fisica", "Geologia", 
							   "Matematica", "Scienza", "Zoologia"
							   };

	private JComboBox comboBoxCategorie = new JComboBox(Categorie);
	private JComboBox comboBoxArte = new JComboBox(SottoCategorieArte);
	private JComboBox comboBoxNarrativa = new JComboBox(SottoCategorieNarrativa);
	private JComboBox comboBoxReligione = new JComboBox(SottocategorieReligione);
	private JComboBox comboBoxVari = new JComboBox(SottocategorieVari);
	private JComboBox comboBoxScienzeUmane = new JComboBox(SottocategorieScienzeUmane);
	private JComboBox comboBoxScienze = new JComboBox(SottocategorieScienza);
	
	private JLabel label=null;
	/**
	 * Launch the application.
	 */
	public static void Inserisci() {
		try {
			InserisciLibroUI dialog = new InserisciLibroUI();
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
	public InserisciLibroUI() {
		getContentPane().setBackground(new Color(173, 216, 230));
		getContentPane().setForeground(new Color(255, 255, 255));
		setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		setResizable(false);
		setLocationByPlatform(true);
		
		setRootPaneCheckingEnabled(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setForeground(new Color(0, 191, 255));
		setBackground(Color.WHITE);
		setTitle("Inserimento Libro\r\n");
		setType(Type.UTILITY);
		setBounds(100, 100, 495, 579);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setBounds(129, 511, 227, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(0, 0, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String Titolo=tit.getText().toUpperCase();
						String TitoloChecked=CheckClass.checkApostrophe(Titolo);
						String Autore=aut.getText().toUpperCase();
						String AutoreChecked=CheckClass.checkApostrophe(Autore);
						String CasaEditrice=casaE.getText().toUpperCase();
						String CasaEditriceChecked=CheckClass.checkApostrophe(CasaEditrice);
						String AnnoPStringa=annoP.getText();
						
						// Controllo se il campo anno e` vuoto o contiene caratteri
						if (AnnoPStringa.equals("    ") || (Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", AnnoPStringa))) 
						{
							JOptionPane.showMessageDialog(okButton, "Il campo 'Anno di pubblicazione' e` vuoto oppure non valido.\nInserisci l'anno di pubblicazione in formato:\n'aaaa'","Errore!",JOptionPane.ERROR_MESSAGE);
							annoP.setText("");
						}
						else
						{
							Integer AnnoPubblicazione=Integer.parseInt(annoP.getText());
							
							if(AnnoPubblicazione>Calendar.getInstance().get(Calendar.YEAR))
							{
								JOptionPane.showMessageDialog(okButton, "Ricontrolla il campo 'Anno di pubblicazione'\nHai inserito una data 'maggiore' di quella attuale","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								String Codice=cod.getText().trim();
								
								if (Codice.isEmpty())
								{
									JOptionPane.showMessageDialog(okButton, "Il campo 'Codice' non puo` essere vuoto","Errore!",JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String Stato="DISPONIBILE";
									String Collocazione=Coll.getText().toUpperCase();
									String CollocazioneChecked=CheckClass.checkApostrophe(Collocazione);
								
										Libro l=new Libro(TitoloChecked, AutoreChecked, CasaEditriceChecked, AnnoPubblicazione, categoria, Stato, Codice, CollocazioneChecked);
										
										GestoreLibro gl=new GestoreLibro();
										
										if (gl.inserisciLibro(l)==true)
										{
											Object[] options={"Si", "No"};
											int n=JOptionPane.showOptionDialog(okButton, "Inserimento Effettuato\nVuoi Inserire un altro libro?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
											
											if (n==JOptionPane.YES_OPTION)
											{
												tit.setText(null);
												aut.setText(null);
												casaE.setText(null);
												annoP.setText(null);
												cod.setText(null);
												Coll.setText(null);
											}
											else
											{
												dispose();
											}
												
										}
										else
										{
													JOptionPane.showMessageDialog(okButton, "Inserimento fallito\n Probabilmente il codice e` gia` presente nel database","Errore!",JOptionPane.ERROR_MESSAGE);
										}
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
		
		label = new JLabel("Sottocategoria");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		label.setBounds(269, 305, 126, 30);
		getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel.setBounds(6, 25, 46, 30);
		getContentPane().add(lblNewLabel);
		
		tit = new JTextField();
		tit.setBounds(6, 58, 350, 20);
		getContentPane().add(tit);
		tit.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Autore");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(6, 95, 53, 30);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Casa Editrice");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(6, 165, 99, 30);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Anno Pubblicazione");
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(6, 235, 153, 30);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria");
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(6, 305, 88, 30);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Codice");
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(6, 375, 53, 30);
		getContentPane().add(lblNewLabel_5);
		
		aut = new JTextField();
		aut.setBounds(6, 128, 350, 20);
		getContentPane().add(aut);
		aut.setColumns(10);
		
		casaE = new JTextField();
		casaE.setBounds(6, 198, 350, 20);
		getContentPane().add(casaE);
		casaE.setColumns(10);
		
		MaskFormatter mask=null;
		try {
			mask=new MaskFormatter("####");
			//mask.setPlaceholder("aaaa");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		annoP = new JFormattedTextField(mask);
		
		annoP.setBounds(6, 268, 350, 20);
		getContentPane().add(annoP);
		annoP.setColumns(10);
		
		MaskFormatter mask2=null;
		try {
			mask2=new MaskFormatter("##########");
			//mask2.setPlaceholder("");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cod = new JFormattedTextField(mask2);
		cod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				cod.setCaretPosition(0);
				
			}
		});
		cod.setFocusLostBehavior(JFormattedTextField.PERSIST);
		cod.setBounds(6, 408, 350, 20);
		getContentPane().add(cod);
		cod.setColumns(10);
		
		JLabel lblCollocazione = new JLabel("Collocazione");
		lblCollocazione.setForeground(Color.DARK_GRAY);
		lblCollocazione.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCollocazione.setBounds(6, 445, 101, 30);
		getContentPane().add(lblCollocazione);
		
		Coll = new JTextField();
		Coll.setColumns(10);
		Coll.setBounds(6, 478, 350, 20);
		getContentPane().add(Coll);
		
		comboBoxCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selezione=comboBoxCategorie.getSelectedItem().toString();
				switch (selezione)
				{
				case "Arte": {
						getContentPane().remove(comboBoxNarrativa);
						getContentPane().remove(comboBoxVari);
						getContentPane().remove(comboBoxReligione);
						getContentPane().remove(comboBoxScienze);
						getContentPane().remove(comboBoxScienzeUmane);
						
						comboBoxArte.setBounds(269, 336, 210, 27);
						getContentPane().add(comboBoxArte);
						revalidate();
						repaint();
						categoria=comboBoxArte.getSelectedItem().toString().toUpperCase();
						
						comboBoxArte.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							
								categoria=comboBoxArte.getSelectedItem().toString().toUpperCase();
								System.out.println(categoria);
							}
						});
						break;
						}
				
				case "Narrativa": {
					
						categoria="NARRATIVA";
						getContentPane().remove(comboBoxArte);
						getContentPane().remove(comboBoxVari);
						getContentPane().remove(comboBoxReligione);
						getContentPane().remove(comboBoxScienze);
						getContentPane().remove(comboBoxScienzeUmane);
						
						comboBoxNarrativa.setBounds(269, 336, 210, 27);
						getContentPane().add(comboBoxNarrativa);
						revalidate();
						repaint();
						categoria=comboBoxNarrativa.getSelectedItem().toString().toUpperCase();
						
						comboBoxNarrativa.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							
								categoria=comboBoxNarrativa.getSelectedItem().toString().toUpperCase();
								System.out.println(categoria);
							}
						});
						break;
				
				}
				case "Religione": {
					
					getContentPane().remove(comboBoxNarrativa);
					getContentPane().remove(comboBoxVari);
					getContentPane().remove(comboBoxScienze);
					getContentPane().remove(comboBoxReligione);
					getContentPane().remove(comboBoxScienzeUmane);
					
					comboBoxReligione.setBounds(269, 336, 210, 27);
					getContentPane().add(comboBoxReligione);
					revalidate();
					repaint();
					categoria=comboBoxReligione.getSelectedItem().toString().toUpperCase();
					
					comboBoxReligione.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						
							categoria=comboBoxReligione.getSelectedItem().toString().toUpperCase();
							System.out.println(categoria);
						}
					});
						break;
						
				}
				case "Vari": {
					
					getContentPane().remove(comboBoxNarrativa);
					getContentPane().remove(comboBoxVari);
					getContentPane().remove(comboBoxScienze);
					getContentPane().remove(comboBoxReligione);
					getContentPane().remove(comboBoxScienzeUmane);
					
					comboBoxVari.setBounds(269, 336, 210, 27);
					getContentPane().add(comboBoxVari);
					revalidate();
					repaint();
					categoria=comboBoxVari.getSelectedItem().toString().toUpperCase();
					
					comboBoxVari.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						
							categoria=comboBoxVari.getSelectedItem().toString().toUpperCase();
							System.out.println(categoria);
						}
					});
					break;
						
				}
				case "Scienze Umane": {
					
					getContentPane().remove(comboBoxNarrativa);
					getContentPane().remove(comboBoxVari);
					getContentPane().remove(comboBoxScienze);
					getContentPane().remove(comboBoxReligione);
					getContentPane().remove(comboBoxScienzeUmane);
					
					comboBoxScienzeUmane.setBounds(269, 336, 210, 27);
					getContentPane().add(comboBoxScienzeUmane);
					revalidate();
					repaint();
					categoria=comboBoxScienzeUmane.getSelectedItem().toString().toUpperCase();
					
					comboBoxScienzeUmane.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						
							categoria=comboBoxScienzeUmane.getSelectedItem().toString().toUpperCase();
							System.out.println(categoria);
						}
					});
					break;
						
				}
				case "Scienza": {
					
					getContentPane().remove(comboBoxNarrativa);
					getContentPane().remove(comboBoxVari);
					getContentPane().remove(comboBoxScienze);
					getContentPane().remove(comboBoxReligione);
					getContentPane().remove(comboBoxScienzeUmane);
					
					comboBoxScienze.setBounds(269, 336, 210, 27);
					getContentPane().add(comboBoxScienze);
					revalidate();
					repaint();
					categoria=comboBoxScienze.getSelectedItem().toString().toUpperCase();
					
					comboBoxScienze.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						
							categoria=comboBoxScienze.getSelectedItem().toString().toUpperCase();
							System.out.println(categoria);
						}
					});
					break;
				}	
				
				default: {
				categoria=selezione.toUpperCase();
				getContentPane().remove(comboBoxArte);
				getContentPane().remove(comboBoxVari);
				getContentPane().remove(comboBoxReligione);
				getContentPane().remove(comboBoxScienze);
				getContentPane().remove(comboBoxScienzeUmane);
				revalidate();
				repaint();
				break;
				}
			}
			}
			
		});
		comboBoxCategorie.setBounds(6, 336, 210, 27);
		getContentPane().add(comboBoxCategorie);
	}
}
