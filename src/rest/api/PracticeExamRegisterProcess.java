package rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.broker.EntityAccessBroker;
import model.dto.ApprovalDTO;
import model.dto.BenotungDTO;
import model.dto.CheckApprovalDTO;
import model.dto.IdDTO;
import model.dto.PruefungDTO;
import model.dto.SubjectDTO;
import model.entitys.Aktuellepruefung;

@Path("practice")
public class PracticeExamRegisterProcess {
	EntityAccessBroker broker = new EntityAccessBroker();
	
	/*
	 * Task: Prüfungen holen
	 * Beschreibung:
	 * 			Eine Liste aller Prüfungen wird zurueckgegeben
	 */
	@GET
	@Path("subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubjectDTO> checkApproval() {
		return broker.getListOfSubjects();
	}
	
	
	/*
	 * Task: aktuelle P. setzen
	 * Beschreibung:
	 * 			
	 */
	@POST
	@Path("activate")
	@Produces(MediaType.APPLICATION_JSON)
	public PruefungDTO setExamAktive(SubjectDTO subject) {
		/*
		 * man bekommt prof email und Prüfung id
		 * 1. prof finden (email)
		 * 2. prüfung finden
		 * 3. AktuellePruefung setzen
		 * 
		 */
		//ApprovalPracticalExamDTO o = broker.getApprovalThesisThemenByStudentID(flag.getId());
		Aktuellepruefung ap = broker.saveAktuellePruefung(subject);
		
		PruefungDTO exam = new PruefungDTO();
		exam.setId(ap.getId());
		exam.setName(ap.getPruefung().getName());
		exam.setRaum(ap.getRaum());
		exam.setDatum(ap.getDatum().toString());
		exam.setAufsicht(ap.getAufsicht());
		
		return exam;
		
	}
	
	
	
	/*
	 * TASK: Aktuelle P. holen
	 */
	@GET
	@Path("exam")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PruefungDTO> getAktiveExams() {
		return broker.getAllAktuellePruefungs();

	}
	
	
	/*
	 * TASK: Zulassung Prüfen
	 */
	@GET
	@Path("approval/{matrikelnummer}")
	@Produces(MediaType.APPLICATION_JSON)
	public ApprovalDTO checkApproval(@PathParam("matrikelnummer") Integer matrikelnummer) {
		ApprovalDTO result = new ApprovalDTO();
		result.setApproval(broker.checkApproval(matrikelnummer));
		return result;
	}
	
	
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public IdDTO registerPracticalExam(CheckApprovalDTO approval) {
		IdDTO Id = new IdDTO();
		Integer id = broker.registerPracticalExam(approval);
		Id.setId(id);
		
		return Id;
	}
	
	
	@POST
	@Path("grade")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer setGradeForPracticeExam(BenotungDTO benotung) {
		return broker.saveStudentAssessment(benotung);
		
	}

}
