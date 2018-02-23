package model.dto;

public class StudentDTO {
	
	private String name;
	private String nachname;
	private String modulname;
	private String email;

	public StudentDTO() {
		
	}

	public StudentDTO(String name, String nachname, String modulname, String email) {
		super();
		this.name = name;
		this.nachname = nachname;
		this.modulname = modulname;
		this.email = email;
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

	public String getModulname() {
		return modulname;
	}

	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
