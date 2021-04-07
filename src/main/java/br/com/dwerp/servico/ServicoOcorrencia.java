package br.com.dwerp.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dwerp.dao.DAOOcorrencia;
import br.com.dwerp.entidade.Ocorrencia;
import br.com.dwerp.hibernate.Transacao;

@Dependent
public class ServicoOcorrencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOOcorrencia dao;
	
	@Transacao
	public void salvar(Ocorrencia ocorrencia){
		try {
			if(ocorrencia.getIdocorrencia() == null){
				dao.salvar(ocorrencia);
			}else{
				dao.alterar(ocorrencia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Ocorrencia> consultar(){
		return dao.consultar();
	}
	
	public List<Ocorrencia> consultar_ocorrencia_usuario(String usuarioid){
		return dao.consultar_ocorrencia_usuario(usuarioid);
	}
	
}
