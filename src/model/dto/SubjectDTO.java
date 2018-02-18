package model.dto;

public class SubjectDTO {
	
	private Number matrikelnumber;	
	private Number mark; 
	private Number attemt; 
	private Number state; 
	private String date; 
	private Number credits; 
	private String name;
	
	public SubjectDTO() {
		
	}
	
	public SubjectDTO(Number matrikelnumber, Number mark, Number attemt, Number state, String date, Number credits, String name) {
		this.matrikelnumber = matrikelnumber;
		this.mark = mark;
		this.attemt = attemt;
		this.state = state;
		this.date = date;
		this.credits = credits;
		this.name = name;
		
	}

	public Number getMatrikelnumber() {
		return matrikelnumber;
	}

	public void setMatrikelnumber(Number matrikelnumber) {
		this.matrikelnumber = matrikelnumber;
	}

	public Number getMark() {
		return mark;
	}

	public void setMark(Number mark) {
		this.mark = mark;
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
		return "SubjectDTO [matrikelnumber=" + matrikelnumber + ", mark=" + mark + ", attemt=" + attemt + ", state="
				+ state + ", date=" + date + ", credits=" + credits + ", name=" + name + "]";
	}


}
