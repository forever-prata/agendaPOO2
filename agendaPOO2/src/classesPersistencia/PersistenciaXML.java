package classesPersistencia;

import classe.Contato;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PersistenciaXML extends Persistencia{
	
	private Contato contato;
	
	public PersistenciaXML(Contato contato) {
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
		String xmlFilePath = "pessoas.xml";
        Document document = readXmlFromFile(xmlFilePath);
        if (document == null) {
            document = createNewXmlDocument();
        }
        
        if (document != null) {
            addNewPerson(document, contato);
            saveXmlToFile(document, xmlFilePath);
        }
	}
	
    private static Document readXmlFromFile(String filename) {
        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(xmlFile);
        } catch (Exception e) {
            return null;
        }
    }
	
    private static Document createNewXmlDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("pessoas");
            doc.appendChild(rootElement);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static void addNewPerson(Document document, Contato contato) {
        Element pessoasElement = document.getDocumentElement();

        Element pessoaElement = document.createElement("pessoa");

        Element nomeElement = document.createElement("nome");
        nomeElement.appendChild(document.createTextNode(contato.getNome()));
        pessoaElement.appendChild(nomeElement);

        Element emailElement = document.createElement("email");
        emailElement.appendChild(document.createTextNode(contato.getEmail()));
        pessoaElement.appendChild(emailElement);
        
        Element telefoneElement = document.createElement("telefone");
        telefoneElement.appendChild(document.createTextNode(contato.getTelefone()));
        pessoaElement.appendChild(telefoneElement);

        Element nscElement = document.createElement("nsc");
        Date nasc = contato.getNasc();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedNasc = dateFormat.format(nasc);
        nscElement.appendChild(document.createTextNode(formattedNasc));
        pessoaElement.appendChild(nscElement);
        
        pessoasElement.appendChild(pessoaElement);
    }
    
    private static void saveXmlToFile(Document document, String filename) {
        try {
            javax.xml.transform.TransformerFactory transformerFactory =
            javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            javax.xml.transform.stream.StreamResult result =
                new javax.xml.transform.stream.StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void listar() {
		String xmlFilePath = "pessoas.xml";
		listPeople(readXmlFromFile(xmlFilePath));
	}
	
    private static void listPeople(Document document) {
        Element rootElement = document.getDocumentElement();
        NodeList peopleList = rootElement.getElementsByTagName("pessoa");

        for (int i = 0; i < peopleList.getLength(); i++) {
            Element pessoaElement = (Element) peopleList.item(i);
            String nome = pessoaElement.getElementsByTagName("nome").item(0).getTextContent();
            String email = pessoaElement.getElementsByTagName("email").item(0).getTextContent();
            String telefone = pessoaElement.getElementsByTagName("telefone").item(0).getTextContent();
            String nsc = pessoaElement.getElementsByTagName("nsc").item(0).getTextContent();

            System.out.println("Nome: " + nome + ", Email: " + email + ", Telefone: "+ telefone + ", Nsc: " + nsc);
        }
    }
}
