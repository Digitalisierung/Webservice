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
 * The persistent class for the fachbereich database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="fachbereich")
@NamedQuery(name="Fachbereich.findAll", query="SELECT f FROM Fachbereich f")
public class Fachbereich implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="fachbereich")
	private Set<Student> students;

	public Fachbereich() {
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

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		if(getStudents() == null) {
			setStudents(new HashSet<Student>());
		}
		getStudents().add(student);
		student.setFachbereich(this);

		return student;
	}

	public Student removeStudent(Student student) {
		if(getStudents() == null) {
			setStudents(new HashSet<Student>());
		}
		getStudents().remove(student);
		student.setFachbereich(null);

		return student;
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
		Fachbereich other = (Fachbereich) obj;
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
		return "Fachbereich [id=" + id + ", name=" + name + "]";
	}

}