package Libro;

import java.math.BigInteger;
import java.util.ArrayList;
import DAO.DAO_Prestito;
import ExceptionHandler.RicercaLibroUIException;
import DAO.DAO_Libro;

/**
 * 
 */
public class GestoreLibro implements IGestoreLibro {

    /**
     * Default constructor
     */
    public GestoreLibro() {
    }

    /**
     * 
     */
    private static GestoreLibro Istance;


    /**
     * 
     */
    protected void GestoreProdotto() {
        // TODO implement here
    }

    /**
     * 
     */
    public static GestoreLibro getIstance() {
        // TODO implement here
    	if (Istance==null)
    	{
    		Istance=new GestoreLibro();
    	}
    	return Istance;
    }

    /**
     * @param l
     * @throws  
     */
    public boolean inserisciLibro(Libro l) {
        // TODO implement here
    	boolean esito=false;
    	
    	esito=DAO_Libro.insert(l);
    	
    	return esito;
    }

    /**
     * @param string 
     */
    public boolean modificaLibro(Libro l,String string) {
        // TODO implement here
    	boolean esito=false;
    	
    	esito=DAO_Libro.modify(l, string);
    	
    	return esito;
    	
    }

    /**
     * @param l
     */
    public boolean rimuoviLibro(Libro l) {
    	 // TODO implement here
    	boolean esito;
    	
    	esito=DAO_Libro.remove(l);
    	
		return esito;
    }

    /**
     * 
     */
    public boolean effettuaPrestitoLibro(Long iDUtilizzatore, String CodiceLibro) {
        // TODO implement here
    	
    	boolean esito;
    	
    	esito=DAO_Libro.insertID_CodiceLibro(iDUtilizzatore, CodiceLibro);
    	
    	return esito;
    }

    /**
     * 
     */
    public void ricercaPrestitoScaduto() {
 
    }

	@Override
	public ArrayList<Libro> ricercaLibro(Libro l){
		
		ArrayList<Libro> listaLibri=DAO_Libro.find(l);
		
		return listaLibri;
		
	}
	
	
	public Libro ricercaLibro2(Libro l) {
		Libro libro=DAO_Libro.find2(l);
		return libro;
	}
	
	 public boolean restituzioneLibro (Libro l) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		esito=DAO_Prestito.restituzione(l);
		
		return esito;
	}

	@Override
	public boolean modificaLibro2 (String codiceLibro) {
		// TODO Auto-generated method stub
		boolean esito=false;
		
		esito=DAO_Libro.modify2(codiceLibro);
		
		return esito;
	}

	public ArrayList<Libro> ricercaLibroByTitAut(Libro l) {
		
		ArrayList<Libro> listaLibri=DAO_Libro.findByTitAut(l);
		
		return listaLibri;
	}

	public ArrayList<Libro> ricercaLibroByCod(Libro l) {
		
		ArrayList<Libro> listaLibri=DAO_Libro.findByCod(l);
		
		return listaLibri;
	}

	public ArrayList<Libro> ricercaLibroByArg(Libro l) {
		
		ArrayList<Libro> listaLibri=DAO_Libro.findByArg(l);
		
		return listaLibri;
	}
}