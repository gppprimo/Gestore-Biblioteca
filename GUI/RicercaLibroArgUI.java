package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class RicercaLibroArgUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	public static void ricercaLibroArg() {
		try {
			RicercaLibroArgUI dialog = new RicercaLibroArgUI();
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
	public RicercaLibroArgUI() {
		
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
			JLabel lblTitolo = new JLabel("Categoria");
			lblTitolo.setBounds(10, 11, 76, 30);
			lblTitolo.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
			contentPanel.add(lblTitolo);
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
			comboBoxCategorie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String selezione=comboBoxCategorie.getSelectedItem().toString();
					switch (selezione)
					{
					case "Arte": {
							contentPanel.remove(comboBoxNarrativa);
							contentPanel.remove(comboBoxVari);
							contentPanel.remove(comboBoxReligione);
							contentPanel.remove(comboBoxScienze);
							contentPanel.remove(comboBoxScienzeUmane);
							
							comboBoxArte.setBounds(365, 39, 210, 27);
							//getContentPane().add(comboBoxArte);
							contentPanel.add(comboBoxArte);
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
							contentPanel.remove(comboBoxArte);
							contentPanel.remove(comboBoxVari);
							contentPanel.remove(comboBoxReligione);
							contentPanel.remove(comboBoxScienze);
							contentPanel.remove(comboBoxScienzeUmane);
							
							comboBoxNarrativa.setBounds(365, 39, 210, 27);
							contentPanel.add(comboBoxNarrativa);
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
						
						contentPanel.remove(comboBoxNarrativa);
						contentPanel.remove(comboBoxVari);
						contentPanel.remove(comboBoxScienze);
						contentPanel.remove(comboBoxReligione);
						contentPanel.remove(comboBoxScienzeUmane);
						
						comboBoxReligione.setBounds(365, 39, 210, 27);
						contentPanel.add(comboBoxReligione);
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
						
						contentPanel.remove(comboBoxNarrativa);
						contentPanel.remove(comboBoxVari);
						contentPanel.remove(comboBoxScienze);
						contentPanel.remove(comboBoxReligione);
						contentPanel.remove(comboBoxScienzeUmane);
						
						comboBoxVari.setBounds(365, 39, 210, 27);
						contentPanel.add(comboBoxVari);
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
						
						contentPanel.remove(comboBoxNarrativa);
						contentPanel.remove(comboBoxVari);
						contentPanel.remove(comboBoxScienze);
						contentPanel.remove(comboBoxReligione);
						contentPanel.remove(comboBoxScienzeUmane);
						
						comboBoxScienzeUmane.setBounds(365, 39, 210, 27);
						contentPanel.add(comboBoxScienzeUmane);
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
						
						contentPanel.remove(comboBoxNarrativa);
						contentPanel.remove(comboBoxVari);
						contentPanel.remove(comboBoxScienze);
						contentPanel.remove(comboBoxReligione);
						contentPanel.remove(comboBoxScienzeUmane);
						
						comboBoxScienze.setBounds(365, 39, 210, 27);
						contentPanel.add(comboBoxScienze);
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
					contentPanel.remove(comboBoxArte);
					contentPanel.remove(comboBoxVari);
					contentPanel.remove(comboBoxReligione);
					contentPanel.remove(comboBoxScienze);
					contentPanel.remove(comboBoxScienzeUmane);
					revalidate();
					repaint();
					break;
					}
				}					
				}
			});
			comboBoxCategorie.setBounds(6, 39, 210, 27);
			contentPanel.add(comboBoxCategorie);
		}
		
		
		JLabel lblNewLabel = new JLabel("Sottocategoria");
		lblNewLabel.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel.setBounds(365, 11, 114, 30);
		contentPanel.add(lblNewLabel);
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
						
						String argomento=categoria.toUpperCase();
						String argomentoChecked=CheckClass.checkApostrophe(argomento);
						
						if (argomento.equals(""))
						{
							JOptionPane.showMessageDialog(okButton, "Non hai compilato il campo 'Argomento' !", "Errore",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Libro l=new Libro (null, null, null, null, argomentoChecked, null, null, null);
							
							GestoreLibro gl=new GestoreLibro();
							
							ArrayList<Libro> lista;
							
							lista = gl.ricercaLibroByArg(l);
							
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
