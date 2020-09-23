package Entidade;

public class Carro {
 
	private CarroTipo tipo;
	private String placa;
	private int ano;
	private String cor;
	private Modelo modelo;
	private Fabricante fabricante;
	
	public Carro(CarroTipo tipo, String placa, int ano, String cor, Modelo modelo, Fabricante fabricante) {
		super();
		this.tipo = tipo;
		this.placa = placa;
		this.ano = ano;
		this.cor = cor;
		this.modelo = modelo;
		this.fabricante = fabricante;
	}
	
	public CarroTipo getTipo() {
		return tipo;
	}
	public void setTipo(CarroTipo tipo) {
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
	
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Carro [tipo=" + tipo + ", placa=" + placa + ", ano=" + ano + ", cor=" + cor + ", modelo=" + modelo
				+ ", fabricante=" + fabricante + "]";
	}
	
	
}
