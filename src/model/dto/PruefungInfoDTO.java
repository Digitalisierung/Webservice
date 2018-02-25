package model.dto;

public class PruefungInfoDTO {
	
	private Number id;
	private String pruefung;
	private Number semester;
	private String datum;
	private String pruefer;
	private Number status;
	
	public PruefungInfoDTO() {
		
	}

	public PruefungInfoDTO(Number id,String pruefung, Number semester, String datum, String pruefer, Number status) {
		this.id = id;
		this.pruefung = pruefung;
		this.semester = semester;
		this.datum = datum;
		this.pruefer = pruefer;
		this.status = status;
	}

	public String getPruefung() {
		return pruefung;
	}

	public void setPruefung(String pruefung) {
		this.pruefung = pruefung;
	}

	public Number getSemester() {
		return semester;
	}

	public void setSemester(Number semester) {
		this.semester = semester;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getPruefer() {
		return pruefer;
	}

	public void setPruefer(String pruefer) {
		this.pruefer = pruefer;
	}

	public Number getStatus() {
		return status;
	}

	public void setStatus(Number status) {
		this.status = status;
	}


	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PruefungInfoDTO [id=" + id + ", pruefung=" + pruefung + ", semester=" + semester + ", datum=" + datum
				+ ", pruefer=" + pruefer + ", status=" + status + "]";
	}
	
	
}