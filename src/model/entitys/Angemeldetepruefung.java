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
 * The persistent class for the angemeldetepruefung database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="angemeldetepruefung")
@NamedQuery(name="Angemeldetepruefung.findAll", query="SELECT a FROM Angemeldetepruefung a")
public class Angemeldetepruefung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="status")
	private Integer status;

	//bi-directional many-to-one association to Aktuellepruefung
	@ManyToOne
	@JoinColumn(name="akt_id")
	private Aktuellepruefung aktuellePruefung;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student teilnehmer;

	//bi-directional many-to-one association to GeschriebenePruefungen
	@OneToMany(mappedBy="statedTest")
	private Set<GeschriebenePruefungen> geschriebenePruefungen;

	public Angemeldetepruefung() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Aktuellepruefung getAktuellePruefung() {
		return this.aktuellePruefung;
	}

	public void setAktuellePruefung(Aktuellepruefung aktuellePruefung) {
		this.aktuellePruefung = aktuellePruefung;
	}

	public Student getTeilnehmer() {
		return this.teilnehmer;
	}

	public void setTeilnehmer(Student teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

	public Set<GeschriebenePruefungen> getGeschriebenePruefungen() {
		return this.geschriebenePruefungen;
	}

	public void setGeschriebenePruefungen(Set<GeschriebenePruefungen> geschriebenePruefungen) {
		this.geschriebenePruefungen = geschriebenePruefungen;
	}

	public GeschriebenePruefungen addGeschriebenePruefungen(GeschriebenePruefungen geschriebenePruefungen) {
		if(getGeschriebenePruefungen() == null) {
			setGeschriebenePruefungen(new HashSet<GeschriebenePruefungen>());
		}
		getGeschriebenePruefungen().add(geschriebenePruefungen);
		geschriebenePruefungen.setStatedTest(this);

		return geschriebenePruefungen;
	}

	public GeschriebenePruefungen removeGeschriebenePruefungen(GeschriebenePruefungen geschriebenePruefungen) {
		if(getGeschriebenePruefungen() == null) {
			setGeschriebenePruefungen(new HashSet<GeschriebenePruefungen>());
		}
		getGeschriebenePruefungen().remove(geschriebenePruefungen);
		geschriebenePruefungen.setStatedTest(null);

		return geschriebenePruefungen;
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
		Angemeldetepruefung other = (Angemeldetepruefung) obj;
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
		return "Angemeldetepruefung [id=" + id + ", status=" + status + ", aktuellePruefung=" + aktuellePruefung
				+ ", student=" + teilnehmer + "]";
	}

}