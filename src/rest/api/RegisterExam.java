package rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dto.AbmeldungDTO;
import model.dto.AnmeldungDTO;
import model.dto.AnmeldungenDTO;
import model.dto.PruefungDTO;

@Path("exams")
public class RegisterExam {
	
	private EntityManager em;
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PruefungDTO> get(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		
		List<PruefungDTO> ls = new ArrayList<PruefungDTO>();
		System.out.println("Vor dem Select");
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createQuery("SELECT p.id, p.name, b.aufsicht, b.datum, b.raum "
				+ "FROM Pruefungen p "
				+ "JOIN Aktuellepruefung b ON p.id=b.pruefung.id").getResultList();
		
		System.out.println("Nach dem Select");

		for(Object[] o : result) {
			ls.add(new PruefungDTO((Number)o[0], (String)o[1], (String)o[2], o[3].toString(), (String)o[4]));
		}
		return ls;
	}
	
	@GET
	@Path("dto")
	public AnmeldungDTO test(){

		return new AnmeldungDTO(1,1);
	}
	/*
	 * 
	 * 
	 * http://localhost:8080/service/thesis/exams/anmelden
	 * {
    		"akt_id": 1,
    		"student_id": 2
		}
	 * 
	 * */
	@POST
	@Path("anmelden")
	@Produces(MediaType.APPLICATION_JSON)
	public int KlausurAnmelden(AnmeldungDTO an) {
		System.out.println(an);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		em.getTransaction().begin();
		
		/*
		 * INSERT  k39752uz_gpm_server1.angemeldetepruefung (student_id, akt_id) 
			SELECT  1,1
			WHERE   NOT EXISTS 
        (   SELECT  *
            FROM    k39752uz_gpm_server1.angemeldetepruefung 
            WHERE   student_id = 1 
            AND     akt_id = 1
        )
		 */
		
		//Kein Dot.Walkin -> enfach orginalen Namen nehmen!
		
		Query query = em.createNativeQuery("INSERT INTO Angemeldetepruefung (student_id , akt_id )"+ 
				" SELECT " + an.getStudent_id() + ", " + an.getAkt_id() 
				+ " WHERE NOT EXISTS ("
				+ " SELECT * FROM Angemeldetepruefung "
				+ " WHERE student_id = "+ an.getStudent_id()
				+ " AND akt_id = " + an.getAkt_id() +")");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        
        return 1;
	}
	
	@PUT
	@Path("abmelden")
	@Produces(MediaType.APPLICATION_JSON)
	public AbmeldungDTO KlausurAbmelden(AbmeldungDTO ab) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("UPDATE Angemeldetepruefung a SET a.status = 0 "
				+ "WHERE a.teilnehmer.matrikelnummer=" + ab.getStudent_id()
				+ " AND a.aktuellePruefung.id=" + ab.getAkt_id());
		query.executeUpdate();
		
		em.getTransaction().commit();
	    em.close();
		return ab;
	}
	
	@GET
	@Path("checktestate/{id}")
	public List<Number> checkTestat(@PathParam("id") Integer id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		/*
		 * 
		 *  SELECT d.`id` 
			FROM  `angemeldetepruefung` d
			JOIN  `aktuellepruefung` c ON d.`akt_id` = c.`id` 
			LEFT JOIN  `testat` t ON c.`pruefung_id` = t.`pruefung_id` 
			WHERE t.`pruefung_id` IS NULL 
			AND d.`student_id` =1
		 * 
		 * */
		
		List<Number> nichtZugelassenePruefungen = new ArrayList<Number>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createQuery(""
				+ "SELECT d.id " + 
				"FROM Angemeldetepruefung d " + 
				"JOIN Aktuellepruefung c ON d.aktuellePruefung.id = c.id " + 
				"JOIN Testat t ON t.exam.id = c.pruefung.id " + 
				"WHERE (t.exam.id IS NULL AND d.teilnehmer.matrikelnummer = "+ id + ")").getResultList();

		System.out.println("Nach dem Select");
		
		for(Object[] o : result) {
			nichtZugelassenePruefungen.add((Number)o[0]);
		}

		
		return nichtZugelassenePruefungen;
	}
	
	
	@GET
	@Path("anmeldeliste/{prof_id}/{akt_id}")
	public List<AnmeldungenDTO> showAnmeldungen(@PathParam("prof_id") Integer prof_id,@PathParam("akt_id") Integer akt_id){
		List<AnmeldungenDTO> anmeldungen = new ArrayList<AnmeldungenDTO>();
		/*
		 * 
		 *  SELECT c.`matrikelnummer` , c.`vorname`, c.`name`  
			FROM  `angemeldetepruefung` a
			JOIN  `aktuellepruefung` b ON a.`akt_id` = b.`id` 
			JOIN  `student` c ON a.`student_id` = c.`matrikelnummer` 
			WHERE b.`prof_id` =2
			AND b.`id` =1
		 * 
		 * */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = 
				em.createQuery("SELECT c.matrikelnummer, c.vorname ,c.name "
						+ " FROM Angemeldetepruefung a"
						+ " JOIN Aktuellepruefung b ON a.aktuellePruefung.id = b.id"
						+ " JOIN Student c ON a.teilnehmer.matrikelnummer = c.matrikelnummer"
						+ " WHERE b.prof.id= "+ prof_id 
						+ " AND b.id = " + akt_id
						+ " AND a.status = 1").getResultList();
		
		for(Object[] o : result) {
			anmeldungen.add( new AnmeldungenDTO( (String)o[2], (String)o[1], (Number)o[0] ));
		}
		
		return anmeldungen;
	}
	
}
