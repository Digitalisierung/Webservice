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
 * The persistent class for the professor database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="professor")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="email")
	private String email;

	@Column(name="name")
	private String name;

	@Column(name="titel")
	private String titel;

	@Column(name="vorname")
	private String vorname;

	//bi-directional many-to-one association to Aktuellepruefung
	@OneToMany(mappedBy="prof")
	private Set<Aktuellepruefung> currentExams;

	//bi-directional many-to-one association to Aushang
	@OneToMany(mappedBy="professor")
	private Set<Aushang> aushaenge;

	public Professor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Set<Aktuellepruefung> getCurrentExams() {
		return this.currentExams;
	}

	public void setCurrentExams(Set<Aktuellepruefung> currentExams) {
		this.currentExams = currentExams;
	}

	public Aktuellepruefung addCurrentExam(Aktuellepruefung currentExam) {
		if(getCurrentExams() == null) {
			setCurrentExams(new HashSet<Aktuellepruefung>());
		}
		getCurrentExams().add(currentExam);
		currentExam.setProf(this);

		return currentExam;
	}

	public Aktuellepruefung removeCurrentExam(Aktuellepruefung currentExam) {
		if(getCurrentExams() == null) {
			setCurrentExams(new HashSet<Aktuellepruefung>());
		}
		getCurrentExams().remove(currentExam);
		currentExam.setProf(null);

		return currentExam;
	}

	public Set<Aushang> getAushaenge() {
		return this.aushaenge;
	}

	public void setAushaenge(Set<Aushang> aushaenge) {
		this.aushaenge = aushaenge;
	}

	public Aushang addAushaenge(Aushang aushaenge) {
		if(getAushaenge() == null) {
			setAushaenge(new HashSet<Aushang>());
		}
		getAushaenge().add(aushaenge);
		aushaenge.setProfessor(this);

		return aushaenge;
	}

	public Aushang removeAushaenge(Aushang aushaenge) {
		if(getAushaenge() == null) {
			setAushaenge(new HashSet<Aushang>());
		}
		getAushaenge().remove(aushaenge);
		aushaenge.setProfessor(null);

		return aushaenge;
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
		Professor other = (Professor) obj;
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
		return "Professor [id=" + id + ", email=" + email + ", name=" + name + ", titel=" + titel + ", vorname="
				+ vorname + "]";
	}

}