package CF_Generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author Pasquale Puzio
 *
 * Dialog che permette la ricerca e la selezione del proprio Comune di nascita
 */
public class ComuniDialog extends JDialog implements ActionListener
{
	private ComuniTable listaComuni;
	
	private CalcPanel panel;
	
	private JButton okButton, cancelButton, searchButton;
	
	private JList comuniList;
	
	private JTextField search;
	
	public ComuniDialog(ComuniTable comuniTable, CalcPanel calcPanel)
	{
		listaComuni = comuniTable;
		panel = calcPanel;
		setTitle("Seleziona il comune");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		// codice per centrare la dialog
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x, y;
		y = (screenSize.height/2) - (getHeight()/2);
		x = (screenSize.width/2) - (getWidth()/2);
		setLocation(x, y);
		createComponent();
		setVisible(true);
	}
	
	private void createComponent()
	{
		JPanel nord = new JPanel(new GridLayout(1, 2));
		search = new JTextField();
		search.setHorizontalAlignment(JTextField.CENTER);
		search.addKeyListener(new KeyAdapter() 
	  	{
	  		// azione associata alla pressione di ENTER
	  		public void keyPressed (KeyEvent e) 
	  		{
	  			if (e.getKeyCode() == KeyEvent.VK_ENTER)
	  				searchButton.doClick();
	  		}
	  	});
		searchButton = new JButton("Cerca");
			searchButton.addActionListener(this);
		nord.add(search);
		nord.add(searchButton);
		this.add(nord, BorderLayout.NORTH);
		comuniList = new JList();
			comuniList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			comuniList.setListData(listaComuni.getAllCities());
			comuniList.addKeyListener(new KeyAdapter() 
		  	{
		  		// azione associata alla pressione di ENTER
		  		public void keyPressed (KeyEvent e) 
		  		{
		  			if (e.getKeyCode() == KeyEvent.VK_ENTER)
		  				okButton.doClick();
		  		}
		  	});
		JScrollPane scroll = new JScrollPane(comuniList);
		this.add(scroll, BorderLayout.CENTER);
		JPanel sud = new JPanel(new GridLayout(1, 2));
		okButton = new JButton("Conferma");
			okButton.addActionListener(this);
		cancelButton = new JButton("Annulla");
			cancelButton.addActionListener(this);
		sud.add(okButton);
		sud.add(cancelButton);
		this.add(sud, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source.equals(cancelButton))
		{
			dispose();
		}
		else if (source.equals(okButton))
		{
			if (comuniList.getSelectedValue() != null)
			{
				panel.setComune(comuniList.getSelectedValue().toString(), listaComuni.getCode(comuniList.getSelectedValue().toString()));
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Seleziona un comune altrimenti clicca su Annulla", "Nessun comune selezionato", JOptionPane.WARNING_MESSAGE);
		}
		else if (source.equals(searchButton))
		{
			if (search.getText() != null)
				comuniList.setListData(listaComuni.searchCities(search.getText().toUpperCase()));
			else
				comuniList.setListData(listaComuni.getAllCities());
		}
	}

}
