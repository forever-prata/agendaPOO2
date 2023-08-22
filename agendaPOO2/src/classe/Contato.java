package classe;

import java.util.Date;

public class Contato {
	private String nome;
	private Date nasc;
	private String telefone;
	private String email;
	
	public Contato(String nome, Date nasc, String telefone, String email) {
		super();
		this.nome = nome;
		this.nasc = nasc;
		this.telefone = telefone;
		this.email = email;
	}

	public Contato() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNasc() {
		return nasc;
	}

	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contato [nome=");
		builder.append(nome);
		builder.append(", nasc=");
		builder.append(nasc);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", getNome()=");
		builder.append(getNome());
		builder.append(", getNasc()=");
		builder.append(getNasc());
		builder.append(", getTelefone()=");
		builder.append(getTelefone());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
