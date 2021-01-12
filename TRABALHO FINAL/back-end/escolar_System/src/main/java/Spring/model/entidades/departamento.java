package Spring.model.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import Spring.model.enumerador.tipo_departamento;

@Entity
public class departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data_contratacao")
	private Date data_contratacao;
	
	@ManyToOne
	@JoinColumn(name="matricula_prof")
	private professor professor;
	
	@ManyToOne
	@JoinColumn(name="cod_curso")
	private curso curso;
	
	@Column(name="tipo_departamento")
	@Enumerated(EnumType.STRING)
	private tipo_departamento tipo_departamento;
	
	@Column(name="departamento_ativo")
	private int departamento_ativo;

	public departamento(int id, String nome, Date data_contratacao, professor matricula_prof, curso cod_curso,
			Spring.model.enumerador.tipo_departamento tipo_departamento, int departamento_ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_contratacao = data_contratacao;
		this.professor = matricula_prof;
		this.curso = cod_curso;
		this.tipo_departamento = tipo_departamento;
		this.departamento_ativo = departamento_ativo;
	}

	public departamento() {
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

	public Date getData_contratacao() {
		return data_contratacao;
	}

	public void setData_contratacao(Date data_contratacao) {
		this.data_contratacao = data_contratacao;
	}

	public professor getMatricula_prof() {
		return professor;
	}

	public void setMatricula_prof(professor matricula_prof) {
		this.professor = matricula_prof;
	}

	public curso getCod_curso() {
		return curso;
	}

	public void setCod_curso(curso cod_curso) {
		this.curso = cod_curso;
	}

	public tipo_departamento getTipo_departamento() {
		return tipo_departamento;
	}

	public void setTipo_departamento(tipo_departamento tipo_departamento) {
		this.tipo_departamento = tipo_departamento;
	}

	public int getDepartamento_ativo() {
		return departamento_ativo;
	}

	public void setDepartamento_ativo(int departamento_ativo) {
		this.departamento_ativo = departamento_ativo;
	}

	@Override
	public String toString() {
		return "departamento [id=" + id + ", nome=" + nome + ", data_contratacao=" + data_contratacao
				+ ", matricula_prof=" + professor + ", cod_curso=" + curso + ", tipo_departamento="
				+ tipo_departamento + ", departamento_ativo=" + departamento_ativo + "]";
	}
}
