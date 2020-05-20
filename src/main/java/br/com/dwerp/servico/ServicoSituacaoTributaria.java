package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOSituacaoTributaria;
import br.com.dwerp.entidade.SituacaoTributaria;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoSituacaoTributaria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSituacaoTributaria dao;
	
	@Transacao
	public void salvar(SituacaoTributaria situacaotributaria){
		try {
			if(situacaotributaria.getIdsituacaotributaria() == null){
				dao.salvar(situacaotributaria);
			}else{
				dao.alterar(situacaotributaria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<SituacaoTributaria> consultar(){
		return dao.consultar();
	}
	
	
}
