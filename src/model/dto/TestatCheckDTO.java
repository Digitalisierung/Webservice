package model.dto;

public class TestatCheckDTO {
	
	private Number student_id;
	private Number pruefungs_id;
	
	public TestatCheckDTO() {
		
	}
	public TestatCheckDTO(Number student_id, Number pruefungs_id) {
		
		this.student_id = student_id;
		this.pruefungs_id = pruefungs_id;
	}
	
	@Override
	public String toString() {
		return "TestatCheckDTO [student_id=" + student_id + ", pruefungs_id=" + pruefungs_id + "]";
	}
	public Number getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Number student_id) {
		this.student_id = student_id;
	}
	public Number getPruefungs_id() {
		return pruefungs_id;
	}
	public void setPruefungs_id(Number pruefungs_id) {
		this.pruefungs_id = pruefungs_id;
	}
	
	
	
	
}
