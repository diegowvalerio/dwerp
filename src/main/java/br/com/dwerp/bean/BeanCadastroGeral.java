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


import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.entidade.Estado;
import br.com.dwerp.entidade.Funcao;
import br.com.dwerp.entidade.Setor;
import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCidade;
import br.com.dwerp.servico.ServicoEmpresa;
import br.com.dwerp.servico.ServicoEstado;
import br.com.dwerp.servico.ServicoFuncao;
import br.com.dwerp.servico.ServicoSetor;
import br.com.dwerp.servico.ServicoCadastroGeral;

@Named
@ViewScoped
public class BeanCadastroGeral implements Serializable{
	private static final long serialVersionUID = 1L;

	private CadastroGeral cadastrogeral = new CadastroGeral();
	@Inject
	private ServicoCadastroGeral servico;
	private List<CadastroGeral> lista;
	private List<CadastroGeral> listafuncionario;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	@Inject
	private ServicoEmpresa servicoEmpresa;
	private List<Empresa> listaempresa;
	
	@Inject
	private ServicoFuncao servicoFuncao;
	private List<Funcao> listafuncao;
	
	@Inject
	private ServicoSetor servicoSetor;
	private List<Setor> listasetor;
	
	@Inject
	private ServicoEstado servicoEstado;
	private List<Estado> listaestado;
	
	private Boolean isFuncionario = false;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	public BeanCadastroGeral() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		listafuncionario = servico.consultar_funcionarios();
		
		listaempresa = servicoEmpresa.consultar();
		listafuncao = servicoFuncao.consultar();
		listasetor = servicoSetor.consultar_ativos();
		listaestado = servicoEstado.consultar();
		
		this.cadastrogeral = this.getCadastroGeral();
		this.cadastrogeral.setDtcadastro(data);
		
	}
	
	public String salvar(){
		if(cadastrogeral.getIdcadastrogeral() == null){
			cadastrogeral.setDtcadastro(data);
		}
		try{
		servico.salvar(cadastrogeral);
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
	
	public void bo_funcionario() {
		if(this.cadastrogeral.getOp_funcionario().equals(true)) {
			isFuncionario = true;
		}else {
			isFuncionario = false;
		}
	}

	public String excluir() {
		try{
		servico.excluir(cadastrogeral.getIdcadastrogeral());
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
	
	/* editar cadastro */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("cadastrogeralAux", this.cadastrogeral);

		return "edita-cadastrogeral";
	}
	
	
	/* inserir fechametno RH */
	public String encaminha2() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("cadastrogeralAux", this.cadastrogeral);

		return "cadastro-fechamento";
	}
	
	/* consultar fechamentos RH */
	public String encaminha3() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("cadastrogeralAux", this.cadastrogeral);

		return "consulta-fechamento";
	}
	
	public void renderizar() {
		if (this.cadastrogeral.getTipojf().equals("J")) {
			isRederiza = true;
			isRederiza2 = false;
			cadastrogeral.setCpf(null);
			cadastrogeral.setRg(null);

		}
		if (this.cadastrogeral.getTipojf().equals("F")) {
			isRederiza = false;
			isRederiza2 = true;
			cadastrogeral.setCnpj(null);
			cadastrogeral.setInsc_estadual(null);
			cadastrogeral.setRazao_social(null);
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public CadastroGeral getCadastroGeral() {
		return cadastrogeral;
	}

	public void setCadastroGeral(CadastroGeral cadastrogeral) {
		this.cadastrogeral = cadastrogeral;
	}

	public List<CadastroGeral> getLista() {
		return lista;
	}

	public void setLista(List<CadastroGeral> lista) {
		this.lista = lista;
	}

	public CadastroGeral getCadastrogeral() {
		return cadastrogeral;
	}

	public void setCadastrogeral(CadastroGeral cadastrogeral) {
		this.cadastrogeral = cadastrogeral;
	}

	public List<Empresa> getListaempresa() {
		return listaempresa;
	}

	public void setListaempresa(List<Empresa> listaempresa) {
		this.listaempresa = listaempresa;
	}

	public List<Funcao> getListafuncao() {
		return listafuncao;
	}

	public void setListafuncao(List<Funcao> listafuncao) {
		this.listafuncao = listafuncao;
	}

	public List<Setor> getListasetor() {
		return listasetor;
	}

	public void setListasetor(List<Setor> listasetor) {
		this.listasetor = listasetor;
	}

	public List<Estado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<Estado> listaestado) {
		this.listaestado = listaestado;
	}

	public Boolean getIsFuncionario() {
		return isFuncionario;
	}

	public void setIsFuncionario(Boolean isFuncionario) {
		this.isFuncionario = isFuncionario;
	}

	public List<CadastroGeral> getListafuncionario() {
		return listafuncionario;
	}

	public void setListafuncionario(List<CadastroGeral> listafuncionario) {
		this.listafuncionario = listafuncionario;
	}

}
