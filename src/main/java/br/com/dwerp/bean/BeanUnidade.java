package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Unidade;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoUnidade;

@Named
@ViewScoped
public class BeanUnidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Unidade unidade = new Unidade();
	@Inject
	private ServicoUnidade servico;
	
	private List<Unidade> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.unidade = this.getUnidade();
	}
	
	public String salvar(){
		try{
		servico.salvar(unidade);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-unidade";
	}

	public String excluir() {
		try{
		servico.excluir(unidade.getIdunidade());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-unidade";
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getLista() {
		return lista;
	}

	public void setLista(List<Unidade> lista) {
		this.lista = lista;
	}
	
	
}
