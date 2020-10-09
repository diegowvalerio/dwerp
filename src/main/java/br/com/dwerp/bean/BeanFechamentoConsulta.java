package br.com.dwerp.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.entidade.HoraExtra;
import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.relatorio.Relatorio;
import br.com.dwerp.servico.ServicoFechamento;


@Named
@ViewScoped
public class BeanFechamentoConsulta implements Serializable{
	private static final long serialVersionUID = 1L;

	private Fechamento fechamento = new Fechamento();
	private CadastroGeral funcionario = new CadastroGeral();
	
	@Inject
	private ServicoFechamento servico;
	
	private List<Fechamento> lista;
	
	@PostConstruct
	public void ini(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		
		if (session.getAttribute("cadastrogeralAux") != null ) {
			funcionario = (CadastroGeral) session.getAttribute("cadastrogeralAux");
		
		lista = funcionario.getFechamentos();
		}
		session.removeAttribute("cadastrogeralAux");	
	}	
	
	public void excluir() {
		try{
		servico.excluir(fechamento.getIdfechamento());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
	}	
	
	/* editar */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("fechamentoAux", this.fechamento);

		return "edita-fechamento";
	}
	
	public Fechamento getFechamento() {
		return fechamento;
	}

	public CadastroGeral getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(CadastroGeral funcionario) {
		this.funcionario = funcionario;
	}


	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}


	public List<Fechamento> getLista() {
		return lista;
	}


	public void setLista(List<Fechamento> lista) {
		this.lista = lista;
	}

	
}
