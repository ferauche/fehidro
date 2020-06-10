package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fehidro.model.Avaliacao;
import fehidro.model.CriterioAvaliacao;
import fehidro.model.Pontuacao;
import fehidro.model.Proposta;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.rest.client.AvaliacaoRESTClient;
import fehidro.rest.client.CriterioAvaliacaoRESTClient;
import fehidro.rest.client.PontuacaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
import fehidro.rest.client.SubcriterioAvaliacaoRESTClient;

@ManagedBean
@SessionScoped
public class AvaliacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	//Proposta
	private PropostaRESTClient restProposta;
	private List<SelectItem> propostas;
	//Subcriterio
	private SubcriterioAvaliacaoRESTClient restSubcriterio;
	private List<SelectItem> subcriterios;
	//Criterio
	private CriterioAvaliacaoRESTClient restCriterio;
	private List<SelectItem> criterios;
	//Pontuacao
	private PontuacaoRESTClient restPontuacao;
	private List<SelectItem> pontuacoes;
	
	//Avaliacao
	private AvaliacaoRESTClient restAvaliacao;
	private Avaliacao avaliacao;
	private List<Avaliacao> avaliacoes;
	
	private String consulta;
	
	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	public AvaliacaoBean() {
		startView(true);
	}
	
	public String index() {
		startView(true);
		return "/avaliacao/index?faces-redirect=true"; 
	}
	
	private void startView(boolean setInfo) {
		this.restAvaliacao = new AvaliacaoRESTClient();
//		this.idAvaliacao = null;
		this.avaliacao = new Avaliacao();
		this.avaliacao.setProposta(new Proposta());
		this.avaliacao.setCriterio(new CriterioAvaliacao());
		this.avaliacao.setSubcriterio(new SubcriterioAvaliacao());
		this.avaliacao.setNota(new Pontuacao());
			
		if (setInfo)
			setInfo();
	}
	
	private void setInfo() {
		this.setAvaliacoes(this.restAvaliacao.findAll());
		this.setPropostas();
		this.setCriterios();
		this.setSubcriterios();
		this.setPontuacoes();
	}
	
	public String cadastro() {
		startView(true);
		return "/avaliacao/cadastro?faces-redirect=true";
	}

	public String editar() 
	{
		if (avaliacao.getId() != null) {

			Avaliacao a = this.restAvaliacao.find(avaliacao.getId());
			setAvaliacao(a);
		}
		setInfo();

		return "/avaliacao/cadastro?faces-redirect=true";
	}
	
	public String salvar() {
		
		//TODO: Verificar
		avaliacao.setProposta( restProposta.find(avaliacao.getProposta().getId()) );
		avaliacao.setCriterio ( restCriterio.find(avaliacao.getCriterio().getId()) );
		avaliacao.setSubcriterio( restSubcriterio.find(avaliacao.getSubcriterio().getId()) );
		avaliacao.setNota( restPontuacao.find(avaliacao.getNota().getId()) );
		
		if ( this.avaliacao.getId() == null) {
			this.restAvaliacao.create(this.avaliacao);
		}
		else {
			this.restAvaliacao.edit(this.avaliacao);
		}
		startView(true);

		return null;
	}

	//Propostas
	public List<SelectItem> getPropostas() {
		return propostas;
	}
	public void setPropostas() {
		this.restProposta = new PropostaRESTClient();
		List<Proposta> propostasBase = this.restProposta.findAll();
		List<SelectItem> propostas = new ArrayList<>();

		for (Proposta i : propostasBase) 
		{
			propostas.add(new SelectItem(i.getId(), i.getNomeProjeto()));
		}
		
		this.propostas = propostas;
	}
	//Criterios
	public List<SelectItem> getCriterios() {
		return criterios;
	}
	public void setCriterios() {
		this.restCriterio = new CriterioAvaliacaoRESTClient();
		List<CriterioAvaliacao> criteriosBase = this.restCriterio.findAll();
		List<SelectItem> criterios = new ArrayList<>();

		for (CriterioAvaliacao i : criteriosBase) 
		{
			criterios.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.criterios = criterios;
	}
	//Pontuacoes
	public List<SelectItem> getPontuacoes() {
		return pontuacoes;
	}
	
	public void setPontuacoes() {
		this.restPontuacao = new PontuacaoRESTClient();
		List<Pontuacao> pontuacaoBase = this.restPontuacao.findAll();
		List<SelectItem> pontuacoes = new ArrayList<>();

		for (Pontuacao i : pontuacaoBase) 
		{
			pontuacoes.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.pontuacoes = pontuacoes;
	}

	//Subcriterios
	public List<SelectItem> getSubcriterios() {
		return subcriterios;
	}
	public void setSubcriterios() {
		this.restSubcriterio = new SubcriterioAvaliacaoRESTClient();
		List<SubcriterioAvaliacao> subcriteriosBase = this.restSubcriterio.findAll();
		System.out.println("Size = "+subcriteriosBase.size());
		List<SelectItem> subcriterios = new ArrayList<>();

		for (SubcriterioAvaliacao i : subcriteriosBase) 
		{
			subcriterios.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.subcriterios = subcriterios;
	}

	//Avaliacao
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	//List Avaliacoes
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
