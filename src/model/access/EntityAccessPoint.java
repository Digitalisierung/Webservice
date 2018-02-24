package model.access;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import controller.exception.SaveFailedException;
import model.dto.BenotungDTO;
import model.dto.SubjectDTO;
import model.entitys.Aktuellepruefung;
import model.entitys.Angemeldetepruefung;
import model.entitys.Aushang;
import model.entitys.GeschriebenePruefungen;
import model.entitys.Professor;
import model.entitys.Pruefungen;
import model.entitys.Student;
import model.entitys.VorgemerkteAushaenge;
import model.exceptions.EntryNotFoundException;

public class EntityAccessPoint {

	private EntityManager em;

	public EntityAccessPoint(EntityManager em) {
		this.em = em;
	}

	public Aushang updateDataBase(Aushang aushang) throws EntryNotFoundException, SaveFailedException {
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

	public Professor getProfessorByEmail(String email) {
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

	public Professor getProfessorByID(Integer id) {
		Professor prof = em.find(Professor.class, id);

		if (prof == null) {
			// throw new Exception()
		}

		return prof;
	}

	public List<Aushang> getAushangsListByProfessorID(Integer id) {
		Query query = em.createQuery("SELECT a FROM Aushang a WHERE a.professor.id=" + id + " AND a.aktiv=1");
		@SuppressWarnings("unchecked")
		List<Aushang> aushaenge = (List<Aushang>) query.getResultList();

		if (aushaenge == null) {
			aushaenge = new ArrayList<Aushang>();
		}

		return aushaenge;
	}
	
	public Aushang getAushangByID(Integer id) {
		Aushang aus = em.find(Aushang.class, id);
		
		if(aus == null) {
			// throw new Exception()
		}
		
		return aus;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Aktuellepruefung> getAllAktuellePruefungs(){
		Query query = em.createQuery("SELECT a FROM Aktuellepruefung a");
		
		return query.getResultList();
		
	}
	
	
	public Aktuellepruefung getAktuellePruefungByID(Integer id) {
		Aktuellepruefung ap = em.find(Aktuellepruefung.class, id);
		
		if(ap == null) {
			// throw new Exception();
		}
		
		return ap;
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

	// PRAXISPROJEKT ANMELDEN # Prüfungen holen #
	public List<SubjectDTO> getListOfSubjects() {
		List<SubjectDTO> result = new ArrayList<SubjectDTO>();
		
		Query query = em.createQuery("SELECT p FROM Pruefungen p");
		
		@SuppressWarnings("unchecked")
		List<Pruefungen> ls = query.getResultList();
		
		for(Pruefungen p : ls) {
			result.add(new SubjectDTO(p.getId(), p.getName(), p.getCredits()));
		}
		
		return result;
	}

	public Student getStudentByID(Integer matrikelnummer) throws EntryNotFoundException {
		Student student = em.find(Student.class, matrikelnummer);

		if (student == null) {
			throw new EntryNotFoundException("Student mit id=" + matrikelnummer + " gibt einen null-Wert zurück.");
		}

		return student;
	}

	public VorgemerkteAushaenge getApprovalThesisByStudentID(Integer matrikelnummer) {
		VorgemerkteAushaenge va = null;

		Query query = em.createQuery("SELECT v FROM VorgemerkteAushaenge v WHERE v.student.matrikelnummer="
				+ matrikelnummer + " AND v.betreuerEinverstanden=1");

		@SuppressWarnings("unchecked")
		List<VorgemerkteAushaenge> ls = query.getResultList();

		for(VorgemerkteAushaenge v : ls) {
			va = v;
		}

		return va;

	}
	
	
	public Pruefungen getPruefungenByID(Integer id) {
		Pruefungen exams = em.find(Pruefungen.class, id);
		
		return exams;
	}

	public Integer getCreditsSumByStudentID(Integer matrikelnummer) {
		Query query = em.createQuery("SELECT SUM(p.credits) "
				+ "FROM GeschriebenePruefungen g "
				+ "JOIN Angemeldetepruefung a ON a.geschriebenePruefungen.id=g.id "
				+ "JOIN Aktuellepruefung ap ON ap.id=a.aktuellePruefung.id "
				+ "JOIN Pruefungen p ON p.id=ap.pruefung.id "
				+ "WHERE (a.teilnehmer.matrikelnummer=" + matrikelnummer + ") AND (g.note<=4.0)");
		
		Long result = (Long)query.getSingleResult();
		
		if(result == null) {
			result = new Long(0);
		}
		
		return (Integer)result.intValue();
		
	}

	public Integer setAushangEntryInaktiv(Integer id) {
		Integer count = 0;
		Query query = em.createQuery("UPDATE Aushang a SET a.aktiv=false WHERE a.id=" + id);
		em.getTransaction().begin();
		count = query.executeUpdate();
		em.getTransaction().commit();

		return count;
	}

	public Object setCurrentExam(String email) {
		Professor prof = getProfessorByEmail(email);
		Query query = em.createQuery("SELECT a FROM Angemeldetepruefung a");
		@SuppressWarnings("unchecked")
		List<Angemeldetepruefung> ls = (List<Angemeldetepruefung>) query.getResultList();
		Set<Angemeldetepruefung> set = new HashSet<Angemeldetepruefung>();

		for (Angemeldetepruefung ap : ls) {
			set.add(ap);
		}

		if (prof != null) {
			Aktuellepruefung ap = new Aktuellepruefung();
			ap.setAngemeldetePruefunge(set);
			ap.setDatum(new Date());
			ap.setProf(prof);
			// ap.setPruefung(pruefung);

		}
		return null;
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
	
	
	public Aktuellepruefung saveAktuellepruefung(Aktuellepruefung ap) {
		
		em.getTransaction().begin();
		em.persist(ap);
		em.flush();
		em.getTransaction().commit();
		
		return ap;
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
	
	
	public Integer saveStudentAssessment(BenotungDTO benotung) {
		Integer id = 0;
		Query query = em.createQuery("SELECT a FROM Angemeldetepruefung a WHERE a.teilnehmer.matrikelnummer=" + benotung.getMatrikelnummer());
		@SuppressWarnings("unchecked")
		List<Angemeldetepruefung> ls = query.getResultList();
		
		for(Angemeldetepruefung a : ls) {
			if(a.getAktuellePruefung().getPruefung().getId() == 22) {
				GeschriebenePruefungen gp = new GeschriebenePruefungen();
				gp.setNote(benotung.getGrade().doubleValue());
				gp.setStatedTest(a);
				gp.setVersuch(1);
				
				em.getTransaction().begin();
				em.persist(gp);
				em.flush();
				id = gp.getId();
				em.getTransaction().commit();
			}
			
		}
		
		return id;
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
	
	
	public Integer registerPracticeExam(Angemeldetepruefung ap) {
		
		em.getTransaction().begin();
		em.persist(ap);
		em.flush();
		em.getTransaction().commit();
		
		return ap.getId();
	}

}
