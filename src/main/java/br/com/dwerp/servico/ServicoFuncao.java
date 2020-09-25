package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOFuncao;
import br.com.dwerp.entidade.Funcao;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoFuncao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOFuncao dao;
	
	@Transacao
	public void salvar(Funcao funcao){
		try {
			if(funcao.getIdfuncao() == null){
				dao.salvar(funcao);
			}else{
				dao.alterar(funcao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Funcao> consultar(){
		return dao.consultar();
	}
	
	
}
