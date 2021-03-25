package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Canal;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCanal;

@Named
@ViewScoped
public class BeanCanal implements Serializable{

	private static final long serialVersionUID = 1L;
	private Canal canal = new Canal();
	@Inject
	private ServicoCanal servico;
	private List<Canal> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public List<Canal> getLista() {
		return lista;
	}

	public void setLista(List<Canal> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(canal.getIdcanal());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}

	public String salvar(){
		try {
			servico.salvar(canal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-canal";
	}
}
