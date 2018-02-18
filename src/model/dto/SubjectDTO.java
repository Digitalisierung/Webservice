package model.dto;

public class SubjectDTO {
	private String name;
	private Number credits;
	private Number attempt;
	private Number mark;
	
	public SubjectDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number getCredits() {
		return credits;
	}

	public void setCredits(Number credits) {
		this.credits = credits;
	}

	public Number getAttempt() {
		return attempt;
	}

	public void setAttempt(Number attempt) {
		this.attempt = attempt;
	}

	public Number getMark() {
		return mark;
	}

	public void setMark(Number mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "SubjectDTO [name=" + name + ", credits=" + credits + ", attempt=" + attempt + ", mark=" + mark + "]";
	}

}
