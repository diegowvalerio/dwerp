package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOSetor;
import br.com.dwerp.entidade.Setor;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoSetor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSetor dao;
	
	@Transacao
	public void salvar(Setor setor){
		try {
			if(setor.getIdsetor() == null){
				dao.salvar(setor);
			}else{
				dao.alterar(setor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Setor> consultar(){
		return dao.consultar();
	}
	
	public List<Setor> consultar_ativos(){
		return dao.consultar_ativos();
	}
	
	
}
