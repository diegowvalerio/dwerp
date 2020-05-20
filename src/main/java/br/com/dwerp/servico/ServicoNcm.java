package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAONcm;
import br.com.dwerp.entidade.Ncm;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoNcm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAONcm dao;
	
	@Transacao
	public void salvar(Ncm ncm){
		try {
			if(ncm.getIdncm() == null){
				dao.salvar(ncm);
			}else{
				dao.alterar(ncm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Ncm> consultar(){
		return dao.consultar();
	}
	
	
}
