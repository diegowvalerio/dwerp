package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Funcao;

@FacesConverter(forClass = Funcao.class,value="conversorFuncao")
public class FuncaoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Funcao) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Funcao) {
        	Funcao entity= (Funcao) value;
            if (entity != null && entity instanceof Funcao && entity.getIdfuncao() != null) {
                uiComponent.getAttributes().put( entity.getIdfuncao().toString(), entity);
                return entity.getIdfuncao().toString();
            }
        }
        return "";
    }
}