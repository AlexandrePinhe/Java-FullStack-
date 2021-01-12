package Spring.model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class professor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="matricula")
	private int matricula;
	
	@Column(name="nome")
	private String nome;

	
	public professor(int matricula, String nome) {
		super();
		this.matricula = matricula;
		this.nome = nome;
	}

	public professor() {
		// TODO Auto-generated constructor stub
	}
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "professor [matricula=" + matricula + ", nome=" + nome + "]";
	}
}
