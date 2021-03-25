package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Canal;

@FacesConverter(forClass = Canal.class,value="conversorCanal")
public class CanalConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Canal) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Canal) {
        	Canal entity= (Canal) value;
            if (entity != null && entity instanceof Canal && entity.getIdcanal() != null) {
                uiComponent.getAttributes().put( entity.getIdcanal().toString(), entity);
                return entity.getIdcanal().toString();
            }
        }
        return "";
    }
}