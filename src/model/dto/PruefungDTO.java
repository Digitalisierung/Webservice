package model.dto;

import java.util.Date;

public class PruefungDTO {
	
	private Number id;
	private String name;
	private String aufsicht;
	private String datum;
	private String Raum;
	

	public PruefungDTO(Number id, String name, String aufsicht, String datum, String raum) {
		super();
		this.id = id;
		this.name = name;
		this.aufsicht = aufsicht;
		this.datum = datum;
		this.Raum = raum;
	}
	

	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAufsicht() {
		return aufsicht;
	}
	public void setAufsicht(String aufsicht) {
		this.aufsicht = aufsicht;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getRaum() {
		return Raum;
	}
	public void setRaum(String raum) {
		Raum = raum;
	}
	
	@Override
	public String toString() {
		return "PruefungDTO [id=" + id + ", name=" + name + ", aufsicht=" + aufsicht + ", datum=" + datum + ", Raum="
				+ Raum + "]";
	}

}
