package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCest;
import br.com.dwerp.entidade.Cest;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoCest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCest dao;
	
	@Transacao
	public void salvar(Cest cest){
		try {
			if(cest.getIdcest() == null){
				dao.salvar(cest);
			}else{
				dao.alterar(cest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Cest> consultar(){
		return dao.consultar();
	}
	
	
}
