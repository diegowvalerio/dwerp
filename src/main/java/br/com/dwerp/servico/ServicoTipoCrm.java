package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOTipoCrm;
import br.com.dwerp.entidade.TipoCrm;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoTipoCrm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOTipoCrm dao;
	
	@Transacao
	public void salvar(TipoCrm tipocrm){
		try {
			if(tipocrm.getIdtipocrm() == null){
				dao.salvar(tipocrm);
			}else{
				dao.alterar(tipocrm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<TipoCrm> consultar(){
		return dao.consultar();
	}
	
	public List<TipoCrm> consultarativos(){
		return dao.consultar_ativos();
	}
	
	
}
