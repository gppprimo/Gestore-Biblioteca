package UtilizzatoreServizio;

import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

import Libro.Libro;

/**
 * 
 */
public class Prestito {

   

    /**
     * 
     */
    private String Stato;
    private Long IDUtilizzatore;
    private String codiceLibro;
    /**
     * 
     */
    private Date DataInizioPrestito;

    private Date DataFinePrestito;

    public Prestito(Long long1, String codice, Date date, Date date2) {
		// TODO Auto-generated constructor stub
    	
    	this.IDUtilizzatore=long1;
    	this.DataInizioPrestito=date;
    	this.DataFinePrestito=date2;
    	this.setCodiceLibro(codice);
	}

	/**
     * @return
     */
    public Libro getLibroCorrispondente() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public UtilizzatoreServizio getUtilizzatoreCorrispondente() {
        // TODO implement here
        return null;
    }

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	public Date getDataInizioPrestito() {
		return DataInizioPrestito;
	}

	public void setDataInizioPrestito(Date dataInizioPrestito) {
		DataInizioPrestito = dataInizioPrestito;
	}

	public Date getDataFinePrestito() {
		return DataFinePrestito;
	}

	public void setDataFinePrestito(Date dataFinePrestito) {
		DataFinePrestito = dataFinePrestito;
	}

	public Long getIDUtilizzatore() {
		return IDUtilizzatore;
	}

	public void setIDUtilizzatore(Long iDUtilizzatore) {
		IDUtilizzatore = iDUtilizzatore;
	}

	public String getCodiceLibro() {
		return codiceLibro;
	}

	public void setCodiceLibro(String codiceLibro) {
		this.codiceLibro = codiceLibro;
	}
    

}