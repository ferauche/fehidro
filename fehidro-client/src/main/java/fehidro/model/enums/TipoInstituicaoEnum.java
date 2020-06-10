package fehidro.model.enums;

public enum TipoInstituicaoEnum {
	TipoInstituicao1("Munic�pios e entidades municipais"),
	TipoInstituicao2("�rg�os e entidades estaduais"),
	TipoInstituicao3("Entidades da sociedade civil sem finalidades lucrativas"),
	TipoInstituicao4("Usu�rios de recursos h�dricos com finalidades lucrativas");

	TipoInstituicaoEnum(String s) {
		tipo = s;
	}

	private final String tipo;

	public boolean equalsName(String tiponame) {
		return tiponame.equals(tipo);
	}

	public String toString() {
		return this.tipo;
	}

	public String getString() {
		return this.tipo;
	}
}