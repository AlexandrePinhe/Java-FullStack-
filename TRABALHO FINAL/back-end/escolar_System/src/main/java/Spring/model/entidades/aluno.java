package Spring.model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class aluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cpf")
	private int cpf;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="nome")
	private String nome;
	
	@Column(name="Endereco")
	private String Endereco;
	
	public aluno(int id, int cpf, String nome, String endereco) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.Endereco = endereco;
	}
	
	public aluno() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "aluno [id= " +id+ "cpf=" + cpf + ", nome=" + nome + ", Endereco=" + Endereco + "]";
	}
}
