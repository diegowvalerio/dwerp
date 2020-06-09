package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.entidade.Estrutura;
import br.com.dwerp.entidade.Produto;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSubGrupo;
import br.com.dwerp.servico.ServicoProduto;

@Named
@ViewScoped
public class BeanProdutoEdita implements Serializable{
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	private Estrutura estrutura = new Estrutura();
	@Inject
	private ServicoProduto servico;
	private List<Produto> lista;
	
	@Inject
	private ServicoSubGrupo servicoSubGrupo;
	private SubGrupo subGrupo = new SubGrupo();
	private List<SubGrupo> listasubgrupo;
	
	private List<Estrutura> estruturas;
	
	private String opcao;
	private Date data;
	private Boolean habilita_tipoproduto = false;
	private Boolean isRederiza2 = false;
	
	public BeanProdutoEdita() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		listasubgrupo = servicoSubGrupo.consultar();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.produto = (Produto) session.getAttribute("produtoAux");
		this.estruturas = this.produto.getEstruturas();

		session.removeAttribute("produtoAux");
		
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
	
	public void addNovoEstrutura() {
		if (this.produto.getDescricao() == null) {
			throw new RuntimeException("O Nome do Produto não pode ser nulo");
		} else {
			estrutura = new Estrutura();
			estrutura.setProduto(produto);
		}
	}
	
	public void removerEstrutura() {
		int index = estruturas.indexOf(estrutura);
		if (index > -1) {
			this.estruturas.remove(index);
		}
	}
	
	public void editarsalvarEstrutura() {

		if (estrutura.getProduto_estrutura() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = estruturas.indexOf(estrutura);
				if (index > -1) {
					estruturas.remove(index);
					estrutura.setProduto(produto);
					estrutura.setValor_custo_estrutura(estrutura.getProduto_estrutura().getValor_custo());
					estrutura.setTotal_custo_estrutura(estrutura.getQtde_estrutura()*estrutura.getValor_custo_estrutura());
					estruturas.add(index, estrutura);
				} else {
					estrutura.setProduto(produto);
					estrutura.setValor_custo_estrutura(estrutura.getProduto_estrutura().getValor_custo());
					estrutura.setTotal_custo_estrutura(estrutura.getQtde_estrutura()*estrutura.getValor_custo_estrutura());
					estruturas.add(estrutura);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			estrutura = new Estrutura();
		}
	}
	
	public double TotalCusto() {
		double totalcusto = 0;
		for (Estrutura et : getEstruturas()) {
			totalcusto = totalcusto + et.getTotal_custo_estrutura();
		}
		return totalcusto;		
	}
	
	public boolean habiliata_tipoproduto() {
		if(produto.getEstruturas().size()>0) {
			habilita_tipoproduto = true;
		}else {
			habilita_tipoproduto = false;
		}
		return habilita_tipoproduto;		
	}
	
	public List<Produto> completaproduto(String nome) {
		return servico.buscaproduto(nome);
	}
	
	/* editar produto */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("produtoAux", this.produto);

		return "edita-produto";
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public List<Estrutura> getEstruturas() {
		return estruturas;
	}

	public void setEstruturas(List<Estrutura> estruturas) {
		this.estruturas = estruturas;
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

	public Boolean getHabilita_tipoproduto() {
		return habilita_tipoproduto;
	}

	public void setHabilita_tipoproduto(Boolean habilita_tipoproduto) {
		this.habilita_tipoproduto = habilita_tipoproduto;
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
