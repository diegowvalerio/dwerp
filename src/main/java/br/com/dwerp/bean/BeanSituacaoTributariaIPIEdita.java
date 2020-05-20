package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.SituacaoTributariaIPI;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSituacaoTributariaIPI;

@Named
@ViewScoped
public class BeanSituacaoTributariaIPIEdita implements Serializable{

	private static final long serialVersionUID = 1L;
	private SituacaoTributariaIPI situacaotributariaipi = new SituacaoTributariaIPI();
	@Inject
	private ServicoSituacaoTributariaIPI servico;
	private List<SituacaoTributariaIPI> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public SituacaoTributariaIPI getSituacaoTributariaIPI() {
		return situacaotributariaipi;
	}

	public void setSituacaoTributariaIPI(SituacaoTributariaIPI situacaotributariaipi) {
		this.situacaotributariaipi = situacaotributariaipi;
	}

	public List<SituacaoTributariaIPI> getLista() {
		return lista;
	}

	public void setLista(List<SituacaoTributariaIPI> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(situacaotributariaipi.getIdsituacaotributariaipi());
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
		servico.salvar(situacaotributariaipi);
	}
}
