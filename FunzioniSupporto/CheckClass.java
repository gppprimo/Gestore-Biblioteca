package FunzioniSupporto;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import FunzioniSupporto.ID_Generator;
import UtilizzatoreServizio.GestoreUtilizzatoreServizio;
import UtilizzatoreServizio.UtilizzatoreServizio;

public class CheckClass extends SQLException{

	public static String checkApostrophe (String input){
		
		int count=0;
		StringBuilder inputCorretto=new StringBuilder(input);
		for (int i=0;i<input.length();i++)
		{
			if (input.charAt(i)=='\'')
			{
				inputCorretto.insert(i+count, '\'');
				count=count+2;				
			}
		}
		String inputC=inputCorretto.toString();
				return inputC;
	}

	public static Long checkIDUtilizzatore(Long iDUtilizzatore) {
		// TODO Auto-generated method stub
		Long ID_UtilizzatoreChecked=iDUtilizzatore;
		GestoreUtilizzatoreServizio gUS=new GestoreUtilizzatoreServizio();
		UtilizzatoreServizio UtilizzatoreStessoID=gUS.ricercaUtilizzatore(ID_UtilizzatoreChecked);
		
		if(UtilizzatoreStessoID!=null)
		{
			while (UtilizzatoreStessoID.getID()==ID_UtilizzatoreChecked)
			{
				ID_UtilizzatoreChecked=ID_Generator.ID_Generation();
			}
		}
		
			
		return ID_UtilizzatoreChecked;
	}

	public static boolean AnnoPubblicazioneChecked(String annoPubblicazione) {
		// TODO Auto-generated method stub
		boolean esito=false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		dateFormat.format(annoPubblicazione);
		//Integer annoCorrente=Date.parse(dataA);
		System.out.println(annoPubblicazione);
		return esito;
		
	}
	
	
}
