package Entidade;

public class Modelo {
  
	private String modeloNome;
    private int modeloID;
    private Fabricante fabricante;
    
    
	public Modelo(String modeloNome, int modeloID, Fabricante fabricante) {
		super();
		this.modeloNome = modeloNome;
		this.modeloID = modeloID;
		this.fabricante = fabricante;
	}
	
	public String getModeloNome() {
		return modeloNome;
	}
	public void setModeloNome(String modeloNome) {
		this.modeloNome = modeloNome;
	}
	public int getModeloID() {
		return modeloID;
	}
	public void setModeloID(int modeloID) {
		this.modeloID = modeloID;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Modelo [modeloNome=" + modeloNome + ", modeloID=" + modeloID + ", fabricante=" + fabricante + "]";
	}


}
