package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Ncm;
import br.com.dwerp.entidade.RegraImposto;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoNcm;
import br.com.dwerp.servico.ServicoRegraImposto;

@Named
@ViewScoped
public class BeanNcm implements Serializable{
	private static final long serialVersionUID = 1L;

	private Ncm ncm = new Ncm();
	@Inject
	private ServicoNcm servico;
	@Inject
	private ServicoRegraImposto servicoRegraImposto;
	private List<Ncm> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.ncm = this.getNcm();
	}
	
	public String salvar(){
		try{
		servico.salvar(ncm);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro j� existente! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-ncm";
	}

	public String excluir() {
		try{
		servico.excluir(ncm.getIdncm());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-ncm";
	}
	
	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public List<Ncm> getLista() {
		return lista;
	}

	public void setLista(List<Ncm> lista) {
		this.lista = lista;
	}
		
	public List<RegraImposto> getRegraImposto() {
		return servicoRegraImposto.consultar();
	}
}
