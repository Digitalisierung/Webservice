package controller.rest.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.broker.EntityAccessBroker;
import model.dto.AdvertisementDTO;
import model.dto.IdDTO;
import model.dto.SelectedThesisThemeDTO;
import model.exceptions.SaveFailedException;


/**
 * 
 * @author Juri Rempel
 *
 */
@Path(value="advertisement")
public class ThesisChooseProcess {
	EntityAccessBroker broker = new EntityAccessBroker();

	/**
	 * Camunda Prozess::Aushang erstellen. 
	 * Beschreibung: Speichert neu erstellte Aushänge
	 * 
	 * @param announce beinhaltet Titel, Beschreibung, Scwierigkeitsgrad des Aushangs und Email Adresse des Professors. 
	 * @return dieselbe Daten wie im announce + id Nummer unter welcher Aushang in der Datenbank gespeichert wurde.
	 */
	@POST
	@Path(value="store")
	@Produces( value= {MediaType.APPLICATION_JSON} )
	public AdvertisementDTO storeAdvertisement(AdvertisementDTO announce) {
		return broker.storeAdvertisement(announce);
		
	}
	

	/**
	 * Camunda Prozess::Bachelorarbeit Thema wählen (Liste anzeigen)
	 * Beschreibung: Gibt eine Liste mit allen aktivierten Aushängen zurück. (ohne jegliche selektierung)
	 * 
	 * @return Alle Aushaenge die aktuell sind
	 */
	@GET
	@Path(value="list")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public List<AdvertisementDTO> getAllActiveAdvertisements(){
		return broker.findAllActiveAdvertisements();
	}

	
	/**
	 * Camunda Prozess::Aushang erstellen
	 * 
	 * @param email Email Adresse des Professors
	 * @return Alle Aushaenge die vom Professor mit {email} erstellt wurden
	 */
	@GET
	@Path(value="list/{email}")
	@Produces( value= {MediaType.APPLICATION_JSON} )
	public List<AdvertisementDTO> getAdvertisementsOfProf(@PathParam("email") String email) {	
		return broker.findAllActiveAdvertisementsByProfessorEmail(email);
		
	}
	
	
	/**
	 * Prozess::Aushang erstellen
	 * Beschreibung: Ändert bestehende Datensätze in der Tabelle 'Aushang'
	 * 
	 * @param announce
	 * @return announce
	 */
	@PUT
	@Path(value="update")
	@Produces( value={MediaType.APPLICATION_JSON} )
	public AdvertisementDTO updateAdvertisement(AdvertisementDTO announce) {
		try{
			return broker.update(announce);
		}catch(Exception e) {
			return announce;
		}
		
	}
	
	
	/**
	 * Prozess::Bachelorarbeit Thema wählen (Auswahl vermerken).
	 * Beschreibung:
	 * 			Erstelt einen neuen Datensatz in der Tabelle 'Vorgemerkte_aushänge'
	 * 
	 * @param selExams
	 * @return
	 */
	@POST
	@Path(value="select")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public SelectedThesisThemeDTO storeSelectedExam(SelectedThesisThemeDTO selExams){
		SelectedThesisThemeDTO sttDTO = null;
			try{
				sttDTO = broker.saveSelectedThesisTheme(selExams);
			}catch (SaveFailedException e) {
				sttDTO = new SelectedThesisThemeDTO();
			}
		
		return sttDTO;
		
	}
	
	
	/**
	 * Prozess::Bacheloarbeit Thema wählen (Auswal bestätigen).
	 * Beschreibung:
	 * 			Vorgemerkter Aushang wird upgedated und betreuer_einverstanden auf 1 gesetzt
	 * 
	 * @param selExam
	 * @return
	 */
	@PUT
	@Path(value="agree")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public SelectedThesisThemeDTO agreeSelectedThesis(SelectedThesisThemeDTO selExam) {
		return broker.update(selExam);
		
	}
	
	/**
	 * Prozess::Bacheloarbeit Thema wählen (Thesis aus der Liste nehmen). 
	 * Beschreibung: In der Tabelle Aushang Attribut 'aktiv' wird auf 0 gesetzt ==> somit erscheint Entry nicht mehr in der Liste
	 * 
	 * @param id
	 * @return
	 */
	@PUT
	@Path(value="deactivate")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public Integer deaktivateAdvertisement(IdDTO id) {
		Integer count = 0;
		try {
			count = broker.deactivateAdvertisementByID(id);
		}catch(SaveFailedException e) {
			
		}
		
		return count;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path(value="delete/{id}")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public Integer deleteVorgemerkteAushaenge(@PathParam("id") Integer id) {
		Integer def = new Integer(0);
		if(broker.deleteVorgemerkteAushaengeById(id)) {
			return id;
		}else {
			return def;
		}
		
	}

}
