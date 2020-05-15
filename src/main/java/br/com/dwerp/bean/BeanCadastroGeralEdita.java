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
import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCidade;
import br.com.dwerp.servico.ServicoCadastroGeral;

@Named
@ViewScoped
public class BeanCadastroGeralEdita implements Serializable{
	private static final long serialVersionUID = 1L;

	private CadastroGeral cadastroGeral = new CadastroGeral();
	@Inject
	private ServicoCadastroGeral servico;
	private List<CadastroGeral> lista;
	
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
		this.cadastroGeral = (CadastroGeral) session.getAttribute("cadastrogeralAux");

		renderizar();

		session.removeAttribute("cadastrogeralAux");
		
	}
	
	public String salvar(){
		try{
		servico.salvar(cadastroGeral);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-cadastrogeral";
	}

	public String excluir() {
		try{
		servico.excluir(cadastroGeral.getIdcadastrogeral());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-cadastrogeral";
	}
	
	public void renderizar() {
		if (this.cadastroGeral.getTipojf().equals("J")) {
			isRederiza = true;
			isRederiza2 = false;
			cadastroGeral.setCpf(null);
			cadastroGeral.setRg(null);

		}
		if (this.cadastroGeral.getTipojf().equals("F")) {
			isRederiza = false;
			isRederiza2 = true;
			cadastroGeral.setCnpj(null);
			cadastroGeral.setInsc_estadual(null);
			cadastroGeral.setRazao_social(null);

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

	public CadastroGeral getCadastroGeral() {
		return cadastroGeral;
	}

	public void setCadastroGeral(CadastroGeral cadastroGeral) {
		this.cadastroGeral = cadastroGeral;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<CadastroGeral> getLista() {
		return lista;
	}

	public void setLista(List<CadastroGeral> lista) {
		this.lista = lista;
	}
	

}
