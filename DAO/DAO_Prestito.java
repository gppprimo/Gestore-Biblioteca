package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DataBaseConnection.DBManager;
import Libro.Libro;
import UtilizzatoreServizio.Prestito;

public class DAO_Prestito {

	
	public static boolean restituzione (Libro l){
		
		boolean esito=false;
		boolean esito2=false;
		
		Connection conn=DBManager.getConnection();
		
		try {
			Statement stat=conn.createStatement();
			Statement stat1=conn.createStatement();
			Statement stat2=conn.createStatement();
			Statement stat3=conn.createStatement();
			Long IDUtilizzatore = null;
			int numeroLibriInPossesso=0;
			
			ResultSet res=stat1.executeQuery("SELECT * FROM PRESTITO WHERE CODICELIBRO="+l.getCodice());
			
			while (res.next()){
				IDUtilizzatore=res.getLong("IDUTILIZZATORE");
			}
			
			stat.execute("DELETE FROM PRESTITO WHERE CODICELIBRO="+l.getCodice());
		
			ResultSet res2=stat2.executeQuery("SELECT * FROM UTILIZZATORE WHERE ID="+IDUtilizzatore);
			
			while (res2.next()){
				numeroLibriInPossesso=res2.getInt("NUMEROLIBRIPOSSEDUTI");
					
			}
			
			if (numeroLibriInPossesso==1)
			{
				stat3.execute("DELETE FROM UTILIZZATORE WHERE ID="+IDUtilizzatore);
			}
			else
			{
				 esito2=decrementaLibriPossesso(IDUtilizzatore);
			}
			
			esito=esito2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esito;
	}

	private static boolean decrementaLibriPossesso(Long iDUtilizzatore) {
		// TODO Auto-generated method stub
		
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		
		Statement stat;
		try {
			stat = conn.createStatement();
			stat.execute("UPDATE UTILIZZATORE SET NUMEROLIBRIPOSSEDUTI=1 WHERE ID="+iDUtilizzatore );
			
			esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esito;
	}

	public static ArrayList<String> ricercaCodiceLibro(String codice) {
		// TODO Auto-generated method stub
				
		Connection conn=DBManager.getConnection();
		
		ArrayList<String> listaCodici=new ArrayList<String>();
		
		try {
			Statement stat=conn.createStatement();
			
			ResultSet res=stat.executeQuery("SELECT * FROM PRESTITO WHERE CODICELIBRO="+codice);
			
			while (res.next())
			{
				listaCodici.add(new String(res.getString("CODICELIBRO")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaCodici;
	}
	
	public static ArrayList<Prestito> ricercaPrestitiEffettuati(){
		
		ArrayList<Prestito> listaPrestiti=new ArrayList<Prestito>();
		
		Connection conn=DBManager.getConnection();
	
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dataAttuale =Calendar.getInstance();
		
		String dataA=dateFormat.format(dataAttuale.getTime());
		
		try {
			PreparedStatement pstat=conn.prepareStatement("SELECT * FROM PRESTITO WHERE DATAFINE > '"+dataA+"'");
			ResultSet res=pstat.executeQuery();
			
			while(res.next()){
				listaPrestiti.add(new Prestito(res.getLong("IDUTILIZZATORE"), res.getString("CODICELIBRO"), res.getDate("DATAINIZIO"), res.getDate("DATAFINE")));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPrestiti;
	}

	public static ArrayList<Prestito> ricercaPrestitiScaduti() {
		ArrayList<Prestito> listaPrestiti=new ArrayList<Prestito>();
		
		Connection conn=DBManager.getConnection();
	
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dataAttuale = Calendar.getInstance();
		String dataA=dateFormat.format(dataAttuale.getTime());
		
		try {
			PreparedStatement pstat=conn.prepareStatement("SELECT * FROM PRESTITO WHERE DATAFINE <= '"+dataA+"'");
			ResultSet res=pstat.executeQuery();
			
			while(res.next()){
				listaPrestiti.add(new Prestito(res.getLong("IDUTILIZZATORE"), res.getString("CODICELIBRO"), res.getDate("DATAINIZIO"), res.getDate("DATAFINE")));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPrestiti;
	}
	
}
