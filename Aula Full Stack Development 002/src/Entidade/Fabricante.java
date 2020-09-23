package Entidade;

public class Fabricante {
 
	private String fabricanteNome;
	private int fabricanteID;
	
	
	public Fabricante(String fabricanteNome, int fabricanteID) {
		super();
		this.fabricanteNome = fabricanteNome;
		this.fabricanteID = fabricanteID;
	}

	public String getFabricanteNome() {
		return fabricanteNome;
	}
	public void setFabricanteNome(String fabricanteNome) {
		this.fabricanteNome = fabricanteNome;
	}
	public int getFabricanteID() {
		return fabricanteID;
	}
	public void setFabricanteID(int fabricanteID) {
		this.fabricanteID = fabricanteID;
	}
	
	@Override
	public String toString() {
		return "Fabricante [fabricanteNome=" + fabricanteNome + ", fabricanteID=" + fabricanteID + "]";
	}
}
