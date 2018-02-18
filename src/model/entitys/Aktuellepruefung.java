package model.entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the aktuellepruefung database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="aktuellepruefung")
@NamedQuery(name="Aktuellepruefung.findAll", query="SELECT a FROM Aktuellepruefung a")
public class Aktuellepruefung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="aufsicht")
	private String aufsicht;

	@Column(name="datum")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	@Column(name="raum")
	private String raum;

	//bi-directional many-to-one association to Pruefungen
	@ManyToOne
	@JoinColumn(name="pruefung_id")
	private Pruefungen pruefung;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="prof_id")
	private Professor prof;

	//bi-directional many-to-one association to Angemeldetepruefung
	@OneToMany(mappedBy="aktuellePruefung")
	private Set<Angemeldetepruefung> angemeldetePruefunge;

	public Aktuellepruefung() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAufsicht() {
		return this.aufsicht;
	}

	public void setAufsicht(String aufsicht) {
		this.aufsicht = aufsicht;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getRaum() {
		return this.raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}

	public Pruefungen getPruefung() {
		return this.pruefung;
	}

	public void setPruefung(Pruefungen pruefung) {
		this.pruefung = pruefung;
	}

	public Professor getProf() {
		return this.prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public Set<Angemeldetepruefung> getAngemeldetePruefunge() {
		return this.angemeldetePruefunge;
	}

	public void setAngemeldetePruefunge(Set<Angemeldetepruefung> angemeldetePruefunge) {
		this.angemeldetePruefunge = angemeldetePruefunge;
	}

	public Angemeldetepruefung addAngemeldetePruefunge(Angemeldetepruefung angemeldetePruefunge) {
		if(getAngemeldetePruefunge() == null) {
			setAngemeldetePruefunge(new HashSet<Angemeldetepruefung>());
		}
		getAngemeldetePruefunge().add(angemeldetePruefunge);
		angemeldetePruefunge.setAktuellePruefung(this);

		return angemeldetePruefunge;
	}

	public Angemeldetepruefung removeAngemeldetePruefunge(Angemeldetepruefung angemeldetePruefunge) {
		if(getAngemeldetePruefunge() == null) {
			setAngemeldetePruefunge(new HashSet<Angemeldetepruefung>());
		}
		getAngemeldetePruefunge().remove(angemeldetePruefunge);
		angemeldetePruefunge.setAktuellePruefung(null);

		return angemeldetePruefunge;
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
		Aktuellepruefung other = (Aktuellepruefung) obj;
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
		return "Aktuellepruefung [id=" + id + ", aufsicht=" + aufsicht + ", datum=" + datum + ", raum=" + raum
				+ ", pruefung=" + pruefung + ", professor=" + prof + "]";
	}

}