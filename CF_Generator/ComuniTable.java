package CF_Generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 
 * @author Pasquale Puzio
 *
 * questa classe contiene l'insieme di tutti i comuni con i relativi codici
 */
public class ComuniTable {
	
	private final String LOADFILE = "/listacomuni.txt";
	
	private ArrayList<String> tablenomi, tablecodici;
	
	public ComuniTable()
	{
		tablenomi = new ArrayList<String>();
		tablecodici = new ArrayList<String>();
		
		try {
			InputStream is = getClass().getResourceAsStream(LOADFILE);
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
			String inputLine;
			
				inputLine = br.readLine();
				
				while (inputLine != null)
				{
					tablecodici.add(inputLine.toUpperCase());
					inputLine = br.readLine();
					tablenomi.add(inputLine.toUpperCase());
					inputLine = br.readLine();
					inputLine = br.readLine();
				}
				
				is.close();
				isr.close();
				br.close();
		}
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
	}
	
	/**
	 * @return restituisce tutti i comuni
	 */
	public Object[] getAllCities()
	{
		return tablenomi.toArray();
	}
	
	/**
	 * cerca tutti i comuni il cui nome inizia con la stringa comune
	 * @param comune stringa di inizio
	 * @return i comuni il cui nome inizia con la stringa comune
	 */
	public Object[] searchCities(String comune)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < tablenomi.size(); i++)
		{
			if (tablenomi.get(i).compareTo(comune) == 1 && !tablenomi.get(i).startsWith(comune))
				break;
			else if (tablenomi.get(i).startsWith(comune))
				result.add(tablenomi.get(i));
		}
		
		return result.toArray();
	}
	
	/**
	 * restituisce il codice di un determinato comune
	 * @param comune
	 * @return
	 */
	public String getCode(String comune)
	{
		for (int i = 0; i < tablenomi.size(); i++)
		{
			if (tablenomi.get(i).compareToIgnoreCase(comune) == 1)
				return null;
			else if (tablenomi.get(i).equalsIgnoreCase(comune))
			{
				return tablecodici.get(i);
			}
		}
		return null;
	}
}
