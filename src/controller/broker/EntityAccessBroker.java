package controller.broker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.exception.SaveFailedException;
import model.access.EntityAccessPoint;
import model.dto.AdvertisementDTO;
import model.dto.BenotungDTO;
import model.dto.CheckApprovalDTO;
import model.dto.IdDTO;
import model.dto.PruefungDTO;
import model.dto.SelectedThesisThemeDTO;
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

public class EntityAccessBroker {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityAccessPoint eap;

	public EntityAccessBroker() {
		this.emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		this.eap = new EntityAccessPoint(em);
	}

	public Integer deactivateAdvertisementByID(IdDTO id) throws SaveFailedException {
		Integer count = 0;
		count = eap.setAushangEntryInaktiv(id.getId());

		return count;
	}

	public AdvertisementDTO storeAdvertisement(AdvertisementDTO announce) {
		Integer id;
		Aushang aushang = new Aushang();
		Professor prof = eap.getProfessorByEmail(announce.getProfessor());

		if (prof == null) {
			// throw new Exception();
			return announce;
		}

		aushang.setTitel(announce.getTopic());
		aushang.setBeschreibung(announce.getDescription());
		aushang.setSchwierigkeitsgrad((Integer) announce.getDegree().intValue());
		aushang.setProfessor(prof);
		aushang.setAktiv(true);

		id = eap.saveAushang(aushang);

		return new AdvertisementDTO(id, aushang.getTitel(), aushang.getBeschreibung(), aushang.getSchwierigkeitsgrad(),
				aushang.getProfessor().getEmail());

	}

	public SelectedThesisThemeDTO saveSelectedThesisTheme(SelectedThesisThemeDTO selExams) throws SaveFailedException {

		Student student;
		Aushang aushang;
		Date datum = new Date();
		VorgemerkteAushaenge va = new VorgemerkteAushaenge();

		try {
			student = eap.getStudentByID((Integer) selExams.getMatrikelnummer().intValue());
			aushang = eap.findAushang((Integer) selExams.getAushangId().intValue());

			va.setAushang(aushang);
			va.setStudent(student);
			va.setDatum(datum);
			va.setBetreuerEinverstanden(new Integer(0));

			va = eap.findVorgemerkterAushang(eap.saveSelectedExam(va));

		} catch (EntryNotFoundException e) {
			throw new SaveFailedException("UpdateFailedException: " + e.getMessage());
		}

		return new SelectedThesisThemeDTO(va.getId(), va.getAushang().getId(), va.getBetreuerEinverstanden(),
				va.getStudent().getMatrikelnummer());

	}

	public Aktuellepruefung saveAktuellePruefung(SubjectDTO subject) {
		Professor prof = eap.getProfessorByEmail(subject.getProf_email());
		Pruefungen exams = eap.getPruefungenByID((Integer)subject.getSubjectID().intValue());
		
		Aktuellepruefung ap = new Aktuellepruefung();
		
		ap.setAufsicht("" + prof.getVorname() + " " +prof.getName());
		ap.setDatum(new Date());
		ap.setProf(prof);
		ap.setPruefung(exams);
		ap.setRaum("");
		ap.setAngemeldetePruefunge(new HashSet<Angemeldetepruefung>());
		
		return eap.saveAktuellepruefung(ap);
		
	}
	
	
	public Integer saveStudentAssessment(BenotungDTO benotung) {
		return eap.saveStudentAssessment(benotung);
	}

	public List<AdvertisementDTO> findAllActiveAdvertisementsByProfessorEmail(String emal) {

		Professor prof = eap.getProfessorByEmail(emal);
		List<Aushang> aushang = eap.getAushangsListByProfessorID(prof.getId());
		List<AdvertisementDTO> advs = new ArrayList<AdvertisementDTO>();

		for (Aushang a : aushang) {
			advs.add(new AdvertisementDTO(a.getId(), a.getTitel(), a.getBeschreibung(), a.getSchwierigkeitsgrad(),
					prof.getEmail()));
		}

		return advs;
	}

