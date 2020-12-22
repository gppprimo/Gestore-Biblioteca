package Libro;



/**
 * 
 */
public class Libro {

    private String Collocazione;

	/**
     * Default constructor
     */
    public Libro(String Titolo,String Autore,String CasaEditrice, Integer AnnoPubblicazione, String Argomento, String Stato, String codice, String Collocazione) {
    	this.Titolo=Titolo;
    	this.Autore=Autore;
    	this.CasaEditrice=CasaEditrice;
    	this.AnnoPubblicazione=AnnoPubblicazione;
    	this.Argomento=Argomento;
    	this.Stato=Stato;
    	this.Codice=codice;
    	this.Collocazione=Collocazione;
    }

    /**
     * 
     */
    private String Autore;

    /**
     * 
     */
    private String Titolo;

    /**
     * 
     */
    private String CasaEditrice;

    /**
     * 
     */
    private Integer AnnoPubblicazione;

    /**
     * 
     */
    private String Argomento;

    /**
     * 
     */
    private String Stato;

    /**
     * 
     */
    private String Codice;

	public String getAutore() {
		return Autore;
	}

	public void setAutore(String autore) {
		Autore = autore;
	}

	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getCasaEditrice() {
		return CasaEditrice;
	}

	public void setCasaEditrice(String casaEditrice) {
		CasaEditrice = casaEditrice;
	}

	public Integer getAnnoPubblicazione() {
		return AnnoPubblicazione;
	}

	public void setAnnoPubblicazione(Integer annoPubblicazione) {
		AnnoPubblicazione = annoPubblicazione;
	}

	public String getArgomento() {
		return Argomento;
	}

	public void setArgomento(String argomento) {
		Argomento = argomento;
	}

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	public String getCodice() {
		return Codice;
	}

	public void setCodice(String codice) {
		Codice = codice;
	}

	public String getCollocazione() {
		return Collocazione;
	}

	public void setCollocazione(String collocazione) {
		Collocazione = collocazione;
	}




}