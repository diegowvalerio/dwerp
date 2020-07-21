package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Almoxarifado;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoAlmoxarifado;

@Named
@RequestScoped
public class BeanAlmoxarifado implements Serializable{
	private static final long serialVersionUID = 1L;

	private Almoxarifado almoxarifado = new Almoxarifado();
	@Inject
	private ServicoAlmoxarifado servico;
	private List<Almoxarifado> lista;

	
	
	public String salvar(){
		try{
		servico.salvar(almoxarifado);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-almoxarifado";
	}

	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public List<Almoxarifado> getLista() {
		return lista;
	}

	public void setLista(List<Almoxarifado> lista) {
		this.lista = lista;
	}
		

}
