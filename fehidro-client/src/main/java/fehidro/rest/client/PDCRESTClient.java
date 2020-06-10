package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.PDC;

public class PDCRESTClient implements RESTClientInterface<PDC>{

	@Override
	public List<PDC> findAll() {
		List<PDC> pdcs = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PDC_URL).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<PDC>> () {});
		
		return pdcs;
	}

	@Override
	public PDC find(Long id) {
		PDC pdc = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PDC_URL + id).
				request(MediaType.APPLICATION_JSON).get()
				.readEntity(PDC.class);
		
		return pdc;
	}

	@Override
	public PDC create(PDC obj) {
		PDC pdc = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PDC_URL).
				queryParam("pdc", obj).
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(PDC.class);	
		
		return pdc;
	}

	@Override
	public PDC edit(PDC obj) {
		PDC pdc = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PDC_URL).
				queryParam("pdc", obj).
				request(MediaType.APPLICATION_JSON).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(PDC.class);	
		
		return pdc;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PDC_URL + id).
				request(MediaType.APPLICATION_JSON).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}
}
