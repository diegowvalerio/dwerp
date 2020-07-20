package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOUnidade;
import br.com.dwerp.entidade.Unidade;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoUnidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOUnidade dao;
	
	@Transacao
	public void salvar(Unidade unidade){
		try {
			if(unidade.getIdunidade() == null){
				dao.salvar(unidade);
			}else{
				dao.alterar(unidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Unidade> consultar(){
		return dao.consultar();
	}
	
	
}
