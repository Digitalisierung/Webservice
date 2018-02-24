package model.dto;

import java.io.Serializable;

public class SubjectDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Number subjectID;
	private Number credits; 
	private String name;
	private String prof_email = "";
	
	public SubjectDTO() {
		
	}
	
	public SubjectDTO(Number subjectID, String name, Number credits) {
		this.subjectID = subjectID;
		this.credits = credits;
		this.name = name;
		
	}


	public Number getSubjectID() {
		return subjectID;
	}

	
	public void setSubjectID(Number subjectID) {
		this.subjectID = subjectID;
	}

	
	public Number getCredits() {
		return credits;
	}

	
	public void setCredits(Number credits) {
		this.credits = credits;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	public String getProf_email() {
		return prof_email;
	}

	
	public void setProf_email(String prof_email) {
		this.prof_email = prof_email;
	}

	
	@Override
	public String toString() {
		return "SubjectDTO [subjectID=" + subjectID + ", credits=" + credits + ", name=" + name + "]";
	}

}
