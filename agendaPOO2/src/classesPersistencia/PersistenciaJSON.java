package classesPersistencia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classe.Contato;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersistenciaJSON extends Persistencia {

    private Contato contato;

    public PersistenciaJSON(Contato contato) {
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
        JSONArray pessoasArray = readJsonFromFile("pessoas.json");

        JSONObject novaPessoaJson = pessoaToJson(contato);

        pessoasArray.add(novaPessoaJson);

        try (FileWriter fileWriter = new FileWriter("pessoas.json")) {
            fileWriter.write(pessoasArray.toJSONString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static JSONArray readJsonFromFile(String filename) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        try (FileReader fileReader = new FileReader(filename)) {
            Object obj = jsonParser.parse(fileReader);
            if (obj instanceof JSONArray) {
                jsonArray = (JSONArray) obj;
            }
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        } catch (ParseException e) {
        	System.out.println(e.getMessage());
        }

        return jsonArray;
    }

    private static JSONObject pessoaToJson(Contato contato) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nome", contato.getNome());
        jsonObject.put("email", contato.getEmail());
        jsonObject.put("telefone", contato.getTelefone());
        Date nasc = contato.getNasc();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedNasc = dateFormat.format(nasc);
        jsonObject.put("nsc", formattedNasc);

        return jsonObject;
    }

    public void listar() {
    	JSONArray pessoasArray = readJsonFromFile("pessoas.json");
    	System.out.println(pessoasArray);
    }
}
