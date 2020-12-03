package spring.model.enumerador;

public enum UsuarioNivel {
	
	ADMINISTRADOR(10, "Administrador"),
	GESTOR(20, "Gestor"),
	COMUM(30, "Comum");

	private int id;
	private String desc;
	
	
	UsuarioNivel(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return this.desc;
	}
}
