package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOSituacaoTributariaPISCOFINS;
import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoSituacaoTributariaPISCOFINS implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSituacaoTributariaPISCOFINS dao;
	
	@Transacao
	public void salvar(SituacaoTributariaPISCOFINS situacaotributariapiscofins){
		try {
			if(situacaotributariapiscofins.getIdsituacaotributariapiscofins() == null){
				dao.salvar(situacaotributariapiscofins);
			}else{
				dao.alterar(situacaotributariapiscofins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<SituacaoTributariaPISCOFINS> consultar(){
		return dao.consultar();
	}
	
	
}
