package Spring.model.enumerador;

public enum tipo_departamento {
	biologicas(1, "biologicas"),
	exatas(2, "exatas"),
	humanas(3, "humanas");
	
	private int id;
	private String desc;
	
	private tipo_departamento(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	
	public String toString() {
		return this.desc;
	}
}
