package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOCadastroGeral;
import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoCadastroGeral implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCadastroGeral dao;
	
	@Transacao
	public void salvar(CadastroGeral cadastrogeral){
		try {
			if(cadastrogeral.getIdcadastrogeral() == null){
				dao.salvar(cadastrogeral);
			}else{
				dao.alterar(cadastrogeral);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<CadastroGeral> consultar(){
		return dao.consultar();
	}
	
	
	//painel de resumo
	
	public List<CadastroGeral> clientesnovos(Date data, Date data2){
		return dao.clientesnovos(data,data2);
	}	
	
}
