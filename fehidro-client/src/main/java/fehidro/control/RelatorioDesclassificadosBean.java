package fehidro.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fehidro.model.Avaliacao;
import fehidro.model.Relatorio;
import fehidro.rest.client.AvaliacaoRESTClient;

@ManagedBean(name="relatorioDesclassificados")
@SessionScoped
public class RelatorioDesclassificadosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Relatorio relatorio;
	private AvaliacaoRESTClient rest;
	
	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioDesclassificadosBean() {
		relatorio = new Relatorio();
		rest  = new AvaliacaoRESTClient();
		
		List<Avaliacao> avaliacoes = rest.findAll();//FIXME: Substituir por metodo mais apropriado + considerar data
		
		this.relatorio.setItensRelatorio(avaliacoes);
	}

	public String getUrl() {
		return "/relatorio/relatorioDesclassificados";
	}
	
}
