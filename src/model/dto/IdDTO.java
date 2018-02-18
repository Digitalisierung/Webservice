package model.dto;

public class IdDTO {
private Integer id;
	
	public IdDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdDTO [id=" + id + "]";
	}

}
