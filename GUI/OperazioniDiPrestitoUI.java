package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CF_Generator.MainFrame;

import FunzioniSupporto.CheckClass;
import FunzioniSupporto.ID_Generator;
import Libro.GestoreLibro;
import Libro.Libro;
import UtilizzatoreServizio.GestoreUtilizzatoreServizio;
import UtilizzatoreServizio.UtilizzatoreServizio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class OperazioniDiPrestitoUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textCF;
	private JTextField textTel;
	private JTextField textInd;
	private JTextField textCodiceLibro;

	/**
	 * Launch the application.
	 */
	public static void OperazioniPrestito() {
		try {
			OperazioniDiPrestitoUI dialog = new OperazioniDiPrestitoUI();
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
	public OperazioniDiPrestitoUI() {
		setTitle("Prestito Libro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(173, 216, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(22, 52, 223, 26);
		contentPanel.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setBounds(22, 134, 223, 26);
		contentPanel.add(textCognome);
		textCognome.setColumns(10);
		
		textCF = new JTextField();
		
		textCF.setBounds(22, 216, 223, 26);
		contentPanel.add(textCF);
		textCF.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(22, 298, 223, 26);
		contentPanel.add(textTel);
		textTel.setColumns(10);
		
		JLabel lblNomeUtilizzatore = new JLabel("Nome");
		lblNomeUtilizzatore.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNomeUtilizzatore.setBounds(21, 22, 46, 30);
		contentPanel.add(lblNomeUtilizzatore);
		
		JLabel lblCognomeUtilizzatore = new JLabel("Cognome");
		lblCognomeUtilizzatore.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCognomeUtilizzatore.setBounds(21, 104, 75, 30);
		contentPanel.add(lblCognomeUtilizzatore);
		
		JLabel lblCodiceFiscale = new JLabel("Codice Fiscale");
		lblCodiceFiscale.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCodiceFiscale.setBounds(21, 186, 109, 30);
		contentPanel.add(lblCodiceFiscale);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 268, 69, 30);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblIndirizzo.setBounds(21, 350, 70, 30);
		contentPanel.add(lblIndirizzo);
		
		textInd = new JTextField();
		textInd.setBounds(22, 380, 223, 26);
		contentPanel.add(textInd);
		textInd.setColumns(10);
		
		JLabel lblCodiceDelLibro = new JLabel("Codice del Libro da associare");
		lblCodiceDelLibro.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblCodiceDelLibro.setBounds(381, 22, 222, 30);
		contentPanel.add(lblCodiceDelLibro);
		
		textCodiceLibro = new JTextField();
		textCodiceLibro.setBounds(381, 54, 184, 26);
		contentPanel.add(textCodiceLibro);
		textCodiceLibro.setColumns(10);
		
		JButton btnCalcolaCf = new JButton("Calcolalo !");
		
		contentPanel.add(btnCalcolaCf);
		
		JLabel lblHaiBisognoDel = new JLabel("Hai bisogno del Cofice Fiscale?");
		lblHaiBisognoDel.setFont(new Font("DecoType Naskh", Font.PLAIN, 16));
		lblHaiBisognoDel.setForeground(Color.DARK_GRAY);
		lblHaiBisognoDel.setBounds(381, 321, 234, 30);
		contentPanel.add(lblHaiBisognoDel);
		
		JButton btnNewButton = new JButton("Calcolalo !");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnNewButton.setForeground(Color.RED);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new MainFrame();
			}
		
		});
		
		btnNewButton.setBounds(432, 350, 117, 29);
		contentPanel.add(btnNewButton);
		
		JButton btnIncolla = new JButton("Incolla");
		btnIncolla.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnIncolla.setForeground(Color.BLUE);
		btnIncolla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCF.setText("");
				textCF.paste();
			}
		});
		btnIncolla.setBounds(257, 218, 117, 29);
		contentPanel.add(btnIncolla);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				okButton.setBackground(Color.BLUE);
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("unused")
					public void actionPerformed(ActionEvent e) {
						
						String nome=textNome.getText().toUpperCase();
						String nomeChecked=CheckClass.checkApostrophe(nome);
						String cognome=textCognome.getText().toUpperCase();
						String cognomeChecked=CheckClass.checkApostrophe(cognome);
						String CF=textCF.getText().toUpperCase();
						String CFChecked=CheckClass.checkApostrophe(CF);
						String Tel=(textTel.getText()).toUpperCase();
						String TelChecked=CheckClass.checkApostrophe(Tel);
						String Indirizzo=textInd.getText().toUpperCase();
						String IndirizzoChecked=CheckClass.checkApostrophe(Indirizzo);
						String codiceLibro=textCodiceLibro.getText().toUpperCase();
						String codiceLibroChecked=CheckClass.checkApostrophe(codiceLibro);
						
						String special = "!@#$%^&*()_";
						String pattern = ".*[" + Pattern.quote(special) + "].*";
						
						if (CFChecked.isEmpty())
						{
							JOptionPane.showMessageDialog(okButton, "Si prega di inserire il 'Codice Fiscale'","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else if (Pattern.matches(special,  nome) || (Pattern.matches(special, cognome)))
						{
							JOptionPane.showMessageDialog(okButton, "Hai inserito un carattere nel campo Nome o Cognome'","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else if (Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", Tel))
						{
							JOptionPane.showMessageDialog(okButton, "Controlla il campo 'Numero di Telefono' !","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else if (codiceLibro.isEmpty())
						{
							JOptionPane.showMessageDialog(okButton, "Non hai inserito alcun codice.\nSi prega di riempire il campo 'Codice' !","Errore!",JOptionPane.ERROR_MESSAGE);
						}
						else
						{		
							
							Long IDUtilizzatore=ID_Generator.ID_Generation();
							Long IDUtilizzatoreChecked=CheckClass.checkIDUtilizzatore(IDUtilizzatore);
							
							GestoreUtilizzatoreServizio gestoreUS=new GestoreUtilizzatoreServizio();
																	
							
							
							Libro l=new Libro (null, null, null, null, null, null, codiceLibroChecked, null);
							UtilizzatoreServizio US=new UtilizzatoreServizio(nomeChecked, cognomeChecked, CFChecked, TelChecked, IndirizzoChecked, 1, IDUtilizzatoreChecked);
							
							GestoreLibro gl=new GestoreLibro();
							ArrayList<Libro> listaLibri=gl.ricercaLibroByCod(l);
		
							//controllo codice libro da associare
							
							if (listaLibri.isEmpty())
							{
								JOptionPane.showMessageDialog(okButton, "Libro non presente nel DataBase\nControlla il codice","Errore!",JOptionPane.ERROR_MESSAGE);
							}
							else if (nomeChecked.isEmpty() || cognomeChecked.isEmpty())
							{
								JOptionPane.showMessageDialog(okButton, "Il campo 'Nome' ed il campo 'Cognome' sono vuoti.\nSi prega di inserire almeno 'Nome' e 'Cognome'","Errore!",JOptionPane.ERROR_MESSAGE);
							}
													
							else
							{
								
								//if find utilizzatore by nome-cognome OK -> check CI || CF || NP
								ArrayList<UtilizzatoreServizio> listaUtilizzatoriStessoNomeCognome=gestoreUS.ricercaUtilizzatore2(nomeChecked, cognomeChecked);
								if ((listaUtilizzatoriStessoNomeCognome.size())!=0)
								{
									for (int i=0;i<listaUtilizzatoriStessoNomeCognome.size();i++)
									{
										/*Object[] options={"Si", "No"};
										int n=JOptionPane.showOptionDialog(okButton, "Nome e Cognome gia` presente nel DataBase!\nUno dei seguenti campi corrisponde?\n"
												+ "Codice Fiscale: "+listaUtilizzatoriStessoNomeCognome.get(i).getCF()+"\nNumero di Carta di Identita`: "+listaUtilizzatoriStessoNomeCognome.get(i).getCI()
												+"\nNumero di Patente: "+listaUtilizzatoriStessoNomeCognome.get(i).getNumPatente(), "Attenzione!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
										*/
																				
										if ((listaUtilizzatoriStessoNomeCognome.get(i).getCF().equals(CF)))
										{
											
											if (listaUtilizzatoriStessoNomeCognome.get(i).getNumeroLibriPossesso()==2)
											{
												JOptionPane.showMessageDialog(okButton, "Beneficiario gia` in possesso di 2 libri.\nNon puo` richiederne altri!","Errore!",JOptionPane.ERROR_MESSAGE);
												break;
											}
											else
											{
												boolean esito2=gl.effettuaPrestitoLibro(listaUtilizzatoriStessoNomeCognome.get(i).getID(), codiceLibroChecked);
												boolean esito=gestoreUS.incrementaNumeroLibriPossesso(listaUtilizzatoriStessoNomeCognome.get(i).getID());
													
												if (esito==true && esito2==true)
												{
													Object[] options={"Si", "No"};
													int n=JOptionPane.showOptionDialog(okButton, "Operazione Eseguita\nVuoi eseguire un'altra operazione?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
													
													if (n==JOptionPane.YES_OPTION)
													{
														textNome.setText(null);
														textCognome.setText(null);
														textCF.setText(null);
														textTel.setText(null);
														textInd.setText(null);
														textCodiceLibro.setText(null);
														break;
													}
													else
													{
														dispose();
														break;
													}
												}
												else
												{
													JOptionPane.showMessageDialog(okButton, "L'operazione non e` andata a buon fine!\nIl Libro inserito potrebbe essere gia` in prestito","Errore!",JOptionPane.ERROR_MESSAGE);
													break;
												}
											}		
										}
										else
										{
											boolean esito1=gestoreUS.inserisciUtilizzatore(US);
											boolean esito2=gl.effettuaPrestitoLibro(US.getID(), codiceLibroChecked);
											
											if (esito1==true && esito2==true)
											{
												Object[] options={"Si", "No"};
												int n=JOptionPane.showOptionDialog(okButton, "Operazione Eseguita\nVuoi eseguire un'altra operazione?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
												
												if (n==JOptionPane.YES_OPTION)
												{
													textNome.setText(null);
													textCognome.setText(null);													
													textCF.setText(null);
													textTel.setText(null);
													textInd.setText(null);
													textCodiceLibro.setText(null);
													break;
												}
												else
												{
													dispose();
													break;
												}
											}
											else
											{
													JOptionPane.showMessageDialog(okButton, "Libro gia` in prestito","Errore!",JOptionPane.ERROR_MESSAGE);
													break;
											}	
										}
									}
								}
								else
								{
									boolean esito1=gestoreUS.inserisciUtilizzatore(US);
									boolean esito2=gl.effettuaPrestitoLibro(IDUtilizzatoreChecked, codiceLibroChecked);
									
									if (esito1==true && esito2==true)
									{
										Object[] options={"Si", "No"};
										int n=JOptionPane.showOptionDialog(okButton, "Operazione Eseguita\nVuoi eseguire un'altra operazione?\n", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
										
										if (n==JOptionPane.YES_OPTION)
										{
											textNome.setText(null);
											textCognome.setText(null);
											textCF.setText(null);
											textTel.setText(null);
											textInd.setText(null);
											textCodiceLibro.setText(null);
											
										}
										else
										{
											dispose();
										}
									}
									else
									{
											JOptionPane.showMessageDialog(okButton, "Libro gia` in prestito","Errore!",JOptionPane.ERROR_MESSAGE);
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
				cancelButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
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
