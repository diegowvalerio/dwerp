package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.entidade.Ocorrencia;
import br.com.dwerp.entidade.Usuario;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCadastroGeral;
import br.com.dwerp.servico.ServicoOcorrencia;
import br.com.dwerp.servico.ServicoUsuario;

@Named
@ViewScoped
public class BeanIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date data_grafico = new Date();
	private Date data_grafico2 = new Date();
	

	@Inject
	private ServicoCadastroGeral servicoCadastroGeral;
	private List<CadastroGeral> lista_clientenovos = new ArrayList<>();

	@Inject
	private ServicoUsuario servicousuario;
	private List<Usuario> lista = new ArrayList<>();

	@Inject
	private ServicoOcorrencia servicoOcorrencia;
	private List<Ocorrencia> lista_ocorrencias_usuario = new ArrayList<>();
	private Ocorrencia ocorrencia = new Ocorrencia();
	
	private DonutChartModel grafico_ocorrencia_usuario;

	@PostConstruct
	public void init() {
		lista_clientenovos = servicoCadastroGeral.clientesnovos(data_grafico, data_grafico2);

		lista = servicousuario.consultar();
		for (Usuario acesso : lista) {
			if (acesso.getLogin().equals(usuarioconectado())) {
				lista_ocorrencias_usuario = servicoOcorrencia.consultar_ocorrencia_usuario(acesso.getIdusuario().toString());
				Collections.sort(lista_ocorrencias_usuario,Collections.reverseOrder(Comparator.comparing(Ocorrencia::getIdocorrencia)));
			}
		}
		
	}

	public void filtrar() {
		lista_clientenovos = servicoCadastroGeral.clientesnovos(data_grafico, data_grafico2);
	}
	
	
	public int getTotalClientes() {
		int total = 0;

		for (CadastroGeral cliente : getLista_clientenovos()) {
			total++;
		}

		return total;
	}
	
	public String ocorrencias_usuario_qtdeaberto() {
		int aberto = 0;
		for (Ocorrencia i : lista_ocorrencias_usuario) {
			if(i.getStatus().equals("ABERTO")) {
				aberto ++;
			}
		}
		if(aberto > 0) {
			return String.valueOf(aberto);
		}else {
			return "";
		}
	}
	
	public String ocorrencias_usuario_qtdeandamento() {
		int andamento = 0;
		for (Ocorrencia i : lista_ocorrencias_usuario) {
			if(i.getStatus().equals("ANDAMENTO")) {
				andamento ++;
			}
		}
		if(andamento > 0) {
			return String.valueOf(andamento);
		}else {
			return "";
		}
	}
	
	public String verifica_status_ocorrencia(String status) {
		if(status.equals("ABERTO")) {
			return "label label-danger";
			
		}else if(status.equals("ANDAMENTO")){
			return "label label-warning";
		}else {
			return "label label-success";
		}
	}
	
	public String encaminha() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("ocorrenciaAux", this.ocorrencia);

		return "pages/crm/ocorrencia/cadastro-ocorrencia";
	}

	public String usuarioconectado() {
		String nome;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			nome = ((UserDetails) principal).getUsername();
		} else {
			nome = principal.toString();
		}
		return nome;
	}
	

	// getters e setters
	public Date getData_grafico() {
		return data_grafico;
	}

	public void setData_grafico(Date data_grafico) {
		this.data_grafico = data_grafico;
	}

	public Date getData_grafico2() {
		return data_grafico2;
	}

	public void setData_grafico2(Date data_grafico2) {
		this.data_grafico2 = data_grafico2;
	}

	public List<CadastroGeral> getLista_clientenovos() {
		return lista_clientenovos;
	}

	public void setLista_clientenovos(List<CadastroGeral> lista_clientenovos) {
		this.lista_clientenovos = lista_clientenovos;
	}

	public List<Ocorrencia> getLista_ocorrencias_usuario() {
		return lista_ocorrencias_usuario;
	}

	public void setLista_ocorrencias_usuario(List<Ocorrencia> lista_ocorrencias_usuario) {
		this.lista_ocorrencias_usuario = lista_ocorrencias_usuario;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public DonutChartModel getGrafico_ocorrencia_usuario() {
		return grafico_ocorrencia_usuario;
	}

	public void setGrafico_ocorrencia_usuario(DonutChartModel grafico_ocorrencia_usuario) {
		this.grafico_ocorrencia_usuario = grafico_ocorrencia_usuario;
	}



}
