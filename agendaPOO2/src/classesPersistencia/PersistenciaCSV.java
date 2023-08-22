package classesPersistencia;

import java.util.List;

import classe.Contato;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PersistenciaCSV extends Persistencia{
	
	private Contato contato;
	
	public PersistenciaCSV(Contato contato) {
		super();
		this.contato = contato;
	}
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public void salvar() {
		String csvFilePath = "pessoas.csv";
		
        List<String> csvData = loadCsvData(csvFilePath);
        addNewPerson(csvData, contato);
        saveCsvToFile(csvData, csvFilePath);
	}
	
    private static List<String> loadCsvData(String filename) {
        List<String> csvData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                csvData.add(line);
            }
        } catch (IOException e) {
            
        }
        return csvData;
    }
    
    private static void addNewPerson(List<String> csvData, Contato contato) {
        Date nasc = contato.getNasc();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedNasc = dateFormat.format(nasc);
        csvData.add(contato.getNome() + "," + contato.getTelefone() + "," + contato.getEmail() + "," + formattedNasc);
    }
    
    private static void saveCsvToFile(List<String> csvData, String csvFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (String line : csvData) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void listar() {
		String csvFilePath = "pessoas.csv";
		List<String> csvData = loadCsvData(csvFilePath);
		System.out.println(csvData);
	}
}
