package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DataBaseConnection.DBManager;
import UtilizzatoreServizio.UtilizzatoreServizio;

public class DAO_Utilizzatore {

	public static boolean inserisciUtilizzatore (UtilizzatoreServizio u){
		
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		Statement stat,stat2;
		try {
			stat2=conn.createStatement();
			stat2.execute("CREATE TABLE IF NOT EXISTS UTILIZZATORE (NOME VARCHAR(255), COGNOME VARCHAR(255), CODICEFISCALE VARCHAR(16), TELEFONO VARCHAR(15), INDIRIZZO VARCHAR (255), NUMEROLIBRIPOSSEDUTI INT, ID BIGINT PRIMARY KEY)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			stat=conn.createStatement();
			stat.execute("INSERT INTO UTILIZZATORE VALUES ('"+u.getNome()+
					"', '"+u.getCognome()+"', '"+u.getCF()+"', '"+u.getNumTelefono()+
					"', '"+u.getIndirizzo()+"', '"+u.getNumeroLibriPossesso()+
					"', '"+u.getID()+"')");
		esito=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	
	}
	
	
	public static UtilizzatoreServizio ricercaUtilizzatore(Long IDUtilizzatore){
		
		Connection conn=DBManager.getConnection();
		UtilizzatoreServizio u=null;
		
		try {
			PreparedStatement pstat=conn.prepareStatement("SELECT * FROM UTILIZZATORE WHERE ID="+IDUtilizzatore);
					
			ResultSet res=pstat.executeQuery();
			
			while (res.next()){
				u=new UtilizzatoreServizio(res.getString("NOME"), res.getString("COGNOME"), res.getString("CODICEFISCALE"), res.getString("TELEFONO"),
						res.getString("INDIRIZZO"), res.getInt("NUMEROLIBRIPOSSEDUTI"), res.getLong("ID"));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return u;
		
	}


	public static ArrayList<UtilizzatoreServizio> ricercaUtilizzatore2(String nome, String cognome) {
		// TODO Auto-generated method stub
		ArrayList<UtilizzatoreServizio> listaUtilizzatoriStessoNomeCognome=new ArrayList<UtilizzatoreServizio>();
		Connection conn=DBManager.getConnection();
		
		try {
			PreparedStatement pstat=conn.prepareStatement("SELECT * FROM UTILIZZATORE WHERE NOME='"+nome+"' AND COGNOME='"+cognome+"'");
					
			ResultSet res=pstat.executeQuery();
			
			while (res.next()){
				listaUtilizzatoriStessoNomeCognome.add(new UtilizzatoreServizio(res.getString("NOME"), res.getString("COGNOME"), res.getString("CODICEFISCALE"), res.getString("TELEFONO"),
						res.getString("INDIRIZZO"), res.getInt("NUMEROLIBRIPOSSEDUTI"), res.getLong("ID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUtilizzatoriStessoNomeCognome;
	}


	public static boolean incrementaLibriInPossesso(long id) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		Connection conn=DBManager.getConnection();
		Statement stat;
		try {
			stat=conn.createStatement();
			stat.execute("UPDATE UTILIZZATORE SET NUMEROLIBRIPOSSEDUTI=2 WHERE ID="+id);
			esito=true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return esito;
		
	}
	
	
}
