package br.com.dwerp.entidade;

public enum TipoCfop {
	
	ENTRADA("ENTRADA"),
	SAIDA("SAIDA");
	
	private String descricao;
	
	private TipoCfop(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	};
}
