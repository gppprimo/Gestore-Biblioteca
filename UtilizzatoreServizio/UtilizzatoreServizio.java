package UtilizzatoreServizio;

import java.math.BigInteger;

/**
 * 
 */
public class UtilizzatoreServizio {

    /**
     * Default constructor
     * @param l 
     * @param object 
     * @param indirizzo2 
     * @param string 
     * @param cF2 
     * @param cI2 
     * @param numeroP 
     * @param cognome2 
     * @param nome2 
     */
    public UtilizzatoreServizio(String nome2, String cognome2, String cF2, String numTel, String indirizzo2, Integer numeroLibri, long l) {
    this.Nome=nome2;
    this.Cognome=cognome2;
    this.CF=cF2;
    this.NumTelefono=numTel;
    this.Indirizzo=indirizzo2;
    this.NumeroLibriPossesso=numeroLibri;
    this.ID=l;
  
    }

    /**
     * 
     */
    private String Nome;

    /**
     * 
     */
    private String Cognome;

    /**
     * 
     */
    private String CI;

    /**
     * 
     */
    private String CF;

    /**
     * 
     */
    private String NumPatente;

    /**
     * 
     */
    private String NumTelefono;

    /**
     * 
     */
    private String Indirizzo;

    /**
     * 
     */
    private Integer NumeroLibriPossesso;

    private long ID;


    /**
     * @return
     */
    public Prestito getPrestito() {
        // TODO implement here
        return null;
    }




	public String getNome() {
		return Nome;
	}




	public void setNome(String nome) {
		Nome = nome;
	}




	public String getCognome() {
		return Cognome;
	}




	public void setCognome(String cognome) {
		Cognome = cognome;
	}




	public String getCI() {
		return CI;
	}




	public void setCI(String cI) {
		CI = cI;
	}




	public String getCF() {
		return CF;
	}




	public void setCF(String cF) {
		CF = cF;
	}




	public String getNumPatente() {
		return NumPatente;
	}




	public void setNumPatente(String numPatente) {
		NumPatente = numPatente;
	}




	public String getNumTelefono() {
		return NumTelefono;
	}




	public void setNumTelefono(String numTelefono) {
		NumTelefono = numTelefono;
	}




	public String getIndirizzo() {
		return Indirizzo;
	}




	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}




	public Integer getNumeroLibriPossesso() {
		return NumeroLibriPossesso;
	}




	public void setNumeroLibriPossesso(Integer numeroLibriPossesso) {
		NumeroLibriPossesso = numeroLibriPossesso;
	}




	public long getID() {
		return ID;
	}




	public void setID(long iD) {
		ID = iD;
	}

}