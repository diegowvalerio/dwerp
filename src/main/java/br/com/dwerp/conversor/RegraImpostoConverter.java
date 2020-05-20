package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.RegraImposto;

@FacesConverter(forClass = RegraImposto.class,value="conversorRegraImposto")
public class RegraImpostoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (RegraImposto) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof RegraImposto) {
        	RegraImposto entity= (RegraImposto) value;
            if (entity != null && entity instanceof RegraImposto && entity.getIdregraimposto() != null) {
                uiComponent.getAttributes().put( entity.getIdregraimposto().toString(), entity);
                return entity.getIdregraimposto().toString();
            }
        }
        return "";
    }
}