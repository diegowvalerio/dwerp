package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.CentroConsumo;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCentroConsumo;

@Named
@ViewScoped
public class BeanCentroConsumo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private CentroConsumo centroConsumo = new CentroConsumo();
	@Inject
	private ServicoCentroConsumo servico;
	private List<CentroConsumo> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.centroConsumo = this.getCentroConsumo();
	}
		
	public String salvar(){
		servico.salvar(centroConsumo);
		lista = servico.consultar();
		return "lista-centroconsumo";
	}
	
	public void excluir(){
		try{
		servico.excluir(centroConsumo.getIdcentroconsumo());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}


	public CentroConsumo getCentroConsumo() {
		return centroConsumo;
	}


	public void setCentroConsumo(CentroConsumo centroConsumo) {
		this.centroConsumo = centroConsumo;
	}


	public List<CentroConsumo> getLista() {
		return lista;
	}


	public void setLista(List<CentroConsumo> lista) {
		this.lista = lista;
	}

	

}
