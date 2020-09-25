package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOFechamento;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoFechamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOFechamento dao;
	
	@Transacao
	public void salvar(Fechamento fechamento){
		try {
			if(fechamento.getIdfechamento() == null){
				dao.salvar(fechamento);
			}else{
				dao.alterar(fechamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Fechamento> consultar(){
		return dao.consultar();
	}
	
	
}
