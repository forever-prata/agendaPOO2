package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import classe.Contato;
import classesPersistencia.Persistencia;
import classesPersistencia.PersistenciaCSV;
import classesPersistencia.PersistenciaJSON;
import classesPersistencia.PersistenciaMySQL;
import classesPersistencia.PersistenciaXML;

public class Main {

	public static void main(String[] args) {
		String date_string = "2007-25-06";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");   
		Date date = null;
		
		try {
			date = formatter.parse(date_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Contato c = new Contato("etste",date,"2","carlos@g");
		Persistencia p = new PersistenciaCSV(c);
		p.salvar();
		p.listar();
	}

}
