package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.ModuloCrm;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoModuloCrm;

@Named
@ViewScoped
public class BeanModuloCrm implements Serializable{

	private static final long serialVersionUID = 1L;
	private ModuloCrm modulocrm = new ModuloCrm();
	@Inject
	private ServicoModuloCrm servico;
	private List<ModuloCrm> lista;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
	}
	
	public ModuloCrm getModuloCrm() {
		return modulocrm;
	}

	public void setModuloCrm(ModuloCrm modulocrm) {
		this.modulocrm = modulocrm;
	}

	public List<ModuloCrm> getLista() {
		return lista;
	}

	public void setLista(List<ModuloCrm> lista) {
		this.lista = lista;
	}

	public void excluir(){
		try{
		servico.excluir(modulocrm.getIdmodulocrm());
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
			servico.salvar(modulocrm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-modulocrm";
	}
}
