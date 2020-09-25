package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.servico.ServicoFechamento;


@Named
@ViewScoped
public class BeanFechamento implements Serializable{
	private static final long serialVersionUID = 1L;

	private Fechamento fechamento = new Fechamento();
	@Inject
	private ServicoFechamento servico;
	
	private List<Fechamento> lista;

	
	
	@PostConstruct
	public void ini(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.fechamento.setFuncionario((CadastroGeral) session.getAttribute("cadastrogeralAux"));
		
		session.removeAttribute("cadastrogeralAux");
		
	}


	public Fechamento getFechamento() {
		return fechamento;
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
