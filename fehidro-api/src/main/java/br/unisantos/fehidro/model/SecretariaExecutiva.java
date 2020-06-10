package br.unisantos.fehidro.model;

import javax.persistence.*;


@Entity
@Table(name = "tb_secretariaexecutiva")
public class SecretariaExecutiva extends Usuario {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ic_administrativo")
	private boolean administrador;

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
}