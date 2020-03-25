package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOGrupo;
import br.com.dwerp.entidade.Grupo;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoGrupo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOGrupo dao;
	
	@Transacao
	public void salvar(Grupo grupo){
		try {
			if(grupo.getIdgrupo() == null){
				dao.salvar(grupo);
			}else{
				dao.alterar(grupo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Grupo> consultar(){
		return dao.consultar();
	}


}
