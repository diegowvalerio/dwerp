package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOEstrutura;
import br.com.dwerp.entidade.Estrutura;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoEstrutura implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOEstrutura dao;
	
	@Transacao
	public void salvar(Estrutura estrutura){
		try {
			if(estrutura.getIdestrutura() == null){
				dao.salvar(estrutura);
			}else{
				dao.alterar(estrutura);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Estrutura> consultar(){
		return dao.consultar();
	}


}
