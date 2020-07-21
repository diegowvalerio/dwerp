package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Almoxarifado;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoAlmoxarifado;

@Named
@ViewScoped
public class BeanAlmoxarifadoEdita implements Serializable{

	private static final long serialVersionUID = 1L;
	private Almoxarifado almoxarifado = new Almoxarifado();
	@Inject
	private ServicoAlmoxarifado servico;
	private List<Almoxarifado> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
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

	public void excluir(){
		try{
		servico.excluir(almoxarifado.getIdalmoxarifado());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}

	public void salvar(){
		servico.salvar(almoxarifado);
	}
}
