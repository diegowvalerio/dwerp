package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.Estado;
import br.com.dwerp.entidade.RegraImposto;
import br.com.dwerp.entidade.RegraImpostoItem;
import br.com.dwerp.entidade.SituacaoTributaria;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoEstado;
import br.com.dwerp.servico.ServicoRegraImposto;
import br.com.dwerp.servico.ServicoSituacaoTributaria;

@Named
@ViewScoped
public class BeanRegraImposto implements Serializable{
	private static final long serialVersionUID = 1L;

	private RegraImposto regraimposto = new RegraImposto();
	private RegraImpostoItem regraimpostoitem = new RegraImpostoItem();
	@Inject
	private ServicoRegraImposto servico;
	private List<RegraImposto> lista;
	private List<RegraImpostoItem> regraimpostoitems;
	
	private Estado estado = new Estado();
	@Inject
	private ServicoEstado servicoestado;
	private List<Estado> listaestados;
	
	@Inject
	private ServicoSituacaoTributaria servicost;
	private List<SituacaoTributaria> listasts;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		this.regraimposto = this.getRegraimposto();
		this.regraimpostoitems = this.regraimposto.getRegraimpostoitem();
		
		this.listasts = servicost.consultar();
		
	}
	
	public String salvar(){
		try{
		servico.salvar(regraimposto);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-regraimposto";
	}

	public String excluir() {
		try{
		servico.excluir(regraimposto.getIdregraimposto());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-regraimposto";
	}
	
	public void preencher() {
		if(regraimpostoitems.size()<1) {
			listaestados = servicoestado.consultar();
			Collections.sort(listaestados, Comparator.comparing(Estado::getSigla));
			
			for(Estado e:listaestados) {
		
				regraimpostoitem = new RegraImpostoItem();
				regraimpostoitem.setRegraimposto(regraimposto);
				regraimpostoitem.setEstado(e);
				regraimpostoitem.setIcms(0);
				regraimpostoitem.setIcmsst(0);
				regraimpostoitem.setMvanormal(0);
				regraimpostoitem.setMvasimples(0);
				regraimpostoitems.add(regraimpostoitem);
			}
		}
	}
	
	//regraimpostoitem
	public void addNovoRegraImpostoItem() {
		if (this.regraimposto.getNome() == null) {
			throw new RuntimeException("O Nome do RegraImposto não pode ser nulo");
		} else {
			regraimpostoitem = new RegraImpostoItem();
			regraimpostoitem.setRegraimposto(regraimposto);
		}
	}
	
	public void removerRegraImpostoItem() {
		int index = regraimpostoitems.indexOf(regraimpostoitem);
		if (index > -1) {
			this.regraimpostoitems.remove(index);
		}
	}
	
	public void editarsalvarRegraImpostoItem() {

		if (regraimpostoitem.getEstado() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = regraimpostoitems.indexOf(regraimpostoitem);
				if (index > -1) {
					regraimpostoitems.remove(index);
					regraimpostoitem.setRegraimposto(regraimposto);
					regraimpostoitems.add(index, regraimpostoitem);
				} else {
					regraimpostoitem.setRegraimposto(regraimposto);
					regraimpostoitems.add(regraimpostoitem);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			regraimpostoitem = new RegraImpostoItem();
		}
	}
	
	/* editar regraimposto */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("regraimpostoAux", this.regraimposto);

		return "edita-regraimposto";
	}

	public RegraImposto getRegraimposto() {
		return regraimposto;
	}

	public void setRegraimposto(RegraImposto regraimposto) {
		this.regraimposto = regraimposto;
	}

	public RegraImpostoItem getRegraimpostoitem() {
		return regraimpostoitem;
	}

	public void setRegraimpostoitem(RegraImpostoItem regraimpostoitem) {
		this.regraimpostoitem = regraimpostoitem;
	}

	public List<RegraImposto> getLista() {
		return lista;
	}

	public void setLista(List<RegraImposto> lista) {
		this.lista = lista;
	}

	public List<RegraImpostoItem> getRegraimpostoitems() {
		return regraimpostoitems;
	}

	public void setRegraimpostoitems(List<RegraImpostoItem> regraimpostoitems) {
		this.regraimpostoitems = regraimpostoitems;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaestados() {
		return listaestados;
	}

	public void setListaestados(List<Estado> listaestados) {
		this.listaestados = listaestados;
	}

	public List<SituacaoTributaria> getListasts() {
		return listasts;
	}

	public void setListasts(List<SituacaoTributaria> listasts) {
		this.listasts = listasts;
	}

	
	

}
