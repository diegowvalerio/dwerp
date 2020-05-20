package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSituacaoTributariaPISCOFINS;

@Named
@RequestScoped
public class BeanSituacaoTributariaPISCOFINS implements Serializable{
	private static final long serialVersionUID = 1L;

	private SituacaoTributariaPISCOFINS situacaotributariapiscofins = new SituacaoTributariaPISCOFINS();
	@Inject
	private ServicoSituacaoTributariaPISCOFINS servico;
	private List<SituacaoTributariaPISCOFINS> lista;

	
	
	public String salvar(){
		try{
		servico.salvar(situacaotributariapiscofins);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro j� existente! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-situacaotributariapiscofins";
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
		

}
