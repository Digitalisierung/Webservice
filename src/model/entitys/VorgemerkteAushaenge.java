package model.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vorgemerkte_aushaenge database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="vorgemerkte_aushaenge")
@NamedQuery(name="VorgemerkteAushaenge.findAll", query="SELECT v FROM VorgemerkteAushaenge v")
public class VorgemerkteAushaenge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum")
	private Date datum;

	@Column(name="betreuer_einverstanden")
	private Integer betreuerEinverstanden;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	//bi-directional many-to-one association to Aushang
	@ManyToOne
	@JoinColumn(name="aushang_id")
	private Aushang aushang;

	
	public VorgemerkteAushaenge() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Integer getBetreuerEinverstanden() {
		return betreuerEinverstanden;
	}

	public void setBetreuerEinverstanden(Integer betreuerEinverstanden) {
		this.betreuerEinverstanden = betreuerEinverstanden;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Aushang getAushang() {
		return this.aushang;
	}

	public void setAushang(Aushang aushang) {
		this.aushang = aushang;
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
		VorgemerkteAushaenge other = (VorgemerkteAushaenge) obj;
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
		return "VorgemerkteAushaenge [id=" + id + ", datum=" + datum + ", themaVorgemerkt=" + betreuerEinverstanden
				+ ", student=" + student + ", aushang=" + aushang + "]";
	}

}