package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSituacaoTributariaPISCOFINS;

@Named
@ViewScoped
public class BeanSituacaoTributariaPISCOFINSEdita implements Serializable{

	private static final long serialVersionUID = 1L;
	private SituacaoTributariaPISCOFINS situacaotributariapiscofins = new SituacaoTributariaPISCOFINS();
	@Inject
	private ServicoSituacaoTributariaPISCOFINS servico;
	private List<SituacaoTributariaPISCOFINS> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public SituacaoTributariaPISCOFINS getSituacaoTributariaPISCOFINS() {
		return situacaotributariapiscofins;
	}

	public void setSituacaoTributariaPISCOFINS(SituacaoTributariaPISCOFINS situacaotributariapiscofins) {
		this.situacaotributariapiscofins = situacaotributariapiscofins;
	}

	public List<SituacaoTributariaPISCOFINS> getLista() {
		return lista;
	}

	public void setLista(List<SituacaoTributariaPISCOFINS> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(situacaotributariapiscofins.getIdsituacaotributariapiscofins());
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
		servico.salvar(situacaotributariapiscofins);
	}
}
