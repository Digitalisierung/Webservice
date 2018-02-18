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
 * The persistent class for the po database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="po")
@NamedQuery(name="Po.findAll", query="SELECT p FROM Po p")
public class Po implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="po")
	private Set<Student> studenten;

	public Po() {
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

	public Set<Student> getStudenten() {
		return this.studenten;
	}

	public void setStudenten(Set<Student> studenten) {
		this.studenten = studenten;
	}

	public Student addStudenten(Student studenten) {
		if(getStudenten() == null) {
			setStudenten(new HashSet<Student>());
		}
		getStudenten().add(studenten);
		studenten.setPo(this);

		return studenten;
	}

	public Student removeStudenten(Student studenten) {
		if(getStudenten() == null) {
			setStudenten(new HashSet<Student>());
		}
		getStudenten().remove(studenten);
		studenten.setPo(null);

		return studenten;
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
		Po other = (Po) obj;
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
		return "Po [id=" + id + ", name=" + name + "]";
	}

}