package model.dto;

import java.io.Serializable;

public class IdDTO implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
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
