package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCanal;
import br.com.dwerp.entidade.Canal;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoCanal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCanal dao;
	
	@Transacao
	public void salvar(Canal canal){
		try {
			if(canal.getIdcanal() == null){
				dao.salvar(canal);
			}else{
				dao.alterar(canal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Canal> consultar(){
		return dao.consultar();
	}
	
	public List<Canal> consultarativos(){
		return dao.consultar_ativos();
	}
	
}
