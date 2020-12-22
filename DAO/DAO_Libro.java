package DAO;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DataBaseConnection.DBManager;
import Libro.Libro;

public class DAO_Libro {

	public static boolean insert(Libro l) {
		
		boolean esito = false;
		
		Connection conn=DBManager.getConnection();
		Statement stat,stat2;
		
		try {
			stat2=conn.createStatement();
			stat2.execute("CREATE TABLE IF NOT EXISTS LIBRO (TITOLO VARCHAR(255), AUTORE VARCHAR(255), CASAEDITRICE VARCHAR(255), ANNOPUBBLICAZIONE INT, ARGOMENTO VARCHAR(255), STATO VARCHAR(40), CODICE VARCHAR(40) NOT NULL PRIMARY KEY, COLLOCAZIONE VARCHAR(20))");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			stat=conn.createStatement();
			stat.execute("INSERT INTO LIBRO VALUES ('"+l.getTitolo()+"', '"+l.getAutore()+"', '"+l.getCasaEditrice()+"', '"+l.getAnnoPubblicazione()+
					"', '"+l.getArgomento()+"', '"+l.getStato()+"', '"+l.getCodice()+"', '"+l.getCollocazione()+"')");
			stat.close();
			esito=true;
			
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		
		return esito;
	}
	
	
	public static boolean remove (Libro l){
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		Statement stat;
		
		try {
			stat=conn.createStatement();
			stat.execute("DELETE FROM LIBRO WHERE CODICE='"+l.getCodice()+"'");
			stat.close();
			esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esito;
		
	}
	
	
	
	public static ArrayList<Libro> find (Libro l) {

		ArrayList<Libro> listaLibri=new ArrayList<Libro>();
		
		Connection conn=DBManager.getConnection();
		
		if (l.getCodice()!=null)
		{
			try {
				Statement stat=conn.createStatement();
				
				ResultSet res=stat.executeQuery("SELECT * FROM LIBRO WHERE CODICE='"+l.getCodice()+"'");
				
				while (res.next())
				{
					listaLibri.add(new Libro (res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
							res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
				}
										
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (l.getTitolo().isEmpty()==false && l.getAutore().isEmpty())
			{
				PreparedStatement pstat;
				try {
					pstat = conn.prepareStatement("SELECT * FROM LIBRO WHERE TITOLO='"+l.getTitolo()+"'");
					ResultSet res=pstat.executeQuery();
					while(res.next())
					{
						listaLibri.add(new Libro(res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
										res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			else if (l.getTitolo().isEmpty() && l.getAutore().isEmpty()==false)
			{
				PreparedStatement pstat;
				try {
					pstat = conn.prepareStatement("SELECT * FROM LIBRO WHERE AUTORE='"+l.getAutore()+"'");
					ResultSet res=pstat.executeQuery();
					while(res.next())
					{
						listaLibri.add(new Libro(res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
										res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "List contained 0 elements!", "Error",
		                JOptionPane.ERROR_MESSAGE);
			}
		
		return listaLibri;
	}
	
	
	public static Libro find2(Libro l) {
		
		Connection conn=DBManager.getConnection();
		Libro libro=new Libro(null, null, null, null, null, null, null, null);
			try {
				Statement stat=conn.createStatement();
				
				ResultSet res=stat.executeQuery("SELECT * FROM LIBRO WHERE CODICE="+l.getCodice());
				
				while (res.next())
				{
					libro=new Libro (res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
							res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE"));
				}
										
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return libro;
	}


	public static boolean modify(Libro l, String string) {
		// TODO Auto-generated method stub
		boolean esito = false;
		
		Connection conn=DBManager.getConnection();
		try {
			Statement stat=conn.createStatement();
			stat.execute("UPDATE LIBRO SET TITOLO = '"+l.getTitolo()
					+ "', AUTORE = '"+l.getAutore()+"', ANNOPUBBLICAZIONE ='"+l.getAnnoPubblicazione()
					+ "', CASAEDITRICE = '"+l.getCasaEditrice()+"', ARGOMENTO ='"+l.getArgomento()
					+ "', CODICE = '"+l.getCodice()+"', COLLOCAZIONE = '"+l.getCollocazione()
					+ "', STATO = '"+l.getStato()+"' WHERE CODICE= '"+string+"'");
			
			esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return esito;
	}


	public static boolean insertID_CodiceLibro(Long iDUtilizzatore, String codiceLibro) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		
		try {
			Statement stat=conn.createStatement();
			Statement stat2=conn.createStatement();
			stat.execute("CREATE TABLE IF NOT EXISTS PRESTITO (IDUTILIZZATORE BIGINT NOT NULL, CODICELIBRO VARCHAR(255) NOT NULL UNIQUE, DATAINIZIO DATE, DATAFINE DATE, FOREIGN KEY (CODICELIBRO) REFERENCES LIBRO (CODICE))");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar data1 = Calendar.getInstance();
			Calendar data2 = Calendar.getInstance();
			data2.add(Calendar.MONTH, 2);
				
			stat2.execute("INSERT INTO PRESTITO VALUES ('"+iDUtilizzatore+"', '"+codiceLibro+"', '"+dateFormat.format(data1.getTime())+"', '"+dateFormat.format(data2.getTime())+"')");
			esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return esito;
	}


	public static boolean modify2(String codice) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		
		try {
			Statement stat=conn.createStatement();
			String s=new String ("PRESTITO");
			stat.execute("UPDATE LIBRO SET STATO='"+s+"' WHERE CODICE="+codice);
			esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return esito;
	}


	public static ArrayList<Libro> findByTitAut(Libro l) {
	
		ArrayList<Libro> listaLibri=new ArrayList<Libro>();
		
		Connection conn=DBManager.getConnection();
		
		if (l.getTitolo().isEmpty()==false && l.getAutore().isEmpty()==false)
		{
				PreparedStatement pstat;
				try {
					pstat = conn.prepareStatement("SELECT * FROM LIBRO WHERE TITOLO='"+l.getTitolo()+"' AND AUTORE='"+l.getAutore()+"'");
					ResultSet res=pstat.executeQuery();
					while(res.next())
					{
						listaLibri.add(new Libro(res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
										res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		else if (l.getTitolo().isEmpty()==false && l.getAutore().isEmpty()==true)
		{
			PreparedStatement pstat;
			try {
				pstat = conn.prepareStatement("SELECT * FROM LIBRO WHERE TITOLO='"+l.getTitolo()+"'");
				ResultSet res=pstat.executeQuery();
				
				while(res.next())
				{
					listaLibri.add(new Libro(res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
									res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		else if (l.getTitolo().isEmpty()==true && l.getAutore().isEmpty()==false)
		{
			PreparedStatement pstat;
			try {
				pstat = conn.prepareStatement("SELECT * FROM LIBRO WHERE AUTORE='"+l.getAutore()+"'");
				ResultSet res=pstat.executeQuery();
				
				while(res.next())
				{
					listaLibri.add(new Libro(res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
									res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		else
		{
			JButton okButton=new JButton();
			JOptionPane.showMessageDialog(okButton, "Devi riempire almeno un campo!", "Errore",JOptionPane.ERROR_MESSAGE);
		}
		
		return listaLibri;

	}


	public static ArrayList<Libro> findByCod(Libro l) {
		ArrayList<Libro> listaLibri=new ArrayList<Libro>();
		
		Connection conn=DBManager.getConnection();
		
		try {
				Statement stat=conn.createStatement();
				
				ResultSet res=stat.executeQuery("SELECT * FROM LIBRO WHERE CODICE='"+l.getCodice()+"'");
				
				while (res.next())
				{
					listaLibri.add(new Libro (res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
							res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
				}
										
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listaLibri;
	}


	public static ArrayList<Libro> findByArg(Libro l) {
		ArrayList<Libro> listaLibri=new ArrayList<Libro>();
		
		Connection conn=DBManager.getConnection();
		
		try {
				Statement stat=conn.createStatement();
				
				ResultSet res=stat.executeQuery("SELECT * FROM LIBRO WHERE ARGOMENTO='"+l.getArgomento()+"'");
				
				while (res.next())
				{
					listaLibri.add(new Libro (res.getString("TITOLO"), res.getString("AUTORE"), res.getString("CASAEDITRICE"), res.getInt("ANNOPUBBLICAZIONE"),
							res.getString("ARGOMENTO"), res.getString("STATO"), res.getString("CODICE"), res.getString("COLLOCAZIONE")));
				}
										
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listaLibri;
	}
}
