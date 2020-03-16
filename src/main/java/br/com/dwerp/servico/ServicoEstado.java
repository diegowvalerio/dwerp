package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOEstado;
import br.com.dwerp.entidade.Estado;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoEstado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOEstado dao;
	
	@Transacao
	public void salvar(Estado estado){
		try {
			if(estado.getIdestado() == null){
				dao.salvar(estado);
			}else{
				dao.alterar(estado);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Estado> consultar(){
		return dao.consultar();
	}
}
