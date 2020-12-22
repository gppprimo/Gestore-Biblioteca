package FunzioniSupporto;

import java.util.Random;

public class ID_Generator {

	public static Long ID_Generation(){
		Random r=new Random();
		Long IDUtilizzatore= Math.abs(r.nextLong());
		return IDUtilizzatore;
	}
	
}
