package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DropMode;
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
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Point;
import java.awt.SystemColor;

public class ModificaLibro2UI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tit;
	private JTextField aut;
	private JTextField casaE;
	private JTextField annoP;
	private JTextField cod;
	private JTextField Coll;
	private JTextField stat;
	private int codice;

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
	 * @param listaLibri 
	 * @param textField 
	 * @param modifica2 
	 * @param cod2 
	 */
	public static void modifica(ArrayList<Libro> listaLibri, ModificaLibroUI dialog2, JTextField textField) {
		try {
			ModificaLibro2UI dialog = new ModificaLibro2UI(listaLibri, dialog2, textField);
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
	 * @param listaLibri 
	 */
	public ModificaLibro2UI(ArrayList<Libro> listaLibri,ModificaLibroUI dialog2, JTextField textField) {
		getContentPane().setBackground(new Color(173, 216, 230));
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setForeground(new Color(0, 191, 255));
		setBackground(UIManager.getColor("Button.disabledForeground"));
		setTitle("Modifica Libro");
		setType(Type.UTILITY);
		setBounds(100, 100, 495, 641);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		tit = new JTextField();
		tit.setToolTipText("");
		tit.setBounds(10, 39, 350, 20);
		getContentPane().add(tit);
		tit.setColumns(10);
		
		aut = new JTextField();
		aut.setToolTipText("");
		aut.setBounds(10, 107, 350, 20);
		getContentPane().add(aut);
		aut.setColumns(10);
		
		casaE = new JTextField();
		casaE.setBounds(10, 175, 350, 20);
		getContentPane().add(casaE);
		casaE.setColumns(10);
		
		annoP = new JTextField();
		annoP.setBounds(10, 243, 350, 20);
		getContentPane().add(annoP);
		annoP.setColumns(10);
		
		cod = new JTextField();
		cod.setEditable(false);
		cod.setBounds(10, 379, 350, 20);
		getContentPane().add(cod);
		cod.setColumns(10);
		
		Coll = new JTextField();
		Coll.setColumns(10);
		Coll.setBounds(10, 447, 350, 20);
		getContentPane().add(Coll);
		
		stat = new JTextField();
		stat.setBounds(10, 515, 350, 20);
		getContentPane().add(stat);
		stat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 6, 46, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autore");
		lblNewLabel_1.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 74, 53, 30);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Casa Editrice");
		lblNewLabel_2.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 142, 99, 30);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Anno Pubblicazione");
		lblNewLabel_3.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 210, 153, 30);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria");
		lblNewLabel_4.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 278, 76, 30);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Codice");
		lblNewLabel_5.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 346, 53, 30);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblCollocazione = new JLabel("Collocazione");
		lblCollocazione.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCollocazione.setBounds(10, 414, 101, 30);
		getContentPane().add(lblCollocazione);
		
		label = new JLabel("Sottocategoria");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		label.setBounds(269, 278, 126, 30);
		getContentPane().add(label);
		
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
						
						comboBoxArte.setBounds(269, 307, 210, 27);
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
						
						comboBoxNarrativa.setBounds(269, 307, 210, 27);
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
					
					comboBoxReligione.setBounds(269, 307, 210, 27);
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
					
					comboBoxVari.setBounds(269, 307, 210, 27);
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
					
					comboBoxScienzeUmane.setBounds(269, 307, 210, 27);
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
					
					comboBoxScienze.setBounds(269, 307, 210, 27);
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
		comboBoxCategorie.setBounds(10, 307, 210, 27);
		getContentPane().add(comboBoxCategorie);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblStato.setBounds(10, 482, 40, 30);
		getContentPane().add(lblStato);
		tit.setText(listaLibri.get(0).getTitolo());
		aut.setText(listaLibri.get(0).getAutore());
		casaE.setText(listaLibri.get(0).getCasaEditrice());
		annoP.setText(listaLibri.get(0).getAnnoPubblicazione().toString());
		cod.setText(listaLibri.get(0).getCodice().toString());
		stat.setText(listaLibri.get(0).getStato());
		Coll.setText(listaLibri.get(0).getCollocazione());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setBounds(96, 563, 264, 39);
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
						if (AnnoPStringa.isEmpty() || (Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", AnnoPStringa))) 
						{
							JOptionPane.showMessageDialog(okButton, "Il campo 'Anno di pubblicazione' e` vuoto o non valido.\nInserisci l'anno di pubblicazione in formato:\nyyyy","Errore!",JOptionPane.ERROR_MESSAGE);
							annoP.setText("");
						}
						else
						{
							Integer AnnoPubblicazione=Integer.parseInt(annoP.getText());
							int length = (int) Math.log10(AnnoPubblicazione) + 1;
							if(length!=4)
							{
								JOptionPane.showMessageDialog(okButton, "Ricontrolla il campo 'Anno di pubblicazione'\nIl formato e`: yyyy","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else if(AnnoPubblicazione>Calendar.getInstance().get(Calendar.YEAR))
							{
								JOptionPane.showMessageDialog(okButton, "Ricontrolla il campo 'Anno di pubblicazione'\nHai inserito una data 'maggiore' di quella attuale","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
						
								String Argomento=categoria.toUpperCase();
								String ArgomentoChecked=CheckClass.checkApostrophe(Argomento);
								String Codice=cod.getText().toUpperCase();
								String CodiceChecked=CheckClass.checkApostrophe(Codice);
								String Stato=stat.getText();
								String Collocazione=Coll.getText().toUpperCase();
								String CollocazioneChecked=CheckClass.checkApostrophe(Collocazione);
						
								Libro l=new Libro(TitoloChecked, AutoreChecked, CasaEditriceChecked, AnnoPubblicazione, ArgomentoChecked, Stato, CodiceChecked, CollocazioneChecked);
								
								GestoreLibro gl=new GestoreLibro();
								
								ArrayList<Libro>listaLibri=new ArrayList<Libro>();
								listaLibri=gl.ricercaLibro(l);
								
								if (gl.modificaLibro(l, listaLibri.get(0).getCodice())==true)
								{
									int n=JOptionPane.showOptionDialog(okButton, "Modifica Effettuata\nVuoi Modificare un altro libro?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
									if(n == JOptionPane.NO_OPTION)
									{
										dispose();
										dialog2.dispose();
									}
									else
									{
										textField.setText("");
										dispose();	
									}
								}
								else
								{
									JOptionPane.showMessageDialog(okButton, "Modifica fallita","Errore!",JOptionPane.ERROR_MESSAGE);
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
				JButton cancelButton = new JButton("Cancel");
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
