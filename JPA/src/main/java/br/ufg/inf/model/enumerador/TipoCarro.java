package br.ufg.inf.model.enumerador;

public enum TipoCarro {
	SEDAN(1, "Sedam"),
	HATCH(2, "Hatch"),
	PIC_UP(3, "Pic-up");
	
	private int id;
	private String desc;
	
	private TipoCarro(int id, String desc) {
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
