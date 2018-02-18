package model.entitys;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the aushang database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="aushang")
@NamedQuery(name="Aushang.findAll", query="SELECT a FROM Aushang a")
public class Aushang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="beschreibung")
	private String beschreibung;

	@Column(name="schwierigkeitsgrad")
	private Integer schwierigkeitsgrad;

	@Column(name="titel")
	private String titel;
	
	@Column(name="aktiv")
	private Boolean aktiv;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;

	//bi-directional many-to-one association to VorgemerkteAushaenge
	@OneToMany(mappedBy="aushang")
	private Set<VorgemerkteAushaenge> vorgemerkte_Aushaenge;

	public Aushang() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Integer getSchwierigkeitsgrad() {
		return this.schwierigkeitsgrad;
	}

	public void setSchwierigkeitsgrad(Integer schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Boolean getAktiv() {
		return aktiv;
	}

	public void setAktiv(Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<VorgemerkteAushaenge> getVorgemerkte_Aushaenge() {
		return this.vorgemerkte_Aushaenge;
	}

	public void setVorgemerkte_Aushaenge(Set<VorgemerkteAushaenge> vorgemerkte_Aushaenge) {
		this.vorgemerkte_Aushaenge = vorgemerkte_Aushaenge;
	}

	public VorgemerkteAushaenge addVorgemerkte_Aushaenge(VorgemerkteAushaenge vorgemerkte_Aushaenge) {
		if(getVorgemerkte_Aushaenge() == null) {
			setVorgemerkte_Aushaenge(new HashSet<VorgemerkteAushaenge>());
		}
		getVorgemerkte_Aushaenge().add(vorgemerkte_Aushaenge);
		vorgemerkte_Aushaenge.setAushang(this);

		return vorgemerkte_Aushaenge;
	}

	public VorgemerkteAushaenge removeVorgemerkte_Aushaenge(VorgemerkteAushaenge vorgemerkte_Aushaenge) {
		if(getVorgemerkte_Aushaenge() == null) {
			setVorgemerkte_Aushaenge(new HashSet<VorgemerkteAushaenge>());
		}
		getVorgemerkte_Aushaenge().remove(vorgemerkte_Aushaenge);
		vorgemerkte_Aushaenge.setAushang(null);

		return vorgemerkte_Aushaenge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Aushang other = (Aushang) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Aushang [id=" + id + ", beschreibung=" + beschreibung + ", schwierigkeitsgrad=" + schwierigkeitsgrad
				+ ", titel=" + titel + ", professor=" + professor + "]";
	}

}