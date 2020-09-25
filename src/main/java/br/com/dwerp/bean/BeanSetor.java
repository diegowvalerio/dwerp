package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Setor;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSetor;

@Named
@ViewScoped
public class BeanSetor implements Serializable{
	private static final long serialVersionUID = 1L;

	private Setor setor = new Setor();
	@Inject
	private ServicoSetor servico;
	
	private List<Setor> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.setor = this.getSetor();
	}
	
	public String salvar(){
		servico.salvar(setor);
		lista = servico.consultar();
		
		return "lista-setor";
	}
	

	public String excluir() {
		try {
			servico.excluir(setor.getIdsetor());
			lista = servico.consultar();
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}

		return "lista-setor";
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getLista() {
		return lista;
	}

	public void setLista(List<Setor> lista) {
		this.lista = lista;
	}

}
