package Libro;

import java.math.BigInteger;
import java.util.ArrayList;

import ExceptionHandler.RicercaLibroUIException;

/**
 * 
 */
public interface IGestoreLibro {

    /**
     * @param l
     * @return 
     */
    public boolean inserisciLibro(Libro l);

    /**
     * @param l
     * @return 
     */
    public boolean modificaLibro(Libro l, String codice);

    /**
     * @param l
     * @return 
     */
    public boolean rimuoviLibro(Libro l);

    /**
     * 
     */
    public boolean effettuaPrestitoLibro(Long ID, String Cod);

    /**
     * @param l
     * @return  
     */
    public ArrayList<Libro> ricercaLibro(Libro l);

    /**
     * @param l
     */
    public boolean restituzioneLibro(Libro l);

    /**
     * 
     */
    public void ricercaPrestitoScaduto();

	boolean modificaLibro2(String codiceLibro);


}