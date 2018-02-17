package model.access;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.QueryException;

import controller.exception.SaveFailedException;
import model.entitys.Aushang;
import model.entitys.Professor;
import model.entitys.Student;
import model.entitys.VorgemerkteAushaenge;
import model.exceptions.EntryNotFoundException;

public class EntityAccessPoint {

	private EntityManager em;

	public EntityAccessPoint(EntityManager em) {
		this.em = em;
	}

	public Aushang updateDataBase(Aushang aushang) throws EntryNotFoundException, QueryException, SaveFailedException {
		Aushang a;
		Integer count = 0;
		Integer id = aushang.getId();
		EntityTransaction et = em.getTransaction();
		Query query = em.createQuery("UPDATE Aushang a SET " + "a.titel='" + aushang.getTitel() + "', "
				+ "a.beschreibung='" + aushang.getBeschreibung() + "', " + "a.schwierigkeitsgrad="
				+ aushang.getSchwierigkeitsgrad() + " " + "WHERE a.id=" + id);
		et.begin();
		count = query.executeUpdate();
		et.commit();

		if (count > 0) {
			a = em.find(Aushang.class, id);
			if (a == null) {
				throw new EntryNotFoundException(
						"After update: Aushang mit id=" + id + " gibt einen null-Wert zurück.");
			}
			return a;
		} else {
			throw new SaveFailedException("SaveFailedException: Es wurden " + count + " Datensätze geändert.");
		}

	}

	public VorgemerkteAushaenge updateDataBase(VorgemerkteAushaenge va)
			throws EntryNotFoundException, SaveFailedException {
		Integer count = 0;
		Integer id = va.getId();
		Query query = em.createQuery("UPDATE VorgemerkteAushaenge v SET " + "v.betreuerEinverstanden="
				+ va.getBetreuerEinverstanden() + " " + "WHERE v.id=" + id);
		em.getTransaction().begin();
		count = query.executeUpdate();
		em.getTransaction().commit();

		if (count > 0) {
			va = null;
			va = em.find(VorgemerkteAushaenge.class, id);
			if (va == null) {
				throw new EntryNotFoundException(
						"After update: VorgemerkterAushang mit id=" + id + " gibt einen null-Wert zurück.");
			}
			return va;
		} else {
			throw new SaveFailedException("UpdateFailedException: es wurden " + count + " Datensätze geändert.");
		}

	}

	public Professor getProfessor(String email) {
		Query query = em.createQuery("SELECT p FROM Professor p WHERE p.email LIKE '" + email + "'");

		@SuppressWarnings("unchecked")
		List<Professor> professoren = (List<Professor>) query.getResultList();
		Professor prof = null;

		for (Professor p : professoren) {
			if (email.equals(p.getEmail())) {
				prof = p;
				break;
			}
		}

		return prof;
	}

	public Integer saveAushang(Aushang aushang) {
		Integer id = 0;

		if (aushang != null) {
			em.getTransaction().begin();
			em.persist(aushang);
			em.flush();
			id = aushang.getId();
			em.getTransaction().commit();
		}

		return id;

	}

	public Integer saveSelectedExam(VorgemerkteAushaenge va) {
		Integer id = 0;

		if (va != null) {
			em.getTransaction().begin();
			em.persist(va);
			em.flush();
			id = va.getId();
			em.getTransaction().commit();
		}

		return id;

	}

	public List<Aushang> getAushangsListOf(Integer id) {
		Query query = em.createQuery("SELECT a FROM Aushang a WHERE a.professor.id=" + id + " AND a.aktiv=1");
		@SuppressWarnings("unchecked")
		List<Aushang> aushaenge = (List<Aushang>) query.getResultList();

		if (aushaenge == null) {
			aushaenge = new ArrayList<Aushang>();
		}

		return aushaenge;
	}

	public List<Aushang> getAllAktiveAushangs() {
		Query query = em.createQuery("SELECT a FROM Aushang a WHERE a.aktiv=1");
		@SuppressWarnings("unchecked")
		List<Aushang> aushaenge = (List<Aushang>) query.getResultList();

		if (aushaenge == null) {
			aushaenge = new ArrayList<Aushang>();
		}

		return aushaenge;
	}

	public Student findStudent(Integer id) throws EntryNotFoundException {
		Student s = em.find(Student.class, id);

		if (s == null) {
			throw new EntryNotFoundException("Student mit id=" + id + " gibt einen null-Wert zurück.");
		}

		return s;
	}

	public Aushang findAushang(Integer id) throws EntryNotFoundException {
		Aushang a = em.find(Aushang.class, id);

		if (a == null) {
			throw new EntryNotFoundException("Aushang mit id=" + id + " gibt einen null-Wert zurück.");
		}

		return a;
	}

	public VorgemerkteAushaenge findVorgemerkterAushang(Integer id) {
		return em.find(VorgemerkteAushaenge.class, id);

	}

	public Integer setAushangEntryInaktiv(Integer id) {
		Integer count = 0;
		Query query = em.createQuery("UPDATE Aushang a SET a.aktiv=false WHERE a.id=" + id);
		em.getTransaction().begin();
		count = query.executeUpdate();
		em.getTransaction().commit();

		return count;
	}

	public Boolean deleteVorgemAushangBy(Integer id) {
		Boolean flag = false;
		VorgemerkteAushaenge va = em.find(VorgemerkteAushaenge.class, id);

		if (va != null) {
			em.getTransaction().begin();
			em.remove(va);
			em.getTransaction().commit();
		}

		VorgemerkteAushaenge result = em.find(VorgemerkteAushaenge.class, id);

		if (result == null) {
			flag = true;
		}

		return flag;

	}



}
