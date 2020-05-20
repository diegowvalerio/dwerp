package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Cest;

@FacesConverter(forClass = Cest.class,value="conversorCest")
public class CestConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Cest) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Cest) {
        	Cest entity= (Cest) value;
            if (entity != null && entity instanceof Cest && entity.getIdcest() != null) {
                uiComponent.getAttributes().put( entity.getIdcest().toString(), entity);
                return entity.getIdcest().toString();
            }
        }
        return "";
    }
}