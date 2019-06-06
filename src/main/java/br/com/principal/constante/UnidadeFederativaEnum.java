package br.com.principal.constante;

public enum UnidadeFederativaEnum {

	ACRE("Acre"), 
	ALAGOAS("Alagoas"), 
	AMAPA("Amapá"), 
	AMAZONAS("Amazonas"), 
	BAHIA("Bahia"), 
	CEARA("Ceará"), 
	DISTRITUTO_FEDERAL("Distrito Federal"), 
	ESPIRITO_SANTO("Espírito Santo"), 
	GOIAS("Goiás"), 
	MARANHAO("Maranhão"), 
	MATO_GROSSO("Mato Grosso"), 
	MATO_GROSSO_SUL("Mato Grosso do Sul"), 
	MINAS_GERAIS("Minas Gerais"), 
	PARA("Pará"), 
	PARAIBA("Paraíba"), 
	PARANA("Paraná"), 
	PERNAMBUCO("Pernambuco"), 
	PIAUI("Piauí"), 
	RIO_DE_JANEIRO("Rio de Janeiro"), 
	RIO_GRANDE_NORTE("Rio Grande do Norte"), 
	RIO_GRANDE_SUL("Rio Grande do Sul"), 
	RONDONIA("Rondônia"), 
	RORAIMA("Roraima"), 
	SANTA_CATARINA("Santa Catarina"), 
	SAO_PAULO("São Paulo"), 
	SERGIPE("Sergipe"), 
	TOCANTINS("Tocantins");

	private final String nome;

	private UnidadeFederativaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}