package Spring.model.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class curso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="cod_disciplina")
	private disciplina disciplina;
	
	@Column(name="data_matricula")
	private Date data_matricula;
	
	@ManyToOne
	@JoinColumn(name="id_aluno")
	private aluno aluno;
	
	public curso(int id, String nome, disciplina cod_disciplina, Date data_matricula, aluno cpf_aluno) {
		super();
		this.id = id;
		this.nome = nome;
		this.disciplina = cod_disciplina;
		this.data_matricula = data_matricula;
		this.aluno = cpf_aluno;
	}
	
	public curso() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public disciplina getCod_disciplina() {
		return disciplina;
	}

	public void setCod_disciplina(disciplina cod_disciplina) {
		this.disciplina = cod_disciplina;
	}

	public Date getData_matricula() {
		return data_matricula;
	}

	public void setData_matricula(Date data_matricula) {
		this.data_matricula = data_matricula;
	}

	public aluno getId_aluno() {
		return aluno;
	}

	public void setId_aluno(aluno Id_aluno) {
		this.aluno = Id_aluno;
	}

	@Override
	public String toString() {
		return "curso [id=" + id + ", nome=" + nome + ", cod_disciplina=" + disciplina + ", data_matricula="
				+ data_matricula + ", Id_aluno=" + aluno + "]";
	}
}
