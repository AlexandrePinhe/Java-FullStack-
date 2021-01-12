package Spring.model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class disciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="qtde_Alunos")
	private int Qtde_Alunos;

	
	public disciplina(int iD, String nome, int qtde_Alunos) {
		super();
		this.ID = iD;
		this.nome = nome;
		this.Qtde_Alunos = qtde_Alunos;
	}

	public disciplina() {
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtde_Alunos() {
		return Qtde_Alunos;
	}

	public void setQtde_Alunos(int qtde_Alunos) {
		Qtde_Alunos = qtde_Alunos;
	}

	@Override
	public String toString() {
		return "disciplina [ID=" + ID + ", Nome=" + nome + ", Qtde_Alunos=" + Qtde_Alunos + "]";
	}
	
	
}
