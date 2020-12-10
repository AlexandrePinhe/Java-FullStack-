package spring.model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Modelo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_Modelo")
	private int modeloId;
	
	@Column(name="Nome_Modelo")
	private String nomeModelo;
	
	@ManyToOne
	@JoinColumn(name="Id_Fabricante")
	private Fabricante fabricante;
	
	
	public Modelo(String nomeModelo, int modeloId, Fabricante fabricante) {
		super();
		this.nomeModelo = nomeModelo;
		this.modeloId = modeloId;
		this.fabricante = fabricante;
	}
	
	public Modelo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNomeModelo() {
		return nomeModelo;
	}
	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}
	public int getModeloId() {
		return modeloId;
	}
	public void setModeloId(int modeloId) {
		this.modeloId = modeloId;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	@Override
	public String toString() {
		return "Modelo =" + nomeModelo + ", " + fabricante ;
	}
	
}
