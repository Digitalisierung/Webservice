package rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dto.SubjectDTO;

@Path("practical_exam")
public class PracticeExamRegisterProcess {
	
	@GET
	@Path("approval/{matrikel}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubjectDTO> checkApproval(@PathParam("matrikel") Integer matrikelnummer) {
		return null;
	}
	
	@GET
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public String registerPracticalExam() {
		return "It works!";
	}

}
