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

import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.entidade.Fornecedor;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCidade;
import br.com.dwerp.servico.ServicoFornecedor;

@Named
@ViewScoped
public class BeanFornecedorEdita implements Serializable{
	private static final long serialVersionUID = 1L;

	private Fornecedor pessoa = new Fornecedor();
	@Inject
	private ServicoFornecedor servico;
	private List<Fornecedor> lista;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	@PostConstruct
	public void ini(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.pessoa = (Fornecedor) session.getAttribute("fornecedorAux");

		renderizar();

		session.removeAttribute("fornecedorAux");
		
	}
	
	public String salvar(){
		try{
		servico.salvar(pessoa);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-fornecedor";
	}

	public String excluir() {
		try{
		servico.excluir(pessoa.getIdfornecedor());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-fornecedor";
	}
	
	public void renderizar() {
		if (this.pessoa.getTipojf().equals("J")) {
			isRederiza = true;
			isRederiza2 = false;
			pessoa.setCpf(null);
			pessoa.setRg(null);

		}
		if (this.pessoa.getTipojf().equals("F")) {
			isRederiza = false;
			isRederiza2 = true;
			pessoa.setCnpj(null);
			pessoa.setInsc_estadual(null);
			pessoa.setRazao_social(null);

		}

	}
	
	public List<Cidade> completaCidade(String nome) {
		return servicoCidade.buscacidadenome(nome);
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

	public Fornecedor getPessoa() {
		return pessoa;
	}

	public void setPessoa(Fornecedor pessoa) {
		this.pessoa = pessoa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Fornecedor> getLista() {
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}
	

}
