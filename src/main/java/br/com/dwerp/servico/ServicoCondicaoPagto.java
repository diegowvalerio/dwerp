package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCondicaoPagto;
import br.com.dwerp.entidade.CondPgto;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoCondicaoPagto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCondicaoPagto dao;
	
	@Transacao
	public void salvar(CondPgto condicaopagto){
		try {
			if(condicaopagto.getIdcondpgto() == null){
				dao.salvar(condicaopagto);
			}else{
				dao.alterar(condicaopagto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<CondPgto> consultar(){
		return dao.consultar();
	}

}
