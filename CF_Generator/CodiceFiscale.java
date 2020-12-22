package CF_Generator;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * @author Pasquale Puzio
 *
 */
public abstract class CodiceFiscale
{
	/**
	 * indica se è già avvenuto o meno il caricamento dei comuni con i rispettivi codici
	 */
	private static boolean loaded = false;
	
	private static final char[] mesi = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
	
	private static final char[] consonanti = {'B', 'C', 'D', 'F', 'G', 'H', 'J' ,'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'V', 'X', 'Y', 'Z'};
	
	private static final char[] vocali = {'A', 'E', 'I', 'O', 'U'};
	
	private static final char[] CONTROLLO = {'A', 'B', 'C', 'D', 'E' , 'F', 'G', 'H', 'I' , 'J' ,'K', 'L', 'M', 'N', 'O' , 'P', 'Q', 'R', 'S', 'T', 'U' , 'W', 'V', 'X', 'Y', 'Z'};
	
	/**
	 * contiene i valori per i caratteri in posizione pari
	 */
	private static Hashtable<Object, Integer> PARI = new Hashtable<Object, Integer>();
	
	/**
	 * contiene i valori per i caratteri in posizione dispari
	 */
	private static Hashtable<Object, Integer> DISPARI = new Hashtable<Object, Integer>();
	
	/**
	 * metodo per calcolare il codice fiscale
	 * @param nome Nome
	 * @param cognome Cognome
	 * @param sesso Sesso
	 * @param codice Codice del comune di nascita
	 * @param giorno Giorno di nascita
	 * @param mese Mese di nascita
	 * @param anno Anno di nascita
	 * @return Codice Fiscale
	 */
	public static String calculateCodiceFiscale(String nome, String cognome, char sesso, String codice, String giorno, int mese, String anno)
	{
		if (!loaded)
		{
			setHashTables();
			loaded = true;
		}
		String result = "";
		String temp;
		temp = extractSurname(cognome);
		if (temp != null)
			result += temp;
		else return null;
		temp = extractName(nome);
		if (temp != null)
			result += temp;
		else return null;
		result += anno;
		result += String.valueOf(mesi[mese]);
		if (String.valueOf(sesso).equalsIgnoreCase("F"))
		{
			giorno = String.valueOf((Integer.parseInt(giorno) + 40));
		}
		result += giorno;
		result += codice;
		result += getControlChar(result);
		return result;
	}
	
	/**
	 * metodo per estrarre i caratteri relativi al nome
	 * @param nome stringa contenente il nome
	 * @return tre caratteri relativi al nome
	 */
	private static String extractName(String nome)
	{
		String cons = "";
		String voc = "";
		// inserisco i dati in un ArrayList per facilitare il lavoro
		ArrayList<String> c = new ArrayList<String>();
		ArrayList<String> v = new ArrayList<String>();
		for (int j = 0; j < consonanti.length; j++)
		{
			c.add(String.valueOf(consonanti[j]));
		}
		for (int j = 0; j < vocali.length; j++)
		{
			v.add(String.valueOf(vocali[j]));
		}
		
		// divido le consonanti e le vocali
		// se c'è qualche carattere diverso restituisco null
		for (int i = 0; i<nome.length(); i++)
		{
			if (c.contains(nome.substring(i, i+1)))
				cons += nome.substring(i, i+1);
			else if (v.contains(nome.substring(i, i+1)))
				voc += nome.substring(i, i+1);
			else if (!nome.substring(i, i+1).equalsIgnoreCase(" ") && !nome.substring(i, i+1).equalsIgnoreCase("'"))
				return null;
		}
		
		// verifichiamo tutti i vari casi possibili
		String result = "";
		if (cons.length() >= 4)
		{
			result = cons.substring(0, 1);
			result += cons.substring(2 , 4);
		}
		else if (cons.length() == 3)
		{
			result = cons.substring(0, 3);
		}
		else if (cons.length() == 2 && voc.length() >= 1)
		{
			result = cons + voc.substring(0, 1);
		}
		else if (cons.length() == 1 && voc.length() >= 2)
		{
			result = cons + voc.substring(0, 2);
		}
		else if (cons.length() == 0 && nome.length() >= 3)
		{
			if (voc.length() >= 3)
				result = voc.substring(0, 3);
			else
			{
				result = voc.substring(0, voc.length());
				for (int i = 0; i < (3 - voc.length()); i++)
					result += "X";
			}
		}
		else if (cons.length() + voc.length() < 3)
		{
			result = cons + voc;
			for (int i = 0; i < (3 - cons.length() - voc.length()); i++)
				result += "X";
		}
		else return null;
		return result;
	}
	
	/**
	 * metodo per estrarre i caratteri relativi al cognome
	 * @param nome stringa contenente il cognome
	 * @return tre caratteri relativi al cognome
	 */
	private static String extractSurname(String cognome)
	{
		String cons = "";
		String voc = "";
		// inserisco i dati in un array list per facilitare il lavoro
		ArrayList<String> c = new ArrayList<String>();
		ArrayList<String> v = new ArrayList<String>();
		for (int j = 0; j < consonanti.length; j++)
		{
			c.add(String.valueOf(consonanti[j]));
		}
		for (int j = 0; j < vocali.length; j++)
		{
			v.add(String.valueOf(vocali[j]));
		}
		
		// divido le consonanti e le vocali
		// se c'è qualche carattere diverso restituisco null
		for (int i = 0; i<cognome.length(); i++)
		{
			if (c.contains(cognome.substring(i, i+1)))
				cons += cognome.substring(i, i+1);
			else if (v.contains(cognome.substring(i, i+1)))
				voc += cognome.substring(i, i+1);
			else if (!cognome.substring(i, i+1).equalsIgnoreCase(" ") && !cognome.substring(i, i+1).equalsIgnoreCase("'"))
				return null;
		}
		
		// verifichiamo tutti i vari casi possibili
		String result = "";
		if (cons.length() >= 3)
		{
			result = cons.substring(0, 3);
		}
		else if (cons.length() == 2 && voc.length() >= 1)
		{
			result = cons + voc.substring(0, 1);
		}
		else if (cons.length() == 1 && voc.length() >= 2)
		{
			result = cons + voc.substring(0, 2);
		}
		else if (cons.length() == 0 && cognome.length() >= 3)
		{
			if (voc.length() >= 3)
				result = voc.substring(0, 3);
			else
			{
				result = voc.substring(0, voc.length());
				for (int i = 0; i < (3 - voc.length()); i++)
					result += "X";
			}
		}
		else if (cons.length() + voc.length() < 3)
		{
			result = cons + voc;
			for (int i = 0; i < (3 - cons.length() - voc.length()); i++)
				result += "X";
		}
		else return null;
		return result;
	}
	
	/**
	 * metodo per calcolare il carattere ci controllo
	 * @param codfis primi 15 caratteri del codice fiscale
	 * @return carattere di controllo
	 */
	public static char getControlChar(String codfis)
	{
		if (!loaded)
		{
			setHashTables();
			loaded = true;
		}
		
		char[] chars = codfis.toCharArray();
		int s = 0;
		
		for (int i = 0; i< chars.length; i++)
		{
			if ((i+1)%2 == 0)
			{
				try {
					int tmp = Integer.parseInt(String.valueOf(chars[i]));
					s += PARI.get(tmp);
				} catch (NumberFormatException e) {
					s += PARI.get(String.valueOf(chars[i]));
				}
			}
			else
			{
				try {
					int tmp = Integer.parseInt(String.valueOf(chars[i]));
					s += DISPARI.get(tmp);
				} catch (NumberFormatException e) {
					s += DISPARI.get(String.valueOf(chars[i]));
				}
			}
		}
		
		s = s%26;
		return CONTROLLO[s];
	}
	
	/**
	 * metodo di settaggio delle tabelle hash
	 */
	private static void setHashTables()
	{		
		PARI.put("A", 0);
		PARI.put(0 , 0);
		PARI.put("B", 1);
		PARI.put(1 , 1);
		PARI.put("C", 2);
		PARI.put(2 , 2);
		PARI.put("D", 3);
		PARI.put(3 , 3);
		PARI.put("E", 4);
		PARI.put(4 , 4);
		PARI.put("F", 5);
		PARI.put(5 , 5);
		PARI.put("G", 6);
		PARI.put(6 , 6);
		PARI.put("H", 7);
		PARI.put(7 , 7);
		PARI.put("I", 8);
		PARI.put(8 , 8);
		PARI.put("J", 9);
		PARI.put(9 , 9);
		PARI.put("K", 10);
		PARI.put("L", 11);
		PARI.put("M", 12);
		PARI.put("N", 13);
		PARI.put("O", 14);
		PARI.put("P", 15);
		PARI.put("Q", 16);
		PARI.put("R", 17);
		PARI.put("S", 18);
		PARI.put("T", 19);
		PARI.put("U", 20);
		PARI.put("V", 21);
		PARI.put("W", 22);
		PARI.put("X", 23);
		PARI.put("Y", 24);
		PARI.put("Z", 25);
	
		DISPARI.put("A", 1);
		DISPARI.put(0 , 1);
		DISPARI.put("B", 0);
		DISPARI.put(1 , 0);
		DISPARI.put("C", 5);
		DISPARI.put(2 , 5);
		DISPARI.put("D", 7);
		DISPARI.put(3 , 7);
		DISPARI.put("E", 9);
		DISPARI.put(4 , 9);
		DISPARI.put("F", 13);
		DISPARI.put(5 , 13);
		DISPARI.put("G", 15);
		DISPARI.put(6 , 15);
		DISPARI.put("H", 17);
		DISPARI.put(7 , 17);
		DISPARI.put("I", 19);
		DISPARI.put(8 , 19);
		DISPARI.put("J", 21);
		DISPARI.put(9 , 21);
		DISPARI.put("K", 2);
		DISPARI.put("L", 4);
		DISPARI.put("M", 18);
		DISPARI.put("N", 20);
		DISPARI.put("O", 11);
		DISPARI.put("P", 3);
		DISPARI.put("Q", 6);
		DISPARI.put("R", 8);
		DISPARI.put("S", 12);
		DISPARI.put("T", 14);
		DISPARI.put("U", 16);
		DISPARI.put("V", 10);
		DISPARI.put("W", 22);
		DISPARI.put("X", 25);
		DISPARI.put("Y", 24);
		DISPARI.put("Z", 23);
	}
	
}
