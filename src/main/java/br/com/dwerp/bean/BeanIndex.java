package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCadastroGeral;

@Named
@ViewScoped
public class BeanIndex implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date data_grafico = new Date();
	private Date data_grafico2 = new Date();
	
	@Inject
	private ServicoCadastroGeral servicoCadastroGeral;
	private List<CadastroGeral> lista_clientenovos = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		lista_clientenovos = servicoCadastroGeral.clientesnovos(data_grafico, data_grafico2);		
	}
	
	
	public void filtrar(){
		lista_clientenovos = servicoCadastroGeral.clientesnovos(data_grafico, data_grafico2);		
	}
	
	public int getTotalClientes() {
		int total = 0;

		for (CadastroGeral cliente : getLista_clientenovos()) {
			total++;
		}

		return total;
	}
	
	
	
	
	//getters e setters
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
	
}
