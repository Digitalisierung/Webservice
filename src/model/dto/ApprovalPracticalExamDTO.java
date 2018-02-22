package model.dto;

public class ApprovalPracticalExamDTO {
	private String topic;
	private String description;
	private Number degree;
	
	private String profName;
	private String profForename;
	private String profTitel;
	private String profEmail;
	
	private Number profAgreed;
	
	
	public ApprovalPracticalExamDTO() {
		
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Number getDegree() {
		return degree;
	}


	public void setDegree(Number degree) {
		this.degree = degree;
	}


	public String getProfName() {
		return profName;
	}


	public void setProfName(String profName) {
		this.profName = profName;
	}


	public String getProfForename() {
		return profForename;
	}


	public void setProfForename(String profForename) {
		this.profForename = profForename;
	}


	public String getProfTitel() {
		return profTitel;
	}


	public void setProfTitel(String profTitel) {
		this.profTitel = profTitel;
	}


	public String getProfEmail() {
		return profEmail;
	}


	public void setProfEmail(String profEmail) {
		this.profEmail = profEmail;
	}


	public Number getProfAgreed() {
		return profAgreed;
	}


	public void setProfAgreed(Number profAgreed) {
		this.profAgreed = profAgreed;
	}


	@Override
	public String toString() {
		return "PracticalExamRegisterDTO [topic=" + topic + ", description=" + description + ", degree=" + degree
				+ ", profName=" + profName + ", profForename=" + profForename + ", profTitel=" + profTitel
				+ ", profEmail=" + profEmail + ", profAgreed=" + profAgreed + "]";
	}
	
	

}
