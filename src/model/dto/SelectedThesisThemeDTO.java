package model.dto;

import java.util.Date;

public class SelectedThesisThemeDTO {

	// ID des/eines Datensatzes aus der Tabelle 'Vorgemerkte_aushaenge'
	private Number Id;
	// ID des Aushangs welcher vorgemerkt wurde
	private Number aushangId;
	private Number betreuerEinverstanden;
	private Number matrikelnummer;
	private Date datum;

	
	public SelectedThesisThemeDTO() {
		
	}

	public SelectedThesisThemeDTO(Integer Id, Integer aushangId, Integer betreuerEinverstanden, Integer matrikelnummer) {
		this.Id = Id;
		this.aushangId = aushangId;
		this.betreuerEinverstanden = betreuerEinverstanden;
		this.matrikelnummer = matrikelnummer;
		
	}

	public Number getId() {
		return Id;
	}



	public void setId(Number Id) {
		this.Id = Id;
	}



	public Number getAushangId() {
		return aushangId;
	}



	public void setAushangId(Number aushangId) {
		this.aushangId = aushangId;
	}



	public Number getBetreuerEinverstanden() {
		return betreuerEinverstanden;
	}



	public void setBetreuerEinverstanden(Number betreuerEinverstanden) {
		this.betreuerEinverstanden = betreuerEinverstanden;
	}



	public Number getMatrikelnummer() {
		return matrikelnummer;
	}

	public void setMatrikelnummer(Number matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}
	

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aushangId == null) ? 0 : aushangId.hashCode());
		result = prime * result + ((matrikelnummer == null) ? 0 : matrikelnummer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SelectedThesisThemeDTO)) {
			return false;
		}
		SelectedThesisThemeDTO other = (SelectedThesisThemeDTO) obj;
		if (aushangId == null) {
			if (other.aushangId != null) {
				return false;
			}
		} else if (!aushangId.equals(other.aushangId)) {
			return false;
		}
		if (matrikelnummer == null) {
			if (other.matrikelnummer != null) {
				return false;
			}
		} else if (!matrikelnummer.equals(other.matrikelnummer)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SelectedExamDTO [Aushang ID=" + aushangId + ", Betreuer einverstanden=" + betreuerEinverstanden + ", Matrikelnummer="
				+ matrikelnummer + "]";
	}

}
