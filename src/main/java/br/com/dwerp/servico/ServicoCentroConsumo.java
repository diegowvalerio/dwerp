package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCentroConsumo;
import br.com.dwerp.entidade.CentroConsumo;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoCentroConsumo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCentroConsumo dao;
	
	@Transacao
	public void salvar(CentroConsumo centroConsumo){
		try {
			if(centroConsumo.getIdcentroconsumo() == null){
				dao.salvar(centroConsumo);
			}else{
				dao.alterar(centroConsumo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<CentroConsumo> consultar(){
		return dao.consultar();
	}


}
