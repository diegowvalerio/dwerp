package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.entidade.Produto;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSubGrupo;
import br.com.dwerp.servico.ServicoProduto;

@Named
@ViewScoped
public class BeanProduto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	@Inject
	private ServicoProduto servico;
	private List<Produto> lista;
	
	@Inject
	private ServicoSubGrupo servicoSubGrupo;
	private SubGrupo subGrupo = new SubGrupo();
	private List<SubGrupo> listasubgrupo;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	public BeanProduto() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		listasubgrupo = servicoSubGrupo.consultar();
		
		this.produto = this.getPessoa();
		this.produto.setDtcadastro(data);
		
	}
	
	public String salvar(){
		if(produto.getIdproduto() == null){
			produto.setDtcadastro(data);
		}
		try{
		servico.salvar(produto);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-produto";
	}

	public String excluir() {
		try{
		servico.excluir(produto.getIdproduto());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-produto";
	}
	
	/* editar produto */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("produtoAux", this.produto);

		return "edita-produto";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public List<SubGrupo> getListasubgrupo() {
		return listasubgrupo;
	}

	public void setListasubgrupo(List<SubGrupo> listasubgrupo) {
		this.listasubgrupo = listasubgrupo;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Boolean getIsRederiza() {
		return isRederiza;
	}

	public void setIsRederiza(Boolean isRederiza) {
		this.isRederiza = isRederiza;
	}

	public Boolean getIsRederiza2() {
		return isRederiza2;
	}

	public void setIsRederiza2(Boolean isRederiza2) {
		this.isRederiza2 = isRederiza2;
	}

	public Produto getPessoa() {
		return produto;
	}

	public void setPessoa(Produto produto) {
		this.produto = produto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}
	

}
