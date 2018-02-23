package model.dto;

import java.io.Serializable;

public class SubjectDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Number id;
	private Number attemt; // Versuch
	private Number state; 
	private String date; 
	private Number credits; 
	private String name;
	
	public SubjectDTO() {
		
	}
	
	public SubjectDTO(Number id, String name, Number credits, Number attemt, Number state, String date) {
		this.id = id;
		this.attemt = attemt;
		this.state = state;
		this.date = date;
		this.credits = credits;
		this.name = name;
		
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public Number getAttemt() {
		return attemt;
	}

	public void setAttemt(Number attemt) {
		this.attemt = attemt;
	}

	public Number getState() {
		return state;
	}

	public void setState(Number state) {
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "SubjectDTO [ID=" + id + ", attemt=" + attemt + ", state="
				+ state + ", date=" + date + ", credits=" + credits + ", name=" + name + "]";
	}

}
