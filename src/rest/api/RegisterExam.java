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
import model.dto.PruefungInfoDTO;
import model.dto.StudentDTO;

@Path("exams")
public class RegisterExam {
	
	private EntityManager em;
	
/**
 * Durch die GET-Methode werden bei der DB alle aktuellen Klausuren
 * mit einer SELECT-Query abgefragt
 * @param -
 * @return Die Liste aller aktuellen Pr�fungen
 */
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PruefungDTO> get(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		
		List<PruefungDTO> ls = new ArrayList<PruefungDTO>();
		System.out.println("Vor dem Select");
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createQuery("SELECT b.id, p.name, b.aufsicht, b.datum, b.raum "
				+ "FROM Pruefungen p "
				+ "JOIN Aktuellepruefung b ON p.id=b.pruefung.id").getResultList();
		
		//System.out.println("Nach dem Select");

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
	
	/**
	 * Es wird ein Eintrag in der Tabelle "angemeldetepruefung" gesetzt 
	 * in dem die studenten_id und die akt_id genutzt werden und der 
	 * status der Anmeldung Defaultm��ig auf 1 gesetzt wird
	 * @param an - eine DTO in der student_id und akt_id �bergeben werden
	 * @return 1 um zu zeigen, dass es erfolgreich funktioniert hat
	 */
	
	
	//Hallo Juri 24.02.18 21:15
	
	@POST
	@Path("anmelden")
	@Produces(MediaType.APPLICATION_JSON)
	public int KlausurAnmelden(AnmeldungDTO an) {
		System.out.println(an);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		em.getTransaction().begin();
		
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
	
	
	/**
	 * Ein Eintrag der abh�ngig von der student_id und der akt_id 
	 * den status des Datensatzes auf 0 setzt
	 * @param ab - eine DTO in der student_id und akt_id �bergeben werden
	 * @return das geschickte Object wird wider returned - mit mos worten "falls n�tig"
	 */
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
	
	
	/**
	 * Die Anmeldeliste wird mit der Testat Liste verglichen
	 * alle Pr�fungen f�r die der Student kein Testat besitzt werden auf -1 gesetzt
	 * @param id - Die student_id der DB
	 * @return eine Liste der Angemeldeten Pr�fungen 
	 * f�r die der Student kein Testat besitzt
	 */
	@GET
	@Path("checktestate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Number> checkTestat(@PathParam("id") Integer id) {
		
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		
		List<Number> nichtZugelassenePruefungen = new ArrayList<Number>();
		
		@SuppressWarnings("unchecked")
		List<Object> result = em.createQuery(""
				+ "SELECT d.id " + 
				" FROM Angemeldetepruefung d " + 
				" JOIN Aktuellepruefung c ON d.aktuellePruefung.id = c.id " + 
				" WHERE  NOT EXISTS ( "
				+ " SELECT t.exam.id "
				+ " FROM Testat t "
				+ " WHERE t.exam.id = c.pruefung.id )"
				+ " AND d.teilnehmer.matrikelnummer = " + id).getResultList();

		System.out.println("Nach dem Select");
		em.getTransaction().begin();
		for(Object o : result) {
			nichtZugelassenePruefungen.add((Number) o);
			//Updaten der Anmeldungen : Automatisches abmelden der Klausuren

			
			Query query = em.createQuery("UPDATE Angemeldetepruefung a SET a.status = -1 "
					+ "WHERE a.id= " + (Number) o);		
			query.executeUpdate();
			
			
		    
		}
		em.getTransaction().commit();
		em.close();

		return nichtZugelassenePruefungen;
	}
	
	
	/**
	 * Anhand der IDs kann der Professor gezielt die Anmeldeliste der 
	 * jeweiligen Klausur erhalten
	 * @param prof_id Die ProfessorID f�r den Namen des Professors
	 * @param akt_id Die aktuelle Pr�fungsID f�r die Liste
	 * @return Liste der Teilnehmer als DTO
	 */
	@GET
	@Path("anmeldeliste/{prof_id}/{akt_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnmeldungenDTO> showAnmeldungen(@PathParam("prof_id") Integer prof_id,@PathParam("akt_id") Integer akt_id){
		List<AnmeldungenDTO> anmeldungen = new ArrayList<AnmeldungenDTO>();
	
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
	
	
	/**
	 * genutzt f�r eine Dynamisch aufgebaute Email die nach 
	 * jeder Anmeldung verschickt wird
	 * @param martikelnr des Studenten f�r die Infos der Email
	 * @param akt_id der AktuellenPr�fung f�r den Namen der Pr�fung
	 * @return ein DTO mit dem man (Vor-)Namen, Pr�fungsnamen und Email bekommt
	 */
	@GET
	@Path("email/{martikelnr}/{akt_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDTO getStudent(@PathParam("martikelnr") Integer martikelnr,@PathParam("akt_id") Integer akt_id)
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Object[]> results = em.createQuery(
				 "SELECT s.name, s.vorname, p.name , s.email "
				 + " FROM Angemeldetepruefung a"
				 + " JOIN Student s ON a.teilnehmer.matrikelnummer = s.matrikelnummer"
				 + " JOIN Aktuellepruefung ak ON a.aktuellePruefung.id = ak.id"
				 + " JOIN Pruefungen p ON ak.pruefung.id = p.id"
				 + " WHERE s.matrikelnummer = " + martikelnr
				 + " AND ak.id = " + akt_id
			).getResultList();

		System.out.println("Nach dem Select");
		
		StudentDTO std = null;
		
		for(Object[] o : results) {
			std= new StudentDTO((String) o[0],(String) o[1],(String) o[2],(String) o[3]);
		}
		
		return std;
	}
	
	
	/**
	 * Endg�ltige Bes�tigung aller Zugelassenen und angemeldeten Pr�fungen
	 * @param martikelnr der Studenten
	 * @return eine Liste mit (Vor-)Namen, Pr�fungsnamen und Email
	 */
	@GET
	@Path("angemeldete/{martikelnr}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentDTO> getInfo(@PathParam("martikelnr") Integer martikelnr)
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
		this.em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Object[]> results = em.createQuery(
				 "SELECT s.name, s.vorname, p.name , s.email "
				 + " FROM Angemeldetepruefung a"
				 + " JOIN Student s ON a.teilnehmer.matrikelnummer = s.matrikelnummer"
				 + " JOIN Aktuellepruefung ak ON a.aktuellePruefung.id = ak.id"
				 + " JOIN Pruefungen p ON ak.pruefung.id = p.id"
				 + " WHERE s.matrikelnummer = " + martikelnr
				 + " AND a.status = 1"
			).getResultList();
		
		List<StudentDTO> std = new ArrayList<StudentDTO>();
		
		for(Object[] o : results) {
			std.add(new StudentDTO((String) o[0],(String) o[1],(String) o[2],(String) o[3]));
		}
		
		return std;
	}
	
	    @GET
		@Path("meineAnmeldungen/{martikelnr}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<PruefungInfoDTO> getAnmeldungen(@PathParam("martikelnr") Integer martikelnr)
		{
	
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fh-aachen.services");
			this.em = emf.createEntityManager();
			@SuppressWarnings("unchecked")
			List<Object[]> results = em.createQuery(
					 "SELECT p.name, p.semester , ak.datum , ak.prof.name ,a.status"
					 + " FROM Angemeldetepruefung a"
					 + " JOIN Student s ON a.teilnehmer.matrikelnummer = s.matrikelnummer"
					 + " JOIN Aktuellepruefung ak ON a.aktuellePruefung.id = ak.id"
					 + " JOIN Pruefungen p ON ak.pruefung.id = p.id"
					 + " WHERE s.matrikelnummer = " + martikelnr
				).getResultList();
			
			List<PruefungInfoDTO> std = new ArrayList<PruefungInfoDTO>();
			
			for(Object[] o : results) {
				std.add(new PruefungInfoDTO((String) o[0],(Number) o[1], o[2].toString(),(String) o[3],(Number) o[4]));
			}
			
			return std;
		}
	
}
