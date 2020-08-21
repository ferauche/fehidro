package br.unisantos.fehidro.model.dao;

import java.util.List;

import br.unisantos.fehidro.model.CriterioAvaliacao;
import br.unisantos.fehidro.model.SubcriterioAvaliacao;

public class CriterioAvaliacaoDAO extends DAOFactory<CriterioAvaliacao> {
	
	public CriterioAvaliacaoDAO() {
		super(CriterioAvaliacao.class);
	}
	
	public void cadastrar(CriterioAvaliacao criterioAvaliacao) {
		adicionar(criterioAvaliacao);
	}
	
	public void atualizar(CriterioAvaliacao criterioAvaliacao) {
		alterar(criterioAvaliacao);
	}
	
	public void remover(long id) {
		excluir(id);
	}

	public CriterioAvaliacao obter(long id) {
		PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
		SubcriterioAvaliacaoDAO subcriterioDAO = new SubcriterioAvaliacaoDAO();
		TipoPropostaDAO tipoPropostaDAO = new TipoPropostaDAO();
		
		CriterioAvaliacao c = consultarGenerico("CriterioAvaliacao.consultarPorId", id); 

		if (c != null) {
			c.setPontuacoes(pontuacaoDAO.obterPorCriterio(id));
			c.setSubcriterios(subcriterioDAO.obterPorCriterio(id));

			if (c.getSubcriterios() != null) {
				for(SubcriterioAvaliacao s : c.getSubcriterios()) {
					s.setTiposProposta(tipoPropostaDAO.obterPorSubcriterio(s.getId()));
					s.setPontuacoes(pontuacaoDAO.obterPorSubcriterio(s.getId()));
				}
			}
		}

		return c;
	}
	
	public List<CriterioAvaliacao> listar() {
		return listarGenerico("CriterioAvaliacao.listarTodos");
	}
}