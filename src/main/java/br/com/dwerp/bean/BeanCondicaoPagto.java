package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.CondPgto;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCondicaoPagto;

@Named
@ViewScoped
public class BeanCondicaoPagto implements Serializable{
	private static final long serialVersionUID = 1L;

	private CondPgto condicaopagto = new CondPgto();
	@Inject
	private ServicoCondicaoPagto servico;
	
	private List<CondPgto> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.condicaopagto = this.getCondicaopagto();
	}
	
	public CondPgto getCondicaopagto() {
		return condicaopagto;
	}

	public void setCondicaopagto(CondPgto condicaopagto) {
		this.condicaopagto = condicaopagto;
	}

	public List<CondPgto> getLista() {
		return lista;
	}

	public void setLista(List<CondPgto> lista) {
		this.lista = lista;
	}

	public String salvar(){
		try{
		servico.salvar(condicaopagto);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro j� existente! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		
		return "lista-condicaopagto";
	}

	public String excluir() {
		try{
		servico.excluir(condicaopagto.getIdcondpgto());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-condicaopagto";
	}

}
