package model.entitys;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the geschriebene_pruefungen database table.
 * 
 */
@Entity
@Table(schema="k39752uz_gpm_server1", name="geschriebene_pruefungen")
@NamedQuery(name="GeschriebenePruefungen.findAll", query="SELECT g FROM GeschriebenePruefungen g")
public class GeschriebenePruefungen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="note")
	private Double note;

	@Column(name="versuch")
	private Integer versuch;

	//bi-directional many-to-one association to Angemeldetepruefung
	@ManyToOne
	@JoinColumn(name="ang_id")
	private Angemeldetepruefung statedTest;

	public GeschriebenePruefungen() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getNote() {
		return this.note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public Integer getVersuch() {
		return this.versuch;
	}

	public void setVersuch(Integer versuch) {
		this.versuch = versuch;
	}

	public Angemeldetepruefung getStatedTest() {
		return this.statedTest;
	}

	public void setStatedTest(Angemeldetepruefung statedTest) {
		this.statedTest = statedTest;
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
		GeschriebenePruefungen other = (GeschriebenePruefungen) obj;
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
		return "GeschriebenePruefungen [id=" + id + ", note=" + note + ", versuch=" + versuch + "]";
	}

}