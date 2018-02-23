package model.dto;

import java.io.Serializable;

public class CheckApprovalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Number examID;
	private Number matrikelnummer;
	
	public CheckApprovalDTO() {
		
	}

	public Number getExamID() {
		return examID;
	}

	public void setExamID(Number examID) {
		this.examID = examID;
	}

	public Number getMatrikelnummer() {
		return matrikelnummer;
	}

	public void setMatrikelnummer(Number matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}

	@Override
	public String toString() {
		return "CheckApprovalDTO [examID=" + examID + ", matrikelnummer=" + matrikelnummer + "]";
	}

}
