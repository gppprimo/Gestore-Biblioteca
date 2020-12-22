package FunzioniSupporto;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Backup;

import DataBaseConnection.DBManager;

public class BackUp {

	public boolean BackUpBatabase(){
			
		boolean esito=false;
		
        try {
			FileOutputStream fos = new FileOutputStream("DBBiblioteca.zip");
			Backup.execute("DBBiblioteca.zip", "", "DataBaseBiblioteca", true);
			esito=true;
			fos.close();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		return esito;
	}
	
	
}
