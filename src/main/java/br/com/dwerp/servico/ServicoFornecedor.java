package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOFornecedor;
import br.com.dwerp.entidade.Fornecedor;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoFornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOFornecedor dao;
	
	@Transacao
	public void salvar(Fornecedor pessoa){
		try {
			if(pessoa.getIdfornecedor() == null){
				dao.salvar(pessoa);
			}else{
				dao.alterar(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Fornecedor> consultar(){
		return dao.consultar();
	}
	
	
}
