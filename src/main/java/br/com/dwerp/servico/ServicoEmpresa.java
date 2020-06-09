package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOEmpresa;
import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.hibernate.Transacao;


@Dependent
public class ServicoEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOEmpresa dao;
	
	@Transacao
	public void salvar(Empresa empresa){
		try {
			if(empresa.getIdempresa() == null){
				dao.salvar(empresa);
			}else{
				dao.alterar(empresa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Empresa> consultar(){
		return dao.consultar();
	}
	
	
}
