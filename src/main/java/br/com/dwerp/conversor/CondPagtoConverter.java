package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.CondPgto;

@FacesConverter(forClass = CondPgto.class,value="conversorCondPag")
public class CondPagtoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (CondPgto) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof CondPgto) {
        	CondPgto entity= (CondPgto) value;
            if (entity != null && entity instanceof CondPgto && entity.getIdcondpgto() != null) {
                uiComponent.getAttributes().put( entity.getIdcondpgto().toString(), entity);
                return entity.getIdcondpgto().toString();
            }
        }
        return "";
    }
}