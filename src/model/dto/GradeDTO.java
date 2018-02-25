package model.dto;

public class GradeDTO {

	
	private Number akt_id;
	private Number student_id;
	private Number note;

	
	public GradeDTO() {
		
	}

	public GradeDTO(Number akt_id, Number student_id, Number note) {
		super();
		this.akt_id = akt_id;
		this.student_id = student_id;
		this.note = note;
	
	}

	public Number getAkt_id() {
		return akt_id;
	}

	public void setAkt_id(Number akt_id) {
		this.akt_id = akt_id;
	}

	public Number getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Number student_id) {
		this.student_id = student_id;
	}

	public Number getNote() {
		return note;
	}

	public void setNote(Number note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "GradeDTO [akt_id=" + akt_id + ", student_id=" + student_id + ", note=" + note + 
				"]";
	}
	

}
