package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOModuloCrm;
import br.com.dwerp.entidade.ModuloCrm;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoModuloCrm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOModuloCrm dao;
	
	@Transacao
	public void salvar(ModuloCrm modulocrm){
		try {
			if(modulocrm.getIdmodulocrm() == null){
				dao.salvar(modulocrm);
			}else{
				dao.alterar(modulocrm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<ModuloCrm> consultar(){
		return dao.consultar();
	}
	
	public List<ModuloCrm> consultarativos(){
		return dao.consultar_ativos();
	}
	
	
}
