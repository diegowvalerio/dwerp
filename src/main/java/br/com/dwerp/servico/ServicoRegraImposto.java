package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAORegraImposto;
import br.com.dwerp.entidade.RegraImposto;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoRegraImposto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAORegraImposto dao;
	
	@Transacao
	public void salvar(RegraImposto regraimposto){
		try {
			if(regraimposto.getIdregraimposto() == null){
				dao.salvar(regraimposto);
			}else{
				dao.alterar(regraimposto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<RegraImposto> consultar(){
		return dao.consultar();
	}
	
	
}
