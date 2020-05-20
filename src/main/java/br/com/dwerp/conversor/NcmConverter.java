package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Ncm;


@FacesConverter(forClass = Ncm.class,value="conversorNcm")
public class NcmConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Ncm) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Ncm) {
        	Ncm entity= (Ncm) value;
            if (entity != null && entity instanceof Ncm && entity.getIdncm() != null) {
                uiComponent.getAttributes().put( entity.getIdncm().toString(), entity);
                return entity.getIdncm().toString();
            }
        }
        return "";
    }
}