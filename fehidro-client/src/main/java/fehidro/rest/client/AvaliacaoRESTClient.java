package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.Avaliacao;
import fehidro.model.CriterioAvaliacao;
import fehidro.model.PDC;
import fehidro.model.Proposta;
import fehidro.model.SubPDC;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;

//TODO: VERIFICAR
public class AvaliacaoRESTClient implements RESTClientInterface<Avaliacao>{

	@Override
	public List<Avaliacao> findAll() {
		List<Avaliacao> avaliacoes = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		return avaliacoes;
	}

	@Override
	public Avaliacao find(Long id) {
		Avaliacao avaliacao = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + id).
				request(MediaType.APPLICATION_JSON).get()
				.readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	public List<Avaliacao> listarPDC(PDC pdc) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarPDC/" +pdc).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarPDC(SubPDC subpdc) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarSubPDC/" +subpdc).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarAvaliador(Usuario avaliador) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarAvaliador/" +avaliador).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterio(CriterioAvaliacao criterio) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarCriterio/" +criterio).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterio(SubcriterioAvaliacao subcriterio) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarSubcriterio/" +subcriterio).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarProposta(Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarProposta/" +proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}

	public List<Avaliacao> listarAvaliadorProposta(Usuario avaliador, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarAvaliadorProposta/" +avaliador+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterioProposta(CriterioAvaliacao criterio, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarCriterioProposta/" +criterio+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarSubcriterioProposta(SubcriterioAvaliacao subcriterio, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "listarSubcriterioProposta/" +subcriterio+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	@Override
	public Avaliacao create(Avaliacao obj) {
		Avaliacao avaliacao = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL).
				queryParam("avaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	@Override
	public Avaliacao edit(Avaliacao obj) {
		Avaliacao avaliacao = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL).
				queryParam("avaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_USUARIO_URL + id).
				request(MediaType.APPLICATION_JSON).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}

}
