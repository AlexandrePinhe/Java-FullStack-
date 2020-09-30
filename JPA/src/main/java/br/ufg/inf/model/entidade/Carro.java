package br.ufg.inf.model.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.ufg.inf.model.enumerador.TipoCarro;

 @Entity
public class Carro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDcarro")
	private Integer idCarro;	
	@Column(name="fabricante")
	private Fabricante fabricante;
	@Column(name="tipo")
	private TipoCarro tipo;
	@Column(name="placa")
	private String placa;
	@Column(name="ano")
	private int ano;
	@Column(name="cor")
	private String cor;
	@Column(name="modelo")
	private String modelo;
	
	public Carro() {
		
	}
	
	public Carro(Fabricante fabricante, Integer idCarro, TipoCarro tipo, String placa, int ano, String cor,
			String modelo) {
		super();
		this.fabricante = fabricante;
		this.idCarro = idCarro;
		this.tipo = tipo;
		this.placa = placa;
		this.ano = ano;
		this.cor = cor;
		this.modelo = modelo;
	}
	
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}

	public TipoCarro getTipo() {
		return tipo;
	}
	public void setTipo(TipoCarro tipo) {
		this.tipo = tipo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return "Carro [tipo=" + tipo + ", placa=" + placa + ", ano=" + ano + ", cor=" + cor + ", modelo=" + modelo
				+ "]";
	}
	
}
