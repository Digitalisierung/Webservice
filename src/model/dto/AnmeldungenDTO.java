package model.dto;

public class AnmeldungenDTO {

		
	private String name;
	private String nachname;
	private Number id;
	
	public AnmeldungenDTO() {
		
	}

	
	
	public AnmeldungenDTO(String name, String nachname, Number id) {
		this.name = name;
		this.nachname = nachname;
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AnmeldungenDTO [name=" + name + ", nachname=" + nachname + ", id=" + id + "]";
	}

	
}
