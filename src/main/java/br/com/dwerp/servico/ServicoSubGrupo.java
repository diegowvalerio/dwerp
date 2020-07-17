package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOSubGrupo;
import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoSubGrupo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSubGrupo dao;
	
	@Transacao
	public void salvar(SubGrupo subgrupo){
		try {
			if(subgrupo.getIdsubgrupo() == null){
				dao.salvar(subgrupo);
			}else{
				dao.alterar(subgrupo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<SubGrupo> consultar(){
		return dao.consultar();
	}
	
	public List<SubGrupo> consultar_ativos(){
		return dao.consultar_ativos();
	}


}
