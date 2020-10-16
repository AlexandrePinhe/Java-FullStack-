package spring.model.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import spring.model.enumerador.TipoCarro;

 @Entity
public class Carro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_carro")
	private Integer idCarro;
	
	@ManyToOne
	@JoinColumn(name="Id_Fabricante")
	private Fabricante fabricante;
	
	@Column(name="Tipo")
	@Enumerated(EnumType.STRING)
	private TipoCarro tipo;
	
	@Column(name="Placa")
	private String placa;
	
	@Column(name="Ano")
	private int ano;
	
	@Column(name="cor")
	private String cor;
	
	
	@ManyToOne
	@JoinColumn(name="Id_Modelo")
	private Modelo modelo;
	
	public Carro() {
		
	}
	
	public Carro(Fabricante fabricante, Integer idCarro, TipoCarro tipo, String placa, int ano, String cor,
			Modelo modelo) {
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
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return "Carro [tipo=" + tipo + ", placa=" + placa + ", ano=" + ano + ", cor=" + cor + ", modelo=" + modelo
				+ "]";
	}
	
}
