package classesPersistencia;

import java.util.ArrayList;
import java.util.List;

import classe.Contato;
import mySQL.ContatoDAO;

public class PersistenciaMySQL extends Persistencia{
	
	private Contato contato;
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public PersistenciaMySQL(Contato contato) {
		super();
		this.contato = contato;
	}
	
	public void salvar() {
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.inserir(contato);
	}
	public void listar() {
		List<Contato> lista = new ArrayList<Contato>();
		ContatoDAO contatoDAO = new ContatoDAO();
		lista = contatoDAO.pesquisarTodos();	
		for (Contato contato: lista) {
			System.out.println(contato.toString());
		}	
	}
}
