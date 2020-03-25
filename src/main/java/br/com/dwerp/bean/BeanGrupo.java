package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.Grupo;
import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoGrupo;

@Named
@ViewScoped
public class BeanGrupo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Grupo grupo = new Grupo();
	private SubGrupo subgrupo = new SubGrupo();
	@Inject
	private ServicoGrupo servico;
	private List<Grupo> lista;
	private List<SubGrupo> subgrupos;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		this.grupo = this.getGrupo();
		this.subgrupos = this.grupo.getSubgrupos();
		
	}
	
	public String salvar(){
		try{
		servico.salvar(grupo);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-grupo";
	}

	public String excluir() {
		try{
		servico.excluir(grupo.getIdgrupo());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-grupo";
	}
	
	//subgrupo
	public void addNovoSubGrupo() {
		if (this.grupo.getNome() == null) {
			throw new RuntimeException("O Nome do Grupo não pode ser nulo");
		} else {
			subgrupo = new SubGrupo();
			subgrupo.setGrupo(grupo);
		}
	}
	
	public void removerSubGrupo() {
		int index = subgrupos.indexOf(subgrupo);
		if (index > -1) {
			this.subgrupos.remove(index);
		}
	}
	
	public void editarsalvarSubGrupo() {

		if (subgrupo.getNome() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = subgrupos.indexOf(subgrupo);
				if (index > -1) {
					subgrupos.remove(index);
					subgrupo.setGrupo(grupo);
					subgrupos.add(index, subgrupo);
				} else {
					subgrupo.setGrupo(grupo);
					subgrupos.add(subgrupo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			subgrupo = new SubGrupo();
		}
	}
	
	/* editar grupo */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("grupoAux", this.grupo);

		return "edita-grupo";
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getLista() {
		return lista;
	}

	public void setLista(List<Grupo> lista) {
		this.lista = lista;
	}

	public List<SubGrupo> getSubgrupos() {
		return subgrupos;
	}

	public void setSubgrupos(List<SubGrupo> subgrupos) {
		this.subgrupos = subgrupos;
	}

	public SubGrupo getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubGrupo subgrupo) {
		this.subgrupo = subgrupo;
	}
	
	

}
