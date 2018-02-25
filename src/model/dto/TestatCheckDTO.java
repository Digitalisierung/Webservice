package model.dto;

public class TestatCheckDTO {
	
	private Number angemeldete_id;
	private Number student_id;
	private String nachname;
	private String vorname;
	private String email;
	private Number aktuelle_id;
	private String modulname;
	private Number status;
	
	public TestatCheckDTO() {
		
	}

	public TestatCheckDTO(Number angemeldete_id, Number student_id, String nachname, String vorname, String email,
			Number aktuelle_id, String modulname, Number status) {
		super();
		this.angemeldete_id = angemeldete_id;
		this.student_id = student_id;
		this.nachname = nachname;
		this.vorname = vorname;
		this.email = email;
		this.aktuelle_id = aktuelle_id;
		this.modulname = modulname;
		this.status = status;
	}


	public Number getAngemeldete_id() {
		return angemeldete_id;
	}

	public void setAngemeldete_id(Number angemeldete_id) {
		this.angemeldete_id = angemeldete_id;
	}

	public Number getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Number student_id) {
		this.student_id = student_id;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Number getAktuelle_id() {
		return aktuelle_id;
	}

	public void setAktuelle_id(Number aktuelle_id) {
		this.aktuelle_id = aktuelle_id;
	}

	public String getModulname() {
		return modulname;
	}

	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	public Number getStatus() {
		return status;
	}

	public void setStatus(Number status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "TestatCheckDTO [angemeldete_id=" + angemeldete_id + ", student_id=" + student_id + ", nachname="
				+ nachname + ", vorname=" + vorname + ", email=" + email + ", aktuelle_id=" + aktuelle_id
				+ ", modulname=" + modulname + ", status=" + status + "]";
	}

	
	
	
}
