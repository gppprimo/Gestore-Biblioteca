package FunzioniSupporto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;




public class prova_ {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		/*
		try {
			FileOutputStream fos=new FileOutputStream("Ciao.zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		//System.out.println(CheckClass.checkApostrophe(s));
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year);
	
		
		
		
		
		
	}

}
