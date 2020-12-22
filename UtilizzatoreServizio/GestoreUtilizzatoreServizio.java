package UtilizzatoreServizio;

import java.util.*;

import DAO.DAO_Prestito;
import DAO.DAO_Utilizzatore;
import Libro.GestoreLibro;
import Libro.Libro;

/**
 * 
 */
public class GestoreUtilizzatoreServizio implements IGestoreUtilizzatoreServizio {

    /**
     * Default constructor
     */
    public GestoreUtilizzatoreServizio() {
    }

    /**
     * 
     */
    private static GestoreUtilizzatoreServizio Istance;


    /**
     * 
     */
    protected void GestoreProdotto() {
        // TODO implement here
    }

    /**
     * @return 
     * 
     */
    public static GestoreUtilizzatoreServizio getIstance() {
        // TODO implement here
    	if (Istance==null)
    	{
    		Istance=new GestoreUtilizzatoreServizio();
    	}
    	return Istance;
    }

    /**
     * @param u
     */
    public boolean inserisciUtilizzatore(UtilizzatoreServizio u) {
        // TODO implement here
    	
    	boolean esito=false;
    	
    	esito=DAO_Utilizzatore.inserisciUtilizzatore(u);
    	
    	return esito;
    }
    
    public ArrayList<String> ricercaCodiceLibro(String codice){
    	ArrayList<String> lista;
    	
    	lista=DAO_Prestito.ricercaCodiceLibro(codice);
    	
    	return lista;
    }
    
    public ArrayList<Prestito> cercaPrestitiEffettuati(){
    	
    	ArrayList<Prestito> listaPrestiti=new ArrayList<Prestito>();
    	
    	listaPrestiti=DAO_Prestito.ricercaPrestitiEffettuati();
    	
    	return listaPrestiti;
    	
    }
    
    public ArrayList<Prestito> cercaPrestitiScaduti(){
    	
    	ArrayList<Prestito> listaPrestiti=new ArrayList<Prestito>();
    	
    	listaPrestiti=DAO_Prestito.ricercaPrestitiScaduti();
    	
    	return listaPrestiti;
    }
    
    public UtilizzatoreServizio ricercaUtilizzatore(Long IDUtilizzatore){
    	
    	UtilizzatoreServizio u=DAO_Utilizzatore.ricercaUtilizzatore(IDUtilizzatore);
    	
    	return u;
    	
    }
    
    public ArrayList<UtilizzatoreServizio> ricercaUtilizzatore2(String nome, String cognome){
    	
    	ArrayList<UtilizzatoreServizio> listaUtilizzatoriStessoNomeCognome=DAO_Utilizzatore.ricercaUtilizzatore2(nome, cognome);
    	
    	return listaUtilizzatoriStessoNomeCognome;
    }


	public boolean incrementaNumeroLibriPossesso(long id) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		esito=DAO_Utilizzatore.incrementaLibriInPossesso(id);
		
		return esito;
	}
    
}