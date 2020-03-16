package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOPessoa;
import br.com.dwerp.entidade.Pessoa;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoPessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOPessoa dao;
	
	@Transacao
	public void salvar(Pessoa pessoa){
		try {
			if(pessoa.getIdpessoa() == null){
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
	
	public List<Pessoa> consultar(){
		return dao.consultar();
	}
	
	
}
