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
 * The persistent class for the studiengang database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="studiengang")
@NamedQuery(name="Studiengang.findAll", query="SELECT s FROM Studiengang s")
public class Studiengang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="studiengang")
	private Set<Student> studierende;

	public Studiengang() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudierende() {
		return this.studierende;
	}

	public void setStudierende(Set<Student> studierende) {
		this.studierende = studierende;
	}

	public Student addStudierende(Student studierende) {
		if(getStudierende() == null) {
			setStudierende(new HashSet<Student>());
		}
		getStudierende().add(studierende);
		studierende.setStudiengang(this);

		return studierende;
	}

	public Student removeStudierende(Student studierende) {
		if(getStudierende() == null) {
			setStudierende(new HashSet<Student>());
		}
		getStudierende().remove(studierende);
		studierende.setStudiengang(null);

		return studierende;
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
		Studiengang other = (Studiengang) obj;
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
		return "Studiengang [id=" + id + ", name=" + name + "]";
	}

}