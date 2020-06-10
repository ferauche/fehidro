package br.unisantos.fehidro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Table(name = "tb_pdc")
@Entity
@NamedQueries({
	@NamedQuery(name = "PDC.listarTodos", query = "select p from PDC p order by p.titulo"),
	@NamedQuery(name = "PDC.consultarPorId", query = "select p from PDC p where p.id=?1")
})
public class PDC extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_titulo")
	private String titulo;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.EAGER)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<SubPDC> subPDCs = new ArrayList<SubPDC>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<SubPDC> getSubPDCs() {
		return subPDCs;
	}

	public void setSubPDCs(List<SubPDC> subPDCs) {
		this.subPDCs = subPDCs;
	}
	
	public void addSubPDCs(SubPDC subPDC) {
		if (this.subPDCs.contains(subPDC))
			return;
		
		this.subPDCs.add(subPDC);
		subPDC.setPdc(this);
	}
	
	public void removeSubPDCs(SubPDC subPDC) {
		if (!this.subPDCs.contains(subPDC))
			return;
		this.subPDCs.remove(subPDC);
		subPDC.setPdc(null);
	}
}
