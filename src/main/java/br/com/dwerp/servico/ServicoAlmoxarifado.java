package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOAlmoxarifado;
import br.com.dwerp.entidade.Almoxarifado;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoAlmoxarifado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOAlmoxarifado dao;
	
	@Transacao
	public void salvar(Almoxarifado almoxarifado){
		try {
			if(almoxarifado.getIdalmoxarifado() == null){
				dao.salvar(almoxarifado);
			}else{
				dao.alterar(almoxarifado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Almoxarifado> consultar(){
		return dao.consultar();
	}
	
	public List<Almoxarifado> consultar_ativos(){
		return dao.consultar_ativos();
	}
	
	
}
