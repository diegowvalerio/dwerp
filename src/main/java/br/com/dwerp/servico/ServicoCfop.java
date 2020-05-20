package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCfop;
import br.com.dwerp.entidade.Cfop;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoCfop implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCfop dao;
	
	@Transacao
	public void salvar(Cfop cfop){
		try {
			if(cfop.getIdcfop() == null){
				dao.salvar(cfop);
			}else{
				dao.alterar(cfop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Cfop> consultar(){
		return dao.consultar();
	}
	
	
}
