package br.com.dwerp.entidade;

public enum TipoOperacaoCfop {
	
	VENDA("VENDA"),
	COMPRA("COMPRA"),
	DEVOLUCAO_VENDA("DEVOLUCAO_VENDA"),
	DEVOLUCAO_COMPRA("DEVOLUCAO_COMPRA"),
	OUTRAS_SAIDAS("OUTRAS_SAIDAS"),
	SAIDA_DOACAO("SAIDA_DOACAO"),
	ENTRADA_DOACAO("ENTRADA_DOACAO"),
	REM_CONSIGNADO("REM_CONSIGNADO"),
	RET_CONSIGNADO("RET_CONSIGNADO");
	
	private String descricao;
	
	private TipoOperacaoCfop(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	};
}
