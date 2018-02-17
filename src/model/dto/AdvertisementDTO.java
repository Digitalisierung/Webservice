package model.dto;

public class AdvertisementDTO {
	private Integer id = 0;
	private String topic = "";
	private String description = "";
	private Integer degree = 0;
	private String professor = ""; //Prof Email
	
	public AdvertisementDTO() {
		
	}
	
	public AdvertisementDTO(Integer id, String topic, String description, Integer degree, String professor) {
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.degree = degree;
		this.professor = professor;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}

}
