package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dwerp.entidade.Pessoa;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoPessoa;

@Named
@ViewScoped
public class BeanPessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	@Inject
	private ServicoPessoa servico;
	private List<Pessoa> lista;

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.pessoa = this.getPessoa();
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
		return "lista-pessoa";
	}

	public String excluir() {
		try{
		servico.excluir(pessoa.getIdpessoa());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-pessoa";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getLista() {
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}
	

}
