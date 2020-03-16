package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Estado;


@FacesConverter(forClass = Estado.class,value="conversorUf")
public class UfConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Estado) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Estado) {
        	Estado entity= (Estado) value;
            if (entity != null && entity instanceof Estado && entity.getIdestado() != null) {
                uiComponent.getAttributes().put( entity.getIdestado().toString(), entity);
                return entity.getIdestado().toString();
            }
        }
        return "";
    }
}