package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Produto;

@FacesConverter(forClass = Produto.class,value="conversorProduto")
public class ProdutoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Produto) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Produto) {
        	Produto entity= (Produto) value;
            if (entity != null && entity instanceof Produto && entity.getIdproduto() != null) {
                uiComponent.getAttributes().put( entity.getIdproduto().toString(), entity);
                return entity.getIdproduto().toString();
            }
        }
        return "";
    }
}