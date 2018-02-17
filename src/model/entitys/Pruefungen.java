package model.entitys;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the pruefungen database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="pruefungen")
@NamedQuery(name="Pruefungen.findAll", query="SELECT p FROM Pruefungen p")
public class Pruefungen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="credits")
	private Integer credits;

	@Column(name="fachbereich")
	private String fachbereich;

	@Column(name="name")
	private String name;

	@Column(name="Semester")
	private Integer semester;

	@Column(name="Wahlmodul")
	private long wahlmodul;

	//bi-directional many-to-one association to Aktuellepruefung
	@OneToMany(mappedBy="pruefung")
	private Set<Aktuellepruefung> aktuellePruefunge;

	//bi-directional many-to-one association to Testat
	@OneToMany(mappedBy="exam")
	private Set<Testat> testats;

	public Pruefungen() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public String getFachbereich() {
		return this.fachbereich;
	}

	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public long getWahlmodul() {
		return this.wahlmodul;
	}

	public void setWahlmodul(long wahlmodul) {
		this.wahlmodul = wahlmodul;
	}

	public Set<Aktuellepruefung> getAktuellePruefunge() {
		return this.aktuellePruefunge;
	}

	public void setAktuellePruefunge(Set<Aktuellepruefung> aktuellePruefunge) {
		this.aktuellePruefunge = aktuellePruefunge;
	}

	public Aktuellepruefung addAktuellePruefunge(Aktuellepruefung aktuellePruefunge) {
		if(getAktuellePruefunge() == null) {
			setAktuellePruefunge(new HashSet<Aktuellepruefung>());
		}
		getAktuellePruefunge().add(aktuellePruefunge);
		aktuellePruefunge.setPruefung(this);

		return aktuellePruefunge;
	}

	public Aktuellepruefung removeAktuellePruefunge(Aktuellepruefung aktuellePruefunge) {
		if(getAktuellePruefunge() == null) {
			setAktuellePruefunge(new HashSet<Aktuellepruefung>());
		}
		getAktuellePruefunge().remove(aktuellePruefunge);
		aktuellePruefunge.setPruefung(null);

		return aktuellePruefunge;
	}

	public Set<Testat> getTestats() {
		return this.testats;
	}

	public void setTestats(Set<Testat> testats) {
		this.testats = testats;
	}

	public Testat addTestat(Testat testat) {
		if(getTestats() == null) {
			setTestats(new HashSet<Testat>());
		}
		getTestats().add(testat);
		testat.setExam(this);

		return testat;
	}

	public Testat removeTestat(Testat testat) {
		if(getTestats() == null) {
			setTestats(new HashSet<Testat>());
		}
		getTestats().remove(testat);
		testat.setExam(null);

		return testat;
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
		Pruefungen other = (Pruefungen) obj;
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
		return "Pruefungen [id=" + id + ", credits=" + credits + ", fachbereich=" + fachbereich + ", name=" + name
				+ ", semester=" + semester + ", wahlmodul=" + wahlmodul + "]";
	}

}