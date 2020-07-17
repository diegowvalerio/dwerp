package br.com.dwerp.bean;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dwerp.entidade.Produto;
import br.com.dwerp.servico.ServicoProduto;

@ManagedBean
@RequestScoped
public class BeanProdutoImg implements Serializable{
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	@Inject
	private ServicoProduto servico;
	
	public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            
            produto = servico.consultar(Integer.parseInt(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(produto.getImagem()));
        }
    }
	
	public StreamedContent getImageByte() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
           String id = context.getExternalContext().getRequestParameterMap().get("id");
           
            return new DefaultStreamedContent(new ByteArrayInputStream(id.getBytes()));
        }
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	


}
