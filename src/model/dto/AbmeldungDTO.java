package model.dto;

public class AbmeldungDTO {
	
	private Number student_id;
	private Number akt_id;
	
	public AbmeldungDTO() {
		
	}
	
	
	public AbmeldungDTO(Number student_id, Number akt_id) {

		this.student_id = student_id;
		this.akt_id = akt_id;
	}


	public Number getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Number student_id) {
		this.student_id = student_id;
	}
	public Number getAkt_id() {
		return akt_id;
	}
	public void setAkt_id(Number akt_id) {
		this.akt_id = akt_id;
	}
	
	@Override
	public String toString() {
		return "AbmeldungDTO [student_id=" + student_id + ", akt_id=" + akt_id + "]";
	}
	
	
	
	
}
