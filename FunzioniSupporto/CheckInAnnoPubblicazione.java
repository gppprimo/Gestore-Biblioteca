package FunzioniSupporto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInAnnoPubblicazione {

	public boolean check(CharSequence annoPubblicazione){

	    Pattern pattern = Pattern.compile("'");
	    Matcher matcher = pattern.matcher(annoPubblicazione);
	    return matcher.find();
		}
}
