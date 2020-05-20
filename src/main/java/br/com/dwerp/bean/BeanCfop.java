package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Cfop;
import br.com.dwerp.entidade.SituacaoTributariaIPI;
import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCfop;
import br.com.dwerp.servico.ServicoSituacaoTributariaIPI;
import br.com.dwerp.servico.ServicoSituacaoTributariaPISCOFINS;

@Named
@ViewScoped
public class BeanCfop implements Serializable{
	private static final long serialVersionUID = 1L;

	private Cfop cfop = new Cfop();
	@Inject
	private ServicoCfop servico;
	
	@Inject
	private ServicoSituacaoTributariaPISCOFINS servicoSituacaoTributariaPISCOFINS;
	@Inject
	private ServicoSituacaoTributariaIPI servicoSituacaoTributariaIPI;
	
	private List<Cfop> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.cfop = this.getCfop();
	}
	
	public String salvar(){
		try{
		servico.salvar(cfop);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro j� existente! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-cfop";
	}

	public String excluir() {
		try{
		servico.excluir(cfop.getIdcfop());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-cfop";
	}
	
	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public List<Cfop> getLista() {
		return lista;
	}

	public void setLista(List<Cfop> lista) {
		this.lista = lista;
	}
	
	public List<SituacaoTributariaPISCOFINS> getSituacaoTributariaPISCOFINS(){
		return servicoSituacaoTributariaPISCOFINS.consultar();
	}
	
	public List<SituacaoTributariaIPI> getSituacaoTributariaIPI(){
		return servicoSituacaoTributariaIPI.consultar();
	}

}
