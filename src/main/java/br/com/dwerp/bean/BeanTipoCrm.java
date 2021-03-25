package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.TipoCrm;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoTipoCrm;

@Named
@ViewScoped
public class BeanTipoCrm implements Serializable{

	private static final long serialVersionUID = 1L;
	private TipoCrm tipocrm = new TipoCrm();
	@Inject
	private ServicoTipoCrm servico;
	private List<TipoCrm> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public TipoCrm getTipoCrm() {
		return tipocrm;
	}

	public void setTipoCrm(TipoCrm tipocrm) {
		this.tipocrm = tipocrm;
	}

	public List<TipoCrm> getLista() {
		return lista;
	}

	public void setLista(List<TipoCrm> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(tipocrm.getIdtipocrm());
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
			servico.salvar(tipocrm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-tipocrm";
	}
}
