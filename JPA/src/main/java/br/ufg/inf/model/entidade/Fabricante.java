package br.ufg.inf.model.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fabricante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fabricanteId")
	private Integer fabricanteId;
	
	@Column(name="fabricanteNome")
	private String fabricanteNome;
	
	public Fabricante(String fabricanteNome, Integer fabricanteId) {
		super();
		this.fabricanteNome = fabricanteNome;
		this.fabricanteId = fabricanteId;
	}
	public Fabricante() {
		// TODO Auto-generated constructor stub
	}
	public String getFabricanteNome() {
		return fabricanteNome;
	}
	public void setFabricanteNome(String fabricanteNome) {
		this.fabricanteNome = fabricanteNome;
	}
	public Integer getFabricanteId() {
		return fabricanteId;
	}
	public void setFabricanteId(Integer fabricanteId) {
		this.fabricanteId = fabricanteId;
	}
	@Override
	public String toString() {
		return "Fabricante [fabricanteId=" + fabricanteId + ", fabricanteNome=" + fabricanteNome + "]";
	}

	
}
