package model.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the krank_urlaub database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="krank_urlaub")
@NamedQuery(name="KrankUrlaub.findAll", query="SELECT k FROM KrankUrlaub k")
public class KrankUrlaub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="antrag_freigabe")
	private Date antragFreigabe;

	@Temporal(TemporalType.DATE)
	@Column(name="antrag_stellen")
	private Date antragStellen;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student studiker;

	public KrankUrlaub() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAntragFreigabe() {
		return this.antragFreigabe;
	}

	public void setAntragFreigabe(Date antragFreigabe) {
		this.antragFreigabe = antragFreigabe;
	}

	public Date getAntragStellen() {
		return this.antragStellen;
	}

	public void setAntragStellen(Date antragStellen) {
		this.antragStellen = antragStellen;
	}

	public Student getStudiker() {
		return this.studiker;
	}

	public void setStudiker(Student studiker) {
		this.studiker = studiker;
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
		KrankUrlaub other = (KrankUrlaub) obj;
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
		return "KrankUrlaub [id=" + id + ", antragFreigabe=" + antragFreigabe + ", antragStellen=" + antragStellen
				+ ", student=" + studiker + "]";
	}

}