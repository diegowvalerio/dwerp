package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Funcao;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoFuncao;

@Named
@ViewScoped
public class BeanFuncao implements Serializable{
	private static final long serialVersionUID = 1L;

	private Funcao funcao = new Funcao();
	@Inject
	private ServicoFuncao servico;
	
	private List<Funcao> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.funcao = this.getFuncao();
	}
	
	public String salvar(){
		servico.salvar(funcao);
		lista = servico.consultar();
		
		return "lista-funcao";
	}
	

	public String excluir() {
		try {
			servico.excluir(funcao.getIdfuncao());
			lista = servico.consultar();
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}

		return "lista-funcao";
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public List<Funcao> getLista() {
		return lista;
	}

	public void setLista(List<Funcao> lista) {
		this.lista = lista;
	}

}