	public List<AdvertisementDTO> findAllActiveAdvertisements() {
		List<Aushang> aushaenge = eap.getAllAktiveAushangs();
		List<AdvertisementDTO> advs = new ArrayList<AdvertisementDTO>();

		for (Aushang a : aushaenge) {
			advs.add(new AdvertisementDTO(a.getId(), a.getTitel(), a.getBeschreibung(), a.getSchwierigkeitsgrad(),
					a.getProfessor().getEmail()));
		}

		return advs;

	}

	// PRAXISPROJEKT ANMELDEN # Für Prüfungen hollen Task #
	public List<SubjectDTO> getListOfSubjects() {
		return eap.getListOfSubjects();
	}

	public List<PruefungDTO> getAllAktuellePruefungs() {
		List<Aktuellepruefung> exams = eap.getAllAktuellePruefungs();
		
		List<PruefungDTO> result = new ArrayList<PruefungDTO>();
		
		for(Aktuellepruefung exam : exams) {
			result.add(new PruefungDTO(
					exam.getId(), 
					exam.getPruefung().getName(), 
					(exam.getProf().getTitel() + " " + exam.getProf().getVorname() + " " + exam.getProf().getName()), 
					exam.getDatum().toString(), 
					exam.getRaum()
					)
					);
		}
		
		return result;
	}

	public AdvertisementDTO update(AdvertisementDTO announce) throws Exception {

		Aushang aushang;
		Aushang result;

		try {
			aushang = eap.findAushang(announce.getId());

			aushang.setTitel(announce.getTopic());
			aushang.setBeschreibung(announce.getDescription());
			aushang.setSchwierigkeitsgrad(announce.getDegree());

			result = eap.updateDataBase(aushang);

			return new AdvertisementDTO(result.getId(), result.getTitel(), result.getBeschreibung(),
					result.getSchwierigkeitsgrad(), result.getProfessor().getEmail());

		} catch (EntryNotFoundException e) {
			throw new Exception();
		} catch (SaveFailedException e) {
			throw new Exception();
		}

	}

	public SelectedThesisThemeDTO update(SelectedThesisThemeDTO selectedThesis) {
		VorgemerkteAushaenge result = null;
		Integer vorgemerkterAushangid = (Integer) selectedThesis.getId().intValue();
		Integer betreuerEinverstd = (Integer) selectedThesis.getBetreuerEinverstanden().intValue();

		VorgemerkteAushaenge va = eap.findVorgemerkterAushang(vorgemerkterAushangid);
		va.setBetreuerEinverstanden(betreuerEinverstd);
		va.setDatum(new Date());

		try {
			result = eap.updateDataBase(va);
		} catch (EntryNotFoundException e) {
			result = new VorgemerkteAushaenge();
			result.setId(0);
			result.setBetreuerEinverstanden(0);
			result.setAushang(va.getAushang());
			result.setStudent(va.getStudent());
		} catch (SaveFailedException e) {
			result = new VorgemerkteAushaenge();
			result.setId(0);
			result.setBetreuerEinverstanden(0);
			result.setAushang(va.getAushang());
			result.setStudent(va.getStudent());
		}
		return new SelectedThesisThemeDTO(result.getId(), result.getAushang().getId(),
				result.getBetreuerEinverstanden(), result.getStudent().getMatrikelnummer());

	}
	
	
	public Boolean deleteVorgemerkteAushaengeById(Integer id) {
		return eap.deleteVorgemAushangBy(id);
	}

	
	public Integer registerPracticalExam(CheckApprovalDTO approval) {
		Integer id = 0;
		Student student = null;
		Aktuellepruefung akt_p = null;
		
		try {
			student = eap.getStudentByID((Integer)approval.getMatrikelnummer().intValue());
			akt_p = eap.getAktuellePruefungByID((Integer)approval.getExamID().intValue());
		} catch (EntryNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		Angemeldetepruefung ap = new Angemeldetepruefung();
		
		ap.setAktuellePruefung(akt_p);
		ap.setGeschriebenePruefungen(new HashSet<GeschriebenePruefungen>());
		ap.setStatus(1);
		ap.setTeilnehmer(student);
		
		id = eap.registerPracticeExam(ap);
		
		return id;
	}
	
	
	public Boolean checkApproval(Integer matrikelnummer) {
		Integer credits = eap.getCreditsSumByStudentID(matrikelnummer);
		
		if(credits >= 120) {
			return true;
		}
		
		return false;
	}

}
