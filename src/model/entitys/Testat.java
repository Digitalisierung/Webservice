package model.entitys;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the testat database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="testat")
@NamedQuery(name="Testat.findAll", query="SELECT t FROM Testat t")
public class Testat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Pruefungen
	@ManyToOne
	@JoinColumn(name="pruefung_id")
	private Pruefungen exam;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student studierender;

	
	public Testat() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pruefungen getExam() {
		return this.exam;
	}

	public void setExam(Pruefungen exam) {
		this.exam = exam;
	}

	public Student getStudierender() {
		return this.studierender;
	}

	public void setStudierender(Student studierender) {
		this.studierender = studierender;
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
		Testat other = (Testat) obj;
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
		return "Testat [id=" + id + ", exam=" + exam + ", studierender=" + studierender + "]";
	}

}