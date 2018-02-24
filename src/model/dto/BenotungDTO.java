package model.dto;

import java.io.Serializable;

public class BenotungDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Number matrikelnummer;
	private Number grade;
	
	public BenotungDTO() {
		
	}
	
	public BenotungDTO(Number matrikelnummer, Number grade) {
		this.matrikelnummer = matrikelnummer;
		this.grade = grade;
	}

	public Number getMatrikelnummer() {
		return matrikelnummer;
	}

	public void setMatrikelnummer(Number matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}

	public Number getGrade() {
		return grade;
	}

	public void setGrade(Number grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "BenotungDTO [matrikelnummer=" + matrikelnummer + ", mark=" + grade + "]";
	}

}
