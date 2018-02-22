package rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.broker.EntityAccessBroker;
import model.dto.IdDTO;
import model.dto.SubjectDTO;

@Path("practice")
public class PracticeExamRegisterProcess {
	EntityAccessBroker broker = new EntityAccessBroker();
	
	/*
	 * 
	 */
	@GET
	@Path("approval/{matrikel}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubjectDTO> checkApproval(@PathParam("matrikel") Integer matrikelnummer) {
		return broker.getListOfSubjects(matrikelnummer);
	}
	
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public IdDTO registerPracticalExam(IdDTO flag) {
		flag.setId(1);
		//broker.surchStudent(flag);
		return flag;
	}
	
	
	@POST
	@Path("grade")
	@Produces(MediaType.APPLICATION_JSON)
	public IdDTO setPracticalWorkMark(IdDTO mark) {
		return mark;

	}

}
