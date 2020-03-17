package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.FormaPag;

@FacesConverter(forClass = FormaPag.class,value="conversorFormaPag")
public class FormaPagConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (FormaPag) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof FormaPag) {
        	FormaPag entity= (FormaPag) value;
            if (entity != null && entity instanceof FormaPag && entity.getIdformapag() != null) {
                uiComponent.getAttributes().put( entity.getIdformapag().toString(), entity);
                return entity.getIdformapag().toString();
            }
        }
        return "";
    }
}