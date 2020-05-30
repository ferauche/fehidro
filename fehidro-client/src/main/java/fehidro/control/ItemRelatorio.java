package fehidro.control;

import java.util.List;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;

public class ItemRelatorio {
	
	private Proposta proposta;
	private int soma;
	private List<Avaliacao> avaliacoes;
	
	public ItemRelatorio() {
	}
	
	public void addAvaliacao(Avaliacao a)
	{
		avaliacoes.add(a);
	}
	
	public ItemRelatorio(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
		this.proposta = avaliacoes.get(0).getProposta();
		int s = 0;
		for(int i=0;i<avaliacoes.size();i++)
		{
			s += avaliacoes.get(i).getNota();
		}
		this.soma = s;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public int getSoma() {
		return soma;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
}