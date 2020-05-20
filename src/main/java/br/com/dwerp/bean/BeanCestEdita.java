package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Cest;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCest;

@Named
@ViewScoped
public class BeanCestEdita implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cest cest = new Cest();
	@Inject
	private ServicoCest servico;
	private List<Cest> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public Cest getCest() {
		return cest;
	}

	public void setCest(Cest cest) {
		this.cest = cest;
	}

	public List<Cest> getLista() {
		return lista;
	}

	public void setLista(List<Cest> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(cest.getIdcest());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}

	public void salvar(){
		servico.salvar(cest);
	}
}
