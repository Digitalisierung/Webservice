package rest.api;

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
import controller.exception.SaveFailedException;
import model.dto.AdvertisementDTO;
import model.dto.IdDTO;
import model.dto.SelectedThesisThemeDTO;

@Path("advertisement")
public class ThesisChooseProcess {
	EntityAccessBroker broker = new EntityAccessBroker();

	/*
	 * Prozess::Aushang erstellen
	 * Beschreibung: 
	 * 			Speichert neu erstellte Aushänge
	 */
	@POST
	@Path("store")
	@Produces( MediaType.APPLICATION_JSON )
	public AdvertisementDTO storeAdvertisement(AdvertisementDTO announce) {
		return broker.storeAdvertisement(announce);
		
	}
	
	/*
	 * Prozess::Bachelorarbeit Thema wählen (Liste anzeigen)
	 * Beschreibung:
	 * 			Gibt eine Liste mit allen aktivierten Aushängen zurück. (ohne jegliche selektierung)
	 */
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdvertisementDTO> getAllActiveAdvertisements(){
		return broker.findAllActiveAdvertisements();
	}

	
	/*
	 * Prozess::Aushang erstellen
	 * Beschreibung:
	 * 			Gibt eine Liste mit allen Aushängen zurück die vom Professor mit email stammen
	 */
	@GET
	@Path("list/{email}")
	@Produces( MediaType.APPLICATION_JSON )
	public List<AdvertisementDTO> getAdvertisementsOfProf(@PathParam("email") String email) {	
		return broker.findAllActiveAdvertisementsOf(email);
		
	}
	
	
	/*
	 * Prozess::Aushang erstellen
	 * Beschreibung:
	 * 			Ändert bestehende Datensätze in der Tabelle 'Aushang'
	 */
	@PUT
	@Path("update")
	@Produces( MediaType.APPLICATION_JSON )
	public AdvertisementDTO updateAdvertisement(AdvertisementDTO announce) {
		try{
			return broker.update(announce);
		}catch(Exception e) {
			return announce;
		}
		
	}
	
	
	/*
	 * Prozess::Bachelorarbeit Thema wählen (Auswahl vermerken)
	 * Beschreibung:
	 * 			Erstelt einen neuen Datensatz in der Tabelle 'Vorgemerkte_aushänge'
	 */
	@POST
	@Path("select")
	@Produces(MediaType.APPLICATION_JSON)
	public SelectedThesisThemeDTO storeSelectedExam(SelectedThesisThemeDTO selExams){
		SelectedThesisThemeDTO sttDTO = null;
			try{
				sttDTO = broker.saveSelectedThesisTheme(selExams);
			}catch (SaveFailedException e) {
				sttDTO = new SelectedThesisThemeDTO();
			}
		
		return sttDTO;
		
	}
	
	
	/*
	 * Prozess::Bacheloarbeit Thema wählen (Auswal bestätigen)
	 * Beschreibung:
	 * 			Vorgemerkter Aushang wird upgedated und betreuer_einverstanden auf 1 gesetzt
	 * 
	 */
	@PUT
	@Path("agree")
	@Produces(MediaType.APPLICATION_JSON)
	public SelectedThesisThemeDTO agreeSelectedThesis(SelectedThesisThemeDTO selExam) {
		return broker.update(selExam);
		
	}
	
	/*
	 * Prozess::Bacheloarbeit Thema wählen (Thesis aus der Liste nehmen)
	 * Beschreibung:
	 * 			In der Tabelle Aushang Attribut 'aktiv' wird auf 0 gesetzt ==> somit erscheint Entry nicht mehr in der Liste
	 * 
	 */
	@PUT
	@Path("deactivate")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deaktivateAdvertisement(IdDTO id) {
		Integer count = 0;
		try {
			count = broker.method(id);
		}catch(SaveFailedException e) {
			
		}
		
		return count;
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteVorgemerkteAushaenge(@PathParam("id") Integer id) {
		Integer def = new Integer(0);
		if(broker.deleteVorgemerkteAushaengeById(id)) {
			return id;
		}else {
			return def;
		}
		
	}

}
