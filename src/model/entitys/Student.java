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
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matrikelnummer;

	@Column(name = "credits")
	private Integer credits;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "semester")
	private Integer semester;

	@Column(name = "status")
	private Integer status;

	@Column(name = "vorname")
	private String vorname;
	
	// bi-directional many-to-one association to Fachbereich
	@ManyToOne
	@JoinColumn(name="fachbereich_id")
	private Fachbereich fachbereich;

	// bi-directional many-to-one association to Po
	@ManyToOne
	@JoinColumn(name="po_id")
	private Po po;

	// bi-directional many-to-one association to Studiengang
	@ManyToOne
	@JoinColumn(name="studiengang_id")
	private Studiengang studiengang;

	// bi-directional many-to-one association to Angemeldetepruefung
	@OneToMany(mappedBy = "teilnehmer")
	private Set<Angemeldetepruefung> statedExams;

	// bi-directional many-to-one association to KrankUrlaub
	@OneToMany(mappedBy = "studiker")
	private Set<KrankUrlaub> krankUrlaubungen;

	// bi-directional many-to-one association to Testat
	@OneToMany(mappedBy = "studierender")
	private Set<Testat> testats;

	// bi-directional many-to-one association to VorgemerkteAushaenge
	@OneToMany(mappedBy = "student")
	private Set<VorgemerkteAushaenge> vorgemerkteAushaenge;

	public Student() {
	}

	
	public Integer getMatrikelnummer() {
		return this.matrikelnummer;
	}

	public void setMatrikelnummer(Integer matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}

	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
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

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Set<Angemeldetepruefung> getStatedExams() {
		return this.statedExams;
	}

	public void setStatedExams(Set<Angemeldetepruefung> statedExams) {
		this.statedExams = statedExams;
	}

	public Angemeldetepruefung addStatedExam(Angemeldetepruefung statedExam) {
		if(getStatedExams() == null) {
			setStatedExams(new HashSet<Angemeldetepruefung>());
		}
		getStatedExams().add(statedExam);
		statedExam.setTeilnehmer(this);

		return statedExam;
	}

	public Angemeldetepruefung removeStatedExam(Angemeldetepruefung statedExam) {
		if(getStatedExams() == null) {
			setStatedExams(new HashSet<Angemeldetepruefung>());
		}
		getStatedExams().remove(statedExam);
		statedExam.setTeilnehmer(null);

		return statedExam;
	}

	public Set<KrankUrlaub> getKrankUrlaubungen() {
		return this.krankUrlaubungen;
	}

	public void setKrankUrlaubungen(Set<KrankUrlaub> krankUrlaubungen) {
		this.krankUrlaubungen = krankUrlaubungen;
	}

	public KrankUrlaub addKrankUrlaubungen(KrankUrlaub krankUrlaubungen) {
		if(getKrankUrlaubungen() == null) {
			setKrankUrlaubungen(new HashSet<KrankUrlaub>());
		}
		getKrankUrlaubungen().add(krankUrlaubungen);
		krankUrlaubungen.setStudiker(this);

		return krankUrlaubungen;
	}

	public KrankUrlaub removeKrankUrlaubungen(KrankUrlaub krankUrlaubungen) {
		if(getKrankUrlaubungen() == null) {
			setKrankUrlaubungen(new HashSet<KrankUrlaub>());
		}
		getKrankUrlaubungen().remove(krankUrlaubungen);
		krankUrlaubungen.setStudiker(null);

		return krankUrlaubungen;
	}

	public Fachbereich getFachbereich() {
		return this.fachbereich;
	}

	public void setFachbereich(Fachbereich fachbereich) {
		this.fachbereich = fachbereich;
	}

	public Po getPo() {
		return this.po;
	}

	public void setPo(Po po) {
		this.po = po;
	}

	public Studiengang getStudiengang() {
		return this.studiengang;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
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
		testat.setStudierender(this);

		return testat;
	}

	public Testat removeTestat(Testat testat) {
		if(getTestats() == null) {
			setTestats(new HashSet<Testat>());
		}
		getTestats().remove(testat);
		testat.setStudierender(null);

		return testat;
	}

	public Set<VorgemerkteAushaenge> getVorgemerkteAushaenge() {
		return this.vorgemerkteAushaenge;
	}

	public void setVorgemerkteAushaenge(Set<VorgemerkteAushaenge> vorgemerkteAushaenge) {
		this.vorgemerkteAushaenge = vorgemerkteAushaenge;
	}

	public VorgemerkteAushaenge addVorgemerkteAushaenge(VorgemerkteAushaenge vorgemerkteAushaenge) {
		if(getVorgemerkteAushaenge() == null) {
			setVorgemerkteAushaenge(new HashSet<VorgemerkteAushaenge>());
		}
		getVorgemerkteAushaenge().add(vorgemerkteAushaenge);
		vorgemerkteAushaenge.setStudent(this);

		return vorgemerkteAushaenge;
	}

	public VorgemerkteAushaenge removeVorgemerkteAushaenge(VorgemerkteAushaenge vorgemerkteAushaenge) {
		if(getVorgemerkteAushaenge() == null) {
			setVorgemerkteAushaenge(new HashSet<VorgemerkteAushaenge>());
		}
		getVorgemerkteAushaenge().remove(vorgemerkteAushaenge);
		vorgemerkteAushaenge.setStudent(null);

		return vorgemerkteAushaenge;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
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
		return "Student [matrikelnummer=" + matrikelnummer + ", credits=" + credits + ", email=" + email + ", name="
				+ name + ", semester=" + semester + ", status=" + status + ", vorname=" + vorname + ", fachbereich="
				+ fachbereich + ", po=" + po + ", studiengang=" + studiengang + "]";
	}

}