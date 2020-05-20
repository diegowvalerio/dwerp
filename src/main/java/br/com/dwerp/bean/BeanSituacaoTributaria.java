package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.SituacaoTributaria;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSituacaoTributaria;

@Named
@RequestScoped
public class BeanSituacaoTributaria implements Serializable{
	private static final long serialVersionUID = 1L;

	private SituacaoTributaria situacaotributaria = new SituacaoTributaria();
	@Inject
	private ServicoSituacaoTributaria servico;
	private List<SituacaoTributaria> lista;

	
	
	public String salvar(){
		try{
		servico.salvar(situacaotributaria);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro j� existente! N�o foi poss�vel realizar a opera��o.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-situacaotributaria";
	}

	public SituacaoTributaria getSituacaoTributaria() {
		return situacaotributaria;
	}

	public void setSituacaoTributaria(SituacaoTributaria situacaotributaria) {
		this.situacaotributaria = situacaotributaria;
	}

	public List<SituacaoTributaria> getLista() {
		return lista;
	}

	public void setLista(List<SituacaoTributaria> lista) {
		this.lista = lista;
	}
		

}
