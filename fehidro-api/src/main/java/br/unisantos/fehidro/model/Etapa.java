package br.unisantos.fehidro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "tb_etapa")
@Entity
public class Etapa extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="nr_etapa")
	private int numero;
	
	@Column(name="ds_etapa")
	private String descricao;
	
	@Column(name="id_responsavel")
	private Integer responsavel;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "etapa_id")
	private List<Cronograma> cronogramas = new ArrayList<Cronograma>();

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Cronograma> getCronogramas() {
		return cronogramas;
	}

	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}
	
	public Integer getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Integer responsavel) {
		this.responsavel = responsavel;
	}
	
}
