package Camada.Ctrl;

public enum Categoria {
	Simples("Simples"), 
	Executiva("Executiva"), 
	Premiun ("Premiun"), 
	Personalite("Personalite");
	
	private String categoria;
	
	Categoria(String categoria){
		this.categoria = categoria;
	}
	public String getCategoria() {
		return categoria;
	}
}
