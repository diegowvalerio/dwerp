package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOFormaPag;
import br.com.dwerp.entidade.FormaPag;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoFormaPag implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOFormaPag dao;
	
	@Transacao
	public void salvar(FormaPag formpag){
		try {
			if(formpag.getIdformapag() == null){
				dao.salvar(formpag);
			}else{
				dao.alterar(formpag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<FormaPag> consultar(){
		return dao.consultar();
	}


}
